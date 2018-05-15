package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import graph.Edge;
import graph.Graph;
import api.Util;

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

	public String BFS(Graph g, int vertex) {
		String resultBFS = "";
		ArrayList<ArrayList<Integer>> adj = this.getAdjacencyList(g);
		boolean visited[] = new boolean[g.getVertexes().size() + 1];
		int depht[] = new int[g.getVertexes().size() + 1];
		int father[] = new int[g.getVertexes().size() + 1];
		int vertexesVisited[] =  new int[g.getVertexes().size() + 1];
		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[vertex] = true;
		depht[vertex] = 0;
		queue.add(vertex);
		vertexesVisited[vertex] = vertex;

		while (queue.size() != 0) {
			vertex = queue.pop();
			vertexesVisited[vertex] = vertex;
			ArrayList<Integer> turn = adj.get(vertex);
			int turnVertex;
			for (int i = 0; i < turn.size(); i++) {
				turnVertex = turn.get(i);
				if (!visited[turn.get(i)]) {
					depht[turnVertex] = depht[vertex] + 1;
					father[turnVertex] = vertex;
					visited[turnVertex] = true;
					queue.add(turn.get(i));
				}
			}
		}
		
		int n = g.getVertexes().size() + 1;
		for (int j = 1; j < n; j++) {
			resultBFS += vertexesVisited[j] + " - " + depht[j] + " " + ((father[j] == 0 ? "-" : father[j]));
			resultBFS += NOVA_LINHA;
		}
		
		return resultBFS;
	}

	public String DFS(Graph g, int vertex) {
		ArrayList<ArrayList<Integer>> adj = this.getAdjacencyList(g);
		int n = g.getVertexes().size()+1;
		boolean visited[] = new boolean[n];
		int depht[] = new int[n];
		int father[] = new int[n];
		int vertexesVisited[] =  new int[n];
		int i = 0;
		DFSUtil(vertex, visited, adj, depht, father, vertexesVisited, i);
		
		String saida = "";
		for (int j = 1; j < n; j++) {
			saida += vertexesVisited[j] + " - " + depht[j] + " " + ((father[j] == 0 ? "-" : father[j]));
			saida += NOVA_LINHA;
		}
		return saida;
	}

	private void DFSUtil(int v, boolean visited[], ArrayList<ArrayList<Integer>> adj, int[] depht, int[] father, int vertexesVisited[], int i) {
		visited[v] = true;
		vertexesVisited[v] = v;
		i++;
		ArrayList<Integer> turn = adj.get(v);
		for (Integer vertexTurn : turn) {
			if (!visited[vertexTurn]) {
				depht[vertexTurn] = depht[v] + 1;
				father[vertexTurn] = v;
				DFSUtil(vertexTurn, visited, adj, depht, father, vertexesVisited, i);
			}
		}
	}

	public boolean connected(Graph graph) {
		ArrayList<ArrayList<Integer>> adjMatrix = this.getAdjacencyList(graph);
		boolean visited[] = new boolean[graph.getVertexes().size() + 1];
		isConnected(1, visited, adjMatrix);
		for (int i = 0; i < visited.length; i++) {
			if(visited[i] = false) {
				return false;
			}
		}
		return true;
	}

	private void isConnected(int v, boolean[] visited, ArrayList<ArrayList<Integer>> adjMatrix) {
		visited[v] = true;
		ArrayList<Integer> turn = adjMatrix.get(v);
		for (Integer vertex : turn) {
			if (!visited[vertex]) {
				isConnected(vertex, visited, adjMatrix);
			}
		} 
		
	}

	public String graphRepresentation(Graph g, String type) {
		if (type.equalsIgnoreCase(ADJACENCY_LIST)) {
			return printAdjacencyList(g);
		} else if (type.equals(ADJACENCY_MATRIX)) {
			return this.printAdjacencyMatrix(g);
		}

		return "";
	}

	private String printAdjacencyMatrix(Graph graph) {
		double[][] adjacencyMatrix = getAdjacencyMatrix(graph);
		String saida = "";
		for (int i = 0; i <= adjacencyMatrix.length; i++) {
			if(i == 0) {
				saida += " " + ESPACO_EM_BRANCO;
			} else {
				saida += i + ESPACO_EM_BRANCO;
			}
		}
		saida = saida.substring(0, saida.length() - 1);
		saida += NOVA_LINHA;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			saida += (i+1) + ESPACO_EM_BRANCO;
			for (int j = 0; j < adjacencyMatrix.length; j++) {

				if(adjacencyMatrix[i][j] - (int) adjacencyMatrix[i][j] == 0) {
					int result = (int) (adjacencyMatrix[i][j]);
					saida += Integer.toString(result) + ESPACO_EM_BRANCO;
				} else {
					saida += Double.toString(adjacencyMatrix[i][j]) + ESPACO_EM_BRANCO;
				}
			}
			saida = saida.substring(0, saida.length() - 1);
			saida += NOVA_LINHA;
		}
		saida = saida.substring(0, saida.length() - 1);
		return saida;
	}

	private double[][] getAdjacencyMatrix(Graph graph) {
		double[][] adjacencyMatrix = initAdjacencyMatrix(graph);
		Set<Integer> vertexes = graph.getVertexes();
		Set<Edge> edges = graph.getEdges();
		Integer valueMap;
		for (Integer vertex : vertexes) {
			for (Edge edge : edges) {
				valueMap = edge.getEdge().get(vertex);
				if (valueMap != null) {
					Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
					pair.put(vertex, valueMap);
					Double pesoAresta = edge.getWeightedEdge().get(pair);
					if (pesoAresta == null) {
						adjacencyMatrix[vertex - 1][valueMap - 1] = 1;
						adjacencyMatrix[valueMap - 1][vertex - 1] = 1;
					} else {
						adjacencyMatrix[vertex - 1][valueMap - 1] = edge.getWeightedEdge().get(pair);
						adjacencyMatrix[valueMap - 1][vertex - 1] = edge.getWeightedEdge().get(pair);
					}
				}
			}
		}

		return adjacencyMatrix;
	}

	private double[][] initAdjacencyMatrix(Graph graph) {
		double[][] adjacencyMatrix = new double[graph.getVertexes().size()][graph.getVertexes().size()];
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				adjacencyMatrix[i][j] = 0;
			}
		}
		return adjacencyMatrix;
	}

	private String printAdjacencyList(Graph g) {
		ArrayList<ArrayList<Integer>> list = this.getAdjacencyList(g);
		ArrayList<Integer> vertexes;
		String saida = "";
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) != null) {
				saida += NOVA_LINHA + i + " -";
				vertexes = list.get(i);
				for (Integer integer : vertexes) {
					saida += ESPACO_EM_BRANCO + integer;
					Set<Edge> e = g.getEdges();
					for (Edge edge : e) {
						Map<Integer, Integer> v = new HashMap<Integer, Integer>();
						v.put(i, integer);
						if ((edge.getWeightedEdge().get(v)) != null) {
							if(edge.getWeightedEdge().get(v) - edge.getWeightedEdge().get(v).intValue() == 0) {
								saida += "(" + edge.getWeightedEdge().get(v).intValue() + ")";
							} else {
								saida += "(" + edge.getWeightedEdge().get(v) + ")";
							}
						}

						v = new HashMap<Integer, Integer>();
						v.put(integer, i);
						if ((edge.getWeightedEdge().get(v)) != null) {
							if(edge.getWeightedEdge().get(v) - edge.getWeightedEdge().get(v).intValue() == 0) {
								saida += "(" + edge.getWeightedEdge().get(v).intValue() + ")";
							} else {
								saida += "(" + edge.getWeightedEdge().get(v) + ")";
							}
						}

					}
				}
			}
		}

		return saida;
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
					Collections.sort(adjacency.get(valueMap));
					adjacency.get(vertex).add(valueMap);
					Collections.sort(adjacency.get(vertex));
				}
			}
		}
		return adjacency;
	}

	public String shortestPath(Graph graph, int v1, int v2) throws Exception {
		Set<Integer> vertexes = graph.getVertexes();
		if (!vertexes.contains(v1) && !vertexes.contains(v2)) {
			throw new Exception("V�rtices n�o fazem parte do grafo.");
		}

		return floydWarshallPath(graph, v1, v2);
	}

	private String floydWarshallPath(Graph graph, int v1, int v2) {
		double[][] distances = this.getAdjacencyMatrix(graph);
		int numVertex = distances.length;
		int[][] next = new int[numVertex][numVertex];
		Set<Edge> edges = graph.getEdges();
		for (Edge edge : edges) {
			Integer[] v = edge.getVertexes();
			next[v[0] - 1][v[1] - 1] = v[1];
		}
		distances = adjustFMMatrix(distances);
		for (int k = 0; k < numVertex; k++) {
			for (int i = 0; i < numVertex; i++) {
				for (int j = 0; j < numVertex; j++) {
					if (distances[i][k] + distances[k][j] < distances[i][j]) {
						distances[i][j] = distances[i][k] + distances[k][j];
						next[i][j] = next[i][k];
					}
				}
			}
		}

		ArrayList<Integer> path = buildPath(v1, v2, next);
		String ans = path.toString().replace(",", "").replace("[", "").replace("]", "");

		return ans;
	}

	private double[][] adjustFMMatrix(double[][] distances) {

		int numVertex = distances.length;
		for (int i = 0; i < numVertex; i++) {
			for (int j = 0; j < numVertex; j++) {
				if (i != j && distances[i][j] == 0) {
					distances[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}

		return distances;
	}

	private ArrayList<Integer> buildPath(int v1, int v2, int[][] next) {

		ArrayList<Integer> path = new ArrayList<>();
		if (!((v1 - 1 >= 0 && v1 - 1 < next.length) && (v2 - 1 >= 0 && v2 - 1 < next.length))) {
			return path;
		}

		path.add(v1);
		while (v1 != v2) {
			v1 = next[v1 - 1][v2 - 1];
			path.add(v1);
		}

		return path;
	}
	
	private String BFS(Graph g, int vertex, int[] depth) {
		String resultBFS = "";
		ArrayList<ArrayList<Integer>> adj = this.getAdjacencyList(g);
		boolean visited[] = new boolean[g.getVertexes().size() + 1];
		int father[] = new int[g.getVertexes().size() + 1];
		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[vertex] = true;
		depth[vertex] = 0;
		queue.add(vertex);
		
		while (queue.size() != 0) {
			vertex = queue.pop();
			resultBFS += vertex + " - " + depth[vertex] + " - " + father[vertex];
			resultBFS += NOVA_LINHA;
			ArrayList<Integer> turn = adj.get(vertex);
			int turnVertex;
			for (int i = 0; i < turn.size(); i++) {
				turnVertex = turn.get(i);
				if (!visited[turn.get(i)]) {
					depth[turnVertex] = depth[vertex] + 1;
					father[turnVertex] = vertex;
					visited[turnVertex] = true;
					queue.add(turn.get(i));
				}
			}
		}
		return resultBFS;
	}

	public String mst(Graph g) {
		String result = "";
		int size = g.getVertexes().size();
		int[] parents = new int[size + 1];
		for (int i = 0; i < size; i++) {
			parents[i] = -1;
		}

		ArrayList<Integer> input = new ArrayList<>();
		ArrayList<Integer> output = new ArrayList<>();

		Edge[] sortedEdges = getEdgesSorted(g.getEdges());

		int maximumNumberOfEdges = size - 1;
		int edgeIndex = 0;
		int edgeNumber = 0;

		while (edgeNumber < maximumNumberOfEdges) {

			Edge actualEdge = sortedEdges[edgeIndex];

			int src = actualEdge.getSrc();
			int dest = actualEdge.getDest();

			int srcParent = find(parents, src);
			int destParent = find(parents, dest);

			
			boolean belongToSameSubset = (srcParent == destParent);

			if (!belongToSameSubset) {
				edgeNumber++;
				union(parents, srcParent, destParent);
				input.add(src);
				output.add(dest);
				result += src + " " + dest + " ";
				result += NOVA_LINHA;
			}

			edgeIndex++;
			if (edgeIndex - 1 == maximumNumberOfEdges) {
				break;
			}
		}
		int root = -1;
		Graph graph = new Graph(input,output,size);
		Set<Integer> v = graph.getVertexes();
		for (Integer integer : v) {
			root = integer;
		}
		int[] depth = new int[this.getGreaterVertex(graph)+1];

		return this.BFS(graph, root, depth);

		
	}

	private Edge[] getEdgesSorted(Set<Edge> edges) {
		Edge[] sortedEdges = edges.toArray(new Edge[edges.size()]);
		Arrays.sort(sortedEdges);
		return sortedEdges;
	}

	private static int find(int[] parents, int v) {
		if (parents[v] == -1) {
			return v;
		}
		return find(parents, parents[v]);
	}

	private static void union(int[] parents, int x, int y) {
		int xset = find(parents, x);
		int yset = find(parents, y);
		parents[xset] = yset;
	}

}
