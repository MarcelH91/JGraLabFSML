package fsml.parser;
// Generated from FSML.egl by ANTLR 4.0

import java.util.HashMap;
import java.util.InputMismatchException;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;

import de.uni_koblenz.jgralab.ImplementationType;
import fsml.schema.FSMLSchema;
import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;

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
	
	@Override public void enterId(FSMLParser.IdContext ctx) { }
	@Override public void exitId(FSMLParser.IdContext ctx) { 
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

	@Override public void enterInput(FSMLParser.InputContext ctx) { }
	@Override public void exitInput(FSMLParser.InputContext ctx) {
		input = ctx.Name().getText();
	}

	@Override public void enterInitial(FSMLParser.InitialContext ctx) { }
	@Override public void exitInitial(FSMLParser.InitialContext ctx) {
		initial = true;
	}
	
	@Override public void enterFsm(FSMLParser.FsmContext ctx) {
		FSMLSchema schema = FSMLSchema.instance();
		graph = schema.createFSMLSchemaGraph(ImplementationType.STANDARD);
		stateMap = new HashMap<String, State>();
		input = "";
		createdstatenumber = 0;
		realstatenumber = 0;
	}
	@Override public void exitFsm(FSMLParser.FsmContext ctx) { 
		if(realstatenumber!=createdstatenumber)
			throw new InputMismatchException("Invalid target states found!");
	}

	@Override public void enterAction(FSMLParser.ActionContext ctx) { }
	@Override public void exitAction(FSMLParser.ActionContext ctx) {
		action = ctx.Name().getText();
	}

	@Override public void enterState(FSMLParser.StateContext ctx) { }
	@Override public void exitState(FSMLParser.StateContext ctx) { 
		state.set_initial(initial);
		initial = false;
		realstatenumber+=1;
	}

	@Override public void enterTransition(FSMLParser.TransitionContext ctx) {
		alphaState = state;
		omegaState = state;
	}
	@Override public void exitTransition(FSMLParser.TransitionContext ctx) {
		transition = graph.createTransition(alphaState, omegaState);
		transition.set_action(action);
		transition.set_input(input);
		transition.set_id(alphaState.get_id()+"->"+omegaState.get_id());
		input = "";
		action = "";
	}

	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	@Override public void visitTerminal(TerminalNode node) { }
	@Override public void visitErrorNode(ErrorNode node) { }
	
	public FSMLSchemaGraph getGraph() {
		return graph;
	}
}