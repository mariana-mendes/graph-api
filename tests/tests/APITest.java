package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import api.API;
import graph.Graph;

public class APITest {

	Graph graph;
	API api;

	@Before
	public void TestAPI() {
		api = new API();
		graph = new Graph();
	}

	@Test
	public void testGetVertexNumber() {

		graph = api.readGraph("input3.txt");
		Assert.assertEquals(5, api.getVertexNumber(graph));
		Assert.assertNotEquals(0, api.getVertexNumber(graph));
		Assert.assertNotEquals(2, api.getVertexNumber(graph));
	}

	@Test
	public void testGetEdgeNumber() {

		graph = api.readGraph("input3.txt");
		Assert.assertEquals(5, api.getVertexNumber(graph));
		Assert.assertNotEquals(4, api.getEdgeNumber(graph));
		Assert.assertNotEquals(0, api.getEdgeNumber(graph));

	}

	@Test
	public void testGetMeanEdge() {

		graph = api.readGraph("input.txt");
		Assert.assertEquals(2, api.getMeanEdge(graph), 0);
		Assert.assertNotEquals(0, api.getMeanEdge(graph));
		Assert.assertNotEquals(5, api.getMeanEdge(graph));
	}

	@Test
	public void testShortestPath() {
		String caminho;
		graph = api.readGraph("input3.txt");
		caminho = api.shortestPath(graph, 1, 5);
		Assert.assertEquals("1 5", caminho);
		
		graph = api.readWeightedGraph("input4.txt");
		caminho = api.shortestPath(graph, 1, 5);
		Assert.assertEquals("1 2 5", caminho);

		graph = api.readWeightedGraph("input2.txt");
		caminho = api.shortestPath(graph, 1, 5);
		Assert.assertEquals("1 2 3 5", caminho);

	}

	@Test
	public void testGraphRepresentationSemPeso() {
		Graph g;
		String adjList, adjMatrix;
		
		String list = "\n";
		list += "1 - 2 5" + "\n";
		list += "2 - 1 5" + "\n";
		list += "3 - 5" + "\n";
		list += "4 - 5" + "\n";
		list += "5 - 1 2 3 4";
		
		String matrix = "";
		matrix += "  1 2 3 4 5" + "\n";
		matrix += "1 0 1 0 0 1" + "\n";
		matrix += "2 1 0 0 0 1" + "\n";
		matrix += "3 0 0 0 0 1" + "\n";
		matrix += "4 0 0 0 0 1" + "\n";
		matrix += "5 1 1 1 1 0";
		
		g = api.readGraph("input3.txt");
		adjList = api.graphRepresentation(g, "AL");
		assertEquals(list, adjList);
		
		adjMatrix = api.graphRepresentation(g, "AM");
		assertEquals(matrix, adjMatrix);
	}
	
	@Test
	public void testGraphRepresentationComPeso() {
		Graph g;
		String adjList, adjMatrix;
		
		String list = "\n";
		list += "1 - 5 2" + "\n";
		list += "2 - 1 5" + "\n";
		list += "3 - 5" + "\n";
		list += "4 - 5" + "\n";
		list += "5 - 1 2 4 3";
		
		String matrix = "";
		matrix += "  1 2 3 4 5" + "\n";
		matrix += "1 0 0.1 0 0 1" + "\n";
		matrix += "2 0.1 0 0 0 0.2" + "\n";
		matrix += "3 0 0 0 -9.5 5" + "\n";
		matrix += "4 0 0 -9.5 0 2.3" + "\n";
		matrix += "5 1 0.2 5 2.3 0";
		
		g = api.readWeightedGraph("input4.txt");
		//adjList = api.graphRepresentation(g, "AL");
		//assertEquals(list, adjList);
		
		adjMatrix = api.graphRepresentation(g, "AM");
		assertEquals(matrix, adjMatrix);
	}

	@Test
	public void testBFS() {
		// 2 ------- 0
		// / \
		// 1---5 ------- 1
		// / \
		// 4 3 ------- 2
		//String bfsResult = "2 - 0 - " + System.lineSeparator() + "1 - 1 - 2" + System.lineSeparator() + "5 - 1 - 2"
				//+ System.lineSeparator() + "4 - 2 - 5" + System.lineSeparator() + "3 - 2 - 5" + System.lineSeparator();
		//assertEquals(bfsResult, api.BFS(api.readGraph("input.txt"), 2));
		
		String result = "1 - 0 -\n" + 
				"2 - 1 1\n" + 
				"3 - 2 5\n" + 
				"4 - 2 5\n" + 
				"5 - 1 1\n";
		assertEquals(result, api.BFS(api.readGraph("input3.txt"), 1));
	}
	
	@Test
	public void testDFS() {
		String result = "1 - 1 5\n" + 
				"2 - 2 1\n" + 
				"3 - 1 5\n" + 
				"4 - 1 5\n" + 
				"5 - 0 -\n";
		assertEquals(result, api.DFS(api.readGraph("input3.txt"), 5));
	}

	@Test
	public void testConnectedGraph() {
		graph = api.readGraph("input.txt");
		Assert.assertTrue(api.connected(graph));
	}

}
