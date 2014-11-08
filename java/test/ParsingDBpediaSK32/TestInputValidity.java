package test;
import java.util.List;

import ttltoxml.DataParser;
import ttltoxml.RecordModel;
import ttltoxml.Settings;
import junit.framework.Assert;
import junit.framework.TestCase;


@SuppressWarnings("deprecation")
public class TestInputValidity extends TestCase {
	
	public void testValidity() throws Exception {
	   
	   for(int j = 0; j < Settings.Files.size() ; j++){
			DataParser File = new DataParser(Settings.Files.get(j),"test"); // real ttl dump files - defined in ttltoxml.settings
			List<RecordModel> listP = File.ParseTest();
			DataParser TestFile = new DataParser(Settings.TestFiles.get(j),"test"); // test input files - defined in ttltoxml.settings
			List<RecordModel> listTF = TestFile.ParseTest();
		   
			boolean flag = true;
			 for(int k = 0; k < listTF.size(); k++){
				 RecordModel model = listTF.get(k);
				 flag = containsId(listP, model.getProperty(), model.getResource(), model.getValue());  // test if there are tested entities in real ttl dump files
				  if(flag){
				  	  break;
				  }
			  }
			 System.out.println(flag);
			 Assert.assertEquals(true, flag);

	   }
	}
	
	public static boolean containsId(List<RecordModel> list, String p, String r, String v) {
	    for (RecordModel object : list) {
	        if (object.getProperty().equals(p) && object.getResource().equals(r)) {
	        	
	            return true;
	        }
	    }
	    return false;
	}
}
