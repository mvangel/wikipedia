package ttltoxml;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.lang.PipedRDFIterator;
import org.apache.jena.riot.lang.PipedRDFStream;
import org.apache.jena.riot.lang.PipedTriplesStream;

import com.hp.hpl.jena.graph.Triple;

public class DataParser {
	private String fileToParse;
	PipedRDFStream<Triple> inputStream;
	String file;
	PipedRDFIterator<Triple> iter;
	ExecutorService executor;
	
	public DataParser(String files, String test){
		fileToParse = files;
	}
	
	public DataParser(String files){ //creating input stream to parse by records in ttl file
		iter = new PipedRDFIterator<Triple>();
		inputStream = new PipedTriplesStream(iter);
		file = System.getProperty("user.dir")+ "/data/" + files;
		
		Runnable parser = new Runnable() {
			@Override
			public void run() {
				RDFDataMgr.parse(inputStream, file);
			}
		};
		executor = Executors.newSingleThreadExecutor();
		executor.submit(parser);
	}
	
	
	public RecordModel Parse(){

		
		Triple next = iter.next(); // another record in file
		
		RecordModel record = new RecordModel(); 
		if (record.setAttributes(next))
			return record;

		return null;
				  
	}
	
	public boolean hasNext(){
		return iter.hasNext();
	}
	
	public List<RecordModel> ParseTest(){
		
		
		final String file = System.getProperty("user.dir")+ "/data/" + fileToParse;
		PipedRDFIterator<Triple> iter = new PipedRDFIterator<Triple>();
		final PipedRDFStream<Triple> inputStream = new PipedTriplesStream(iter);
		
		ExecutorService executor = Executors.newSingleThreadExecutor();

		Runnable parser = new Runnable() {
			@Override
			public void run() {
				RDFDataMgr.parse(inputStream, file);
			}
		};
		executor.submit(parser);

		List<RecordModel> list = new ArrayList<RecordModel>();
		while (iter.hasNext()) {
			Triple next = iter.next();
			
			RecordModel record = new RecordModel(); 
			if (record.setAttributes(next))
				list.add(record);
			
		}
		return list;
				  
	}
	
}
