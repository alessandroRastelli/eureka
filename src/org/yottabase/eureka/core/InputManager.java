package org.yottabase.eureka.core;

public interface InputManager {
	
	/**
	 * Restituisce la prossima WebPage se presente nel complesso dei file .warc di input,
	 * null altrimenti.
	 * 
	 * @param webpage
	 */
	public WebPage getNextWebPage();

}
