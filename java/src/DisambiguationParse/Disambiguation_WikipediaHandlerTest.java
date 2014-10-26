package DisambiguationParse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.jhu.nlp.wikipedia.PageCallbackHandler;
import edu.jhu.nlp.wikipedia.WikiPage;




public class Disambiguation_WikipediaHandlerTest extends Disambiguation_WikipediaParseTest implements PageCallbackHandler {
	
        public void process(WikiPage page) {
    
    		
    		////WRITER INDEXU
    		if(IndexFunction==true){
    			
    	/*		String titleforme = page.getTitle().trim();
        		String titleid = page.getID();
    			
    		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:/Trash/WikiParse/index.txt", true)))) {
    		    out.println("T:"+titleforme+"|"+titleid+"|"+spracovanysubor+"|>");
    		}catch (IOException e) {
    		    //exception handling left as an exercise for the reader
    		}*/
    		
    		}
        	
        	
        	if(page.getTitle().indexOf("disambiguation")>=0 || page.getCategories().indexOf("disambiguation")>=0){
        	
        		
        		//System.out.println(page.getTitle().trim());
               
        		
        		listOfTitles.add(page.getTitle().trim());
        	
                    
        	}
        }

}