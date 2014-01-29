package fsml.semantics;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import fsml.exception.FSMConstraintCheckException;
import fsml.exception.FSMInputMismatchException;
import fsml.parser.FSMLBaseListener;
import fsml.parser.FSMLLexer;
import fsml.parser.FSMLParser;
import fsml.schema.FSMLSchemaGraph;
import fsml.semantics.extending.SESimulator;
import fsml.semantics.traverse.TravSimulator;

public class NegativeConstraintTest {

	@Test(expected=FSMInputMismatchException.class)
	public void illegalSymbol() throws IOException {
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
		
		
		TravSimulator sim = new TravSimulator(fsg, input);
		assertNotNull(sim.getActive());
	}
	
	@Test(expected=FSMConstraintCheckException.class)
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
		
		
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("testfiles/test.input"), StandardCharsets.UTF_8);
		
		
		TravSimulator sim = new TravSimulator(fsg, input);
		assertNotNull(sim.getActive());
	}
	
	@Test(expected=FSMConstraintCheckException.class)
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
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("testfiles/test.input"), StandardCharsets.UTF_8);
		
		TravSimulator sim = new TravSimulator(fsg, input);
		assertNotNull(sim.getActive());
	}
	
	@Test(expected=FSMConstraintCheckException.class)
	public void initialTwice() throws IOException {
		String file = "testfiles/initialNotOk.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		
		fsmlp.fsm();
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("testfiles/test.input"), StandardCharsets.UTF_8);
		
		TravSimulator sim = new TravSimulator(fsg, input);
		assertNotNull(sim.getActive());
	}
	
	@Test(expected=FSMConstraintCheckException.class)
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
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("testfiles/test.input"), StandardCharsets.UTF_8);
		
		SESimulator sim = new SESimulator(fsg, input);
		assertNotNull(sim.getActive());
	}

}
