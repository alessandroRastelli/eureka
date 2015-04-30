package org.yottabase.eureka.core;

public class WebPageSearchResult {

	/**
	 * Titolo
	 */
	private String title;

	/**
	 * Pezzo di pagina che contiene il risultato della query
	 */
	private String highlightedSnippet;

	/**
	 * Url della pagina
	 */
	private String url;
	
	public WebPageSearchResult() {
		super();
	}

	public WebPageSearchResult(String title, String highlightedSnippet, String url) {
		this.title = title;
		this.highlightedSnippet = highlightedSnippet;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHighlightedSnippet() {
		return highlightedSnippet;
	}

	public void setHighlightedSnippet(String snippet) {
		this.highlightedSnippet = snippet;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "WebPageSearchResult" + "\n" +
				"\t" + "TITLE" + "\t\t" + title + "\n" +
				"\t" + "SNIPPET" + "\t\t" + highlightedSnippet + "\n" +
				"\t" + "URL" + "\t\t" + url + "\n";
	}
	

}
