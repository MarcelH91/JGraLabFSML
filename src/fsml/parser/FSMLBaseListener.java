package fsml.parser;
// Generated from FSML.g by ANTLR 4.1

import java.util.HashMap;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.uni_koblenz.jgralab.ImplementationType;
import fsml.exception.FSMMismatchException;
import fsml.schema.FSMLSchema;
import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;

/**
 * This class provides an empty implementation of {@link FSMLListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
public class FSMLBaseListener implements FSMLListener {
	
	private FSMLSchemaGraph graph;
	private HashMap<String, State> stateMap;
	private State state;
	private boolean initial;
	private String input;
	private String action;
	private Transition transition;
	private State alphaState;
	private State omegaState;
	private int createdstatenumber;
	private int realstatenumber;
	
	/**
	 * The default implementation does nothing.
	 */
	@Override 
	public void enterId(@NotNull FSMLParser.IdContext ctx) { }
	
	/**
	 * Differs 4 cases
	 * I.) input = "" -> it's a state
	 * I.I) the state is part of stateMap -> the state was created already
	 * 		state is set to the saved state from the map.
	 * I.II) the method creates the state
	 * II) input != "" -> it's a transition's target
	 * II.I) the target already exists in statemap and can be retrieved
	 * II.II) the target doesn't exist yet and has to be created as a dump
	 * 
	 * some additional measure via createdstatenumber and realstatenumber
	 * is initiated here in order to provide consistency. The transition's
	 * target has to exist.
	 * 
	 */
	@Override public void exitId(@NotNull FSMLParser.IdContext ctx) {
		if(input.equals("")){
			if(stateMap.containsKey(ctx.Name().getText())){
				state = stateMap.get(ctx.Name().getText());
			}else{
				state = graph.createState();
				state.set_id(ctx.Name().getText());
				stateMap.put(ctx.Name().getText(), state);
				createdstatenumber +=1;
			}
		}else{
			if(stateMap.containsKey(ctx.Name().getText())){
				omegaState = stateMap.get(ctx.Name().getText());
			}else{ 
				omegaState = graph.createState();
				omegaState.set_id(ctx.Name().getText());
				stateMap.put(ctx.Name().getText(), omegaState);
				createdstatenumber +=1;
			}
		}
	}

	/**
	 * The default implementation does nothing.
	 */
	@Override 
	public void enterInput(@NotNull FSMLParser.InputContext ctx) { }
	
	/**
	 * sets input to the parsed string.
	 */
	@Override 
	public void exitInput(@NotNull FSMLParser.InputContext ctx) {
		input = ctx.Name().getText();
	}

	/**
	 * The default implementation does nothing.
	 */
	@Override 
	public void enterInitial(@NotNull FSMLParser.InitialContext ctx) {
	}
	
	/**
	 * assigns true to initial
	 */
	@Override 
	public void exitInitial(@NotNull FSMLParser.InitialContext ctx) {
		initial = true;
	}

	/**
	 * Creates the schemagraph-object FSMLSchema and initialises some
	 * global variables.
	 */
	@Override 
	public void enterFsm(@NotNull FSMLParser.FsmContext ctx) {
		FSMLSchema schema = FSMLSchema.instance();
		graph = schema.createFSMLSchemaGraph(ImplementationType.STANDARD);
		stateMap = new HashMap<String, State>();
		input = "";
		createdstatenumber = 0;
		realstatenumber = 0;
	}
	/**
	 * Checks wether there are as many created states than real states
	 * and throws an exception if the constraint doesn't hold.
	 */
	@Override 
	public void exitFsm(@NotNull FSMLParser.FsmContext ctx) {
		if(realstatenumber!=createdstatenumber)
			throw new FSMMismatchException("Invalid target states found!"+createdstatenumber+"from"+realstatenumber);
	}

	/**
	 * The default implementation does nothing.
	 */
	@Override 
	public void enterAction(@NotNull FSMLParser.ActionContext ctx) { }
	
	/**
	 * Assigns the parsed text to action
	 */
	@Override 
	public void exitAction(@NotNull FSMLParser.ActionContext ctx) { 
		action = ctx.Name().getText();
	}

	/**
	 * The default implementation does nothing.
	 */
	@Override 
	public void enterState(@NotNull FSMLParser.StateContext ctx) { }
	
	/**
	 * Assigns initial to the created state, resets initial and adds 1
	 * to realstatenumber.
	 */
	@Override 
	public void exitState(@NotNull FSMLParser.StateContext ctx) { 
		state.set_initial(initial);
		initial = false;
		realstatenumber+=1;
	}

	/**
	 * Assigns the created state to alphaState and omegaState since a 
	 * transition can only be created by giving start and end states
	 * as parameters.
	 */
	@Override 
	public void enterTransition(@NotNull FSMLParser.TransitionContext ctx) {
		alphaState = state;
		omegaState = state;
	}
	
	/**
	 * Creates a transition and assigns its attributes. Resets input and action.
	 */
	@Override 
	public void exitTransition(@NotNull FSMLParser.TransitionContext ctx) {
		transition = graph.createTransition(alphaState, omegaState);
		transition.set_action(action);
		transition.set_input(input);
		transition.set_id(alphaState.get_id()+"->"+omegaState.get_id());
		input = "";
		action = "";
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * The default implementation does nothing.
	 */
	@Override public void enterEveryRule(@NotNull ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 * <p/>
	 * The default implementation does nothing.
	 */
	@Override public void exitEveryRule(@NotNull ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 * <p/>
	 * The default implementation does nothing.
	 */
	@Override public void visitTerminal(@NotNull TerminalNode node) { }
	/**
	 * {@inheritDoc}
	 * <p/>
	 * The default implementation does nothing.
	 */
	@Override public void visitErrorNode(@NotNull ErrorNode node) { 
		int a = node.getSourceInterval().a;
		int b = node.getSourceInterval().b;
		throw new FSMMismatchException("A parsing error occured. a="+a+", b="+b);
	}
	
	/**
	 * Returns the schemagraph-object
	 * @return
	 */
	public FSMLSchemaGraph getGraph() {
		return graph;
	}
}