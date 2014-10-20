package fiit.ir.NER_dictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import fiit.ir.NER_dictionary.rules.RuleSet;

/**
 * Named Entity Recognition dictionary
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	RuleSet ruleSet = null;
    	
    	try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RuleSet.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ruleSet = (RuleSet) jaxbUnmarshaller.unmarshal(new File("Category_recognition_rules.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	for (String arg: args) {
    		InputSource inputSource = null;
    		PrintStream outputFileStream = null;
    		
    		try {
				outputFileStream = new PrintStream(new FileOutputStream("NER_dictionary.tsv"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
			try {
				inputSource = new InputSource(new FileInputStream(new File(arg)));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			try {
				XMLReader reader = XMLReaderFactory.createXMLReader();
				reader.setContentHandler(new WikiXMLParserHandler(outputFileStream, ruleSet));
				reader.parse(inputSource);
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} finally {
				outputFileStream.close();
			}
    	}
    }
}
