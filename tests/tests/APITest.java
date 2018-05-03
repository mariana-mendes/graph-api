package tests;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import api.API;
import api.GraphController;
import graph.Edge;
import graph.Graph;

public class APITest {
	
	//API api;
	GraphController graphController;
	
	@Before
	public void criaAPI() {
		//api = new API();
		graphController = new GraphController();
		
	}
	@Test
	public void test() {
		try {
			Graph graph = graphController.readGraph("teste.txt");
			Set<Integer> v = graph.getVertexes();
			for (Integer i: v) {
				System.out.println(i);
			}
			
			Set<Edge> e = graph.getEdges();
			for(Edge ee :e) {
				System.out.println(ee.getEdge());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
