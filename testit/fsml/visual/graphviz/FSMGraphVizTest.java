package fsml.visual.graphviz;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import fsml.parser.FSMLBaseListener;
import fsml.parser.FSMLParsing;
import fsml.schema.FSMLSchemaGraph;

public class FSMGraphVizTest {

	@Test
	/**
	 * Tests wether the desired outputfile has been created
	 */
	public void testVisualFileExistence() throws IOException {
		String file = "testfiles/turnstile.fsml";
		
		FSMLBaseListener fsmlbl = FSMLParsing.parse(file);
		FSMLSchemaGraph fsg = fsmlbl.getGraph();
		
		FSMVisualisationGV gsv = new FSMVisualisationGV("D:/Utilities/Graphviz2.36/bin/dot.exe",
						"tmp");
		gsv.run(fsg, "output", "turnstileExistence", "jpg");
		
		File testFile = new File("output/turnstileExistence.jpg");
		
		assertTrue(testFile.canRead());
		assertTrue(testFile.canWrite());
		
		testFile.delete();
	}

}
