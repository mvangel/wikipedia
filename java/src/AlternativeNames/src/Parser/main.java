package Parser;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * Spuscacia trieda
 * @author Spred
 *
 */
public class main {
	public static GUI gu;
	public static void main(String[] args) throws IOException {
		gu = new GUI();
		gu.createGUI();
		gu.setVisible(true);
		gu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gu.setSize(1000,1000);
	}
	
}