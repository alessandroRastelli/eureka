package org.yottabase.eureka.core;

public class WebPage {
	
	public final static String URL = "url";
	
	public final static String TITLE = "title";
	
	public final static String CONTENT = "text";
	
	public final static String CONTENT_WITH_TAGS = "fullText";
	
	/**
	 * Url della pagina
	 */
	private String url;

	/**
	 * Titolo
	 */
	private String title;

	/**
	 * Body della pagina html
	 */
	private String content;

	/**
	 * Body della pagina html senza tags
	 */
	private String contentWithTags;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentWithTags() {
		return contentWithTags;
	}

	public void setContentWithTags(String contentWithTags) {
		this.contentWithTags = contentWithTags;
	}
	
	@Override
	public String toString() {
		return "WebPage [url=" + url + ", title=" + title + "]";
	}

}
