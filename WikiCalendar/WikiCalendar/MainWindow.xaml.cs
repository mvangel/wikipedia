using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Xml.Linq;
using System.Text.RegularExpressions;

namespace WikiCalendar
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            XDocument input = XDocument.Load(@"..\..\..\..\Data\input.xml", LoadOptions.PreserveWhitespace);

            // Console.WriteLine(booksFromFile);
            

            CalendarEvent ce = new CalendarEvent(input);
            TextConsole.Text = ce.text;

            // matching infobox from page text
            String infoboxPattern = "{{Infobox (.+\n)+}}";

            MatchCollection infobox = System.Text.RegularExpressions.Regex.Matches(ce.text, infoboxPattern, System.Text.RegularExpressions.RegexOptions.Multiline);
            
            foreach (Match m in infobox)
            {
                String datePattern = "[a-z]+_date\\s+=.+";
                MatchCollection dateLine = Regex.Matches(m.Value,datePattern);
               
                foreach( Match n in dateLine)
                {
                    Console.WriteLine(n.Value);
                    //dateFormat is Y|MM|DD or 'BCE'|Y 
                    String dateExtractPattern //="([0-9]{0,}\\|[0-1]{0,1}[0-9]{1}\\|[0-3]{0,1}[0-9]{1})|(BCE\\|[0-9]+)";
                   = "([0-9]{0,4}\\|[0-1]{0,1}[0-9]{1}\\|[0-3]{0,1}[0-9]{1})" //Y|MM|DD
                    + "|((?:(BC)?|(BCE)?)\\|[0-9]+)" //'BCE'|Y 
                    //+ "|([0-3]{0,1}[0-9]{1} \b(?:(January)?|(February)?|(March)?|(April)?|(May)?|(June)?|(July)?|(August)?|(September)?|(October)?|(November)?|(December)?) [0-9]{0,}\\s?(?:(BC)?|(BCE)?))" 
                    //+ "(?:([0-3]{0,1}[0-9]{1}) (\b(?:January)?|(?:February)?|(?:March)?|(?:April)?|(?:May)?|(?:June)?|(?:July)?|(?:August)?|(?:September)?|(?:October)?|(?:November)?|(?:December)?) ([0-9]{0,})(\s?(?:BC)?|(?:BCE)?))";
                    + "|((?:([0-3]?[0-9]) ((?:January)?|(?:February)?|(?:March)?|(?:April)?|(?:May)?|(?:June)?|(?:July)?|(?:August)?|(?:September)?|(?:October)?|(?:November)?|(?:December)?) )[0-9]{0,}\\s?(?:(BC)?|(BCE)?))" //3 July 2001
                    + "|(?:(?:January)?|(?:February)?|(?:March)?|(?:April)?|(?:May)?|(?:June)?|(?:July)?|(?:August)?|(?:September)?|(?:October)?|(?:November)?|(?:December)?) (?:([0-3]?[0-9])), [0-9]{0,}\\s(?:(BC)?|(BCE)?)"; // July 3, 2001
                    MatchCollection dateExtract = Regex.Matches(n.Value, dateExtractPattern);
                    Console.WriteLine("dates formated Y|MM|DD or 'BCE'|Y ");
                    foreach(Match o in dateExtract){
                        Console.WriteLine(o.Value);
                    }
                    /*
                     * |birth_date={{birth date|1809|2|12}} 
                     * | birth_date = {{birth date|mf=yes|1905|2|2}}
                     * birth_date  = {{birth date and age|1947|04|01|df=y}} 
                     * | birth_date = {{Birth date|df=yes|1885|4|3}}
                     * | birth_date = {{birth date and age|1970|04|29}}
                     * | birth_date = {{birth date|df=yes|1894|7|26}}
                     * |birth_date= {{Birth date|1803|2|2}}
                     */
                }
                
            }


        }
    }
}
