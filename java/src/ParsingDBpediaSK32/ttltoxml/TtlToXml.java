package ttltoxml;


public class TtlToXml {
	public static void GenerateXml() {
		XmlHandler xml = new XmlHandler();
		for(int i = 0; i < Settings.Files.size() ; i++){  //run parser for each file
			
			DataParser parser = new DataParser(Settings.Files.get(i));  
			while (parser.hasNext()) {
				RecordModel r = parser.Parse();  // RecordModel contains single record from file
				if(r != null)
					XmlHandler.handle(r);   // Parsed record is saved to xml document
			}
			
		}
		
		
		xml.saveXmlFile();    //saving xml documnt on disk
		System.out.println("File Generated!");
	}
}