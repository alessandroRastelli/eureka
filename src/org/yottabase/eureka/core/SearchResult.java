package org.yottabase.eureka.core;

import java.util.LinkedList;
import java.util.List;

public class SearchResult {

	/**
	 * Numero di item totali riscontrati dalla ricerca
	 */
	private Integer itemsCount;
	
	/**
	 * Numero di pagina della ricerca
	 */
	private Integer page;
	
	/**
	 * Numero di item (reali) presenti nella pagina
	 */
	private Integer itemsInPage;
	
	/**
	 * Items della ricerca
	 */
	private List<WebPageSearchResult> webPages;
	
	/**
	 * Elenco di possibile alternative della query (Forse cercavi)
	 */
	private List<String> suggestedSearches;

	/**
	 * Numero di secondi che sono stati utilizzati per effettuare la ricerca
	 */
	private Double queryResponseTime;
	
	/**
	 * La query realmente eseguita in caso di mispelling
	 */
	private String executedQuery;
	
	/**
	 * Suggerimenti di ricerche correlate
	 */
	private List<String> moreLikeThis;
	
	public SearchResult() {
		this.webPages = new LinkedList<WebPageSearchResult>();
		this.suggestedSearches = new LinkedList<String>();
		this.moreLikeThis = new LinkedList<String>();
	}

	public Integer getItemsCount() {
		return itemsCount;
	}

	public void setItemsCount(Integer itemsCount) {
		this.itemsCount = itemsCount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getItemsInPage() {
		return itemsInPage;
	}

	public void setItemsInPage(Integer itemInPage) {
		this.itemsInPage = itemInPage;
	}

	public List<WebPageSearchResult> getWebPages() {
		return webPages;
	}

	public void setWebPages(List<WebPageSearchResult> webPages) {
		this.webPages = webPages;
	}

	public List<String> getSuggestedSearches() {
		return suggestedSearches;
	}

	public void setSuggestedSearches(List<String> suggestedSearch) {
		this.suggestedSearches = suggestedSearch;
	}

	public Double getQueryResponseTime() {
		return queryResponseTime;
	}

	public void setQueryResponseTime(Double queryResponseTime) {
		this.queryResponseTime = queryResponseTime;
	}
	
	public String getExecutedQuery() {
		return executedQuery;
	}

	public void setExecutedQuery(String executedQuery) {
		this.executedQuery = executedQuery;
	}
	
	public List<String> getMoreLikeThis() {
		return moreLikeThis;
	}

	public void setMoreLikeThis(List<String> moreLikeThis) {
		this.moreLikeThis = moreLikeThis;
	}

	public boolean addWebSearchResult(WebPageSearchResult page) {
		return this.webPages.add(page);
	}
	
	public boolean addSuggestedSearch(String suggestedSearch) {
		return this.suggestedSearches.add(suggestedSearch);
	}
	
	public boolean addMoreLikeThis(String moreLikeThis) {
		return this.moreLikeThis.add(moreLikeThis);
	}

}
