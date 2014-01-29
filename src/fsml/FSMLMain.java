package fsml;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import fsml.parser.FSMLBaseListener;
import fsml.parser.FSMLParsing;
import fsml.schema.FSMLSchemaGraph;
import fsml.semantics.extending.SESimulator;
import fsml.semantics.traverse.TravSimulator;
import fsml.utilities.FSMLFileHandler;
import fsml.visual.graphviz.FSMVisualisationGV;
//import fsml.visual.draw2d.FSMVisualisation;

public class FSMLMain {
	
	public static void main(String[] args0) throws Exception{
		String file = "testfiles/turnstile.fsml";
		
		FSMLBaseListener fsmlbl = FSMLParsing.parse(file);
		
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("testfiles/test.input"), StandardCharsets.UTF_8);
		
		SESimulator sesim = new SESimulator(fsg, input);
		sesim.computeFSM();
		FSMLFileHandler.writeOutput((ArrayList<String>) sesim.getOutput(), "output/turnstilese.output");
		
		input = (ArrayList<String>) Files.readAllLines(Paths.get("testfiles/test.input"), StandardCharsets.UTF_8);
		TravSimulator tsim = new TravSimulator(fsg, input);
		tsim.computeFSM();
		FSMLFileHandler.writeOutput((ArrayList<String>) tsim.getOutput(), "output/turnstilet.output");
		
		
//		fsml.visual.draw2d.FSMVisualisation fsv = new FSMVisualisation();
//		fsv.run(fsg);
		fsml.visual.graphviz.FSMVisualisationGV gsv = 
				new FSMVisualisationGV("D:/Utilities/Graphviz2.30/bin/dot.exe",
						"tmp");
		gsv.run(fsg, "output", "turnstile", "jpg");
	}
}
