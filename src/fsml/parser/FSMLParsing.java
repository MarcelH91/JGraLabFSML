package fsml.parser;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class FSMLParsing {

	/**
	 * Parses the file and returns a FSMLBaseListener-Object
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static FSMLBaseListener parse(String file) throws IOException{
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		
		fsmlp.fsm();
		
		return fsmlbl;
		
	}
}
