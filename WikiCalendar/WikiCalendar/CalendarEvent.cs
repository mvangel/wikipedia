using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace WikiCalendar
{
    class CalendarEvent
    {
        public DateTime date { get; set; }
        public long dateOffset { get; set; }
        public String description {get; set;}
        public String title {get;set;}
        public long id { get; set; }
        public String text { get; set; }

        public CalendarEvent(XDocument input)
        {
            XElement page = input.Element("page");
            title = page.Element("title").Value;
            id = long.Parse(page.Element("id").Value);
            text = page.Element("revision").Element("text").Value;

            //describeEvent();

        }

        void describeEvent()
        {
            StringBuilder sb = new StringBuilder();

            sb.AppendLine("Title:" + title);
            sb.AppendLine("ID: " + id);
            sb.AppendLine("Text:" + text);

            Console.WriteLine(sb.ToString());
        }
    }
}
