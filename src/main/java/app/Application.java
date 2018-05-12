package app;

import api.API;

public class Application {
	
	public static void main(String[] args) {
		API a = new API();
		//so pra testar a representação em lista de ajdacencia
		a.graphRepresentation(a.readGraph("./input.txt"), "AL");
	}

}
