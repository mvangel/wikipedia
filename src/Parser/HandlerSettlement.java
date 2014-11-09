package Parser;

import java.util.ArrayList;
import java.util.Arrays;
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
public class HandlerSettlement  extends DefaultHandler {
	
	private List<Infobox_settlement> infobox_settlementList = null;
	private Infobox_settlement infobox_settlement = null;
	private StringBuffer sb;
	int counter = 0;
	
	public List<Infobox_settlement> getInfoboxList() {
        return infobox_settlementList;
    }

	boolean bTitle = false;
	Help pomoc = new Help();
    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
    	sb = new StringBuffer();
    
        if (qName.equalsIgnoreCase("Page")) {                
        	infobox_settlement = new Infobox_settlement();
            if (infobox_settlementList == null) {
            	infobox_settlementList = new ArrayList<>();
            }
            //vyparsovanie atributu text
        } else if (qName.equalsIgnoreCase("text")) {
        	bTitle = true;
        } 
    }

    //metoda na oparsovanie infoboxu
    /**
     * @param flag
     * @param vysledok
     * @return
     */
    public boolean oparsujSettlement(boolean flag, String vysledok){
    	flag = false;
    	
    	String vystup = pomoc.PouziRegex("\\| ?official_name ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "official_name");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox_settlement.setOfficial_name(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?nickname ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "nickname");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox_settlement.setNickname(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?map_caption ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "map_caption");
    		
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox_settlement.setMap_caption(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?coordinates_region ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "coordinates_region");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z:.,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox_settlement.setCoordinates_region(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?leader_title ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "leader_title");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox_settlement.setLeader_title(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?unit_pref ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "unit_pref");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox_settlement.setUnit_pref(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?area_total_km2 ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "area_total_km2");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox_settlement.setArea_total_km2(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?area_land_km2 ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "area_land_km2");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox_settlement.setArea_land_km2(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?population_total ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{|'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		
    		if(vystup.contains("population_density_km2") ){
    			String[] parts = vystup.split("population_density_km2");
    			vystup = parts[0]; 
    		}
    		
    		if(vystup.contains("population_as_of") ){
    			String[] parts = vystup.split("population_as_of");
    			vystup = parts[0]; 
    		}
    		
    		vystup = pomoc.ocisti_retazec(vystup, "population_total");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox_settlement.setPopulation_total(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?population_density_km2 ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "population_density_km2");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.,:?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox_settlement.setPopulation_density_km2(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?timezone ?= [A-Za-z0-9 _ =*.:?!()+-<>\\[#@\\{}|'` $%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		if(vystup.contains("utc_offset") ){
    			String[] parts = vystup.split("utc_offset");
    			vystup = parts[0]; 
    		}
    		if(vystup.contains("timezone_DST") ){
    			String[] parts = vystup.split("timezone_DST");
    			vystup = parts[0]; 
    		}
    		
    		if(vystup.contains("utc_offset1") ){
    			String[] parts = vystup.split("utc_offset1");
    			vystup = parts[0]; 
    		}
    		
    		vystup = pomoc.ocisti_retazec(vystup, "timezone");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-|]","");
    		vystup = vystup.replaceAll("\\[","");
    		vystup = vystup.replaceAll("\\]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		String a = vystup.substring(0, 1);
    		if(a.contains("|")){
    			infobox_settlement.setTimezone(null);
    		}
    			
    	    infobox_settlement.setTimezone(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?website ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "website");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-|]","");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox_settlement.setWebsite(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?postal_code ?= [^|]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "postal_code");
    		
    		vystup = vystup.replaceAll("[^0-9.:,?! +-\\[\\];\\/*]","");
    		String[] rozdelovac = {",","-"};
    		if (vystup != null){
    		String[] pole =  pomoc.rozdel_do_pola(vystup,rozdelovac);
    		String a;		
    		for(int i=0;i<pole.length;i++){
    			pole[i]  = pomoc.posledna_medzera(pole[i]);
    			infobox_settlement.setPostal_code(pole);
    		}
    		}
    	    
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
    	boolean flag_settlement = false;
    	
        if (bTitle) {
          
        	String vysledok = sb.toString();
        	 //zarovnanie do jedneho riadku
        	vysledok = vysledok.replaceAll("(\r\n|\n)", " ");
        	//odstranenie nepotrebnych medzier
        	vysledok = vysledok.trim().replaceAll(" +", " "); 
        	//vybratie samotneho infoboxu
        	String text = pomoc.PouziRegex("\\{\\{Infobox settlement \\s*(.*)", vysledok);
        	
        	if (text !=null){
        		flag_settlement  = oparsujSettlement(flag_settlement, text);
        	}
        	if (flag_settlement == true){
        		if ( infobox_settlement.getOfficial_name()!= null ||  infobox_settlement.getPopulation_total() != null){
        			String psc = Arrays.toString(infobox_settlement.getPostal_code());
        			/*System.out.println(infobox_settlement.getOfficial_name()+" "
        					+infobox_settlement.getNickname() +" "
        					+infobox_settlement.getMap_caption()+" "
        					+infobox_settlement.getCoordinates_region()+" "
        					+infobox_settlement.getLeader_title()+" "
        					+infobox_settlement.getLeader_title()+" "
        					+infobox_settlement.getUnit_pref()+" "
        					+infobox_settlement.getArea_total_km2()+" "
        					+infobox_settlement.getArea_land_km2()+" "
        					+infobox_settlement.getPopulation_total()+" "
        					+infobox_settlement.getPopulation_density_km2()+" "
        					+infobox_settlement.getTimezone()+" "
        					+infobox_settlement.getWebsite()+" "
        					+psc
        					);*/
        			infobox_settlementList.add(infobox_settlement);
            	      
        			
        		}
        		System.out.println(counter);
        	}	
            bTitle = false;
            flag_settlement = false;
           
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
