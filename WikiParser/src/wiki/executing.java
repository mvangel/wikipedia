package wiki;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;


public class executing {

	static String strPagesWikiFile;
	static String strTypesFile;
	
	public static void main(String[] args) {
		try
		{
		//open file with articles
		Path fileWiki = Paths.get("C:/Users/Domi/Documents/GitHub/wikipedia/data/sample_skwiki-latest-pages-articles.xml");
		byte[] fileArray;
		fileArray = Files.readAllBytes(fileWiki);
		strPagesWikiFile = new String(fileArray, "UTF-8");
		
		//open file with articles
		Path fileTypes = Paths.get("C:/Users/Domi/Documents/GitHub/wikipedia/data/instance_types_sk.ttl");
		fileArray = Files.readAllBytes(fileTypes);
		strTypesFile = new String(fileArray, "UTF-8");

		//just one sentence or now. Leter there will be whole text read from articles
		//String sentence ="Medzi filozofov bežne spájaných s empirizmom patria tiež [[Aristoteles]], [[Tomáš Akvinský]], [[Francis Bacon]], [[Thomas Hobbes]], [[John Locke]], [[David Hume]] a [[John Stuart Mill]].";
		//String sentence ="Medzi filozofov bežne spájaných s empirizmom patria tiež [[Aristoteles Aristoteles]], [[Tomáš Akvinský]], [[Francis Bacon]], [[Thomas Hobbes]], [[John Locke]], [[David Hume]] a [[John Stuart Mill]].";
		//String sentence = "'''Subjektívny idealizmus''' je [[filozofický smer]], ktorého predstavitelia odmietajú oprávnenost tézy o existencii [[objektívna realita|objektívnej reality]].";
		String sentence = "Svoju teóriu rozvinul v reakcii na [[John Locke|Lockeov]] [[materializmus]]";
		
		List<Sentence> parsedSentences = new ArrayList<Sentence>();
		
		//for each sentence will be done following
		Sentence oneSent = new Sentence();
		oneSent.setFullSent(sentence);
		oneSent.setLinks(getLinks(sentence));
		
		parsedSentences.add(oneSent);
		
		String output = getOutput(parsedSentences);
		Path file = Paths.get("C:/Users/Domi/Documents/GitHub/wikipedia/data/output1.xml");
		byte[] buf = output.getBytes("UTF-8");
		Files.write(file, buf);

		
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * gets all links marked as [[..]] from string sentence
	 * returns List<String>
	 * */
	public static List<Link> getLinks(String sentence)
	{
		List<Link> result = new ArrayList<Link>();
		
		//pattern to match link
		Pattern p = Pattern.compile("\\[{2}[\\w\\s\\|]+\\]{2}");
		Matcher m = p.matcher(sentence);

		List<String> strLinks = new ArrayList<String>();
		while (m.find()) {
			String strFound = m.group();
			strLinks.add(strFound);
			result.add(new Link(strFound));
		}
		
		for(Link l:result)
		{
			getLinkType(l);
		}
		
		return result;
	}
	
	public static Link getLinkType(Link l)
	{
		String linkType = "";
		
		//pattern to match db link in Types file
		Pattern p = Pattern.compile("<{1}" + l.getDbLnk() + ">{1}.*\\.");
		Matcher m = p.matcher(strTypesFile);

		while (m.find()) {
			String strLineFound = m.group();
			String[] parts = strLineFound.split("> <");
			if(parts.length==3)
			{
				String typeLink = parts[2];
				String[] splittedType = typeLink.split("/");
				linkType += splittedType[splittedType.length-1].replace("> .", ", ");
			}
		}
		
		//remove final ", "
		if(linkType.endsWith(", "))
		{
			linkType = linkType.substring(0,linkType.length()-3);
		}
		l.setType(linkType);
		return l;
	}
	
	public static String getOutput(List<Sentence> sentences)
	{
		String strResult = "";
		strResult+="<output>";
		
		for(int i=0;i<sentences.size();i++)
		{
			Sentence currentSent=sentences.get(i);
			strResult+="<sentence id=\"" + Integer.toString(i) + "\">";
			strResult+="<allSent>" + currentSent.getFullSent() + "</allSent>";
			
			for(Link l : currentSent.getLinks())
			{
				strResult+="<linkLabel>" + l.getLabel() + "</linkLabel>";
				strResult+="<lemma>" + l.getLemma() + "</lemma>";
				strResult+="<wklnk>" + l.getWLnk() + "</wklnk>";
				strResult+="<dblnk>" + l.getDbLnk() + "</dblnk>";
				strResult+="<abstract>" + l.getArticleAbstract() + "</abstract>";
				strResult+="<type>" + l.getType() + "</type>";
				strResult+="<vc>" + l.getSyntax() + "</vc>";
			}
			
			strResult+="</sentence>";
		}
		
		strResult+="</output>";
		
		return strResult;
	}

}
