package fsml.semantics;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;

public class FSMSimulator {

	private FSMLSchemaGraph fsmGraph;
	private State active;
	private String action;
	private ArrayList<String> input;
	private List<String> output;
	
	public FSMSimulator(FSMLSchemaGraph graph, ArrayList<String> input) {
		this.fsmGraph = graph;
		this.input = input;
		this.output = new ArrayList<String>();
		computeInitialConfiguration();
	}

	
	private void computeInitialConfiguration() {
		checkConsistency();
		Iterable<State> states = fsmGraph.getStateVertices();
		for(State s : states){
			if(s.is_initial())
				active = s;
		}
		if(null == active)
			throw new NullPointerException();
		action = "";
		output.add("Active = "+active.get_id() + "| Action = "+action);
	}
	
	private void checkConsistency() {

		ConstraintChecker cc = new ConstraintChecker();
		if(!cc.checkAllConstraints(fsmGraph))
			throw new InputMismatchException("The graph is inconsistent!");
		
		for(String i : input){
			String cstr = "exists trans : E{Transition}@ trans.input = \""+i+"\"";
			if(!cc.checkGraphConstraint(fsmGraph, cstr))
				throw new InputMismatchException("The input contains invalid elements");
		}
		
	}


	public void computeStep(){
		String trigger = input.get(0);
		Iterable<Edge> transitions = active.incidences(EdgeDirection.OUT);
		boolean transitionFound = false;
		for(Edge e : transitions){
			transitionFound = true;
			Transition t = (Transition) e;
			if(trigger.equals(t.get_input())){
				if(t.get_action().length()>0){
					action = t.get_action();
				}else
					action = "";
				active = t.getOmega();
			}
		}
		if(!transitionFound)
			throw new InputMismatchException("The input contains an invalid element:"+trigger);
		input.remove(0);
		output.add("Active = "+active.get_id() + "| Action = "+action);
	}
	
	public void computeFSM(){
		while(input.size()>0)
			computeStep();
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
