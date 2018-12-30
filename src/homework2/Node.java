package homework2;

import java.util.ArrayList;
import java.util.List;

public class Node<T> extends WeightedNode {
	private T element;
	private List<Edge<T>> outgoingEdges;
	private List<Edge<T>> incomingEdges;
	private List<Node<T>> children;
	
	public Node(T nodeElem,String name, int cost){
		super(name,cost);
		this.element = nodeElem;
		outgoingEdges = (List<Edge<T>>) new ArrayList<Edge<T>>();
		incomingEdges = (List<Edge<T>>) new ArrayList<Edge<T>>();
		children = (List<Node<T>>) new ArrayList<Node<T>>();
	}
	
	public T getElement() {
		return element;
	}
	
	public List<Node<T>> getChildren(){
		return children;
	}
	
	  /**
	   * Search the outgoing edges looking for an edge whose's edge.childNode == dest.
	   * 
	   * @param dest
	   *          the destination
	   * @return the outgoing edge going to dest if one exists, null otherwise.
	   */
	  public Edge<T> findEdge(Node<T> dest) {
	    for (Edge<T> e : outgoingEdges) {
	      if (e.getTo() == dest)
	        return e;
	    }
	    return null;
	  }
	  
	  /**
	   * Add an edge to the vertex. If edge.from is this vertex, its an outgoing
	   * edge. If edge.to is this vertex, its an incoming edge. If neither from or
	   * to is this vertex, the edge is not added.
	   * 
	   * @param e -
	   *          the edge to add
	   * @return true if the edge was added, false otherwise
	   */
	  public boolean addEdge(Edge<T> e) {
	    if (e.getFrom() == this) {
	    	outgoingEdges.add(e);
	    	children.add(e.getTo());
	    }
	      
	    else if (e.getTo() == this)
	      incomingEdges.add(e);
	    else
	      return false;
	    return true;
	  }
}
