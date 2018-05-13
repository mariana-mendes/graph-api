package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	public final static String ADJACENCY_MATRIX = "AM";
	public final static String NOVA_LINHA = "\n";

	private Util util;

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

	public int getVertexNumber(Graph graph) {
		return graph.getVertexes().size();
	}

	public int getEdgeNumber(Graph graph) {
		return graph.getEdges().size();
	}

	public float getMeanEdge(Graph graph) {
		int grade = 0;
		grade = (2 * graph.getEdges().size()) / graph.getVertexes().size();
		return grade;
	}

	private int getGreaterVertex(Graph g) {
		int max = g.getVertexes().stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);
		return max;
	}

	public void BFS(Graph g, int vertex) {

		ArrayList<ArrayList<Integer>> adj = this.getAdjacencyList(g);
		boolean visited[] = new boolean[g.getVertexes().size() + 1];
		int depht[] = new int[g.getVertexes().size() + 1];
		int father[] = new int[g.getVertexes().size() + 1];
		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[vertex] = true;
		depht[vertex] = 0;
		queue.add(vertex);

		while (queue.size() != 0) {
			vertex = queue.pop();
			System.out.println(vertex + " - " + depht[vertex] + " - " + (father[vertex] == 0 ? "" : father[vertex]));
			ArrayList<Integer> turn = adj.get(vertex);
			int turnVertex;
			for (int i = 0; i < turn.size(); i++) {
				turnVertex = turn.get(i);
				if (!visited[turn.get(i)]) {
					depht[turnVertex] = depht[vertex] + 1;
					father[turnVertex] = vertex;
					visited[turn.get(i)] = true;
					queue.add(turn.get(i));
				}
			}
		}
	}

	//TO-DO: ADICIONAR PROFUNDIDAD DE CADA VERTICE
	public void DFS(Graph g, int vertex) {
		ArrayList<ArrayList<Integer>> adj = this.getAdjacencyList(g);
		boolean visited[] = new boolean[g.getVertexes().size() + 1];
		DFSUtil(vertex, visited, adj);
	}

	private void DFSUtil(int v, boolean visited[], ArrayList<ArrayList<Integer>> adj) {
		visited[v] = true;
		System.out.println(v + " ");

		ArrayList<Integer> turn = adj.get(v);
		for (Integer vertexTurn : turn) {
			if (!visited[vertexTurn]) {
				DFSUtil(vertexTurn, visited, adj);
				}
		}
	}

	public void graphRepresentation(Graph g, String type) {
		if(type.equalsIgnoreCase(ADJACENCY_LIST)) {
			this.printAdjacencyList(g);
		} else if(type.equals(ADJACENCY_MATRIX)) {
			this.printAdjacencyMatrix(g);
		}
	}

	private void printAdjacencyMatrix(Graph graph) {
		double[][] adjacencyMatrix = getAdjacencyMatrix(graph);
		String saida = "";
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				saida += Double.toString(adjacencyMatrix[i][j]) + ESPACO_EM_BRANCO;
			}

			saida += NOVA_LINHA;
		}

		System.out.println(saida);
		System.out.println();
	}

	private double[][] getAdjacencyMatrix(Graph graph) {
		double[][] adjacencyMatrix = initAdjacencyMatrix(graph);
		try {
			Set<Edge> edges = graph.getEdges();
			Set<Integer> vertexes = graph.getVertexes();
			/*
			for (Edge e : edges) {
				Set<Integer> inputVertexes = e.getEdge().keySet();
				for (Integer v : inputVertexes) {
					Integer outputVertexe = e.getEdge().get(v);
					adjacencyMatrix[v.intValue()][outputVertexe.intValue()] = 1;
					adjacencyMatrix[outputVertexe.intValue()][v.intValue()] = 1;
				}
			}*/
			Integer valueMap;
			for (Integer vertex : vertexes) {
				for (Edge edge : edges) {
					valueMap = edge.getEdge().get(vertex);
					if (valueMap != null) {
						Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
						pair.put(vertex, valueMap);
						double pesoAresta = edge.getWeight(pair);
						adjacencyMatrix[vertex-1][valueMap-1] = pesoAresta;
						adjacencyMatrix[valueMap-1][vertex-1] = pesoAresta;
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return adjacencyMatrix;
	}

	private double[][] initAdjacencyMatrix(Graph graph) {
		double[][] adjacencyMatrix = new double[graph.getVertexes().size()][graph.getVertexes().size()];
		/*int j = 1;
		for (int i = 1; i < adjacencyMatrix.length; i++) {
			adjacencyMatrix[i][0] = j;
			j++;
		}
		int k = 1;
		for (int i = 1; i < adjacencyMatrix.length; i++) {
			adjacencyMatrix[0][k] = k;
			k++;
		}*/
		return adjacencyMatrix;
	}

	private void printAdjacencyList(Graph g) {
		ArrayList<ArrayList<Integer>> list = this.getAdjacencyList(g);
		ArrayList<Integer> vertexes;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) != null) {
				System.out.println();
				System.out.print(i + " -");
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
		adjacency = this.util.fillArrayList(maxVertex);

		Set<Edge> edges = g.getEdges();
		Integer valueMap;
		for (Integer vertex : vertexes) {
			for (Edge edge : edges) {
				valueMap = edge.getEdge().get(vertex);
				if (valueMap != null) {
					adjacency.get(valueMap).add(vertex);
					adjacency.get(vertex).add(valueMap);
				}
			}
		}
		return adjacency;
	}

}
