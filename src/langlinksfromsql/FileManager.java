package langlinksfromsql;
/**
 *
 * @author Daniel
 */
public class FileManager {
    public static String constructFilePath(String Language, boolean isLangLink, boolean realSample){
        
        StringBuilder file = new StringBuilder();
        String fileName;
        
        /// construct file pathname
        file.append("../data/");                                     
        if(!realSample) file.append("sample_input_");
        file.append(Language).append("wiki-latest-");
        if(isLangLink) file.append("langlinks.sql");   
        else file.append("page.sql");
        /// construct file pathname
        
        fileName = file.toString();
        return fileName;
    }
}
