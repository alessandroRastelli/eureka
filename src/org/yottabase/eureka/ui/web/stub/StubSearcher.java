package org.yottabase.eureka.ui.web.stub;

import java.util.ArrayList;
import java.util.List;

import org.yottabase.eureka.core.SearchResult;
import org.yottabase.eureka.core.Searcher;
import org.yottabase.eureka.core.WebPageSearchResult;

public class StubSearcher implements Searcher{

	@Override
	public SearchResult search(String query, Integer page, Integer itemInPage) {
		
		List<WebPageSearchResult> webPages = new ArrayList<WebPageSearchResult>();
		webPages.add(new WebPageSearchResult(
			"Christian", 
			"Ciao sono uno snippet", 
			"http://www.google.it"
		));
		
		webPages.add(new WebPageSearchResult(
			"Leonardo", 
			"Ciao sono un professor snippet ", 
			"http://www.google.it/leonardo.proff"
		));
		
		webPages.add(new WebPageSearchResult(
			"Alessandro", 
			"Ciao sono uno snippet assistente del proff Leonardo", 
			"http://www.google.it/leonardo.proff/alessandro"
		));
		
		SearchResult result = new SearchResult();
		result.setPage(page);
		result.setItemsInPage(itemInPage);
		result.setItemsCount(3);
		result.setQueryResponseTime(0.45);
		result.setWebPages(webPages);
		result.setExecutedQuery(query);
		
		result.addSuggestedSearch("Alessandro Magno");
		result.addSuggestedSearch("Giulio Cesare");
		result.addSuggestedSearch("Christian Vadalà");
		
		return result;
	}

	@Override
	public List<String> autocomplete(String query) {
		
		String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California",
        	  "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii",
        	  "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
        	  "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
        	  "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
        	  "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
        	  "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
        	  "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
        	  "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
		};
		
		List<String> results = new ArrayList<String>();
		
		for(String state: states){
			if(state.toLowerCase().startsWith(query.toLowerCase())){
				results.add(state);
			}
		}
		
		return results;
	}

	

}
