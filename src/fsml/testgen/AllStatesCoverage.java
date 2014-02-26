package fsml.testgen;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import de.uni_koblenz.jgralab.EdgeDirection;
import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;

public class AllStatesCoverage extends Coverage{

	
	private HashSet<State> visitedStates;
	private Queue<LinkedList<Transition>> queue;
	

	public AllStatesCoverage(FSMLSchemaGraph g){
		super(g);
		initLists();
		calcLists();
	}
	
	public void initLists(){
		visitedStates = new HashSet<State>();
		Iterable<State> states = graph.getStateVertices();
		State current = null;
		for(State s : states){
			if(s.is_initial())
				current = s;
		}
		visitedStates.add(current);
		queue = new LinkedList<LinkedList<Transition>>();
		finalInputLists = new LinkedList<LinkedList<Transition>>();
		
		
		for(Transition t : current.getTransitionIncidences(EdgeDirection.OUT)){
			if(!visitedStates.contains(t.getOmega())){
				LinkedList<Transition> listi = new LinkedList<Transition>();
				listi.add(t);
				visitedStates.add(t.getOmega());
				queue.offer(listi);
			}
		}
		
	}
	
	public void calcLists(){
		if(visitedStates.size()==graph.getVCount()){
			finalInputLists.addAll(queue);
			return;
		}
		while(!queue.isEmpty()){
			LinkedList<Transition> temp = queue.poll();
			State current = temp.get(temp.size()-1).getOmega();
			for(Transition t : current.getTransitionIncidences(EdgeDirection.OUT)){
				if(visitedStates.contains(t.getOmega())){
					finalInputLists.add(temp);
					continue;
				}
				
				LinkedList<Transition> listi = new LinkedList<Transition>();
				listi.addAll(temp);
				listi.add(t);
				queue.offer(listi);
				
				visitedStates.add(t.getOmega());
			}
		}
		
	}


}
