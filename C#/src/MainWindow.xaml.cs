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
using System.Threading;

namespace WikiCalendar
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
		Controller control;
        public MainWindow()
        {
            InitializeComponent();
			control = new Controller();
        }
		private void Button_Click(object sender, RoutedEventArgs e)
		{
			String inputPath =
				//@"D:\downNew\wiki\enwiki-latest-pages-articles1.xml-p000000010p000010000";
				//	@"..\..\..\..\Data\sample_input_enwiki-latest-pages-articles1.xml";
					@"D:\downNew\wiki\enwiki-latest-pages-articles6.xml";
			//@"D:\downNew\wiki\enwiki-latest-pages-articles3.xml-p000025001p000055000";
			
			String exportPath = @"..\..\..\..\Data\sample_output_enwiki-latest-pages-articles1_Real_output.xml";
			
			control.initParsing(inputPath);
			control.exportEventsXML(exportPath);
			TextConsole.Text = control.getXmlString();
			pagesCountTextBox.Text = control.pagesCount.ToString();
			eventsCountTextBox.Text = control.eventsCount.ToString();

		}

		private void dateSearchTextBox_TextChanged(object sender, TextChangedEventArgs e)
		{
			




		}

		private void Button_Click_1(object sender, RoutedEventArgs e)
		{
			TextConsole.Text = "";
			HashSet<CalendarEvent> resultSet = control.searchDayEvents(dateSearchTextBox.Text);

			TextConsole.Text += "\n\nDatekey:" + dateSearchTextBox.Text;
			if (resultSet == null)
			{
				return;
			}
			foreach (CalendarEvent ce in resultSet)
			{
				TextConsole.Text += "\n\t" + ce.title + ":" + "\n";
			}


		}



	
    }
}
