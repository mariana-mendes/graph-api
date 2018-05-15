package api;

import java.io.IOException;

import graph.Graph;

public class API {
	
	private GraphController controller;
	
	public API() {
		this.controller = new GraphController();
	}
	
	public Graph readGraph(String path)  {
		try {
			return this.controller.readGraph(path);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Graph readWeightedGraph(String path) {
		try {
			return this.controller.readWeightedGraph(path);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public int getVertexNumber(Graph graph){
		return controller.getVertexNumber(graph);
	}
	
	public int getEdgeNumber(Graph graph){
		 return controller.getEdgeNumber(graph);	
	}
	
	
	public float getMeanEdge(Graph graph){
		return controller.getMeanEdge(graph);
	}
	
	public String shortestPath(Graph g, int v1, int v2){
		String path = null;
		try {
			path = controller.shortestPath(g,v1,v2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
    
	private int minDistance(int[] distance, Boolean[] set) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String graphRepresentation(Graph g,String type) {
		return this.controller.graphRepresentation(g, type);
	}
	
	public String BFS(Graph g, int vertex) {
		return this.controller.BFS(g, vertex);
	}

	public void DFS(Graph g, int vertex) {
		this.controller.DFS(g, vertex);
	}

	public String MST(Graph g) {
		return this.controller.mst(g);
	}
	
}
