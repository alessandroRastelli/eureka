package org.yottabase.eureka.indexer;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.yottabase.eureka.core.InputManager;
import org.yottabase.eureka.core.WebPage;
import org.yottabase.eureka.parser.InputManagerImpl;
import org.yottabase.eureka.searcher.SearcherConfiguration;

public class Indexer {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, ParseException {
		List<String> inputPaths;
		InputManager iManager;
		
		Directory index;
		StandardAnalyzer analyzer;
		IndexWriterConfig config;
		IndexWriter writer;
		
		FSDirectory spellDir;
		SpellChecker spellChecker;
		DirectoryReader reader;
		KeywordAnalyzer analyzerSuggest;
		IndexWriterConfig configToDict;
	
		Document doc;
		WebPage webPage;
		
		long start, end, pages;
		double time;
		
		
		System.out.println("Index creation...\n");
		
		inputPaths = new LinkedList<String>();
		inputPaths.add( args[0] );
//		inputPaths.add( args[1] );
		iManager = new InputManagerImpl( inputPaths );
		
		index = FSDirectory.open( new File(SearcherConfiguration.getIndexPath()) );
		analyzer = new StandardAnalyzer(Version.LUCENE_47, CharArraySet.EMPTY_SET);
		config = ( new IndexWriterConfig(Version.LUCENE_47, analyzer) ).setOpenMode(OpenMode.CREATE);
		writer = new IndexWriter(index, config);
		/*
		 * initialize dictionary
		 */
		spellDir = FSDirectory.open(new File(SearcherConfiguration.getDictionaryPath()));
		spellChecker = new SpellChecker(spellDir);
		analyzerSuggest = new KeywordAnalyzer();
	    configToDict = new IndexWriterConfig(Version.LUCENE_47, analyzerSuggest);		
	    
	    
		pages = 0;
		start = System.currentTimeMillis();
		
		while ( (webPage = iManager.getNextWebPage()) != null ) {
			doc = new Document();
			
			Field title = new TextField(WebPage.TITLE, webPage.getTitle(), Field.Store.YES);
			Field content = new TextField(WebPage.CONTENT, webPage.getContent(), Field.Store.YES);
			Field url = new StringField(WebPage.URL, webPage.getUrl(), Field.Store.YES);
			title.setBoost(2f);
			
			doc.add(url);
			doc.add(title);
			doc.add(content);
			
			writer.addDocument(doc);
			pages++;
		}
		
		end = System.currentTimeMillis();
		time = (end - start) / 1000d;
		System.out.println("Creation completed!");

		System.out.println(pages + " pages indexed in " + time + " seconds.\n");
		
		writer.close();
		
		System.out.println("Dictionary creation...\n");
		start = System.currentTimeMillis();

		/*
		 * create Dictionary
		 */
		index = FSDirectory.open( new File(SearcherConfiguration.getIndexPath()) );

	    reader = DirectoryReader.open( index);
		spellChecker.indexDictionary(new LuceneDictionary(reader,WebPage.TITLE ), configToDict, true);
		
		end = System.currentTimeMillis();
		time = (end - start) / 1000d;
		System.out.println("Creation completed in " + time + " seconds.");
	}
	
}
