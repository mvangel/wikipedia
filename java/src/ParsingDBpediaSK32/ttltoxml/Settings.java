package ttltoxml;

import java.util.Arrays;
import java.util.List;

public class Settings {
	
	public static List<String> Predicates = Arrays.asList( //list of accepted predicates
		"label",
		"isPrimaryTopicOf",
		"comment",
		"wikiPageExternalLink",
		"subject",
		"wikiPageWikiLink",
		"wikiPageRedirects"
		);
	
	public static List<String> Files = Arrays.asList( // list of files to parse
			"labels_sk.ttl",
			"wikipedia_links_sk.ttl",
			"short_abstracts_sk.ttl",
			"page_links_sk.ttl",
			"redirects_sk.ttl",
			"external_links_sk.ttl",
			"article_categories_sk.ttl"
			);
	
	public static List<String> TestFiles = Arrays.asList( // list of testing files
			"sample-test_labels_sk.ttl",
			"sample-test_wikipedia_links_sk.ttl",
			"sample-test_short_abstracts_sk.ttl",
			"sample-test_page_links_sk.ttl",
			"sample-test_redirects_sk.ttl",
			"sample-test_external_links_sk.ttl",
			"sample-test_article_categories_sk.ttl"
			);
	
	public static String OutputFile = "data/outputFiles/OutFromTTLdumpsDBpedia32.xml"; //output xml file
			
	public static String OutputTemplate = "/data/output-TemplateDBpedia32.xml"; // template xml file 
}
