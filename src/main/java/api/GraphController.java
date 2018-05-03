package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import graph.Graph;

public class GraphController {
	
	Util util;
	public GraphController() {
		util = new Util();
	}
	
	public Graph readGraph(String nameFileTXT) throws IOException {
		List<String> lines = util.readFileTXT(nameFileTXT);
		
		List<Integer> inputVertexes = new ArrayList<Integer>();
		List<Integer> outputVertexes = new ArrayList<Integer>();
		
		int n = Integer.parseInt(lines.get(0));
		int j = 1;
		
		while(n > 0) {
			String[] vertexes = lines.get(j).split(" ");
			int inputVertexe = Integer.parseInt(vertexes[0]);
			int outputVertexe = Integer.parseInt(vertexes[1]);
			inputVertexes.add(inputVertexe);
			outputVertexes.add(outputVertexe);
			n--;
			j++;
		}
		
		Graph graph = new Graph(inputVertexes, outputVertexes);
		
		return graph;
	}

}
