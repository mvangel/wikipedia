/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package langlinksfromsql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Daniel
 */
public class LangLinksFromSQLTest {
    
    private static BufferedReader br;
    private static String line;
    
    public LangLinksFromSQLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        String[] args = null;
        LangLinksFromSQL.main(args);
        
        File OutputFile = new File("../data/sample_output.txt");
        FileInputStream fis = new FileInputStream(OutputFile);
        
        br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
        line = br.readLine();
        System.out.println("* @BeforeClass method - sets up the reader");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        br.close();
        System.out.println("* @AfterClass method");
    }

    //@Ignore
    @Test
    public void testOutputIsNotNull() throws Exception {
        System.out.println("* @Test - testOutputIsNotNull method - if it passes, first line of generated output is not null");
        assertNotNull(line);
    }
 
    //@Ignore
    @Test
    public void testIsValidOutputStructure() throws IOException{
        System.out.println("* @Test - testIsValidOutputStructure method - checks first and last char for each line");
        String lines;
        char firstChar,lastChar;
        char expectedFirstChar = '\'';
        char expectedLastChar = ')';
        
        while((lines = br.readLine())!= null){
            firstChar = lines.charAt(0);
            lastChar = lines.charAt(lines.length()-1);
            assertEquals(expectedFirstChar,firstChar);
            assertEquals(expectedLastChar,lastChar);
        }
    }
}
