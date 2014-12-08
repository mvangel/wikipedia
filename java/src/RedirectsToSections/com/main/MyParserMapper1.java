package com.main;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author root
 */
public class MyParserMapper1 extends
		Mapper<LongWritable, Text, NullWritable, Text> {

	@Override
	public void map(LongWritable key, Text value1, Context context)

	throws IOException, InterruptedException {

		String xmlString = value1.toString();

		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(xmlString);
		String value = "";
		try {
			ParserDriver.pages++;

			Document doc = builder.build(in);
			Element root = doc.getRootElement();

			String text = root.getChild("revision").getChild("text").getTextTrim();

			
			if (text.matches("(?s)^#REDIRECT.*$"))
			{
				String title = root.getChild("title").getTextTrim();
				String id = root.getChild("id").getTextTrim();
				
				String redirect_string = text.substring(text.indexOf('[')+2,text.indexOf(']'));
				String redirect_string_parts[] = redirect_string.split("#");
				if (redirect_string_parts.length >= 2){
					ParserDriver.redirectsnumber++;
				
					Page p = new Page();
					p.title = title;
					p.id = id;
					p.redirect_title = redirect_string_parts[0];
					p.redirect_section = redirect_string_parts[1];
					
					ParserDriver.redirects.add(p);
					
					context.write(NullWritable.get(), new Text(title + "(" + id + ")" + " --> " + redirect_string_parts[0] + " --> " + redirect_string_parts[1]));
				}
			}
			
		} catch (JDOMException ex) {
			Logger.getLogger(MyParserMapper1.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(MyParserMapper1.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}

}
