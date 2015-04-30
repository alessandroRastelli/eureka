package org.yottabase.eureka.ui.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.yottabase.eureka.core.Searcher;
import org.yottabase.eureka.ui.web.core.Action;


public class ApiAutocompleteAction implements Action {

	public Searcher getSearcher(){
		Searcher searcher;
		
		 //searcher = new org.yottabase.eureka.ui.web.stub.StubSearcher();
		 searcher = new org.yottabase.eureka.searcher.IndexSearch();
		
		return searcher;
	}
	
	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Searcher searcher = this.getSearcher();
		
		String q =  request.getParameter("q");
		if(q == null || q.length() == 0 ){
			response.getWriter().write("Il parametro q è obbligatorio");
			return;
		}
		
		List<String> suggestions = searcher.autocomplete(q);
		JSONArray json = new JSONArray();
		
		for(String suggestion: suggestions){
			JSONObject item = new JSONObject();
			item.put("value", suggestion);
			json.put(item);
		}
		
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}

}
