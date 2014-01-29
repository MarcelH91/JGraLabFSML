package fsml.semantics.extending;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import fsml.parser.FSMLBaseListener;
import fsml.parser.FSMLLexer;
import fsml.parser.FSMLParser;
import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;

public class TurnstileSESimulatorTest {
	private FSMLSchemaGraph fsg;
	private State locked;
	private State unlocked;
	private State exception;
	private SESimulator sesim;
	
	@Before
	public void setUp() throws Exception {
		String file = "testfiles/turnstile.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		fsmlp.fsm();
		fsg = fsmlbl.getGraph();
		
		Iterable<State> states = fsg.getStateVertices();
		for(State s : states){
			String id = s.get_id();
			switch(id){
			case "locked" : locked = s; 
			case "unlocked" : unlocked = s;
			case "exception" : exception = s;
			}
		}
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("testfiles/test.input"), StandardCharsets.UTF_8);
		
		sesim = new SESimulator(fsg, input);
		
		
	}

	@Test
	public void testConfigurations() {
		sesim.computeStep();
		assertEquals("collect", sesim.getAction());
		assertEquals(unlocked, sesim.getActive());
		sesim.computeStep();
		assertEquals("", sesim.getAction());
		assertEquals(locked, sesim.getActive());
		sesim.computeStep();
		assertEquals("collect", sesim.getAction());
		assertEquals(unlocked, sesim.getActive());
		sesim.computeStep();
		assertEquals("", sesim.getAction());
		assertEquals(locked, sesim.getActive());
		sesim.computeStep();
		assertEquals("collect", sesim.getAction());
		assertEquals(unlocked, sesim.getActive());
		sesim.computeStep();
		assertEquals("eject", sesim.getAction());
		assertEquals(unlocked, sesim.getActive());
		sesim.computeStep();
		assertEquals("", sesim.getAction());
		assertEquals(locked, sesim.getActive());
		sesim.computeStep();
		assertEquals("alarm", sesim.getAction());
		assertEquals(exception, sesim.getActive());
		sesim.computeStep();
		assertEquals("eject", sesim.getAction());
		assertEquals(exception, sesim.getActive());
		sesim.computeStep();
		assertEquals("", sesim.getAction());
		assertEquals(exception, sesim.getActive());
		sesim.computeStep();
		assertEquals("", sesim.getAction());
		assertEquals(exception, sesim.getActive());
		sesim.computeStep();
		assertEquals("", sesim.getAction());
		assertEquals(locked, sesim.getActive());
		sesim.computeStep();
		assertEquals("collect", sesim.getAction());
		assertEquals(unlocked, sesim.getActive());
		sesim.computeStep();
		assertEquals("", sesim.getAction());
		assertEquals(locked, sesim.getActive());
		sesim.computeFSM();
		assertEquals(16, sesim.getOutput().size());
	}

}
