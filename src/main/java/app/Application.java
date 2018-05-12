package app;

import api.API;

public class Application {
	
	public static void main(String[] args) {
		API a = new API();
		a.DFS(a.readGraph("./input.txt"), 5);
	}

}
