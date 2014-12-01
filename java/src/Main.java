
 
import java.sql.Connection;

 
public class Main {
		
 
  public static void main(String[] argv) {
 
	  MainWindow mw = new MainWindow();
	FileParser fp = new FileParser();
	fp.parseFile("C:\\Users\\whitelilit\\workspace\\VyhladavanieInformacii\\data");
	//Parser p = new Parser();
	//p.readFile();
	
	
	
	/*DatabaseConnector dc = new DatabaseConnector();
	Connection connection = dc.getConnection();	
	DatabaseQuery dq = new DatabaseQuery(connection);
	//poslat do xml writera
	XMLWriter xw = new XMLWriter();
	xw.makeFile(dq.getTotal(), dq.getResultSet());
	XMLFileWriter xfw = new XMLFileWriter();
	xfw.writeFile(dq.getTotal(),dq.getResultSet());
	
	dc.stopConnection();*/
  }
}