using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using WikiCalendar;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;

using System.Xml.Linq;
using WikiCalendar;

namespace WikiCalendarTest
{
	[TestClass]
	public class CalendarEventTest
	{
		CalendarEvent testClass;

		[TestMethod]
		public void exportXML_test()
		{

			XElement expected = new XElement("day",
				new XElement("date", "1992|04|21"),
				new XElement("events",
					new XElement("event",
						new XElement("event_title", "Doom"),
						new XElement("event_type", "XNA"),//TODO eventType recognition
						new XElement("page_id", "12345")
					)
				)
			);



			Assert.AreEqual(expected, expected);//TODO
			//XElement actual = testClass.exportXML();
		}
	}
}
