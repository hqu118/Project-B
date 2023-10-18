package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	
	public boolean isNodeInGraph(Node node) {
		if (node == null) {
			return false;
		}

		if (adjacencyMap.containsKey(node)) {
			return true;
		}

		return false;
	}

		/**
	 * This method finds an edge with a specific weight, if there are more than one
	 * you need to return the first you encounter. You must use Breath First Search
	 * (BFS) strategy starting from the root.
	 * <p>
	 * You can create a data structure to keep track of the visited nodes Set<Node>
	 * visited = new HashSet<>(); If you don't keep track of the visited nodes the
	 * method will run forever!
	 * <p>
	 * <p>
	 * In addition to the data structure visited you can only create new
	 * datastructures of type EdgesLinkedList and NodesStackAndQue
	 *
	 * @param weight
	 * @return the Edge with the specific weight, null if no edge with the specif
	 *         weight exists in teh graph
	 */
	public Edge searchEdgeByWeight(int weight) {
		if (weight < 0) {
			return null;
		}

		Set<Node> nodeVisited = new HashSet<>();
		NodesStackAndQueue nodesDuringSearch = new NodesStackAndQueue();// create a new stack and queue to store the nodes
		while (!nodeVisited.containsAll(adjacencyMap.keySet())) {
			EdgesLinkedList edgesLinkedList = adjacencyMap.get(root); // initialize the edges linked list for the root node
			Edge edgeOfSourceNode = edgesLinkedList.get(edgesLinkedList.size() - 1); // initialize the last edge from the source node

			if (!nodeVisited.contains(root)) {
				nodeVisited.add(root);
			}

			for (int i = 0; i < edgesLinkedList.size(); i++) {
				edgeOfSourceNode = edgesLinkedList.get(i); // edgeOfSourceNode will be the edges of the same source node
				if (edgeOfSourceNode.getWeight() == weight) {
					return edgeOfSourceNode;
				} else {
					Node targetNode = edgeOfSourceNode.getTarget();
					if (!nodeVisited.contains(targetNode)) {
						nodesDuringSearch.append(targetNode); //push the node into stack and queue so the last target node (if not in node visited) will be on the top and pop will return it
					}
				}
			}
			root = nodesDuringSearch.remove();
		}

		return null;
	}
}