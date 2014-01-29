package fsml.parser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;

public class TurnstileParserTest {

	private FSMLSchemaGraph fsg;
	private State locked;
	private State unlocked;
	private State exception;
	
	
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
		
		
	}

	@Test
	public void countTest() {
		assertEquals(3, fsg.getVCount());
		assertEquals(8, fsg.getECount());
	}
	
	@Test
	public void initialTest(){
		assertTrue(locked.is_initial());
	}
	
	@Test
	public void lockedOutTransitionsTest() throws IOException{
		Iterable<Edge> transitions = locked.incidences(EdgeDirection.OUT);
		
		for(Edge e : transitions){
			Transition t = (Transition) e;
			String input = t.get_input();
			switch(input){
			case "ticket" : {
				assertEquals(unlocked, t.getOmega());
				assertEquals("collect", t.get_action());
				continue;
			}
			case "pass" : {
				assertEquals(exception, t.getOmega());
				assertEquals("alarm", t.get_action());
				continue;
			}
			}
			fail("A transition wasn't found!");
		}
	}
	
	@Test
	public void unlockedOutTransitionsTest() throws IOException{
		Iterable<Edge> transitions = unlocked.incidences(EdgeDirection.OUT);
		
		for(Edge e : transitions){
			Transition t = (Transition) e;
			String input = t.get_input();
			switch(input){
			case "ticket" : {
				assertEquals(unlocked, t.getOmega());
				assertEquals("eject", t.get_action());
				continue;
			}
			case "pass" : {
				assertEquals(locked, t.getOmega());
				assertEquals("", t.get_action());
				continue;
			}
			}
			fail("A transition wasn't found");
		}
	}
	
	@Test
	public void exceptionOutTransitionsTest() throws IOException{
		Iterable<Edge> transitions = exception.incidences(EdgeDirection.OUT);
		
		for(Edge e : transitions){
			Transition t = (Transition) e;
			String input = t.get_input();
			switch(input){
			case "ticket" : {
				assertEquals(exception, t.getOmega());
				assertEquals("eject", t.get_action());
				continue;
			}
			case "pass" : {
				assertEquals(exception, t.getOmega());
				assertEquals("", t.get_action());
				continue;
			}
			case "mute" : {
				assertEquals(exception, t.getOmega());
				assertEquals("", t.get_action());
				continue;
			}
			case "release" : {
				assertEquals(locked, t.getOmega());
				assertEquals("", t.get_action());
				continue;
			}
			}
			fail("A transition wasn't found");
		}
	}

}
