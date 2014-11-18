package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class for creating corpus of different types of dictionaries from all parsed
 * data.
 * 
 * @author Pidanic
 *
 */
public class DictionaryCorpusCreator
{
    private static final String SK_PATH = "data" + File.separator + "temp"
            + File.separator + "sample_output_dbpedia_wikipedia_links_sk.csv";

    private static final String EN_PATH = "data" + File.separator + "temp"
            + File.separator + "sample_output_dbpedia_wikipedia_links_en.csv";

    private static final String DE_PATH = "data" + File.separator + "temp"
            + File.separator + "sample_output_dbpedia_wikipedia_links_de.csv";

    private static final String FR_PATH = "data" + File.separator + "temp"
            + File.separator + "sample_output_dbpedia_wikipedia_links_fr.csv";

    private static final File[] FILES_INTERLANGUAGE;
    static
    {
        FILES_INTERLANGUAGE = new File[4];
        FILES_INTERLANGUAGE[0] = new File("data" + File.separator
                + "sample_interlanguage_links_fr.ttl");
        FILES_INTERLANGUAGE[1] = new File("data" + File.separator
                + "sample_interlanguage_links_de.ttl");
        FILES_INTERLANGUAGE[2] = new File("data" + File.separator
                + "sample_interlanguage_links_en.ttl");
        FILES_INTERLANGUAGE[3] = new File("data" + File.separator
                + "sample_interlanguage_links_sk.ttl");
    }

    private static final File[] FILES_WIKILINKS;
    static
    {
        FILES_WIKILINKS = new File[4];
        FILES_WIKILINKS[0] = new File("data" + File.separator
                + "sample_wikipedia_links_fr.ttl");
        FILES_WIKILINKS[1] = new File("data" + File.separator
                + "sample_wikipedia_links_de.ttl");
        FILES_WIKILINKS[2] = new File("data" + File.separator
                + "sample_wikipedia_links_en.ttl");
        FILES_WIKILINKS[3] = new File("data" + File.separator
                + "sample_wikipedia_links_sk.ttl");
    }

    /**
     * <p>
     * Creates corpus of enhanced dictionary.
     * </p>
     * <p>
     * Parses all interlanguage_link and wikipedia_links input files, and
     * matches parsed word through temporary files until required corpus file is
     * created.
     * </p>
     * <p>
     * Because of memory requirements this was done with temporary files.
     * </p>
     * 
     * @throws IOException
     */
    public void createEnhancedDictionary() throws IOException
    {
        File tempDir = new File("data" + File.separator + "temp");
        tempDir.mkdir();

        parseAll();

        matchAll();

        Map<String, Map<String, String>> result = new HashMap<>();

        BufferedReader sk = new BufferedReader(
                new FileReader(new File(SK_PATH)));
        String line = null;
        while ((line = sk.readLine()) != null)
        {
            String[] tokens = line.split(",");
            String key = tokens[0];
            Map<String, String> value = new LinkedHashMap<>();
            value.put(tokens[1], tokens[2]);
            result.put(key, value);
        }
        sk.close();

        BufferedReader fr = new BufferedReader(
                new FileReader(new File(FR_PATH)));
        String frLine = null;
        while ((frLine = fr.readLine()) != null)
        {
            String[] tokens = frLine.split(",");
            String key = tokens[0];
            if(result.containsKey(key))
            {
                result.get(key).put(tokens[1], tokens[2]);
            }
        }
        fr.close();

        BufferedReader en = new BufferedReader(
                new FileReader(new File(EN_PATH)));
        String enLine = null;
        while ((enLine = en.readLine()) != null)
        {
            String[] tokens = enLine.split(",");
            String key = tokens[0];
            if(result.containsKey(key))
            {
                result.get(key).put(tokens[1], tokens[2]);
            }
        }
        en.close();

        BufferedReader de = new BufferedReader(
                new FileReader(new File(DE_PATH)));
        String deLine = null;
        while ((deLine = de.readLine()) != null)
        {
            String[] tokens = deLine.split(",");
            String key = tokens[0];
            if(result.containsKey(key))
            {
                result.get(key).put(tokens[1], tokens[2]);
            }
        }
        de.close();

        BufferedWriter dictionary = new BufferedWriter(new FileWriter(new File(
                "data" + File.separator + "temp" + File.separator
                        + "sample_dictonary.csv")));
        for (Map.Entry<String, Map<String, String>> map : result.entrySet())
        {
            if(map.getValue().size() == 4)
            {
                StringBuilder dictLine = new StringBuilder();
                for (Map.Entry<String, String> entry : map.getValue()
                        .entrySet())
                {
                    dictLine.append(LinksUtil.parseWord(LinksUtil
                            .makeWords(entry.getKey())));
                    dictLine.append(",");
                    dictLine.append(entry.getValue());
                    dictLine.append(",");
                }
                dictLine.replace(dictLine.lastIndexOf(","), dictLine.length(),
                        "\n");
                dictionary.write(dictLine.toString());
            }
        }
        dictionary.flush();
        dictionary.close();

        for (File temp : tempDir.listFiles())
        {
            temp.delete();
        }
        tempDir.delete();
    }

