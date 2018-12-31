package homework2;

import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
	
	private String name;
	
	  /** Vector<Node> of graph verticies */
	  private List<Node<T>> verticies;

	  /** Vector<Edge> of edges in the graph */
	  private List<Edge<T>> edges;
	  
	  /** The vertex identified as the root of the graph */
	  private Node<T> rootVertex;

	/*
	 * List of methods:
	 * 
	 * constructor: CreateGraph graphName
	 * CreateNode nodeName cost // i think not here
	 * AddNode graphName nodeName
	 * AddEdge graphName parentNode childNode
	 * ListNodes graphName
	 * ListChildren graphName parentNode
	 * FindPath graphName from1 [from2 [from3 ... ] ] -> to1 [ to2 [ to3 ... ] ]
	 * 
	 * */
	/**
	 * @effects creates a new Graph with this.name=name
	 * @modifies this
	 * @requires name != "" && name != null
	 * @param name the name to be given to this
	 */
	public Graph(String name) {
		this.name = name;
		verticies = new ArrayList<Node<T>>();
		edges = new ArrayList<Edge<T>>();
	}
	public String getName() {
		return this.name;
	}
	
	/**
	 * @effects adds the given node to this.nodes, if it does not exist
	 * @modifies this
	 * @requires nodeName != "" && nodeName != null
	 * @param nodeName
	 */
	public void addNode(Node<T> node) {
		if(verticies.contains(node) == false) {
			verticies.add(node);
		}
	}
	
	/**
	 * @effects adds an edge between the nodes corresponding to the names
	 * @modifies the relevant nodes and adds an edge to this.edges
	 * @requires childNode!= "" && childNode!= null && parentNode != "" && parentNode != null
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
	}
	
	/**
	 * @effects lists the node in the graph
	 * @modifies none
	 * @requires none
	 * @param graphName
	 * @return a list with the nodes in the graph
	 */
	public List<Node<T>> getNodes(){
		return verticies;
	}
	
	public <T> NodeCountingPath getDFStree(Node<Integer> src, Node<Integer> dst){
		//find graph nodes that are equal to the input
		@SuppressWarnings("unchecked")
		Node<T> srcInstance = (Node<T>) verticies.get(verticies.indexOf(src));
		@SuppressWarnings("unchecked")
		Node<T> dstInstance = (dst != null ) ? (Node<T>) verticies.get(verticies.indexOf(dst)) : null;
		
		return DfsAlgorithm.DFS(srcInstance,dstInstance);
	}
	
	public List<WeightedNode> getNodesRecords(){
		List<WeightedNode> nodesRecords = new ArrayList<WeightedNode>();
		
		 for (Node<T> n : verticies) {
			 WeightedNode nodeRecord = n;
			 nodesRecords.add(nodeRecord);
		 }
		 return nodesRecords;
	}
	
	/**
	   * Get the given node.
	   * 
	   * @param n
	   *          the index [0, size()-1] of the Vertex to access
	   * @return the nth Vertex
	   */
	  public List<Node<T>> getNodeChildren(String name) {
	    for (Node<T> n : verticies) {
	    	if(n.getName().equals(name)) {
	    		return n.getChildren();
	    	}
	    }
		return null;
	  }
	
	/**
	 * @effects returns the cheapest path between the source and the destination
	 * @modifies
	 * @requires !sourceArgs.isEmpty() && !destArgs.isEmpty()
	 * @param sourceArgs
	 * @param destArgs
	 * @return a path between the source and the destination
	 */
	public String findPath(List<String> sourceArgs, List<String> destArgs) {
		return "";
		
	}
}