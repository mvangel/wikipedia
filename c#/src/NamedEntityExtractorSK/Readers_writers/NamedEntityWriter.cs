using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NamedEntityExtractorSK.Readers_writers
{
	public static class NamedEntityWriter
	{
		#region Methods

		public static void WriteData(List<string> data, string fileName)
		{
			File.WriteAllLines(fileName, data.Where(x => !string.IsNullOrWhiteSpace(x)));
		}

		#endregion
	}

	public static class NamedEntityReader
	{
		#region Methods

		public static List<string> ReadData(string fileName)
		{
			var data = File.ReadAllLines(fileName, Encoding.UTF8).ToList();
			return data;
		}

		public static bool FilesExist(params string[] fileNames)
		{
			return !fileNames.Any(x => !File.Exists(x));
		}

		#endregion
	}
}
