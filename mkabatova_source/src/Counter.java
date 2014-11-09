import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Counter {

	void countPages(){
		try {
			String item;
			BufferedReader br = new BufferedReader(new FileReader("C:\\martinka\\VI\\output_test2.txt"));
			while((item = br.readLine()) != null)
			{
				item.split(",");
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
};
