package app;

import api.API;

public class Application {
	
	public static void main(String[] args) {
		API a = new API();
		a.DFS(a.readGraph("./input.txt"), 1);
		a.graphRepresentation(a.readGraph("./input.txt"), "AL");
	//	a.DFS(a.readGraph("./input.txt"), 5);
		//a.graphRepresentation(a.readGraph("./input.txt"), "AL");
	}

}
