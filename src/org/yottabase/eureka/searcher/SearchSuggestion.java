package org.yottabase.eureka.searcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.Lookup.LookupResult;
import org.apache.lucene.search.suggest.tst.TSTLookup;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.yottabase.eureka.core.WebPage;

public class SearchSuggestion {
	private Directory spellDir;
	private SpellChecker spellChecker;
	
	public List<String> autocomplete(String query) {
		List<String> result = new ArrayList<String>();
		
		String prefix = "";
		
		query = query.trim();
		
		if(query.contains(" ")) {
			prefix = query.substring(0, query.lastIndexOf(' ')) + ' ';
			query = query.substring(query.lastIndexOf(' ') + 1);
		}
		
		try {
			Lookup lookup = new TSTLookup();
			Directory indexPathDir = FSDirectory.open(new File(SearcherConfiguration.getIndexPath()));
			IndexReader ir = DirectoryReader.open(indexPathDir);
			
			Dictionary dictionary = new LuceneDictionary(ir, WebPage.TITLE);
			
			lookup.build(dictionary);
			
			List<LookupResult> resultsList = lookup.lookup(query, true, 30);
			
			for(LookupResult lr : resultsList){
				String v = lr.key.toString();
				if( !v.equals(query) ){
					result.add(prefix + v);
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public ArrayList<String> didYouMean(String queryString){
		
		ArrayList<String> similarWords = new ArrayList<String>();
		String similarWordsConcat = "";
		String listSuggest[]=null;

		try {
			spellDir = FSDirectory.open(new File(SearcherConfiguration.getDictionaryPath()));
			spellChecker = new SpellChecker(spellDir);
			spellChecker.setAccuracy(0.8f);
			
			/*
			 * se query è composta da più keywords
			 */
			if (queryString.contains(" ")) {
			    boolean found = false;
			    
			    String[] parts = queryString.split(" ");
			   
			    for (int i = 0; i < parts.length; i++) {
					listSuggest = spellChecker.suggestSimilar(parts[i], 10);
					if ( listSuggest.length > 0 ) {
					    found = true;
						similarWordsConcat += (listSuggest[listSuggest.length-1]);
						similarWordsConcat += " ";
					}
				}
			    if(found)
			    	Collections.addAll(similarWords,similarWordsConcat);

			} else{
				listSuggest = spellChecker.suggestSimilar(queryString, 50);
				
				int last = listSuggest.length - 1;
				if (listSuggest.length > 1) {
					if ( (listSuggest[last]).equals(queryString) )
						last--;
					
					similarWords.add(listSuggest[0]);
				}
			}
			
	        spellChecker.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return similarWords;
	}

}
