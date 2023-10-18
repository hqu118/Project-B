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
		// return true if contains the key
		if (adjacencyMap.containsKey(node)) {
			return true;
		} // else check whether there is target node that is the same
		for (EdgesLinkedList edgesLinkedList : adjacencyMap.values()) {
			Edge edgeInList = edgesLinkedList.get(0);
			while (edgeInList != null) {
				if (node.equals(edgeInList.getTarget())) {
					return true;
				}

				edgeInList = edgeInList.getNext();
			}
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
		Set<Node> nodeVisited = new HashSet<>();
		NodesStackAndQueue nodesDuringSearch = new NodesStackAndQueue();// create a new stack and queue to store the nodes
		
		Node sourceNode = root; // assign root node to the first source node
		Node targetNode = root; // initialize the targetNode
		nodesDuringSearch.append(sourceNode);
		
		EdgesLinkedList edgesLinkedList = adjacencyMap.get(sourceNode); // initialize the edges linked list for the root node
		Edge edgeOfSourceNode = edgesLinkedList.get(0); // initialize the first edge from the source node

		while (!nodeVisited.containsAll(adjacencyMap.keySet())) { // repeat nodeVisited contains all nodes of the graph

			edgesLinkedList = adjacencyMap.get(sourceNode); //get the edges linked list for edges starting with the source node
			if (!nodeVisited.contains(sourceNode)) {
				nodeVisited.add(sourceNode);
			}

			for (int i = 0; i < edgesLinkedList.size(); i++) {
				edgeOfSourceNode = edgesLinkedList.get(i); // edgeOfSourceNode will be the edges of the same source node

				if (edgeOfSourceNode.getWeight() == weight) {
					
					return edgeOfSourceNode;
					
				} else {
					targetNode = edgeOfSourceNode.getTarget();
					
					if (!nodeVisited.contains(targetNode)) {
						nodesDuringSearch.append(targetNode);
					}
				}
			}
			sourceNode = nodesDuringSearch.pop(); // get the next node which will be whatever target node original source node has edge to
		}

		return null;
	}

	/**
	 * Returns the weight of the Edeg with Node source and Node target if the given
	 * Edge is inside the graph. If there is no edge with the specified source and
	 * target, the method returns -1 You must use Depth First Search (DFS) strategy
	 * starting from the root.
	 * <p>
	 * RULES You can create a data structure to keep track of the visited nodes
	 * Set<Node> visited = new HashSet<>(); If you don't keep track of the visited
	 * nodes the method will run forever!
	 * <p>
	 * In addition to the data structure visited you can only create new data
	 * structures of type
	 * <p>
	 * NodesStackAndQueue and EdgesLinkedList
	 *
	 * @param source
	 * @param target
	 * @return the weight of the first encountered edge with source and target, -1
	 *         if no edge with the given source and target exists
	 */
	public int searchWeightByEdge(Node source, Node target) {
		
		Set<Node> nodeVisited = new HashSet<>();
		NodesStackAndQueue nodesDuringSearch = new NodesStackAndQueue();// create a new stack and queue to store the nodes
		
		Node sourceNode = root; // assign root node to the first source node
		nodesDuringSearch.append(sourceNode);
		
		while (!nodeVisited.containsAll(adjacencyMap.keySet())) {
			if (!nodeVisited.contains(sourceNode)) {
				nodeVisited.add(sourceNode);
			}
			
			sourceNode = nodesDuringSearch.pop();
		}
		return -1;
	}


	public Path computeShortestPath(Node source, Node target) {
		int[] distanceFromSourceNode = new int[adjacencyMap.keySet().size()]; // create array to store distance values
																				// of nodes
		HashMap<Node, Node> previousNodeInShortestPath = new HashMap<>(); // create the hashmap for each node followed
																			// by its previous node in shortest path

		NodesStackAndQueue nodesToBeProcessed = new NodesStackAndQueue(); // NodesStackAndQueue for storing nodes that
																			// needs to be processed
		NodesStackAndQueue nodeVisited = new NodesStackAndQueue(); // NodesStackAndQueue for storing all nodes in
																	// shortest path from source to target
		NodesStackAndQueue allNodesInGraph = new NodesStackAndQueue();// NodesStackAndQueue for storing all nodes in
																		// graph

		Node leadNode = source; // this will be the leadNode value for each relaxation, starting with source
								// node as lead node
		Node nodeVisitedInShortestPath = target;
		Edge edgesOfLeadNode = adjacencyMap.get(leadNode).get(0); // this will be every edge with leadNode as source
																	// node

		int indexOfNodes = 0;
		int indexOfSourceNode = 0;
		int totalCost = 0;

		// for each node inside the graph, set the distance values at its corresponding
		// index it is stored in the hash map
		for (Node node : adjacencyMap.keySet()) {
			distanceFromSourceNode[indexOfNodes] = Integer.MAX_VALUE;
			previousNodeInShortestPath.put(node, null); // the previous node is null for each node

			nodesToBeProcessed.push(node); // store all the nodes as nodes to be processed
			allNodesInGraph.push(node);// store all the nodes as nodes to allNodesInGraph which help us to check with
										// index of nodes

			if (node.equals(source)) {
				indexOfSourceNode = indexOfNodes; // get the index of source node inside the distance array
			}

			indexOfNodes++;
		}

		distanceFromSourceNode[indexOfSourceNode] = 0; // set the distance for the source node to be 0

		nodeVisited.append(source);
		totalCost = distanceFromSourceNode[allNodesInGraph.getData().indexOf(target)];
		return new Path(totalCost, nodeVisited.getData());
	}

}