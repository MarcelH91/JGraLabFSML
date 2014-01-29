package fsml;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import fsml.parser.NegativeParserTest;
import fsml.parser.TurnstileParserTest;
import fsml.semantics.NegativeConstraintTest;
import fsml.semantics.extending.NegativeSESimulatorTest;
import fsml.semantics.extending.TurnstileSESimulatorTest;
import fsml.semantics.traverse.NegativeTravSimulatorTest;
import fsml.semantics.traverse.TurnstileTravSimulatorTest;
@RunWith(Suite.class)
@Suite.SuiteClasses({NegativeParserTest.class, TurnstileParserTest.class,
	NegativeConstraintTest.class, NegativeSESimulatorTest.class, NegativeTravSimulatorTest.class,
	TurnstileSESimulatorTest.class, TurnstileTravSimulatorTest.class,
	fsml.visual.graphviz.FSMGraphVizTest.class})
public class FSMTestSuite {

}
