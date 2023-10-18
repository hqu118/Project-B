package main;

import java.util.HashMap;
import java.util.List;

public class Graph {
	private HashMap<Node, EdgesLinkedList> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	private Node root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */
	public Graph(List<String> edges, List<String> weights) {
		if (edges.isEmpty() || weights.isEmpty()) {
			throw new IllegalArgumentException("edges and weights are empty");
		}
		adjacencyMap = new HashMap<>();
		int i = 0;
		for (String edge : edges) {
			String[] split = edge.split(",");
			Node source = new Node(split[0]);
			Node target = new Node(split[1]);
			Edge edgeObject = new Edge(source, target, Integer.parseInt(weights.get(i)));
			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new EdgesLinkedList());
			}
			// adjacencyMap.get(source).append(edgeObject);
			if (i == 0) {
				root = source;
			}
			i++;
		}
	}
}