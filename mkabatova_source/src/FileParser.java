import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;


public class FileParser {

	private BufferedWriter bw;
	
	public void parseFile()
	{
		p_parseFile();
	}
	private void p_parseFile()
	{
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\martinka\\VI\\old\\output_test-pages.txt"),Charset.forName("UTF-8").newEncoder()));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\martinka\\VI\\skwiki-latest-page.sql"),Charset.forName("UTF-8").newDecoder()));
			String sCurrentLine,insert;
			String[] inserts;
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.matches(".*(\\([0-9]+,.*\\))+.*")) {
					insert = sCurrentLine.replaceAll("\\).?,.?\\(", "\\),\\(");
					inserts = insert.split(" ");
					for(int i = 0; i<inserts.length;i++)
					{
						parseInserts(inserts[i]);
					}
				}
				
			}
			bw.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void parseInserts(String insert) throws IOException
	{
		if(insert.matches(".*,.*"))
		{
			String[] ones = insert.split("\\),\\(");
			ones[0] = ones[0].replaceAll("[\\(\\)']", "");
			ones[ones.length-1] = ones[ones.length-1].replaceAll("[\\(\\)']", "");
			for(int j = 0; j < ones.length;j++){
				//ones[j] = ones[j].replaceAll("[']", "");
				writeInserts(ones[j]);
				
			}
			
		}
	}
	private void writeInserts(String s) throws IOException
	{
		String[] ones = s.split(",");
		bw.append(ones[0] + ";" + ones[1] + ";" + ones[2] + "\n");
	}
	
	
	
	
	
	
}
