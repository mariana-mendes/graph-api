package api;

import java.io.IOException;

import graph.Graph;

public class API {
	
	private GraphController controller;
	
	private Graph graph;
	
	public API() {
		
		this.graph = new Graph();
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
	
		return graph.getVertexes().size();
		
	}
	
	public int getEdgeNumber(Graph graph){
		 return graph.getEdges().size();
		
	}
	
	
	public float getMeanEdge(){
		int grade = 0;
		grade = (2*graph.getEdges().size())/graph.getVertexes().size();
		return grade;
		
	}
	
	public void getshortestpath(int v1, int v2){
		
		
	}
    
	private int minDistance(int[] distance, Boolean[] set) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
