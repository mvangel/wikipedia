package com.main;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author root
 */
public class ParserDriver {

	public static  Queue<Page> redirects = new ConcurrentLinkedQueue<Page>();
	public static int pages = 0;
	public static int redirectsnumber = 0;
	public static int sectionsfound = 0;
	
	public static String inputfile;
	public static String outputdirectory;
	
	/**
	 * @param args
	 *            the command line arguments
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		try {
			
			
			
			if (args.length > 0)
			{
				if (args[0].equals("parse"))
				{
					inputfile = args[1];
					outputdirectory = args[2];
					runJob();
				}
				else if (args[0].equals("search"))
				{
					outputdirectory = args[3];
					luceneSearch(args[1], Integer.parseInt(args[2]));
				}
				else
				{
					System.out.print("Arguments error\n");
				}
			}
			else System.out.print("Arguments error\n");

		} catch (IOException ex) {
			//Logger.getLogger(ParserDriver.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

	public static void runJob() throws IOException {
		Configuration conf = new Configuration();
		conf.set("xmlinput.start", "<page>");
		conf.set("xmlinput.end", "</page>");
		conf.set(
				"io.serializations",
				"org.apache.hadoop.io.serializer.JavaSerialization,org.apache.hadoop.io.serializer.WritableSerialization");

		Job job = new Job(conf, "jobName");
		FileInputFormat.setInputPaths(job, inputfile);
		
		job.setJarByClass(ParserDriver.class);
		job.setMapperClass(MyParserMapper1.class);
		job.setNumReduceTasks(0);
		job.setInputFormatClass(XmlInputFormat.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		Path outPath = new Path(outputdirectory + "redirects");
		FileOutputFormat.setOutputPath(job, outPath);
		FileSystem dfs = FileSystem.get(outPath.toUri(), conf);
		if (dfs.exists(outPath)) {
			dfs.delete(outPath, true);
		}

		try {

			job.waitForCompletion(true);
			System.out.println("_____1______");
			
			runJob2();

		} catch (InterruptedException ex) {
			Logger.getLogger(ParserDriver.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ParserDriver.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	public static void runJob2() throws IOException, ParseException {
		System.out.println("-->_____2______");
		Configuration conf = new Configuration();

		conf.set("xmlinput.start", "<page>");
		conf.set("xmlinput.end", "</page>");
		conf.set(
				"io.serializations",
				"org.apache.hadoop.io.serializer.JavaSerialization,org.apache.hadoop.io.serializer.WritableSerialization");

		Job job = new Job(conf, "jobName");

		FileInputFormat.setInputPaths(job, inputfile);
		job.setJarByClass(ParserDriver.class);
		job.setMapperClass(MyParserMapper2.class);
		job.setNumReduceTasks(0);
		job.setInputFormatClass(XmlInputFormat.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		Path outPath = new Path(outputdirectory + "sections");
		FileOutputFormat.setOutputPath(job, outPath);
		FileSystem dfs = FileSystem.get(outPath.toUri(), conf);
		if (dfs.exists(outPath)) {
			dfs.delete(outPath, true);
		}

		try {

			job.waitForCompletion(true);
			System.out.println("_____2______");
			System.out.println("\n\n Number of found pages: " + pages +
								"\nNumber of redirects: " + redirectsnumber +
								"\nNumber of found sections redirected to: " + sectionsfound +
								"\nNumber of redirects without the found redirected section: " + Integer.toString(redirectsnumber - sectionsfound));

		} catch (InterruptedException ex) {
			Logger.getLogger(ParserDriver.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ParserDriver.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}

	private static void luceneSearch(String querystring, int mode) throws IOException, ParseException {
		File f = new File(outputdirectory + "indexes");
		Directory directory = FSDirectory.open(f);
	    DirectoryReader ireader = DirectoryReader.open(directory);
	    IndexSearcher isearcher = new IndexSearcher(ireader);
	    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
	    
	    QueryParser parser = null;
	    if (mode == 1 ) parser = new QueryParser(Version.LUCENE_CURRENT, "title", analyzer);
	    else if (mode == 2 ) parser = new QueryParser(Version.LUCENE_CURRENT, "redirectTitle", analyzer);
	    else if (mode == 3 ) parser = new QueryParser(Version.LUCENE_CURRENT, "redirectSectionTitle", analyzer);
	    else if (mode == 4 ) parser = new QueryParser(Version.LUCENE_CURRENT, "redirectSectionText", analyzer);
	    else if (mode == 5 ) parser = new QueryParser(Version.LUCENE_CURRENT, "id", analyzer);
	    
	    
	    Query query = parser.parse(querystring);
	    ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
	    for (int i = 0; i < hits.length; i++) {
	      Document hitDoc = isearcher.doc(hits[i].doc);
	      System.out.println(hitDoc.get("title") + " (" + hitDoc.get("id") + ")" + " ---> " + hitDoc.get("redirectTitle") + " ---> " + hitDoc.get("redirectSectionTitle") + "\n" + hitDoc.get("redirectSectionText"));
	    }
	    ireader.close();
	    directory.close();
		
	}

}