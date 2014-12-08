package com.main;

import java.io.File;
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
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author root
 */
public class MyParserMapper2 extends
		Mapper<LongWritable, Text, NullWritable, Text> {

	@Override
	public void map(LongWritable key, Text value1, Context context)

	throws IOException, InterruptedException {

		String xmlString = value1.toString();

		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(xmlString);
		String value = "";
		try {

			Document doc = builder.build(in);
			Element root = doc.getRootElement();

			String title = root.getChild("title").getTextTrim();
			
			for(Page p : ParserDriver.redirects) { 
				  if (title.equalsIgnoreCase(p.redirect_title))
				  {
					  String text = root.getChild("revision").getChild("text").getTextTrim();
					 
					  int i = text.indexOf("=" + p.redirect_section + "=");
					  if (i != -1){
						  ParserDriver.sectionsfound++;
						  String tmp = text.substring(i);
						  String extracted = tmp.substring(tmp.indexOf("\n")-1);
						  int j = 0;
						  
						    
						  Pattern pattern = Pattern.compile("={2,}(.*?)={2,}");
						  Matcher matcher = pattern.matcher(extracted);
						    while (matcher.find()) {
						    	j = matcher.start();
						    	break;
						    }
                          String extracted2;
						  if (j == 0) extracted2 = extracted;
						  else extracted2 = extracted.substring(0,j);
						  
						  extracted2 = extracted2.substring(extracted2.indexOf("==\n") + 3);

						  Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);

						  File f = new File(ParserDriver.outputdirectory + "indexes");
						  Directory directory = FSDirectory.open(f);
						  IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_CURRENT, analyzer);
						  IndexWriter iwriter = new IndexWriter(directory, config);
						  org.apache.lucene.document.Document lucenedoc = new org.apache.lucene.document.Document();
						  lucenedoc.add(new Field("title", p.title, TextField.TYPE_STORED));
						  lucenedoc.add(new Field("id", p.id, TextField.TYPE_STORED));
						  lucenedoc.add(new Field("redirectTitle", p.redirect_title, TextField.TYPE_STORED));
						  lucenedoc.add(new Field("redirectSectionTitle", p.redirect_section, TextField.TYPE_STORED));
						  lucenedoc.add(new Field("redirectSectionText", extracted2, TextField.TYPE_STORED));
						  iwriter.addDocument(lucenedoc);
						  iwriter.close();

						  context.write(NullWritable.get(), new Text(p.title + " (" + p.id + ") --> " + p.redirect_title + " --> " + p.redirect_section + "\n " + extracted2 + "\n"));
					  }
				  }
			}
				

		} catch (Exception ex) {
			Logger.getLogger(MyParserMapper2.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}

}