    private static void parseAll() throws IOException
    {
        DbpediaParser parser = new InterlanguageLinksParser();
        parser.parseToFile(FILES_INTERLANGUAGE[0], new File("data"
                + File.separator + "temp" + File.separator
                + "sample_output_interlanguage_links_fr.csv"));
        parser.parseToFile(FILES_INTERLANGUAGE[1], new File("data"
                + File.separator + "temp" + File.separator
                + "sample_output_interlanguage_links_de.csv"));
        parser.parseToFile(FILES_INTERLANGUAGE[2], new File("data"
                + File.separator + "temp" + File.separator
                + "sample_output_interlanguage_links_en.csv"));
        parser.parseToFile(FILES_INTERLANGUAGE[3], new File("data"
                + File.separator + "temp" + File.separator
                + "sample_output_interlanguage_links_sk.csv"));

        parser = new WikipediaLinksParser();
        parser.parseToFile(FILES_WIKILINKS[0], new File("data" + File.separator
                + "temp" + File.separator
                + "sample_output_wikipedia_links_fr.csv"));

        parser.parseToFile(FILES_WIKILINKS[1], new File("data" + File.separator
                + "temp" + File.separator
                + "sample_output_wikipedia_links_de.csv"));

        parser.parseToFile(FILES_WIKILINKS[2], new File("data" + File.separator
                + "temp" + File.separator
                + "sample_output_wikipedia_links_en.csv"));

        parser.parseToFile(FILES_WIKILINKS[3], new File("data" + File.separator
                + "temp" + File.separator
                + "sample_output_wikipedia_links_sk.csv"));
    }

    private static void matchAll() throws IOException
    {
        InterlanguageWikipediaLinksMatcher match = new InterlanguageWikipediaLinksMatcher();
        match.createDbpediaWikipediaMapping("data" + File.separator + "temp"
                + File.separator + "sample_output_interlanguage_links_fr.csv",
                "temp" + File.separator
                        + "sample_output_wikipedia_links_fr.csv");
        match.createDbpediaWikipediaMapping("data" + File.separator + "temp"
                + File.separator + "sample_output_interlanguage_links_fr.csv",
                "temp" + File.separator
                        + "sample_output_wikipedia_links_fr.csv");
        match.createDbpediaWikipediaMapping("data" + File.separator + "temp"
                + File.separator + "sample_output_interlanguage_links_de.csv",
                "temp" + File.separator
                        + "sample_output_wikipedia_links_de.csv");
        match.createDbpediaWikipediaMapping("data" + File.separator + "temp"
                + File.separator + "sample_output_interlanguage_links_sk.csv",
                "temp" + File.separator
                        + "sample_output_wikipedia_links_sk.csv");
        match.createDbpediaWikipediaMapping("data" + File.separator + "temp"
                + File.separator + "sample_output_interlanguage_links_en.csv",
                "temp" + File.separator
                        + "sample_output_wikipedia_links_en.csv");
    }

    // public void createSimpleDictionary() throws IOException
    // {
    // parseToFile(FILES_INTERLANGUAGE[0], new File("data" + File.separator
    // + "fr.csv"));
    // parseToFile(FILES_INTERLANGUAGE[1], new File("data" + File.separator
    // + "de.csv"));
    // parseToFile(FILES_INTERLANGUAGE[2], new File("data" + File.separator
    // + "en.csv"));
    // parseToFile(FILES_INTERLANGUAGE[3], new File("data" + File.separator
    // + "sk.csv"));
    // }
    //
    // private static void parseToFile(File from, File to) throws IOException
    // {
    // BufferedReader br = new BufferedReader(new FileReader(from));
    // String line = null;
    // String lastResource = "";
    // BufferedWriter bw = new BufferedWriter(new FileWriter(to));
    // while ((line = br.readLine()) != null)
    // {
    // if(!line.startsWith("#"))
    // {
    // String[] resources = line.split("\\s+");
    // String resource = LinksUtil.removeBrackets(resources[0]);
    // String idResource = LinksUtil.removeBrackets(resources[2]);
    // if(!lastResource.equals(resource))
    // {
    // String id = LinksUtil.parseWord(idResource);
    // resource = resource.replaceAll(",", "_");
    // String outline = id
    // + ","
    // + LinksUtil
    // .makeWords(LinksUtil.parseWord(resource))
    // + "\n";
    // bw.write(outline);
    // lastResource = resource;
    // }
    // }
    // }
    // bw.flush();
    // bw.close();
    // br.close();
    // }
}
