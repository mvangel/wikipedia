using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using NamedEntityExtractorSK.Data;
using NamedEntityExtractorSK.Readers_writers;
using NamedEntityExtractorSK.Utilities;

namespace NamedEntityExtractorSK
{
	public class Program
	{
		#region Fields

		const string PersonsFileName = "persons.txt";
		const string OrganizationsFileName = "organizations.txt";
		const string LocationsFileName = "locations.txt";

		#endregion

		#region Properties

		private static IList<Infobox> Infoboxes { get; set; }
		private static IList<Geobox> Geoboxes { get; set; }
		private static IList<Citation> Citations { get; set; }

		private static List<string> Persons { get; set; }
		private static List<string> Organizations { get; set; }
		private static List<string> Locations { get; set; }

		#endregion

		#region Methods

		static void Main(string[] args)
		{
			Console.WriteLine("Choose mode: \n'X' - for load from XML,\n'F' - load from existing parsered text files  (persons.txt, organizations.txt, locations.txt ) //if exists..\nMode: ");

			//load xml
			if (Console.ReadLine().ToLower().Equals("x"))
			{
				LoadNamedEntitiesFromXML();
			}
			//load from text files
			else
			{
				LoadNamedEntitiesFromFiles();
			}
		}

		private static void LoadNamedEntitiesFromXML()
		{
			var baseDirectory = AppDomain.CurrentDomain.BaseDirectory.Split('\\');
			var directory = baseDirectory.Take(baseDirectory.Length - 3).Aggregate((a, b) => a + '\\' + b);
			//string filePath = directory + @"\InputData\skwiki-latest-pages-articles.xml";
			string filePath = directory + @"\InputData\sample_skwiki-latest-pages-articles.xml";
			var reader = new InputDataReader();

			reader.SetPagesFromInputFile(filePath);
			var pages = reader.Pages;

			Infoboxes = new List<Infobox>();
			Geoboxes = new List<Geobox>();
			Citations = new List<Citation>();

			Persons = new List<string>();
			Organizations = new List<string>();
			Locations = new List<string>();

			CategorizeProperties(pages);

			Persons = Persons.Select(x => WordUtils.TrimNonLetterCharacters(x, true)).OrderBy(x => x).Distinct(StringComparer.CurrentCultureIgnoreCase).ToList();
			Organizations = Organizations.Select(x => WordUtils.TrimNonLetterCharacters(x)).OrderBy(x => x).Distinct(StringComparer.CurrentCultureIgnoreCase).ToList();
			Locations = Locations.Select(x => WordUtils.TrimNonLetterCharacters(x, false)).OrderBy(x => x).Distinct(StringComparer.CurrentCultureIgnoreCase).ToList();

			NamedEntityWriter.WriteData(Persons, PersonsFileName);
			NamedEntityWriter.WriteData(Organizations, OrganizationsFileName);
			NamedEntityWriter.WriteData(Locations, LocationsFileName);

			var finder = new Finder(Persons, Organizations, Locations);
			finder.Find();
		}

		private static void LoadNamedEntitiesFromFiles()
		{
			if (NamedEntityReader.FilesExist(PersonsFileName, OrganizationsFileName, LocationsFileName))
			{
				Persons = NamedEntityReader.ReadData(PersonsFileName);
				Organizations = NamedEntityReader.ReadData(OrganizationsFileName);
				Locations = NamedEntityReader.ReadData(LocationsFileName);

				var finder = new Finder(Persons, Organizations, Locations);
				finder.Find();
			}
			else
			{
				Console.WriteLine("Files not exists. Load from XML? (Y/N): ");
				if (Console.ReadLine().ToLower().Equals("y"))
				{
					LoadNamedEntitiesFromXML();
				}
			}
		}

		private static void CategorizeProperties(List<Page> pages)
		{
			foreach(var page in pages)
			{
				foreach(var item in page.Infoboxes)
				{
					var type = item.GetType();
					item.SetRegexAttributes();

					if(type == typeof(Infobox))
					{
						var info = item as Infobox;
						Locations.AddRange(GetNamedEntities(info, NamedEntityType.Location));
						Persons.AddRange(GetNamedEntities(info, NamedEntityType.Person));
						Organizations.AddRange(GetNamedEntities(info, NamedEntityType.Organization));
						Infoboxes.Add(info);
					}
					else if(type == typeof(Citation))
					{
						var cit = item as Citation;
						cit.AddFullNames();

						Locations.AddRange(GetNamedEntities(cit, NamedEntityType.Location));
						Persons.AddRange(GetNamedEntities(cit, NamedEntityType.Person));
						Organizations.AddRange(GetNamedEntities(cit, NamedEntityType.Organization));
						Citations.Add(cit);
					}
					else if(type == typeof(Geobox))
					{
						var geo = item as Geobox;

						Locations.AddRange(GetNamedEntities(geo, NamedEntityType.Location));
						Persons.AddRange(GetNamedEntities(geo, NamedEntityType.Person));
						Geoboxes.Add(geo);
					}
				}
			}
		}

		private static string[] GetNamedEntities(KnowlegeData item, NamedEntityType type)
		{
			var items = new List<string>();
			foreach(var value in item.Items.Where(x => x.Key.Type == type).Select(l => l.Value))
			{
				items.AddRange(value.Where(x => x.Length > 1));
			}

			return items.ToArray();
		}

		#endregion
	}
}
