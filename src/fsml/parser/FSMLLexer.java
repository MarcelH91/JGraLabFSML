package fsml.parser;
// Generated from FSML.g by ANTLR 4.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FSMLLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__6=1, T__5=2, T__4=3, T__3=4, T__2=5, T__1=6, T__0=7, Name=8, WS=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'->'", "'initial'", "'{'", "'/'", "'}'", "';'", "'state'", "Name", "WS"
	};
	public static final String[] ruleNames = {
		"T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "Name", "WS", 
		"Letter", "LetterOrDigit"
	};


	public FSMLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FSML.g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 8: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\13D\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\7\t\65\n\t\f\t\16\t"+
		"8\13\t\3\n\6\n;\n\n\r\n\16\n<\3\n\3\n\3\13\3\13\3\f\3\f\2\r\3\3\1\5\4"+
		"\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\2\25\2\1\27\2\1\3\2\5"+
		"\5\2\13\f\16\17\"\"\6\2&&C\\aac|\7\2&&\62;C\\aac|C\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\3\31\3\2\2\2\5\34\3\2\2\2\7$\3\2\2\2\t&\3\2\2\2"+
		"\13(\3\2\2\2\r*\3\2\2\2\17,\3\2\2\2\21\62\3\2\2\2\23:\3\2\2\2\25@\3\2"+
		"\2\2\27B\3\2\2\2\31\32\7/\2\2\32\33\7@\2\2\33\4\3\2\2\2\34\35\7k\2\2\35"+
		"\36\7p\2\2\36\37\7k\2\2\37 \7v\2\2 !\7k\2\2!\"\7c\2\2\"#\7n\2\2#\6\3\2"+
		"\2\2$%\7}\2\2%\b\3\2\2\2&\'\7\61\2\2\'\n\3\2\2\2()\7\177\2\2)\f\3\2\2"+
		"\2*+\7=\2\2+\16\3\2\2\2,-\7u\2\2-.\7v\2\2./\7c\2\2/\60\7v\2\2\60\61\7"+
		"g\2\2\61\20\3\2\2\2\62\66\5\25\13\2\63\65\5\27\f\2\64\63\3\2\2\2\658\3"+
		"\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67\22\3\2\2\28\66\3\2\2\29;\t\2\2\2"+
		":9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\b\n\2\2?\24\3\2\2"+
		"\2@A\t\3\2\2A\26\3\2\2\2BC\t\4\2\2C\30\3\2\2\2\5\2\66<";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}