package app;

import api.API;

public class Application {
	
	public static void main(String[] args) {
		API a = new API();
		String ss = a.BFS(a.readGraph("./input.txt"), 1);
		System.out.println(ss);
	//	a.graphRepresentation(a.readGraph("./input.txt"), "AL");
	//	a.DFS(a.readGraph("./input.txt"), 5);
		//a.graphRepresentation(a.readGraph("./input.txt"), "AL");
	}

}
