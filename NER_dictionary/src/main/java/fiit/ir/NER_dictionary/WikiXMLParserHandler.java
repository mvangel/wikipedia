package fiit.ir.NER_dictionary;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fiit.ir.NER_dictionary.rules.Rule;
import fiit.ir.NER_dictionary.rules.RuleSet;

public class WikiXMLParserHandler extends DefaultHandler{

	private StringBuilder sBuilder;
	private String actualPageTitle;
	private String actualPageText;
	private boolean readText = false;
	private PrintStream outputFileStream;
	private RuleSet ruleSet;
	public static final String DEFAULT_CATEGORY = "Miscellaneous";
	
	public WikiXMLParserHandler(PrintStream outputFileStream, RuleSet ruleSet) {
		super();
		this.outputFileStream = outputFileStream;
		this.ruleSet = ruleSet;
	}
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Start parsing the document." + System.lineSeparator());
		super.startDocument();
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println(System.lineSeparator() + "The document end reached.");
		super.endDocument();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case "title":
			sBuilder = new StringBuilder();
			readText = true;
			break;
		case "text":
			sBuilder = new StringBuilder();
			readText = true;
			break;
		default:
			break;
		}
		super.startElement(uri, localName, qName, attributes);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "title":
			actualPageTitle = sBuilder.toString();
			readText = false;
			break;
		case "text":
			actualPageText = sBuilder.toString();
			readText = false;
			addEntry(actualPageTitle, actualPageText);
			break;
		default:
			break;
		}
		super.endElement(uri, localName, qName);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (readText) {
			sBuilder.append(ch,start,length);
		}
		super.characters(ch, start, length);
	}
	
	private void addEntry(String title, String text) {
		String category = detectCategory(text);
//		System.out.println(title + " - " + category.toString());
		outputFileStream.println(title + "\t" + category.toString());
	}
	
	private String detectCategory(String text) {
		
		for (Rule rule : ruleSet.getRuleList()) {
			Pattern pattern = Pattern.compile(rule.getPattern());
			Matcher matcher = pattern.matcher(text);
			if (matcher.find()) {
				return rule.getCategory();
			}
		}
		return DEFAULT_CATEGORY;
	}
	
	private enum EntityCategory {
		PERSON("Person"), ORGANIZATION("Organization"), LOCATION("Location"), MISCELLANEOUS("Miscellaneous");
		
		private final String text;

	    private EntityCategory(final String text) {
	        this.text = text;
	    }

	    @Override
	    public String toString() {
	        return text;
	    }
	}

}
