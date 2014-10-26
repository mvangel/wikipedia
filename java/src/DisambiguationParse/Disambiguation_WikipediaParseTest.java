package DisambiguationParse;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import edu.jhu.nlp.wikipedia.PageCallbackHandler;
import edu.jhu.nlp.wikipedia.WikiPage;
import edu.jhu.nlp.wikipedia.WikiXMLParser;
import edu.jhu.nlp.wikipedia.WikiXMLParserFactory;


public class Disambiguation_WikipediaParseTest {
     
	
	static ArrayList<String> listOfTitles = new ArrayList<String>();
	static String userdir = System.getProperty("user.dir");
	 String spracovanysubor = userdir+"/wiki/enwiki-latest-pages-articles1.xmlddd";
	
	static boolean IndexFunction = true; //mame zaindexovane, tak false.. ak nemame tak true
	
	@Test
	public void parsertest(){
	 PageCallbackHandler handler = new Disambiguation_WikipediaHandlerTest();
	 String cesta= userdir+"/wiki/enwiki-latest-pages-articles1.xmlddd";
	
     WikiXMLParser wxsp = WikiXMLParserFactory.getSAXParser(cesta);
     try {
     	
 	    
         wxsp.setPageCallback(handler);
         wxsp.parse();
         
        
         
         
 }catch(Exception e) {
         e.printStackTrace();
 }
     
    String test1 = listOfTitles.get(0);
    //Austin (disambiguation)
    
    String test2 = listOfTitles.get(1);
    //Aberdeen (disambiguation)
    
    String test3 = listOfTitles.get(2);
    //Argument (disambiguation)
    
    String test4 = listOfTitles.get(3);
    //Animal (disambiguation)
    
    assertEquals(test1,"Austin (disambiguation)");
    assertEquals(test2,"Aberdeen (disambiguation)");
    assertEquals(test3,"Argument (disambiguation)");
    assertEquals(test4,"Animal (disambiguation)");
     
    //testy na vyparsovanie disambiguacnych stranok ...  
     
     
     
	
	}
}
