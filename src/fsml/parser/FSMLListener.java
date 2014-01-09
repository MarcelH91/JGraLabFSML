package fsml.parser;
// Generated from FSML.egl by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface FSMLListener extends ParseTreeListener {
	void enterId(FSMLParser.IdContext ctx);
	void exitId(FSMLParser.IdContext ctx);

	void enterInput(FSMLParser.InputContext ctx);
	void exitInput(FSMLParser.InputContext ctx);

	void enterInitial(FSMLParser.InitialContext ctx);
	void exitInitial(FSMLParser.InitialContext ctx);

	void enterFsm(FSMLParser.FsmContext ctx);
	void exitFsm(FSMLParser.FsmContext ctx);

	void enterAction(FSMLParser.ActionContext ctx);
	void exitAction(FSMLParser.ActionContext ctx);

	void enterState(FSMLParser.StateContext ctx);
	void exitState(FSMLParser.StateContext ctx);

	void enterTransition(FSMLParser.TransitionContext ctx);
	void exitTransition(FSMLParser.TransitionContext ctx);
}