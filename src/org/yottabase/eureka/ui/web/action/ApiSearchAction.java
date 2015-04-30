package org.yottabase.eureka.ui.web.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.yottabase.eureka.core.SearchResult;
import org.yottabase.eureka.core.Searcher;
import org.yottabase.eureka.core.WebPageSearchResult;
import org.yottabase.eureka.ui.web.core.Action;

public class ApiSearchAction implements Action{

	final int itemInPage = 10;
	
	public Searcher getSearcher(){
		Searcher searcher;
		
		 //searcher = new org.yottabase.eureka.ui.web.stub.StubSearcher();
		 searcher = new org.yottabase.eureka.searcher.IndexSearch();
		
		return searcher;
	}
	
	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String q =  request.getParameter("q");
		if(q == null || q.length() == 0 ){
			response.getWriter().write("Il parametro q è obbligatorio");
			return;
		}
		
		String pageParam = request.getParameter("page");
		if(pageParam == null || pageParam.length() == 0 ){
			response.getWriter().write("Il parametro page è obbligatorio");
			return;
		}
		Integer page = Integer.parseInt(pageParam);
		
		Searcher searcher = this.getSearcher();
		SearchResult result = searcher.search(q, page, this.itemInPage);
		
		JSONObject json = new JSONObject();
		json.put("itemsCount", result.getItemsCount());
		json.put("page", result.getPage());
		json.put("itemsInPage", result.getItemsInPage());
		JSONArray suggestedSearch = new JSONArray(result.getSuggestedSearches().toArray());
		json.put("suggestedSearch", suggestedSearch);
		json.put("queryResponseTime", result.getQueryResponseTime());
		JSONArray moreLikeThis = new JSONArray(result.getMoreLikeThis().toArray());
		json.put("moreLikeThis", moreLikeThis);
		
		JSONArray items = new JSONArray();
		json.put("webPages", items);
		for(WebPageSearchResult webPage : result.getWebPages()){
			JSONObject item = new JSONObject();
			item.put("title", webPage.getTitle());
			item.put("highlightedSnippet", webPage.getHighlightedSnippet());
			item.put("url", webPage.getUrl());
			
			items.put(item);
		}
		
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}

}
