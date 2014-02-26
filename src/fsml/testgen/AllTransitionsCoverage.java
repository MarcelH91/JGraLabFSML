package fsml.testgen;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import de.uni_koblenz.jgralab.EdgeDirection;
import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;

public class AllTransitionsCoverage extends Coverage{
	
	private HashSet<Transition> visitedTransitions;
	private Queue<LinkedList<Transition>> queue;
	
	public AllTransitionsCoverage(FSMLSchemaGraph graph){
		super(graph);
		initLists();
		calcLists();
	}
	
	public void initLists(){
		visitedTransitions = new HashSet<Transition>();
		Iterable<State> states = graph.getStateVertices();
		State current = null;
		for(State s : states){
			if(s.is_initial())
				current = s;
		}
		
		queue = new LinkedList<LinkedList<Transition>>();
		finalInputLists = new LinkedList<LinkedList<Transition>>();
		LinkedList<Transition> listi = new LinkedList<Transition>();
		
		for(Transition t : current.getTransitionIncidences(EdgeDirection.OUT)){
			if(current.equals(t.getOmega())){
				listi.add(t);
				visitedTransitions.add(t);
			}
		}
		for(Transition t : current.getTransitionIncidences(EdgeDirection.OUT)){
			if(!current.equals(t.getOmega())){
				LinkedList<Transition> temp = new LinkedList<Transition>();
				temp.addAll(listi);
				temp.add(t);
				visitedTransitions.add(t);
				queue.add(temp);
			}
		}
	}
	
	public void calcLists(){
		if(visitedTransitions.size()==graph.getECount())
			while(!queue.isEmpty())
				finalInputLists.add(queue.poll());
		while(!queue.isEmpty()){
			LinkedList<Transition> temp = queue.poll();
			State current = temp.get(temp.size()-1).getOmega();
			boolean finished = true;
			for(Transition t : current.getTransitionIncidences(EdgeDirection.OUT)){
				if(visitedTransitions.contains(t))
					continue;
				finished = false;
				LinkedList<Transition> listi = new LinkedList<Transition>();
				listi.addAll(temp);
				listi.add(t);
				queue.offer(listi);
				
				visitedTransitions.add(t);
				finished = false;
			}
			if(finished){
				finalInputLists.add(temp);
			}
		}
	}

}
