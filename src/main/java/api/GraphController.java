package api;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import graph.Edge;
import graph.Graph;

public class GraphController {

	public final static int PRIMEIRA_LINHA = 0;
	public final static String ESPACO_EM_BRANCO = " ";
	public final static String ADJACENCY_LIST = "AL";
	public final static String ADJACENCY_MATRIZ = "AM";

	Util util;

	public GraphController() {
		util = new Util();
	}

	public Graph readGraph(String path) throws IOException {
		if (path == null || path.equals("")) {
			throw new IOException("Path do arquivo vazio ou null.");
		}

		List<String> lines = util.readFileTXT(path);

		List<Integer> inputVertexes = new ArrayList<Integer>();
		List<Integer> outputVertexes = new ArrayList<Integer>();

		int numVertexes = Integer.parseInt(lines.get(PRIMEIRA_LINHA));
		int index = 1;

		while (index < lines.size()) {
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
		if (path == null || path.equals("")) {
			throw new IOException("Path do arquivo vazio ou null.");
		}

		List<String> lines = util.readFileTXT(path);

		List<Integer> inputVertexes = new ArrayList<Integer>();
		List<Integer> outputVertexes = new ArrayList<Integer>();
		List<Double> values = new ArrayList<Double>();

		int numVertexes = Integer.parseInt(lines.get(PRIMEIRA_LINHA));
		int index = 1;

		while (index < lines.size()) {
			String[] vertexes = util.split(lines.get(index), ESPACO_EM_BRANCO);
			int inputVertexe = Integer.parseInt(vertexes[0]);
			int outputVertexe = Integer.parseInt(vertexes[1]);
			double value = Double.parseDouble(vertexes[2]);

			inputVertexes.add(inputVertexe);
			outputVertexes.add(outputVertexe);
			values.add(value);
			index++;
		}

		Graph graph = new Graph(inputVertexes, outputVertexes, values, numVertexes);

		return graph;
	}
	
	public int getVertexNumber(Graph graph){
		
		return graph.getVertexes().size();
		
	}
	
	public int getEdgeNumber(Graph graph){
		 return graph.getEdges().size();
		
	}
	
	
	public float getMeanEdge(Graph graph){
		int grade = 0;
		grade = (2*graph.getEdges().size())/graph.getVertexes().size();
		return grade;
		
	}

	private int getGreaterVertex(Graph g) {
		int max = g.getVertexes().stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);
		return max;
	}

	public void BFS(Graph g, int vertex) {
	}

	public void graphRepresentation(Graph g, String type) {
		if(type.equals(ADJACENCY_LIST)) {
			this.printAdjacencyList(g);
		}else if(type.equals(ADJACENCY_MATRIZ)) {
			this.printAdjacencyMatrix(g);
		}
	}
	
	private void printAdjacencyMatrix(Graph g) {
		int[][] matrizAdjacencia = new int[g.getVertexes().size()+1][g.getVertexes().size()+1];
		
		int j = 1;
		for (int i = 1; i < matrizAdjacencia.length; i++) {
			matrizAdjacencia[i][0] = j;
			j++;
		}
		
		int k = 1;
		for (int i = 1; i < matrizAdjacencia.length; i++) {
			matrizAdjacencia[0][k] = k;
			k++;
		}
		
		
		Set<Edge> edges = g.getEdges();
		
		for(Edge e: edges) {
			Set<Integer> inputVertexes = e.getEgde().keySet();
			
			for(Integer v:inputVertexes) {
				Integer outputVertexe = e.getEgde().get(v);
				matrizAdjacencia[v.intValue()][outputVertexe.intValue()] = 1;
				matrizAdjacencia[outputVertexe.intValue()][v.intValue()] = 1;
			}
		}
	}
	
	private void printAdjacencyList(Graph g) {
		ArrayList<ArrayList<Integer>> list = this.getAdjacencyList(g);
		ArrayList<Integer> vertexes;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				System.out.println();
				System.out.print(i + "-");
				vertexes = list.get(i);
				for (int j = 0; j < vertexes.size(); j++) {
					System.out.print(" " + vertexes.get(j));
				}
			}
		}
	}

	private ArrayList<ArrayList<Integer>> getAdjacencyList(Graph g) {
		int maxVertex = this.getGreaterVertex(g);
		Set<Integer> vertexes = g.getVertexes();
		ArrayList<ArrayList<Integer>> adjacency;
		adjacency = this.fillArrayList(maxVertex);

		Set<Edge> edges = g.getEdges();
		Integer valueMap;
		for (Integer vertex : vertexes) {
			for (Edge edge : edges) {
				valueMap = edge.getEgde().get(vertex);
				if (valueMap != null) {
					adjacency.get(valueMap).add(vertex);
					adjacency.get(vertex).add(valueMap);
				}
			}
		}
		return adjacency;
	}

	/**
	 * Popula um ArrayList com outros ArrayLists vazios, deixando um array no
	 * formato correto para montar uma lista de adjacencia
	 * 
	 * @param maxVertex
	 *            - número de indices preenchidos por arrayLists
	 * @return ArrayLists de arrayLists
	 */
	private ArrayList<ArrayList<Integer>> fillArrayList(int maxVertex) {
		ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= maxVertex; i++) {
			ArrayList<Integer> empty = new ArrayList<Integer>();
			array.add(empty);
		}
		return array;
	}
}
