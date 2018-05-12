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
	
	API api;
	
	@Before
	public void criaAPI() {
		api = new API();
		
	}
	@Test
	public void test() {
		Graph graph;
		graph = api.readGraph("input.txt");
		Set<Integer> v = graph.getVertexes();
		for (Integer i: v) {
			System.out.println(i);
		}
		
		Set<Edge> e = graph.getEdges();
		for(Edge ee :e) {
			System.out.println(ee.getEgde());
		}
	}

}
