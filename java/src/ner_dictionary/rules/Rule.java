package ner_dictionary.rules;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rule")
public class Rule {
	
	private String pattern;
	private String category;
	
	@XmlElement(name="pattern")
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@XmlElement(name="category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
