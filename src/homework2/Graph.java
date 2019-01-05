package homework2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph<T> {
	
	private String name;
	
	/** Vector<Node> of graph vertices */
	private List<Node<T>> verticies;

	/** Vector<Edge> of edges in the graph */
	private List<Edge<T>> edges;
	  
	/** The vertex identified as the root of the graph */
	private Node<T> rootVertex;


	// Representation invariant:
	// A Graph g is a graph containing edges vertices and a root
	// All g's vertices are listed in vertices
	// All g's edges are listed in edges
	// g's root is kept in the field rootVertex 
	 
	// Abstraction function for every Node n:
	// name != "" && name != null && name is an instance of String
	// g doesn't contain the same node twice
	// g doesn't contain two edges with the same direction between the same nodes
	
	
	/**
	 * @effects creates a new Graph with this.name = name
	 * @modifies this
	 * @requires name != "" && name is an instance of String
	 * @param name: the name to be given to this
	 */
	public Graph(String name) {
		this.name = name;
		verticies = new ArrayList<Node<T>>();
		edges = new ArrayList<Edge<T>>();
		checkRep();
	}
	
	/**
	 * @effects returns the name of the graph
	 * @modifies none
	 * @requires none
	 */
	public String getName() {
		return this.name;
	}
	
		/**
	 * @effects resets all WeightedNodes color to White and discoveredByBackwardEdge to false.
	 * @modifies vertices
	 * @requires none
	 */
	public void resetDFSData() {
		for (Node<T> v : verticies) {
			v.setColor("White");
			v.setDiscoveredByBackwardEdge(false);
		}
			
	}
	
	/**
	 * @effects adds the given node to this.nodes, if it does not exist
	 * @modifies this
	 * @requires nodeName != "" && nodeName != null
	 * @param nodeName
	 */
	public Boolean addNode(Node<T> node) {
		if(verticies.contains(node) == false) {
			verticies.add(node);
			checkRep();
			return true;
		}else return false;
	}
	
	/**
	 * @effects adds an edge between the given nodes if there was not already an edge between them.
	 * @modifies this and the relevant nodes
	 * @requires childNode!= null && parentNode != null
	 * @param parentNode
	 * @param childNode
	 */
	public void addEdge(Node<T> parentNode, Node<T> childNode) throws IllegalArgumentException{
		if(verticies.contains(parentNode) == false || verticies.contains(childNode) == false) {
			throw new IllegalArgumentException("nodes not found in graph");
		}
		Node<T> pN = verticies.get(verticies.indexOf(parentNode));
		Node<T> cN = verticies.get(verticies.indexOf(childNode));
		if (pN.findEdge(cN) == null) {
			 Edge<T> e = new Edge<T>(pN, cN);
			pN.addEdge(e);
			if(!parentNode.equals(childNode)) //do not duplicate self loops
				cN.addEdge(e);
		}
		else {
			throw new IllegalArgumentException("tried to add an already existing edge");
		}
		checkRep();
	}
	
	/**
	 * @effects lists the nodes in the graph
	 * @modifies none
	 * @requires none
	 * @return a list with the nodes in the graph
	 */
	public List<Node<T>> getNodes(){
		return verticies;
	}
		/**
	 * @effects returns the DFS tree found by the DFS algorithm, starting from graph root src, and ending at dst (if received)
	 * @modifies Nodes' colors changes during running the function
	 * @requires src - the root for the DFS, dst - ending node (optional)
	 * @return A path representing the DFS' tree built by the algorithm
	 */
	public <T> NodeCountingPath getDFStree(Node<Integer> src, Node<Integer> dst){
		//find graph nodes that are equal to the input
		@SuppressWarnings("unchecked")
		Node<T> srcInstance = (Node<T>) verticies.get(verticies.indexOf(src));
		@SuppressWarnings("unchecked")
		Node<T> dstInstance = (dst != null ) ? (Node<T>) verticies.get(verticies.indexOf(dst)) : null;
		
		return DfsAlgorithm.DFS(srcInstance,dstInstance);
	}
		/**
	 * @effects returns the records of the nodes in the graph
	 * @modifies none
	 * @requires none
	 * @return A list of weighted nodes: the records for each node in the graph
	 */
	public List<WeightedNode> getNodesRecords(){
		List<WeightedNode> nodesRecords = new ArrayList<WeightedNode>();
		
		 for (Node<T> n : verticies) {
			 WeightedNode nodeRecord = n;
			 nodesRecords.add(nodeRecord);
		 }
		 return nodesRecords;
	}
	
	/**
	 * @effects lists the children of the node with the given name 
	 * 			or null if there was no node with the given name
	 * @modifies none
	 * @requires name is an instance of String
	 * @returns a list of the children of the node or null if there was no such node in the graph 
	 */
	 public List<Node<T>> getNodeChildren(String name) {
		 for (Node<T> n : verticies) {
			 if(n.getName().equals(name)) return n.getChildren();
		 }
		 return null;
	 }
	
	/**
	 * @effects returns the cheapest path between a possible source and a possible destination
	 * @modifies
	 * @requires !sourceArgs.isEmpty() && !destArgs.isEmpty()
	 * @param start - a list of possible starting nodes
	 * @param end - a list of possible ending nodes
	 * @return a path between a source and a destination
	 */
	public NodeCountingPath findMinPath(List<Node<T>> start, List<Node<T>> end) {
		List<Node<T>> start_nodes = new ArrayList<>();
		List<Node<T>> end_nodes = new ArrayList<>();
		//find nodes in the graph and build the lists
		for (Node<T> s : start) 
			start_nodes.add((Node<T>) verticies.get(verticies.indexOf(s)));
		for (Node<T> e : end) 
			end_nodes.add((Node<T>) verticies.get(verticies.indexOf(e)));
		
		return PathFinder.findMinPath(start_nodes, end_nodes);
		
	}
	
	/**
	  * Checks to see if the representation invariant is being violated
	  * @throws AssertionError if the representation invariant is violated 
	  **/
	 private void checkRep() {
		 assert name instanceof String:
			 "Name is not a String";
	 	if(name instanceof String) {
	 		assert name != "":
	 			"Name is empty";
	 	}
	 	for(Node<T> node: verticies) {
	 		assert Collections.frequency(verticies, node) < 2:
	 			"The same vertix is in the graph more than once.";
	 	}
	 	for(Edge<T> edge1: edges) {
	 		for(Edge<T> edge2: edges) {
	 			if (edge1.getFrom().equals( edge2.getFrom())) {
	 				assert !edge1.getTo().equals(edge2.getTo()):
	 					"Two edges between the same nodes.";
				}
	 		}
	 	}
	 }

}