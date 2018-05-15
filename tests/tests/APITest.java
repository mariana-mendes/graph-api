package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import api.API;
import graph.Edge;
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

		graph = api.readWeightedGraph("input2.txt");
		caminho = api.shortestPath(graph, 1, 5);
		Assert.assertEquals("1 2 3 5", caminho);

	}

	@Test
	public void testGraphRepresentationList() {
		String list = System.lineSeparator();
		list += "1 - 5 2" + System.lineSeparator();
		list += "2 - 1 5" + System.lineSeparator();
		list += "3 - 5" + System.lineSeparator();
		list += "4 - 5" + System.lineSeparator();
		list += "5 - 1 2 4 3";
		assertEquals(list, api.graphRepresentation(api.readGraph("input.txt"), "AL"));
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
