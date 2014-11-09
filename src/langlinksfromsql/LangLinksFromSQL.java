/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package langlinksfromsql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;

/**
 *
 * @author Daniel
 */
public class LangLinksFromSQL {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     */
    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException, IOException  {
        String line,lineOfskTitle;
        String language;
        String name;
        
        Writer writer;                                                          /// file output

        HashMap<String, String> Titles = new HashMap<String, String>();         /// ID and title in the hash
        
        String sk_idFromPage;
        String sk_TitleFromPage;
        String huTitleFromPage;
        
        int idLastIndex, nameLastIndex;
        int sk_idLastIndexFromPage, sk_titleFirstIndex, sk_titleLastIndex;
                
        File skLanglinks = new File("../data/sample_input_skwiki-latest-langlinks.sql");       /// input files
        File skTitleID = new File("../data/sample_input_skwiki-latest-page.sql");
        
        FileInputStream fis = new FileInputStream (skLanglinks);
        FileInputStream fis3 = new FileInputStream (skTitleID);
        
        BufferedReader skLang = new BufferedReader(new InputStreamReader(fis, "UTF-8"));    /// readers for files
        BufferedReader skTitle = new BufferedReader(new InputStreamReader(fis3, "UTF-8"));        
        
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("../data/sample_output.txt"), "utf-8"));
        
        while((line = skLang.readLine()) != null){             
            idLastIndex = line.indexOf(',');            
            language = line.substring(idLastIndex+2,idLastIndex+4);             /// get language
            
            if(language.contentEquals("hu")){                                   /// this class is for HU - SK links
                nameLastIndex = line.lastIndexOf(')')-1;
                
                name = line.substring(idLastIndex+7,nameLastIndex);             /// name means the title of wiki article
                Titles.put(line.substring(1, idLastIndex), name);   /// key - getID, value - title in 'language'
            }
        }
        
        while((lineOfskTitle = skTitle.readLine()) != null){                    /// get slovak title as well for found IDs
            sk_idLastIndexFromPage = lineOfskTitle.indexOf(',');
            sk_idFromPage = lineOfskTitle.substring(1, sk_idLastIndexFromPage);
                if(Titles.containsKey(sk_idFromPage)){              /// ID contained both in langlinks and titles.sql
                    sk_titleFirstIndex = lineOfskTitle.indexOf('\'')+1;
                    sk_titleLastIndex = lineOfskTitle.indexOf('\'',sk_titleFirstIndex+1);
                    sk_TitleFromPage = lineOfskTitle.substring(sk_titleFirstIndex, sk_titleLastIndex);
                    sk_TitleFromPage = sk_TitleFromPage.replace("_"," ");       /// convert name to suit format
                    huTitleFromPage = Titles.get(sk_idFromPage);                /// id that matches the slovak version
                    writer.write("'"+sk_TitleFromPage+"' (SK) "+" matches "+"'"+huTitleFromPage+"' (HU)");
                    writer.append('\n');
                }
        }
        
        writer.close();
        
//        while((lineOfhuPage = huLang.readLine()) != null){
//            
//            
//            idLastIndex = lineOfhuPage.indexOf(',');
//            nameLastIndex = lineOfhuPage.lastIndexOf(')')-1;
//            
//            id = lineOfhuPage.substring(1,idLastIndex);
//            language = lineOfhuPage.substring(idLastIndex+2,idLastIndex+4);
//            name = lineOfhuPage.substring(idLastIndex+7,nameLastIndex);
            //System.out.println(id+" "+language+" "+name);
            
            //if(language.contentEquals("sk")){
                //while((lineOfhuTitle = huTitle.readLine()) != null){
                    //hu_idLastIndexFromPage = lineOfhuTitle.indexOf(',');
                    
                    //hu_idFromPage = lineOfhuTitle.substring(1, hu_idLastIndexFromPage);
                    //if(hu_idFromPage.contentEquals(id)){
//                        hu_titleFirstIndex = lineOfhuTitle.indexOf('\'')+1;
//                        hu_titleLastIndex = lineOfhuTitle.indexOf('\'',hu_titleFirstIndex+1);
//                        hu_TitleFromPage = lineOfhuTitle.substring(hu_titleFirstIndex, hu_titleLastIndex);
                        //if(convertedNames.contains(hu_TitleFromPage)){
                            //System.out.println("HU titles s SK linkom: "+hu_TitleFromPage);
                        //}
                    //}                    
                //}
            //}
        //}
    }
    
}
