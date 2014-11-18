package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 * 
 * <p>
 * Class that implements searching and indexing logic for Simple dictionary.
 * </p>
 * <p>
 * Simple dictionary searches given input and returns translated result. As an
 * input can be used any of known dbpedia terms. It is not required that all
 * word have to have corresponding match in another language. If translation of
 * given input cannot be found, result contains information about it.
 * </p>
 * 
 * @author Pidanic
 *
 */
public class LuceneSimpleDictionary extends LuceneDbpediaDictionary
{
    @Override
    public List<String> translate(Language from, Language to, String searchText)
            throws IOException, ParseException
    {
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);

        QueryParser qp = new QueryParser(from.toString(), analyzer);
        Query query = qp.parse(searchText);
        TopDocs topDocs = isearcher.search(query, 10);
        ScoreDoc[] hits = topDocs.scoreDocs;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < hits.length; i++)
        {
            Document hitDoc = isearcher.doc(hits[i].doc);
            StringBuilder resultLine = new StringBuilder();
            resultLine.append(hitDoc.get(from.toString()));
            resultLine.append(" -> ");

            String id = hitDoc.get(from.toString() + "id");
            QueryParser parser = new QueryParser(to.toString() + "id", analyzer);
            Query query2 = parser.parse(id);
            TopDocs another = isearcher.search(query2, 1);
            ScoreDoc[] hits2 = another.scoreDocs;
            if(hits2.length > 0)
            {
                Document hitDoc2 = isearcher.doc(hits2[0].doc);
                String word = hitDoc2.get(to.toString());
                resultLine.append(word);
            }
            else
            {
                resultLine.append("Neexistuje preklad");
            }
            result.add(resultLine.toString());
        }
        ireader.close();

        return result;
    }

    @Override
    protected void initializeDictionaryData()
    {
        BufferedReader brEn = null;
        BufferedReader brDe = null;
        BufferedReader brSk = null;
        BufferedReader brFr = null;
        try
        {
            analyzer = new StandardAnalyzer();
            directory = new RAMDirectory();
            IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,
                    analyzer);
            IndexWriter iwriter = new IndexWriter(directory, config);

            // TODO
            // File en = new File("temp\\en.csv");
            // File de = new File("temp\\de.csv");
            // File sk = new File("temp\\sk.csv");
            // File fr = new File("temp\\fr.csv");
            File en = new File("data" + File.separator
                    + "sample_output_interlanguage_links_en.csv");
            File de = new File("data" + File.separator
                    + "sample_output_interlanguage_links_de.csv");
            File sk = new File("data" + File.separator
                    + "sample_output_interlanguage_links_sk.csv");
            File fr = new File("data" + File.separator
                    + "sample_output_interlanguage_links_fr.csv");

            brEn = new BufferedReader(new FileReader(en));
            String line = null;
            while ((line = brEn.readLine()) != null)
            {
                Document doc = new Document();
                String[] words = line.split(",");
                if(words.length >= 2)
                {
                    doc.add(new Field(Language.EN.toString() + "id", words[0],
                            TextField.TYPE_STORED));
                    doc.add(new Field(Language.EN.toString(), LinksUtil
                            .makeWords(LinksUtil.parseWord(words[1])),
                            TextField.TYPE_STORED));
                    iwriter.addDocument(doc);
                }
            }

            brFr = new BufferedReader(new FileReader(fr));
            while ((line = brFr.readLine()) != null)
            {
                Document doc = new Document();
                String[] words = line.split(",");
                if(words.length >= 2)
                {
                    doc.add(new Field(Language.FR.toString() + "id", words[0],
                            TextField.TYPE_STORED));
                    doc.add(new Field(Language.FR.toString(), LinksUtil
                            .makeWords(LinksUtil.parseWord(words[1])),
                            TextField.TYPE_STORED));
                    iwriter.addDocument(doc);
                }
            }

            brDe = new BufferedReader(new FileReader(de));
            while ((line = brDe.readLine()) != null)
            {
                Document doc = new Document();
                String[] words = line.split(",");
                if(words.length >= 2)
                {
                    doc.add(new Field(Language.DE.toString() + "id", words[0],
                            TextField.TYPE_STORED));
                    doc.add(new Field(Language.DE.toString(), LinksUtil
                            .makeWords(LinksUtil.parseWord(words[1])),
                            TextField.TYPE_STORED));
                    iwriter.addDocument(doc);
                }
            }

            brSk = new BufferedReader(new FileReader(sk));
            while ((line = brSk.readLine()) != null)
            {
                Document doc = new Document();
                String[] words = line.split(",");
                if(words.length >= 2)
                {
                    doc.add(new Field(Language.SK.toString() + "id", words[0],
                            TextField.TYPE_STORED));
                    doc.add(new Field(Language.SK.toString(), LinksUtil
                            .makeWords(LinksUtil.parseWord(words[1])),
                            TextField.TYPE_STORED));
                    iwriter.addDocument(doc);
                }
            }

            iwriter.close();
        }
        catch (FileNotFoundException fnf)
        {
            System.err.println("File not found " + fnf.getMessage());
        }
        catch (IOException io)
        {
            System.err.println("IoException " + io.getMessage());
        }
        finally
        {
            try
            {
                if(brEn != null)
                {
                    brEn.close();
                }
                if(brDe != null)
                {
                    brDe.close();
                }
                if(brFr != null)
                {
                    brFr.close();
                }
                if(brSk != null)
                {
                    brSk.close();
                }
            }
            catch (IOException e)
            {
                System.err.println("Problem while closing file "
                        + e.getMessage());
            }
        }
    }
}
