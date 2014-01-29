// begin CONCERN
package fsml.schema.ext;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import fsml.schema.Transition;


public aspect FsmlSchemaExtensionGen {

	/**
	 * Returns the next active transition for a given input.
	 * @param input
	 * @return
	 */
	public fsml.schema.Transition fsml.schema.State.getNextTransition(java.lang.String input){
			Iterable<Edge> transitions = this.incidences(EdgeDirection.OUT);
			boolean transitionFound = false;
			Transition t = null;
			for(Edge e : transitions){
				t = (Transition) e;
				if(input.equals(t.get_input())){
					transitionFound = true;
					break;
				}
			}
			if(!transitionFound)
				throw new fsml.exception.FSMTriggerNotFoundException("The input contains " +
						"an invalid element:"+input);
			return t;
	}
	
	/**
	 * Returns a String, which contains a log entry, created by the transition.
	 * The entry always looks like: ([action], activeState)
	 * @return
	 */
	public java.lang.String fsml.schema.Transition.getOutput(){
				return "    (["+this.get_action()+"], "+this.getOmega().get_id()+")";
	}
	


}
// end CONCERN
