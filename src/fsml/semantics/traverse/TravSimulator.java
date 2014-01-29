package fsml.semantics.traverse;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import fsml.exception.FSMTriggerNotFoundException;
import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;
import fsml.semantics.ConstraintChecker;

public class TravSimulator {

	private FSMLSchemaGraph fsmGraph;
	private State active;
	private String action;
	private ArrayList<String> input;
	private List<String> output;
	
	/**
	 * creates a simulator-object, which holds the FSM's current configuration
	 * using operational semantics.
	 * @param graph
	 * @param input
	 */
	public TravSimulator(FSMLSchemaGraph graph, ArrayList<String> input) {
		this.fsmGraph = graph;
		this.input = input;
		this.output = new ArrayList<String>();
		computeInitialConfiguration();
	}

	
	private void computeInitialConfiguration() {
		ConstraintChecker cc = new ConstraintChecker();
		cc.run(fsmGraph, input);
		Iterable<State> states = fsmGraph.getStateVertices();
		for(State s : states){
			if(s.is_initial())
				active = s;
		}
		action = "";
		output.add("[");
	}
	


	/**
	 * Calculates the next active step and an output based
	 * on the first entry in input. Afterwards the first entry
	 * in input is deleted.
	 * 
	 * The next active step is calculated by traversing all
	 * outgoing transitions from the active state.
	 */
	public void computeStep(){
		String trigger = input.get(0);
		Iterable<Edge> transitions = active.incidences(EdgeDirection.OUT);
		boolean transitionFound = false;
		for(Edge e : transitions){
			Transition t = (Transition) e;
			if(trigger.equals(t.get_input())){
				transitionFound = true;
				if(t.get_action().length()>0){
					action = t.get_action();
				}else
					action = "";
				active = t.getOmega();
				break;
			}
		}
		if(!transitionFound)
			throw new FSMTriggerNotFoundException("The input contains " +
					"an invalid element:"+trigger);
		input.remove(0);
		output.add("    (["+action+"], "+active.get_id()+")");
	}
	
	/**
	 * Simulates the whole FSM based on the given input by
	 * calling computeStep until input is empty.
	 */
	public void computeFSM(){
		while(input.size()>0)
			computeStep();
		output.add("]");
	}


	public List<String> getOutput() {
		return output;
	}


	public String getAction() {
		return action;
	}


	public State getActive() {
		return active;
	}

}
