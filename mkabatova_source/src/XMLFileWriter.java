/*import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.w3c.dom.Document;


public class XMLFileWriter {

	private 
		PageRank pr;
		Document doc;
		String file_path;
		File XML_file;
	XMLFileWriter()
	{
		pr = new PageRank();
		file_path = "C:\\martinka\\sample.xml";
		XML_file = new File(file_path);
		
		 
		
	 
		
	}
	
	public void writeFile(int totalCount, ResultSet results)
	{
		p_writeFile(totalCount, results);
	}
	
	private void p_writeFile(int totalCount , ResultSet results)
	{

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(XML_file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(p_writeStart());
			while(results.next()){
				//bw.write("something");
				bw.append(p_writeElements(totalCount, results));
			}
			bw.append(p_writeEnd());
			bw.close();
			fos.close();
			System.out.println("Written into file");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private String p_writeElements(int total, ResultSet results) throws SQLException
	{
		return  "<pages>\n" +
				"<page_id>" + results.getString("pl_title") + "</page_id>\n" +
				"<link_count>" + results.getString("pocet") + "</link_count>\n" +
				"<page_rank>"+ pr.getPageRank(total, results.getInt("pocet")) + "</page_rank>\n" +
				"</pages>\n";
	}
	private String p_writeStart()
	{
		return "<?xml version=\"1.0\" encoding=\"UTF-16\"?>\n" +
				"<page_rank_schema>\n";
				
	}
	
	private String p_writeEnd()
	{
		return "</page_rank_schema>\n";
	}

}
*/