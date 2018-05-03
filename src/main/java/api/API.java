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
	
}
