package ner_dictionary;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ner_dictionary.rules.Rule;
import ner_dictionary.rules.RuleSet;

public class WikiXMLParserHandler extends DefaultHandler{

	private StringBuilder sBuilder;
	private String actualPageTitle;
	private String actualPageText;
	private boolean readText = false;
	private boolean redirect = false;
	private PrintStream outputFileStream;
	private RuleSet ruleSet;
	public static final String DEFAULT_CATEGORY = "Miscellaneous";
	private Set<Redirect> redirectCache;
	private HashMap<String, String> categories;
	
	public WikiXMLParserHandler(PrintStream outputFileStream, RuleSet ruleSet) {
		super();
		this.outputFileStream = outputFileStream;
		this.ruleSet = ruleSet;
		this.redirectCache = new HashSet<Redirect>();
		this.categories = new HashMap<String, String>();
	}
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case "title":
			sBuilder = new StringBuilder();
			readText = true;
			break;
		case "redirect":
			redirect = true;
			addRedirect(actualPageTitle, attributes.getValue(0));
			break;
		case "text":
			if (!redirect) {
				sBuilder = new StringBuilder();
				readText = true;
			}
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
			if (!redirect) {
				actualPageText = sBuilder.toString();
				readText = false;
				addEntry(actualPageTitle, actualPageText);
			} else {
				redirect = false;
			}
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
		categories.put(title, category);
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
	
	private void addRedirect(String title, String redirectTitle) {
//		outputFileStream.println(title + "\t" + "Redirect");
		redirectCache.add(new Redirect(title, redirectTitle));
	}
	
	public int processRedirects() {
		int cacheSize = redirectCache.size();
		System.out.println("Redirects: " + cacheSize);
		for (Redirect redirect : redirectCache) {
			String redirectCategory = categories.get(redirect.redirectPage);
			if (redirectCategory != null) {
				outputFileStream.println(redirect.page + "\t" + redirectCategory);
				cacheSize--;
			}
		}
		return cacheSize;
	}
	
	public class Redirect
	{
		public String page;
	    public String redirectPage;
		
	    public Redirect(String page, String redirectPage) {
			this.page = page;
			this.redirectPage = redirectPage;
		}
	}
}
