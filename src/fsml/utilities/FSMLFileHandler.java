package fsml.utilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FSMLFileHandler {

	/**
	 * Reads an input file and removes its comments, whitespaces
	 * and comma.
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> readInput(String path) throws IOException{
		ArrayList<String> inputlist = (ArrayList<String>) Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
		ArrayList<String> input = new ArrayList<String>();
		inputlist.remove(0);
		inputlist.remove(inputlist.size()-1);
		for(int i=0; i<inputlist.size(); i++){
			String stemm = inputlist.get(i);
			stemm = stemm.split("%")[0];
			stemm = stemm.replaceAll(" ", "");
			stemm = stemm.replaceAll(",", "");
			input.add(stemm);
		}
		
		return input;
	}
	
	/**
	 * Writes each element of outputlist as a new line into a file called filename.
	 * @param outputlist
	 * @param filename
	 * @throws IOException
	 */
	public static void writeOutput(ArrayList<String> outputlist, String filename) throws IOException{
		Path path = Paths.get(filename);
	    Files.write(path, outputlist, StandardCharsets.UTF_8);
	}
	
	/**
	 * Splits each line of the output-String into elements. Those elements
	 * are added to a String-list. Afterwards the original writeOutput-method
	 * is called.
	 * @param output
	 * @param filename
	 * @throws IOException
	 */
	public static void writeOutput(String output, String filename) throws IOException{
		ArrayList<String> outputlist = new ArrayList<String>();
		String[] outputarray = output.split("\n");
		for(String o : outputarray)
			outputlist.add(o);
		writeOutput(outputlist, filename);
	}
	
}
