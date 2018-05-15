package tests;

import static org.junit.Assert.assertEquals;

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
		graph = api.readGraph("input.txt");
	}

	@Test
	public void test() {
		Graph graph;
		graph = api.readGraph("input.txt");
		Set<Integer> v = graph.getVertexes();
		for (Integer i : v) {
			System.out.println(i);
		}

		Set<Edge> e = graph.getEdges();
		for (Edge ee : e) {
			System.out.println(ee.getEdge());
		}

		System.out.println(api.graphRepresentation(graph, "AM"));
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

		// graph = api.readGraph("input.txt");
		Assert.assertEquals(2, api.getMeanEdge(graph), 0);
		Assert.assertNotEquals(0, api.getMeanEdge(graph));
		Assert.assertNotEquals(5, api.getMeanEdge(graph));
	}

	@Test
	public void testGraphRepresentationList() {
		// // 1 2
		//
		// // 2 5 2
		// // 5 3 / \
		// 1---5
		// // 4 5 / \
		// 1 5 4 3
		String list = System.lineSeparator();
		list += "1 - 5 2" + System.lineSeparator();
		list += "2 - 1 5" + System.lineSeparator();
		list += "3 - 5" + System.lineSeparator();
		list += "4 - 5" + System.lineSeparator();
		list += "5 - 1 2 4 3";
		System.out.println(api.graphRepresentation(graph, "AL"));
		assertEquals(list, api.graphRepresentation(graph, "AL"));
	}

	@Test
	public void testBFS() {
	//		 2     -------  0
	//      / \
	//     1---5   -------  1
	//        / \
	//       4   3 -------  2
	String bfsResult = "2 - 0 - " +System.lineSeparator()+
					   "1 - 1 - 2" + System.lineSeparator()+
					   "5 - 1 - 2" + System.lineSeparator()+
					   "4 - 2 - 5" + System.lineSeparator()+
					   "3 - 2 - 5" + System.lineSeparator();
	assertEquals(bfsResult, api.BFS(graph, 2));
	}

}
