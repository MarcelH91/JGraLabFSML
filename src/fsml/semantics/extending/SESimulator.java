package fsml.semantics.extending;

import java.util.ArrayList;
import java.util.List;

import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;
import fsml.semantics.ConstraintChecker;

public class SESimulator {

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
	public SESimulator(FSMLSchemaGraph graph, ArrayList<String> input) {
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
	 * Instead of traversing the graph the active state calculates
	 * the next active transition based on the trigger.
	 */
	public void computeStep(){
		String trigger = input.get(0);
		Transition t = active.getNextTransition(trigger);
		action = t.get_action();
		active=t.getOmega();
		output.add(t.getOutput());
		input.remove(0);
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


	public State getActive() {
		return active;
	}


	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}


	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

}

