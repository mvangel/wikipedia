using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CanTheyMeet
{
    public partial class CanTheyMeet : Form
    {
        List<Person> people = new List<Person>();
        Person p1 = new Person();
        Person p2 = new Person();
        StreamReader data = new StreamReader("finished1");
        public CanTheyMeet()
        {
            InitializeComponent();
            result.Visible = false;
            FillData();
            data.Close();
            data.Dispose();
           
        }

        private void comboboxPerson1_KeyPress(object sender, KeyPressEventArgs e)
        {

        }

        private void comboboxPerson2_KeyPress(object sender, KeyPressEventArgs e)
        {

        }
        private void FillData()
        { 
            string line;
            while ((line = data.ReadLine()) != null)
            {
                Person p = new Person(); ;
                p.Name = line.Split('=')[1];
                p.Birth = new Date(data.ReadLine().Split('=')[1]);
                p.Death = new Date(data.ReadLine().Split('=')[1]);
                people.Add(p);
            }
        
        }

        private void comboboxPerson1_KeyUp(object sender, KeyEventArgs e)
        {
           
            if ((comboboxPerson1.Text.Length % 3) == 0)
            {
                p1.Name = comboboxPerson1.Text;
              //  comboboxPerson1.Items.Clear();
                var fine = new List<Person>();
                fine.Add(p1);
                string compare = comboboxPerson1.Text.ToUpper();
                foreach (Person p in people)
                {
                    if (p.Name.Contains(compare))
                    {
                        fine.Add(p);
                    }
                }
                comboboxPerson1.DataSource = fine;
                comboboxPerson1.DisplayMember = Name;
                comboboxPerson1.SelectedIndex = 0;
            }
        }

        private void comboboxPerson2_KeyUp(object sender, KeyEventArgs e)
        {
            if ((comboboxPerson2.Text.Length % 3) == 0)
            {
                p2.Name = comboboxPerson2.Text;
            //    comboboxPerson2.Items.Clear();
                var fine = new List<Person>();
                fine.Add(p2);
                string compare = comboboxPerson2.Text.ToUpper();
                foreach (Person p in people)
                {
                    if (p.Name.Contains(compare))
                    {
                        fine.Add(p);
                    }
                }
                comboboxPerson2.DataSource = fine;
                comboboxPerson2.DisplayMember = Name;
                comboboxPerson2.SelectedIndex = 0;
            }
        }
        private string canTheyMeet()
        {
            Person pe1 = (Person)comboboxPerson1.SelectedItem;
            Person pe2 = (Person)comboboxPerson2.SelectedItem;
            if(pe1.Birth.Year!=0 && pe2.Birth.Year !=0 && pe1.Death.Year!=0 && pe2.Death.Year!=0)
            {
                if (calculateMeet(pe1, pe2))
                {
                    return "Yes";
                }
                else return "False";
            }
            else
            {
                return "Maybe";
            }

        }

        private void button1_Click(object sender, EventArgs e)
        {
            Font f = new Font("Times New Roman", 50.0f);
            result.Font=f;
            result.Text = canTheyMeet();
            result.Visible = true;
        }
        private bool calculateMeet(Person pe1,Person pe2)
        {
            Person pom = new Person();
            if(pe2.Birth.Year>pe1.Birth.Year)
            {
                pom = pe1;
                pe1 = pe2;
                pe1 = pom;
            }
            if (pe1.Death.Year < pe2.Birth.Year)
                return false;
            else 
            {
                if (pe1.Death.Year == pe2.Birth.Year)
                {
                    if (pe1.Death.Month != 0 && pe2.Birth.Month != 0 && pe1.Death.Month < pe2.Birth.Month)
                        return false;
                    else if(pe1.Death.Month == pe2.Birth.Month)
                        if (pe1.Death.Day != 0 && pe2.Birth.Day != 0 && pe1.Death.Day < pe2.Birth.Day)
                            return false;
                }
            }
            return true;
        }
       
    }
}
