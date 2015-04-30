package org.yottabase.eureka.searcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.yottabase.eureka.core.SearchResult;
import org.yottabase.eureka.core.WebPageSearchResult;

public class TestSearcher {

	public static void main(String[] args) {
		/*
		 * richiamo la classe SearchIndex per testare il funzionamento
		 * successivamente sarà sostituita dalla chiamata del controller
		 */

		IndexSearch searcher = new IndexSearch();
		SearchResult result = new SearchResult();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean end=true;
      
        while(end){
        	 String inputQuery=null;;
     		try {
     			System.out.println("dammi query");
     			inputQuery= br.readLine();
     			if(inputQuery.equals("exit")){
     				end = false;
     			}
     			else{
     				result = searcher.search(inputQuery, 1, 10);
     	     		
     	     		System.out.println(result.getItemsCount() + " risultati\n");
     	     		System.out.println("Suggested searches: " + result.getSuggestedSearches().toString() + "\n");
     	     		for (WebPageSearchResult page : result.getWebPages())
     	     			System.out.println( page.toString() + "\n" );
     	     		
     			}
     		} catch (IOException e) {
     			e.printStackTrace();
     		}

        }
       
	}
}
