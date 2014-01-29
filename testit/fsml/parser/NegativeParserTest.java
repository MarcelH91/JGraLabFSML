package fsml.parser;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import fsml.exception.FSMMismatchException;
import fsml.schema.FSMLSchemaGraph;

public class NegativeParserTest {


	@Test(expected=FSMMismatchException.class)
	public void resolutionNotOk() throws IOException {
		String file = "testfiles/resolutionNotOk.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		
		fsmlp.fsm();
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		assertNotNull(fsg);
		
	}
	

	
	@Test(expected=FSMMismatchException.class)
	public void parserError() throws IOException {
		String file = "testfiles/parserError.fsml";
		
		FSMLBaseListener fsmlbl = FSMLParsing.parse(file);
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		assertNotNull(fsg);
	}
	

}
