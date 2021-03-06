package fsml.semantics;

import java.util.ArrayList;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.greql.GreqlQuery;
import de.uni_koblenz.jgralab.greql.evaluator.GreqlEnvironmentAdapter;
import de.uni_koblenz.jgralab.greql.evaluator.GreqlQueryImpl;
import fsml.exception.FSMConstraintCheckException;
import fsml.exception.FSMInputMismatchException;

public class ConstraintChecker {

	
	public void run(Graph fsmGraph, ArrayList<String> input){
		if(!checkAllConstraints(fsmGraph))
			throw new FSMConstraintCheckException("The FSMSchemaGraph isn't well formed");
		for(String i : input){
			String cstr = "exists trans : E{Transition}@ trans.input = \""+i+"\"";
			if(!checkGraphConstraint(fsmGraph, cstr))
				throw new FSMInputMismatchException("The input contains invalid elements");
		}
	}
	
	/**
	 * Checks the graph concerning every constrained, which was specified.
	 * @param graph
	 * @return
	 */
	public boolean checkAllConstraints(Graph graph){
		
		
		//Id Resolvability
		String cstr = "not(exists state : V{State} @ state.id = '' ) and "
				+ "not(exists trans : E{Transition} @ trans.id = '')";
		if(!checkGraphConstraint(graph, cstr))
			return false;

		
		//Reachability
		cstr = "forall state : V{State} @ exists statei : V{State} @ statei.initial = true and "
				+ "statei -->{Transition}* state";
		if(!checkGraphConstraint(graph, cstr))
			return false;

		
		//Single Initial
		cstr = "exists statei : V{State}@ statei.initial = true and "
				+ "not(exists state : V{State}@ state.initial = true and state <> statei)";
		if(!checkGraphConstraint(graph, cstr))
			return false;

		
		//Distinct Id's
		cstr = "forall state : V{State} @ not(exists state2 : V{State} @ state2.id = state.id and state <> state2)";
		if(!checkGraphConstraint(graph, cstr))
			return false;

		
		//Determinismus
		cstr = "forall trans : E{Transition} @ not(exists trans2 : E{Transition} @ "
				+ "alpha(trans) = alpha(trans2) and trans.input = trans2.input and trans <> trans2)";
		if(!checkGraphConstraint(graph, cstr))
			return false;

		
		return true;
	}
	
	/**
	 * Evaluates a boolean GReQL-Query on the Graph
	 * @param graph
	 * @param cstr
	 * @return
	 */
	public boolean checkGraphConstraint(Graph graph, String cstr){
		boolean correct = false;
		//each id has to be resolvable -> transition/state.id != null
		GreqlQuery query = new GreqlQueryImpl(cstr);
		try{
			correct = (Boolean) query.evaluate(graph, new GreqlEnvironmentAdapter());
		}catch(de.uni_koblenz.jgralab.greql.exception.UndefinedVariableException e){
			e.printStackTrace();
		}
		return correct;
	}
	
	
}
