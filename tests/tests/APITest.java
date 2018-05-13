package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import api.API;
import api.GraphController;
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
	public void test() {
		Graph graph;
		graph = api.readWeightedGraph("input.txt");
		Set<Integer> v = graph.getVertexes();
		for (Integer i : v) {
			System.out.println(i);
		}

		Set<Edge> e = graph.getEdges();
		for(Edge ee :e) {
			System.out.println(ee.getEdge());
		}
		
		api.graphRepresentation(graph, "AM");
	}

	@Test
	public void testGetVertexNumber() {

		graph = api.readGraph("input.txt");
		Assert.assertEquals(5, api.getVertexNumber(graph));
		Assert.assertNotEquals(0, api.getVertexNumber(graph));
		Assert.assertNotEquals(2, api.getVertexNumber(graph));
	}

	@Test
	public void testGetEdgeNumber() {

		graph = api.readGraph("input.txt");
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

}
