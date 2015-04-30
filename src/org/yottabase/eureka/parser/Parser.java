package org.yottabase.eureka.parser;

import org.jwat.warc.WarcRecord;

public interface Parser {
	
	public WarcRecord getNextRecord();
	
	public void close();

}
