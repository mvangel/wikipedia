package mergingInflectedFormNE;

import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import testAndTools.StringComparatorBaseOnAlphabeticalOrder;
import testAndTools.StringTools;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;


// TODO: Auto-generated Javadoc
/**
 * The Class MergerOfInflectedForm.
 */
public class MergerOfInflectedForm {

/** The Anchor list. */
private LinkedList<String> AnchorList;

/** The named entity list. */
private LinkedList<NamedEntity> namedEntityList;

/**
 * Instantiates a new merger of inflected form.
 *
 * @param laList the la list
 */
public MergerOfInflectedForm(LinkedList<LinkAnchor> laList){
	AnchorList = new LinkedList<String>();
	//System.out.println(laList.size());
	for(LinkAnchor la : laList){
		//System.out.println(la.getCleanLink() + "   " + la.getAnchor());
		AnchorList.add(la.getCleanLink());
		AnchorList.add(la.getAnchor());
		//System.out.println(la.getLinkAddInfo());
	}
	Collections.sort(AnchorList, new StringComparatorBaseOnAlphabeticalOrder());
	StringTools st = new StringTools();
	st.uniq(AnchorList);
	
	namedEntityList = new LinkedList<NamedEntity>();
	
	//this.printAnchorList();
	anchorTextInflectedFormMerge();
	//namedEntityList.add(new NamedEntity("Adam B�och"));
	//System.out.println(new NamedEntity("Adam B�och").compare("Adam B�och"));
}

/**
 * Anchor text inflected form merge.
 */
private void anchorTextInflectedFormMerge(){
	boolean merged = false;
	double maxSimilarity=0;
	int indexOfMaxSimilarity=-1;
	int index = 0;
	double pomSimilarity = 0; 
	
	for(String str : AnchorList){
		merged = false;
		pomSimilarity = 0; 
		index = 0;
		indexOfMaxSimilarity=-1;
		maxSimilarity=0;
		
		for(NamedEntity ne : this.namedEntityList){
			pomSimilarity = ne.compare(str);
			if(pomSimilarity == 1){		// if similarity == 1 it means that string are identical
				merged = true;
				break;
			}
			else if(maxSimilarity < pomSimilarity){
				maxSimilarity = pomSimilarity;
				indexOfMaxSimilarity = index;				
			}
			index++;
			//System.out.println(ne.compare(str) + "   " + str + "    " + ne.getInflectedForms().get(0));
		}
		if(!merged && (maxSimilarity > 0.5)){		// threshold of which strings are consider as similar is 0.5
			NamedEntity pomNE = namedEntityList.get(indexOfMaxSimilarity);
			pomNE.addInflectedForm(str);
		}
		else if(!merged){
			namedEntityList.add(new NamedEntity(str));
		}
	}
}

/**
 * Prints the anchor list.
 */
public void printAnchorList(){
	for(String s : this.AnchorList){
		System.out.println(s);
	}
}

/**
 * Gets the named entity list.
 *
 * @return the named entity list
 */
public LinkedList<NamedEntity> getNamedEntityList(){
	return this.namedEntityList;
}

}
