package fsml.testgen;

import java.util.ArrayList;
import java.util.LinkedList;

import fsml.schema.FSMLSchemaGraph;
import fsml.schema.Transition;

public abstract class Coverage {
	protected FSMLSchemaGraph graph;
	
	protected LinkedList<LinkedList<Transition>> finalInputLists;
	
	public Coverage(FSMLSchemaGraph graph){
		this.graph = graph;
	}
	
	public abstract void initLists();
	
	public abstract void calcLists();

	/**
	 * @return the graph
	 */
	public FSMLSchemaGraph getGraph() {
		return graph;
	}

	/**
	 * @param graph the graph to set
	 */
	public void setGraph(FSMLSchemaGraph graph) {
		this.graph = graph;
		initLists();
		calcLists();
	}
	
	/**
	 * @return the finalInputLists
	 */
	public LinkedList<LinkedList<Transition>> getFinalInputLists() {
		return finalInputLists;
	}
	
	public ArrayList<String> convertTransitionsToStrings(LinkedList<Transition> finalList){
		ArrayList<String> result = new ArrayList<String>();
		for(Transition t : finalList){
			result.add(t.get_input());
		}
		
		return result;
	}
}
