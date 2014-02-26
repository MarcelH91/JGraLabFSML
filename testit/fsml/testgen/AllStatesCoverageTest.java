/**
 * 
 */
package fsml.testgen;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import fsml.schema.FSMLSchemaGraph;
import fsml.schema.Transition;
import fsml.semantics.extending.SESimulator;
import fsml.semantics.traverse.TravSimulator;

/**
 * @author Marcel
 *
 */
public class AllStatesCoverageTest {

	private LinkedList<LinkedList<Transition>> genInput;
	private FSMLSchemaGraph graph;
	private AllStatesCoverage generator;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		graph = HeightMachineGraph.createHMGraph();
		generator = new AllStatesCoverage(graph);
		genInput = generator.getFinalInputLists();
		assertTrue(genInput.size()>0);
	}
	
	@Test
	public void testSemantics(){
		for(int i = 0; i < genInput.size(); i++){
			testList(i);
		}
	}


	private void testList(int index) {
		ArrayList<String> inputListtrav = generator.convertTransitionsToStrings(genInput.get(index)); 
		ArrayList<String> inputListSE = generator.convertTransitionsToStrings(genInput.get(index));
		assertEquals(inputListSE.size(), inputListtrav.size());
		TravSimulator ts = new TravSimulator(graph, inputListtrav);
		SESimulator ss = new SESimulator(graph, inputListSE);
		assertEquals(ts.getActive(), ss.getActive());
		int count = inputListSE.size();
		for(int i = 0; i < count; i++){
			ts.computeStep();
			ss.computeStep();
			assertEquals(ts.getActive(), ss.getActive());
		}
	}

}
