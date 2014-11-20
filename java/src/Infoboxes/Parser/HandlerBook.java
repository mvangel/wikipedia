package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Dokonaly
 *
 */
public class HandlerBook  extends DefaultHandler {
	
	private List<Infobox_book> infoboxBookList = null;
	private Infobox_book infoboxBook = null;
	private StringBuffer sb;
	int counter = 0;
	Help pomoc = new Help();
	public List<Infobox_book> getInfoboxList() {
        return infoboxBookList;
    }

	boolean bTitle = false;
	
    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
    	sb = new StringBuffer();
        if (qName.equalsIgnoreCase("Page")) {                
        	infoboxBook = new Infobox_book();
            if (infoboxBookList == null) {
            	infoboxBookList = new ArrayList<>();
            }
        } 
        if (qName.equalsIgnoreCase("text")) {
        	bTitle = true;
        } 
    }
 
   
    //oparsovanie infoboxu book
    /**
     * @param vysledok
     * @return
     */
    public boolean oparsujBook(String vysledok){
    	boolean flag = false;
    
    	String vystup = pomoc.PouziRegex("\\| ?name ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "name");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setName(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?translator ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "translator");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setTranslator(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?image ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "image");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setImage(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?caption ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "caption");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setCaption(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?author ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "author");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setAuthor(vystup);
    		
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?country ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "country");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setCountry(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?language ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז|]+", vysledok);  	
    	if (vystup != null){
    		
    		if(vystup.contains("series") ){
    			String[] parts = vystup.split("series");
    			vystup = parts[0]; 
    		}
    		
    		if(vystup.contains("subject") ){
    			String[] parts = vystup.split("subject");
    			vystup = parts[0]; 
    		}
    		
    		if(vystup.contains("genre") ){
    			String[] parts = vystup.split("genre");
    			vystup = parts[0]; 
    		}

    		vystup = pomoc.ocisti_retazec(vystup, "language");
    		vystup = vystup.replaceAll("\\|"," ");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]|","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setLanguage(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?subject ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "subject");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setSubject(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?genre ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "genre");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-\\[\\];\\/*]","");
    		String[] rozdelovac = {",",";","*"};
    		if (vystup != null){
    		String[] pole =  pomoc.rozdel_do_pola(vystup,rozdelovac);
    		String a;		
    		for(int i=0;i<pole.length;i++){
    			pole[i]  = pomoc.posledna_medzera(pole[i]);
    			infoboxBook.setGenre(pole);
    		}
    		}
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?published ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "published");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setPublished(vystup);
    	    flag = true;
    	}
    	
    
    	vystup = pomoc.PouziRegex("\\| ?pages ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "pages");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setPages(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?isbn ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "isbn");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setIsbn(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?preceded_by ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "preceded_by");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setPreceded_by(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?followed_by ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup =pomoc.ocisti_retazec(vystup, "followed_by");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setFollowed_by(vystup);
    	    flag = true;
    	}
    	counter++;
    	return flag;
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	boolean flag_book = false;
    	
    	
        if (bTitle) {
        	
        	String vysledok = sb.toString();
        	//zarovnanie do jedneho riadku
        	vysledok = vysledok.replaceAll("(\r\n|\n)", " "); 
        	//odstranenie nepotrebnych medzier
        	vysledok = vysledok.trim().replaceAll(" +", " ");
        	//vybratie infoboxu book
        	String text = pomoc.PouziRegex("\\{\\{Infobox book\\s*(.*)", vysledok);
        
        	if (text !=null){
        		flag_book  = oparsujBook(text);
        	}
        	if (flag_book == true){
        		if ( infoboxBook.getName()!= null && infoboxBook.getAuthor() != null){
        			
        			/*System.out.println(infoboxBook.getName()+" "
        					+infoboxBook.getAuthor() +" "
        					+infoboxBook.getTranslator()+" "
        					+infoboxBook.getImage()+" "
        					+infoboxBook.getCaption()+" "
        					+infoboxBook.getCountry()+" "
        					+infoboxBook.getLanguage()+" "
        					+infoboxBook.getSubject()+" "
        					+infoboxBook.getGenre()+" "
        					+infoboxBook.getPublished()+" "
        					+infoboxBook.getPages()+" "
        					+infoboxBook.getIsbn()+" "
        					+infoboxBook.getFollowed_by()+" "
        					+infoboxBook.getPreceded_by()
        					);*/
        			
        			infoboxBookList.add(infoboxBook);    			
        		}
        		System.out.println(counter);
        	}	
            bTitle = false;
            flag_book = false;     	 
        }
    }
 
    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
         if (sb!=null && bTitle) {
             for (int i=start; i<start+length; i++) {
            	 sb.append(ch[i]);
             }
         }
    }
    
}
