package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Edge {

	private final static double VALUE_DEFAULT = 1;

	private Map<Map<Integer, Integer>, Double> edge;

	public Edge(int vertex1, int vertex2) {
		Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
		pair.put(vertex1, vertex2);
		this.edge = new HashMap<Map<Integer, Integer>, Double>();
		this.edge.put(pair, VALUE_DEFAULT);
	}
	
	public Edge(int vertex1, int vertex2, double value) {
		Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
		pair.put(vertex1, vertex2);
		this.edge = new HashMap<Map<Integer, Integer>, Double>();
		this.edge.put(pair, value);
	}

	public Map<Map<Integer, Integer>, Double> getWeightedEdge(){
		return this.edge;
	}
	
	public Map<Integer, Integer> getEdge() {
		Map<Integer, Integer> e = null;
		Set<Map<Integer, Integer>> key = this.edge.keySet();
		for (Map<Integer, Integer> map : key) {
			e = map;
		}
		return e;
	}
	
	public double getWeight(Map<Integer, Integer> e) throws Exception {
		if(!this.edge.containsKey(e)) {
			throw new Exception("Aresta inexistente.");
		}
		return this.edge.get(e);
	}

}
