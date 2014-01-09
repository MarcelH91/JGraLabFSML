package fsml.visual.graphviz;

import java.io.File;

import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;

public class FSMVisualisationGV {

	public void run(FSMLSchemaGraph fsg, String type){
		GraphViz gv = new GraphViz();
	    gv.addln(gv.start_graph());
	    Iterable<State> states = fsg.getStateVertices();
	    for(State s : states){
	    	gv.addln(createNodeline(s));
	    }
	    
	    Iterable<Transition> trans = fsg.getTransitionEdges();
	    for(Transition t : trans){
	    	gv.addln(createEdgeline(t));
	    }
	    gv.addln(gv.end_graph());
	    
	    System.out.println(gv.getDotSource());
	    File out = new File("tmp/out." + type);    // Windows
	    byte[] g = gv.getGraph(gv.getDotSource(), type);
	    gv.writeGraphToFile( g , out );
	}
	
	public String createNodeline(State s){
		String line = "  "+s.get_id()+" [label=\""+s.get_id()+"\", shape=ellipse";
		if(s.is_initial())
			line += ", style=filled";
		line+="]";
		
		return line;
	}
	
	public String createEdgeline(Transition t){
		String line = "  "+t.getAlpha().get_id() +" -> "+t.getOmega().get_id();
		line+=" [label=\"" +t.get_input();
		if(!t.get_action().equals(""))
			line+="/"+t.get_action();
		line+="\"]";
		return line;
	}
}
