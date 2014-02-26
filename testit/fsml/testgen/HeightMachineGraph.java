package fsml.testgen;

import java.io.IOException;

import fsml.parser.FSMLBaseListener;
import fsml.parser.FSMLParsing;
import fsml.schema.FSMLSchemaGraph;
import fsml.semantics.ConstraintChecker;

public class HeightMachineGraph {

	public static FSMLSchemaGraph createHMGraph() throws IOException {
		String file = "testfiles/heightmachine.fsml";
		
		FSMLBaseListener fsmlbl = FSMLParsing.parse(file);
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		ConstraintChecker cc = new ConstraintChecker();
		cc.checkAllConstraints(fsg);
		
		return fsg;
	}

}
