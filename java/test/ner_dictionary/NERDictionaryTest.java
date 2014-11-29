package ner_dictionary;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import ner_dictionary.indexing.Indexer;
import ner_dictionary.rules.RuleSet;
import ner_dictionary.rules.Rule;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class NERDictionaryTest {

	private static NERDictionary app;
	
	@org.junit.Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
	
	@BeforeClass
	public static void setUp() {
		app = new NERDictionary();
	}
	
	@Test
	public void testParseRules() throws IOException {
		String categoryMappingFilePath = testFolder.newFile().getPath();
		URL url = getClass().getResource("resources/Category_recognition_rules.xml");
		String rulesFilePath = url.getPath();
		RuleSet ruleSet = app.parseRules(rulesFilePath, categoryMappingFilePath);
		Rule rule = ruleSet.getRuleList().get(0);
		
		assertEquals("(?m)^\\s*\\|\\s*birth_", rule.getPattern());
		assertEquals("Person", rule.getCategoryName());
		assertTrue(rule.getCategoryId() == 1);
		assertEquals("none", rule.getTypeAsString());
	}

	@Test
	public void testParseAndIndexWikipedia() throws IOException {
		File indexDirectory = testFolder.newFolder();
		Indexer indexer = new Indexer(indexDirectory.getPath());
		
		URL sourceUrl = getClass().getResource("resources/sample_alts_enwiki-latest-pages-articles1.xml");
		String sourceFileName = URLDecoder.decode(sourceUrl.getPath(), StandardCharsets.UTF_8.displayName());
		List<String> sourceList = new ArrayList<String>();
		sourceList.add(sourceFileName);
		
		String categoryMappingFilePath = testFolder.newFile().getPath();
		URL rulesUrl = getClass().getResource("resources/Category_recognition_rules.xml");
		String rulesFilePath = rulesUrl.getPath();
		RuleSet ruleSet = app.parseRules(rulesFilePath, categoryMappingFilePath);
		
		File outputFile = testFolder.newFile();
		
		app.parseAndIndexWikipedia(sourceList, outputFile.getPath(), ruleSet, indexer);
		
		List<String> lines = Files.readAllLines(outputFile.toPath(), StandardCharsets.UTF_8);
		assertTrue(lines.size() == 1);
		assertEquals("Abraham Lincoln" + WikiXMLParserHandler.SEPARATOR + "1", lines.get(0));
	}

}
