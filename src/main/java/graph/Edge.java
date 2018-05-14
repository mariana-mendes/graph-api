package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Edge implements Comparable<Edge> {

	private final static Double VALUE_DEFAULT = null;
	private double weight;
	private int src;
	private int dest;

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
		this.weight = value;
		this.src = vertex1;
		this.dest = vertex2;
		this.edge.put(pair, new Double(value));
	}

	public Edge() {
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Map<Map<Integer, Integer>, Double> getWeightedEdge() {
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
		if (!this.edge.containsKey(e)) {
			throw new Exception("Aresta inexistente.");
		}
		return this.edge.get(e);
	}

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	@Override
	public int compareTo(Edge edge) {
		return Double.compare(this.weight, edge.getWeight());
	}
}
