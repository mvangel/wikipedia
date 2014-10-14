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
            //XDocument input = XDocument.Load(@"..\..\..\..\Data\input.xml");
			XDocument input = XDocument.Load(@"D:\downNew\wiki\enwiki-latest-pages-articles1.xml-p000000010p000010000");
            // Console.WriteLine(booksFromFile);
     

            //CalendarEvent ce = new CalendarEvent(input);
            //var pages = input.Root.Descendants("page");
			XElement eee = input.Element("siteinfo");
			IEnumerable<XElement> pages = input.Element("mediawiki").Elements("page");
			
			Console.WriteLine(pages.Count().ToString());

            
			
			
			
			
			
			// matching infobox from page text
			
			LinkedList<CalendarEvent> allEvents = new LinkedList<CalendarEvent>();

            foreach(XElement page in pages)
            {
				String infoboxPattern = "{{Infobox (.+\n)+}}";
				MatchCollection infobox = System.Text.RegularExpressions.Regex.Matches(page.Element("revision").Element("text").Value, infoboxPattern, System.Text.RegularExpressions.RegexOptions.Multiline);

				//99% just one iteration
                foreach (Match m in infobox)
                {
                    String datePattern = "[a-z]+_date\\s+=.+";
                    MatchCollection dateLine = Regex.Matches(m.Value,datePattern);
					
					//statisticaly 2 iterations
                    foreach( Match n in dateLine)
                    {
						//TODO check release_date - just year 
						//pub_date


                       // Console.WriteLine(n.Value);
                        //dateFormat is Y|MM|DD or 'BCE'|Y 
						String dateExtractPattern //="([0-9]{0,}\\|[0-1]{0,1}[0-9]{1}\\|[0-3]{0,1}[0-9]{1})|(BCE\\|[0-9]+)";
							= @"([0-9]{1,4}\|[0-1]{0,1}[0-9]{1}\|[0-3]{0,1}[0-9]{1})" //Y|MM|DD
							+ @"|((?:(BC)?|(BCE)?)\|[0-9]+(\|\d\|\d){0}})" //'BCE'|Y 
							+ @"|((?:([0-3]?[0-9]) ((?:January)?|(?:February)?|(?:March)?|(?:April)?|(?:May)?|(?:June)?|(?:July)?|(?:August)?|(?:September)?|(?:October)?|(?:November)?|(?:December)?) )[0-9]{0,}\s?(?:(BC)?|(BCE)?))" //3 July 2001

							+ @"|(?:(?:January)?|(?:February)?|(?:March)?|(?:April)?|(?:May)?|(?:June)?|(?:July)?|(?:August)?|(?:September)?|(?:October)?|(?:November)?|(?:December)?) (?:([0-3]?[0-9])), [0-9]{0,}\s?(?:(BC)?|(BCE)?)"// July 3, 2001
							+ @"|((?:([0-3]?[0-9]) ((?:January)?|(?:February)?|(?:March)?|(?:April)?|(?:May)?|(?:June)?|(?:July)?|(?:August)?|(?:September)?|(?:October)?|(?:November)?|(?:December)?) )(?:(AD)?)\s[0-9]{1,})"; //3 July AD 2001
						MatchCollection dateExtract = Regex.Matches(n.Value, dateExtractPattern);
						//foreach (Match o in dateExtract)
						//{
						//	allEvents.AddLast(new CalendarEvent(page));
						//	allEvents.Last.Value.setDates(o.Value, dateExtractPattern);
						//	//Console.WriteLine(o.Value);
						//}

						//dateExtractPattern = @"|((?:(BC)?|(BCE)?)\|[0-9]+)"; //'BCE'|Y 
						//dateExtract = Regex.Matches(n.Value, dateExtractPattern);
						//foreach (Match o in dateExtract)
						//{
						//	allEvents.AddLast(new CalendarEvent(page));
						//	allEvents.Last.Value.setDates(o.Value, dateExtractPattern);
						//	//Console.WriteLine(o.Value);
						//}
						////+ "|([0-3]{0,1}[0-9]{1} \b(?:(January)?|(February)?|(March)?|(April)?|(May)?|(June)?|(July)?|(August)?|(September)?|(October)?|(November)?|(December)?) [0-9]{0,}\\s?(?:(BC)?|(BCE)?))" 
						////+ "(?:([0-3]{0,1}[0-9]{1}) (\b(?:January)?|(?:February)?|(?:March)?|(?:April)?|(?:May)?|(?:June)?|(?:July)?|(?:August)?|(?:September)?|(?:October)?|(?:November)?|(?:December)?) ([0-9]{0,})(\s?(?:BC)?|(?:BCE)?))";
						//dateExtractPattern = @"|((?:([0-3]?[0-9]) ((?:January)?|(?:February)?|(?:March)?|(?:April)?|(?:May)?|(?:June)?|(?:July)?|(?:August)?|(?:September)?|(?:October)?|(?:November)?|(?:December)?) )[0-9]{0,}\s?(?:(BC)?|(BCE)?))"; //3 July 2001
						//dateExtract = Regex.Matches(n.Value, dateExtractPattern);
						//foreach (Match o in dateExtract)
						//{
						//	allEvents.AddLast(new CalendarEvent(page));
						//	allEvents.Last.Value.setDates(o.Value, dateExtractPattern);
						//	//Console.WriteLine(o.Value);
						//}

						//dateExtractPattern = @"|(?:(?:January)?|(?:February)?|(?:March)?|(?:April)?|(?:May)?|(?:June)?|(?:July)?|(?:August)?|(?:September)?|(?:October)?|(?:November)?|(?:December)?) (?:([0-3]?[0-9])), [0-9]{0,}\s?(?:(BC)?|(BCE)?)"; // July 3, 2001
						//dateExtract = Regex.Matches(n.Value, dateExtractPattern);
						// Console.WriteLine("dates formated Y|MM|DD or 'BCE'|Y ");
						//1 iteration
						foreach (Match o in dateExtract)
						{
							allEvents.AddLast(new CalendarEvent(page));
							allEvents.Last.Value.setDates(o.Value, dateExtractPattern);
							//Console.WriteLine(o.Value);
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
			Console.WriteLine(allEvents.Count);


	
        }
    }
}
