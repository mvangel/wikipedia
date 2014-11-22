package ner_dictionary.rules;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CategorySet {
	
	public static final Integer DEFAULT_CATEGORY_ID = 0;
	public static final String DEFAULT_CATEGORY_NAME = "Miscellaneous";
	private HashMap<String, Integer> categories;
	private Integer lastId = 0;
	
	public CategorySet(RuleSet ruleSet) {
		// Detect all categories, which occurred in rules, assign an id to each
		categories = new HashMap<String, Integer>();
		for (Rule rule : ruleSet.getRuleList()) {
			Integer value = categories.get(rule.getCategoryName());
			if (value == null) {
				categories.put(rule.getCategoryName(), ++lastId);
				rule.setCategoryId(lastId);
			} else {
				rule.setCategoryId(value);
			}
		}
	}
	
	public void saveSetToFile(String filePath) {
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream(filePath);
			for (Map.Entry<String, Integer> entry : categories.entrySet()) {
				prop.setProperty(entry.getKey(), entry.getValue().toString());
			}
			prop.store(output, null);
		} catch (IOException e) {
			System.err.println("The set of categories could not be saved in file.");
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
