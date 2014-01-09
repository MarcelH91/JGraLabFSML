package fsml.negativeTests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import fsml.parser.FSMLBaseListener;
import fsml.parser.FSMLLexer;
import fsml.parser.FSMLParser;
import fsml.schema.FSMLSchemaGraph;
import fsml.semantics.FSMSimulator;

public class NegativeTests {

	@Test(expected=InputMismatchException.class)
	public void determinismNotOk() throws IOException {
		String file = "testfiles/determinismNotOk.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		
		fsmlp.fsm();
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("tmp/test.input"), StandardCharsets.UTF_8);
		
		
		FSMSimulator sim = new FSMSimulator(fsg, input);
	}
	
	@Test(expected=InputMismatchException.class)
	public void idsNotOk() throws IOException {
		String file = "testfiles/idsNotOk.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		
		fsmlp.fsm();
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("tmp/test.input"), StandardCharsets.UTF_8);
		
		
		FSMSimulator sim = new FSMSimulator(fsg, input);
	}
	
	@Test(expected=InputMismatchException.class)
	public void illegalSymbol() throws IOException {
		String file = "testfiles/sample.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		
		fsmlp.fsm();
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("testfiles/illegalSymbol.input"), StandardCharsets.UTF_8);
		
		
		FSMSimulator sim = new FSMSimulator(fsg, input);
	}
	
	@Test(expected=InputMismatchException.class)
	public void infeasibleSymbol() throws IOException {
		String file = "testfiles/sample.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		
		fsmlp.fsm();
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("testfiles/illegalSymbol.input"), StandardCharsets.UTF_8);
		
		
		FSMSimulator sim = new FSMSimulator(fsg, input);
		sim.computeFSM();
	}
	
	@Test(expected=InputMismatchException.class)
	public void initialNotOk() throws IOException {
		String file = "testfiles/initialNotOk.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		
		fsmlp.fsm();
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("tmp/test.input"), StandardCharsets.UTF_8);
		
		FSMSimulator sim = new FSMSimulator(fsg, input);
	}
	

	@Test(expected=org.antlr.v4.runtime.InputMismatchException.class)
	public void parserError() throws IOException {
		String file = "testfiles/parserError.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		
		fsmlp.fsm();
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("tmp/test.input"), StandardCharsets.UTF_8);
		
		
		FSMSimulator sim = new FSMSimulator(fsg, input);
	}
	
	@Test(expected=InputMismatchException.class)
	public void reachabilityNotOk() throws IOException {
		String file = "testfiles/reachabilityNotOk.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		
		fsmlp.fsm();
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("tmp/test.input"), StandardCharsets.UTF_8);
		
		FSMSimulator sim = new FSMSimulator(fsg, input);
	}
	
	@Test(expected=InputMismatchException.class)
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
		
	}

}
