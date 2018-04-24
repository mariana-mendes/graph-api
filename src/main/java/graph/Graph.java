package graph;

import java.util.List;
import java.util.HashMap;

import java.util.Map;
import java.util.Set;

public class Graph {

	private Set<Integer> vertexes;
	private Set<Edge> edges;

	public Graph() {

	}

	public Graph(List vertex1, List vertex2, List value, Set edges) {
		
		this.buildEdges(vertex1,vertex2,value);
		
	}

	private void buildEdges(List vertex1, List vertex2, List value) {
		for (int i = 0; i < vertex1.size(); i++) {
			//erro object - int
			//Edge e = new Edge(value.get(i), vertex1.get(i),vertex2.get(i));
			//this.edges.add(e);
		}
		
	}


}
