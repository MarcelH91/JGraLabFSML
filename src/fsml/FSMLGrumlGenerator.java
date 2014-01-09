package fsml;

import de.uni_koblenz.jgralab.ImplementationType;
import de.uni_koblenz.jgralab.exception.GraphIOException;
import de.uni_koblenz.jgralab.grumlschema.GrumlSchema;
import de.uni_koblenz.jgralab.grumlschema.SchemaGraph;
import de.uni_koblenz.jgralab.grumlschema.domains.BooleanDomain;
import de.uni_koblenz.jgralab.grumlschema.domains.StringDomain;
import de.uni_koblenz.jgralab.grumlschema.structure.AggregationKind;
import de.uni_koblenz.jgralab.grumlschema.structure.Attribute;
import de.uni_koblenz.jgralab.grumlschema.structure.EdgeClass;
import de.uni_koblenz.jgralab.grumlschema.structure.GraphClass;
import de.uni_koblenz.jgralab.grumlschema.structure.IncidenceClass;
import de.uni_koblenz.jgralab.grumlschema.structure.VertexClass;
import de.uni_koblenz.jgralab.schema.Schema;
import de.uni_koblenz.jgralab.utilities.tg2schemagraph.SchemaGraph2Schema;

public class FSMLGrumlGenerator {

	public static void main(String[] args) {
		GrumlSchema grumlSchema = GrumlSchema.instance();
		
		SchemaGraph schemaGraph = grumlSchema.createSchemaGraph(ImplementationType.STANDARD);
		
		GraphClass gc = schemaGraph.createGraphClass();
		gc.set_qualifiedName("FSMLSchemaGraph");
		
		de.uni_koblenz.jgralab.grumlschema.structure.Schema fsmlSchema = schemaGraph.createSchema();
		fsmlSchema.set_name("FSMLSchema");
		fsmlSchema.set_packagePrefix("fsml.schema");
		fsmlSchema.add_graphclass(gc);
		
		schemaGraph.createDefinesGraphClass(fsmlSchema, gc);

		de.uni_koblenz.jgralab.grumlschema.structure.Package defpack = schemaGraph.createPackage();
		defpack.set_qualifiedName("fsmlPack");
		fsmlSchema.add_defaultpackage(defpack);
		
		VertexClass stateClass = schemaGraph.createVertexClass();
		stateClass.set_abstract(false);
		stateClass.set_qualifiedName("State");
		
		Attribute idAtt = schemaGraph.createAttribute();
		idAtt.set_name("id");
		StringDomain sd = schemaGraph.createStringDomain();
		sd.set_qualifiedName("String");
		sd.add_package(defpack);
		idAtt.add_domain(sd);
		stateClass.add_attributes(idAtt);
		
		Attribute initAtt = schemaGraph.createAttribute();
		initAtt.set_name("initial");
		BooleanDomain bd = schemaGraph.createBooleanDomain();
		bd.set_qualifiedName("Boolean");
		bd.add_package(defpack);
		initAtt.add_domain(bd);
		stateClass.add_attributes(initAtt);
		
		Attribute idAtt2 = schemaGraph.createAttribute();
		idAtt2.set_name("id");
		idAtt2.add_domain(sd);
		
		Attribute inputAtt = schemaGraph.createAttribute();
		inputAtt.set_name("input");
		inputAtt.add_domain(sd);
		
		Attribute actionAtt = schemaGraph.createAttribute();
		actionAtt.set_name("action");
		actionAtt.add_domain(sd);
		
		IncidenceClass fromState = schemaGraph.createIncidenceClass();
		fromState.set_aggregation(AggregationKind.NONE);
		fromState.set_max(1);
		fromState.set_min(1);
		fromState.set_roleName("fromState");
		fromState.add_targetclass(stateClass);
		
		IncidenceClass toState = schemaGraph.createIncidenceClass();
		toState.set_aggregation(AggregationKind.NONE);
		toState.set_max(Integer.MAX_VALUE);
		toState.set_min(0);
		toState.set_roleName("toState");
		toState.add_targetclass(stateClass);
		
		EdgeClass transitionClass = schemaGraph.createEdgeClass();
		transitionClass.set_abstract(false);
		transitionClass.set_qualifiedName("Transition");
		transitionClass.add_attributes(idAtt2);
		transitionClass.add_attributes(inputAtt);
		transitionClass.add_attributes(actionAtt);
		transitionClass.add_from(fromState);
		transitionClass.add_to(toState);
		
		defpack.add_domains(sd);
		defpack.add_domains(bd);
		
		defpack.add_graphelementclasses(transitionClass);
		defpack.add_graphelementclasses(stateClass);
		
		SchemaGraph2Schema sg2s = new SchemaGraph2Schema();
		Schema schema = sg2s.convert(schemaGraph);
		try {
			schema.save("fsmlSchema.tg");
		} catch (GraphIOException e) {
			e.printStackTrace();
		}
	}

}
