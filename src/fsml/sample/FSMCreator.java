package fsml.sample;

import de.uni_koblenz.jgralab.ImplementationType;
import de.uni_koblenz.jgralab.exception.GraphIOException;
import fsml.schema.FSMLSchema;
import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;

public class FSMCreator {

	public static void main(String[] args0) throws GraphIOException {
		FSMLSchema schema = FSMLSchema.instance();
		FSMLSchemaGraph graph = schema.createFSMLSchemaGraph(ImplementationType.STANDARD);
		
		State x = graph.createState();
		State y = graph.createState();
		y.set_id("test");
		
		graph.save("sample.tg");
		
		FSMLSchemaGraph graph2 = FSMLSchema.instance().loadFSMLSchemaGraph("sample.tg");
		if(null==graph2.getFirstState().get_id())
			System.out.println("yes");
	}
}
