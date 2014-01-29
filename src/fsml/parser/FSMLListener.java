package fsml.parser;
// Generated from FSML.g by ANTLR 4.1
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FSMLParser}.
 */
public interface FSMLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FSMLParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(@NotNull FSMLParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link FSMLParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(@NotNull FSMLParser.IdContext ctx);

	/**
	 * Enter a parse tree produced by {@link FSMLParser#input}.
	 * @param ctx the parse tree
	 */
	void enterInput(@NotNull FSMLParser.InputContext ctx);
	/**
	 * Exit a parse tree produced by {@link FSMLParser#input}.
	 * @param ctx the parse tree
	 */
	void exitInput(@NotNull FSMLParser.InputContext ctx);

	/**
	 * Enter a parse tree produced by {@link FSMLParser#initial}.
	 * @param ctx the parse tree
	 */
	void enterInitial(@NotNull FSMLParser.InitialContext ctx);
	/**
	 * Exit a parse tree produced by {@link FSMLParser#initial}.
	 * @param ctx the parse tree
	 */
	void exitInitial(@NotNull FSMLParser.InitialContext ctx);

	/**
	 * Enter a parse tree produced by {@link FSMLParser#fsm}.
	 * @param ctx the parse tree
	 */
	void enterFsm(@NotNull FSMLParser.FsmContext ctx);
	/**
	 * Exit a parse tree produced by {@link FSMLParser#fsm}.
	 * @param ctx the parse tree
	 */
	void exitFsm(@NotNull FSMLParser.FsmContext ctx);

	/**
	 * Enter a parse tree produced by {@link FSMLParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(@NotNull FSMLParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FSMLParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(@NotNull FSMLParser.ActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link FSMLParser#state}.
	 * @param ctx the parse tree
	 */
	void enterState(@NotNull FSMLParser.StateContext ctx);
	/**
	 * Exit a parse tree produced by {@link FSMLParser#state}.
	 * @param ctx the parse tree
	 */
	void exitState(@NotNull FSMLParser.StateContext ctx);

	/**
	 * Enter a parse tree produced by {@link FSMLParser#transition}.
	 * @param ctx the parse tree
	 */
	void enterTransition(@NotNull FSMLParser.TransitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FSMLParser#transition}.
	 * @param ctx the parse tree
	 */
	void exitTransition(@NotNull FSMLParser.TransitionContext ctx);
}