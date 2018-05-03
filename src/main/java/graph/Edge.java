package graph;

import java.util.HashMap;
import java.util.Map;

public class Edge {

	private final static double VALUE_DEFAULT = 1;

	private Map<Double, Map<Integer, Integer>> edge;

	public Edge(double value, int vertex1, int vertex2) {
		Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
		pair.put(vertex1, vertex2);
		this.edge = new HashMap<Double, Map<Integer, Integer>>();
		edge.put(value, pair);
	}

	public Edge(int vertex1, int vertex2) {
		Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
		pair.put(vertex1, vertex2);
		this.edge = new HashMap<Double, Map<Integer, Integer>>();
		edge.put(VALUE_DEFAULT, pair);
	}
	
	public Map<Double, Map<Integer,Integer>> getEdge(){
		return edge;
	}

}
