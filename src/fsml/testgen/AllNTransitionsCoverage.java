package fsml.testgen;

import java.util.LinkedList;
import java.util.Queue;

import de.uni_koblenz.jgralab.EdgeDirection;
import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;

public class AllNTransitionsCoverage extends Coverage{
	protected LinkedList<LinkedList<Transition>> initialInputLists;
	private int depth;
	
	public AllNTransitionsCoverage(FSMLSchemaGraph graph, int depth){
		super(graph);
		if(depth < 1)
			throw new IllegalArgumentException("Depth must be > 0");
		setDepth(depth);
	}
	
	public void initLists(){
		Queue<LinkedList<Transition>> queue = new LinkedList<LinkedList<Transition>>();
		initialInputLists = new LinkedList<LinkedList<Transition>>();
		finalInputLists = new LinkedList<LinkedList<Transition>>();
		
		for(Transition t : getGraph().getTransitionEdges()){
			LinkedList<Transition> listi = new LinkedList<Transition>();
			listi.add(t);
			queue.offer(listi);
		}
		while(!queue.isEmpty()){
			LinkedList<Transition> temp = queue.poll();
			if(temp.size()==depth){
				initialInputLists.add(temp);
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
	
	public void calcLists(){
		Iterable<State> states = getGraph().getStateVertices();
		State initialState = null;
		for(State s : states){
			if(s.is_initial())
				initialState = s;
		}
		
		for(LinkedList<Transition> listi : initialInputLists){
			LinkedList<Transition> way = findPath(initialState, listi.get(0).getAlpha());
			way.addAll(listi);
			finalInputLists.add(way);
		}
	}

	/**
	 * Calculates the path of transitions from start to goal via BFS.
	 * @param start
	 * @param goal
	 * @return
	 */
	private LinkedList<Transition> findPath(State start, State goal) {
		if(start.equals(goal))
			return new LinkedList<Transition>();
		Queue<LinkedList<Transition>> queue = new LinkedList<LinkedList<Transition>>();
		
		//fill queue
		for(Transition t : start.getTransitionIncidences(EdgeDirection.OUT)){
			LinkedList<Transition> temp = new LinkedList<Transition>();
			temp.add(t);
			queue.offer(temp);
		}
		
		while(!queue.isEmpty()){
			LinkedList<Transition> listi = queue.poll();
			State current = listi.get(listi.size()-1).getOmega();
			if(current.equals(goal)){
				System.err.println("Goal");
				return listi;
			}
			for(Transition t : current.getTransitionIncidences(EdgeDirection.OUT)){
				LinkedList<Transition> temp = new LinkedList<Transition>();
				temp.addAll(listi);
				temp.add(t);
				queue.offer(temp);
			}
			
		}
		System.err.println("Reached");
		return null;
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
