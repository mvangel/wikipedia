package Parser;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestParse {
	main mainclass = new main();
	JTextField searchExpression;
	JButton btnSearch;
	JTextArea ResultData;
	@Before 
	public void init(){
		searchExpression = new JTextField();
		mainclass.gu = new GUI();
		mainclass.gu.createGUI();
		searchExpression = mainclass.gu.searchExpression;
		btnSearch = mainclass.gu.btnSearch;
		ResultData= mainclass.gu.ResultData;
	}
	@Test
	public void TestNoneresult() {
		searchExpression.setText("peknydenprajem");
		btnSearch.doClick();
		String result = ResultData.getText();
		String ExpectedResult = "Found 0 hits.";
		Assert.assertTrue(result.contains(ExpectedResult));
	}
	@Test
	public void Test1result() {
		searchExpression.setText("blindness");
		btnSearch.doClick();
		String result = ResultData.getText();
		String ExpectedResult = "Found 1 hits.";
		Assert.assertTrue(result.contains(ExpectedResult));
	}
	@Test
	public void TestMoreresult() {
		searchExpression.setText("ford");
		btnSearch.doClick();
		String result = ResultData.getText();
		String ExpectedResult = "Found 7 hits.";
		Assert.assertTrue(result.contains(ExpectedResult));
	}

	@Test
	public void TestAkaresult() {
		searchExpression.setText("\"Ford Model N\"");
		btnSearch.doClick();
		String result = ResultData.getText();
		String ExpectedResult = "aka: Ford Model R Ford Model S";
		Assert.assertTrue(result.contains(ExpectedResult));
	}
	@Test
	public void TestMoreAkaTextresult() {
		searchExpression.setText("blindness");
		btnSearch.doClick();
		String result = ResultData.getText();
		String [] resultSubstring = result.split("Title");
		Assert.assertTrue(resultSubstring.length==3);
	}
}

