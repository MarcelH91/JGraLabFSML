package fsml.semantics.extending;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import fsml.exception.FSMInputMismatchException;
import fsml.parser.FSMLBaseListener;
import fsml.parser.FSMLLexer;
import fsml.parser.FSMLParser;
import fsml.schema.FSMLSchemaGraph;

public class NegativeSESimulatorTest {


	
	@Test(expected=FSMInputMismatchException.class)
	public void infeasibleSymbol() throws IOException {
		String file = "testfiles/turnstile.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		fsmlp.fsm();
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("testfiles/illegalSymbol.input"), StandardCharsets.UTF_8);
		SESimulator sim = new SESimulator(fsg, input);
		sim.computeFSM();
	}

}
