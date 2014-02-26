package fsml.testgen;

import java.util.LinkedList;
import java.util.Queue;

import de.uni_koblenz.jgralab.EdgeDirection;
import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;

public class DepthNTransitionCoverage extends Coverage{
	private int depth;
	private Queue<LinkedList<Transition>> queue;
	
	public DepthNTransitionCoverage(FSMLSchemaGraph graph, int depth){
		super(graph);
		if(depth < 1)
			throw new IllegalArgumentException("Depth must be > 0");
		setDepth(depth);
	}
	
	public void initLists(){
		Iterable<State> states = graph.getStateVertices();
		State current = null;
		for(State s : states){
			if(s.is_initial())
				current = s;
		}
		
		queue = new LinkedList<LinkedList<Transition>>();
		finalInputLists = new LinkedList<LinkedList<Transition>>();
		
		for(Transition t : current.getTransitionIncidences(EdgeDirection.OUT)){
			LinkedList<Transition> listi = new LinkedList<Transition>();
			listi.add(t);
			queue.offer(listi);
		}
		
		
	}
	
	public void calcLists(){
		
		while(!queue.isEmpty()){
			LinkedList<Transition> temp = queue.poll();
			if(temp.size()==depth){
				finalInputLists.add(temp);
				continue;
			}
			State last = temp.get(temp.size()-1).getOmega();
			for(Transition t : last.getTransitionIncidences(EdgeDirection.OUT)){
				LinkedList<Transition> listi = new LinkedList<Transition>();
				listi.addAll(temp);
				listi.add(t);
				queue.offer(listi);
			}
		}
		
	}

	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
		initLists();
		calcLists();
	}

}
