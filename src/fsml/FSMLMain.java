package fsml;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import fsml.parser.FSMLBaseListener;
import fsml.parser.FSMLLexer;
import fsml.parser.FSMLParser;
import fsml.schema.FSMLSchemaGraph;
import fsml.semantics.FSMSimulator;
import fsml.visual.draw2d.FSMVisualisation;
import fsml.visual.graphviz.FSMVisualisationGV;

public class FSMLMain {
	
	public static void main(String[] args0) throws Exception{
		String file = "testfiles/sample.fsml";
		ANTLRFileStream cs = new ANTLRFileStream(file);
		FSMLLexer fsmll = new FSMLLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(fsmll);
		FSMLParser fsmlp = new FSMLParser(tokens);
		
		FSMLBaseListener fsmlbl = new FSMLBaseListener();
		fsmlp.addParseListener(fsmlbl);
		
		fsmlp.fsm();
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("tmp/test.input"), StandardCharsets.UTF_8);
		
		
		FSMSimulator sim = new FSMSimulator(fsg, input);
		sim.computeFSM();
		
		
//		fsml.visual.draw2d.FSMVisualisation fsv = new FSMVisualisation();
//		fsv.run(fsg);
		fsml.visual.graphviz.FSMVisualisationGV gsv = new FSMVisualisationGV();
		gsv.run(fsg, "jpg");
		
	}
}
