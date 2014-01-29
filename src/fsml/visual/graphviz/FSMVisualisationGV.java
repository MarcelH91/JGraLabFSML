package fsml.visual.graphviz;

import java.io.File;
import java.io.IOException;

import fsml.schema.FSMLSchemaGraph;
import fsml.schema.State;
import fsml.schema.Transition;

public class FSMVisualisationGV {

	private GraphViz gv;
	
	/**
	 * Creates a GraphViz-object, where the location of your GraphViz' dot.exe
	 * and the location of temporary files are defined. Both have to
	 * exist before calling this constructor.
	 * 
	 * @param dotLoc (sample : "D:/Utilities/Graphviz2.36/bin/dot.exe")
	 * @param tempLoc (sample : "D:/Implementation/FSML/tmp")
	 * @throws IOException 
	 */
	public FSMVisualisationGV(String dotLoc, String tempLoc) throws IOException{
		File f = new File(dotLoc);
		if(!f.exists())
			throw new IOException("No File at the dotLoc location!");
		f = new File(tempLoc);
		if(!f.exists())
			throw new IOException("Temporary folder doesn't exist yet!");
		gv = new GraphViz(dotLoc, tempLoc);
	}
	
	/**
	 * Creates an output file visualizing the FSM based on a dot file, which
	 * is created by using the GraphViz-Class.
	 * @param fsg	the actual FSM as a FSMLSchemaGraph
	 * @param folder the targetfolder	(sample:"D:/Programming/FSML/tmp/")
	 * @param filename (sample:"turnstile")
	 * @param type the wanted file type. (sample "jpg")
	 * 
	 * Achievable outputfiletypes are gif, dot, fig, pdf, ps, svg, png and every other
	 * type, which is supported to export by GraphViz
	 * 
	 */
	public void run(FSMLSchemaGraph fsg, String folder, String filename, String type){
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
	    
	    File out = new File(folder+"/"+filename+"."+ type);    
	    byte[] g = gv.getGraph(gv.getDotSource(), type);
	    if(null==g)
	    	System.err.println("Error on retrieving graph at FSMVisualisationGV");
	    
	    gv.writeGraphToFile( g , out );
	}
	
	/**
	 * Creates a line saved at a temporary dot file concerning a node.
	 * @param s
	 * @return
	 */
	public String createNodeline(State s){
		String line = "  "+s.get_id()+" [label=\""+s.get_id()+"\", shape=ellipse";
		if(s.is_initial())
			line += ", style=filled";
		line+="]";
		
		return line;
	}
	
	/**
	 * Creates a line saved at a temporary dot file concerning an edge.
	 * @param t
	 * @return
	 */
	public String createEdgeline(Transition t){
		String line = "  "+t.getAlpha().get_id() +" -> "+t.getOmega().get_id();
		line+=" [label=\"" +t.get_input();
		if(!t.get_action().equals(""))
			line+="/"+t.get_action();
		line+="\"]";
		return line;
	}
}
