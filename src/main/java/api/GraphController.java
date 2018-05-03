package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import graph.Graph;

public class GraphController {
	
	public final static int PRIMEIRA_LINHA = 0;
	public final static String ESPACO_EM_BRANCO = " ";
	
	Util util;
	public GraphController() {
		util = new Util();
	}
	
	public Graph readGraph(String path) throws IOException {
		if(path == null || path.equals("")) {
			throw new IOException("Path do arquivo vazio ou null.");
		}
		
		List<String> lines = util.readFileTXT(path);
		
		List<Integer> inputVertexes = new ArrayList<Integer>();
		List<Integer> outputVertexes = new ArrayList<Integer>();
		
		int numVertexes = Integer.parseInt(lines.get(PRIMEIRA_LINHA));
		int index = 1;
		
		while(index < lines.size()) {
			String[] vertexes = util.split(lines.get(index), ESPACO_EM_BRANCO);
			int inputVertexe = Integer.parseInt(vertexes[0]);
			int outputVertexe = Integer.parseInt(vertexes[1]);
			
			inputVertexes.add(inputVertexe);
			outputVertexes.add(outputVertexe);
			index++;
		}
		
		Graph graph = new Graph(inputVertexes, outputVertexes, numVertexes);
		
		return graph;
	}
	
	public Graph readWeightedGraph(String path) throws IOException {
		if(path == null || path.equals("")) {
			throw new IOException("Path do arquivo vazio ou null.");
		}
		
		List<String> lines = util.readFileTXT(path);
		
		List<Integer> inputVertexes = new ArrayList<Integer>();
		List<Integer> outputVertexes = new ArrayList<Integer>();
		List<Double> values = new ArrayList<Double>();
		
		int numVertexes = Integer.parseInt(lines.get(PRIMEIRA_LINHA));
		int index = 1;
		
		while(index < lines.size()) {
			String[] vertexes = util.split(lines.get(index), ESPACO_EM_BRANCO);
			int inputVertexe = Integer.parseInt(vertexes[0]);
			int outputVertexe = Integer.parseInt(vertexes[1]);
			double value = Double.parseDouble(vertexes[2]);
			
			inputVertexes.add(inputVertexe);
			outputVertexes.add(outputVertexe);
			values.add(value);
			index++;
		}
		
		Graph graph = new Graph(inputVertexes, outputVertexes,values, numVertexes);
		
		return graph;
	}

}
