package graph;

import java.util.HashMap;
import java.util.Map;

public class Edge {

	private final static double VALUE_DEFAULT = 1;

	private Map<Double, Map<Integer, Integer>> edgeWidth;
	private Map<Integer,Integer> edge;

	public Edge(double value, int vertex1, int vertex2) {
		Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
		pair.put(vertex1, vertex2);
		this.edgeWidth = new HashMap<Double, Map<Integer, Integer>>();
		edgeWidth.put(value, pair);
	}

	public Edge(int vertex1, int vertex2) {
		Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
		pair.put(vertex1, vertex2);
		this.edge = new HashMap<Integer, Integer>();
		edge.put(vertex1,vertex2);
	}
	
	public Map<Double, Map<Integer,Integer>> getEdgeWidth(){
		return this.edgeWidth;
	}
	
	public Map<Integer,Integer> getEgde(){
		return this.edge;
	}

}
