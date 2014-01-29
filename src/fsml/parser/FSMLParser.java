package fsml.parser;
// Generated from FSML.g by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FSMLParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__6=1, T__5=2, T__4=3, T__3=4, T__2=5, T__1=6, T__0=7, Name=8, WS=9;
	public static final String[] tokenNames = {
		"<INVALID>", "'->'", "'initial'", "'{'", "'/'", "'}'", "';'", "'state'", 
		"Name", "WS"
	};
	public static final int
		RULE_fsm = 0, RULE_state = 1, RULE_initial = 2, RULE_transition = 3, RULE_id = 4, 
		RULE_input = 5, RULE_action = 6;
	public static final String[] ruleNames = {
		"fsm", "state", "initial", "transition", "id", "input", "action"
	};

	@Override
	public String getGrammarFileName() { return "FSML.g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public FSMLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FsmContext extends ParserRuleContext {
		public List<StateContext> state() {
			return getRuleContexts(StateContext.class);
		}
		public StateContext state(int i) {
			return getRuleContext(StateContext.class,i);
		}
		public FsmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fsm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).enterFsm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).exitFsm(this);
		}
	}

	public final FsmContext fsm() throws RecognitionException {
		FsmContext _localctx = new FsmContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_fsm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==2 || _la==7) {
				{
				{
				setState(14); state();
				}
				}
				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StateContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TransitionContext transition(int i) {
			return getRuleContext(TransitionContext.class,i);
		}
		public List<TransitionContext> transition() {
			return getRuleContexts(TransitionContext.class);
		}
		public InitialContext initial() {
			return getRuleContext(InitialContext.class,0);
		}
		public StateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).enterState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).exitState(this);
		}
	}

	public final StateContext state() throws RecognitionException {
		StateContext _localctx = new StateContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_state);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_la = _input.LA(1);
			if (_la==2) {
				{
				setState(20); initial();
				}
			}

			setState(23); match(7);
			setState(24); id();
			setState(25); match(3);
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(26); transition();
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(32); match(5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitialContext extends ParserRuleContext {
		public InitialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initial; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).enterInitial(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).exitInitial(this);
		}
	}

	public final InitialContext initial() throws RecognitionException {
		InitialContext _localctx = new InitialContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_initial);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34); match(2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TransitionContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public InputContext input() {
			return getRuleContext(InputContext.class,0);
		}
		public TransitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).enterTransition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).exitTransition(this);
		}
	}

	public final TransitionContext transition() throws RecognitionException {
		TransitionContext _localctx = new TransitionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_transition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); input();
			setState(39);
			_la = _input.LA(1);
			if (_la==4) {
				{
				setState(37); match(4);
				setState(38); action();
				}
			}

			setState(43);
			_la = _input.LA(1);
			if (_la==1) {
				{
				setState(41); match(1);
				setState(42); id();
				}
			}

			setState(45); match(6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(FSMLParser.Name, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); match(Name);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InputContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(FSMLParser.Name, 0); }
		public InputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).enterInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).exitInput(this);
		}
	}

	public final InputContext input() throws RecognitionException {
		InputContext _localctx = new InputContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_input);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); match(Name);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(FSMLParser.Name, 0); }
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FSMLListener ) ((FSMLListener)listener).exitAction(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51); match(Name);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\138\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\7\2\22\n\2\f\2\16\2\25"+
		"\13\2\3\3\5\3\30\n\3\3\3\3\3\3\3\3\3\7\3\36\n\3\f\3\16\3!\13\3\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\5\5\5*\n\5\3\5\3\5\5\5.\n\5\3\5\3\5\3\6\3\6\3\7\3\7"+
		"\3\b\3\b\3\b\2\t\2\4\6\b\n\f\16\2\2\65\2\23\3\2\2\2\4\27\3\2\2\2\6$\3"+
		"\2\2\2\b&\3\2\2\2\n\61\3\2\2\2\f\63\3\2\2\2\16\65\3\2\2\2\20\22\5\4\3"+
		"\2\21\20\3\2\2\2\22\25\3\2\2\2\23\21\3\2\2\2\23\24\3\2\2\2\24\3\3\2\2"+
		"\2\25\23\3\2\2\2\26\30\5\6\4\2\27\26\3\2\2\2\27\30\3\2\2\2\30\31\3\2\2"+
		"\2\31\32\7\t\2\2\32\33\5\n\6\2\33\37\7\5\2\2\34\36\5\b\5\2\35\34\3\2\2"+
		"\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \"\3\2\2\2!\37\3\2\2\2\"#\7\7"+
		"\2\2#\5\3\2\2\2$%\7\4\2\2%\7\3\2\2\2&)\5\f\7\2\'(\7\6\2\2(*\5\16\b\2)"+
		"\'\3\2\2\2)*\3\2\2\2*-\3\2\2\2+,\7\3\2\2,.\5\n\6\2-+\3\2\2\2-.\3\2\2\2"+
		"./\3\2\2\2/\60\7\b\2\2\60\t\3\2\2\2\61\62\7\n\2\2\62\13\3\2\2\2\63\64"+
		"\7\n\2\2\64\r\3\2\2\2\65\66\7\n\2\2\66\17\3\2\2\2\7\23\27\37)-";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}