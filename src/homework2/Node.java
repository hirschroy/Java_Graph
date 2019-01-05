package homework2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Node<T> extends WeightedNode {
	private T element;
	private List<Edge<T>> outgoingEdges;
	private List<Edge<T>> incomingEdges;
	private List<Node<T>> children;
	private List<Node<T>> parents;
	
	
	// Representation invariant:
	// An Node n is a node containing an element of type T
	// All the edges connecting to n from another node are listed in n.outgoingEdges
	// All the edges connecting from n to another node are listed in n.incomingEdges
	// All n's children are listed in n.children
	// All n's parents are listed in n.parents
	// n.name is its name and n.cost is its cost.
	 
	// Abstraction function for every Node n:
	// name != "" && name != null && name instanceof String
	// cost > 0
	// color is either white grey or black

	/**
	 * @effects Creates a new node of type T, with the given name and the given cost
	 * @modifies this
	 * @requires T != null, name != null && name != "" && cost > 0
	 * @param nodeElem
	 * @param name
	 * @param cost
	 */
	public Node(T nodeElem, String name, int cost){
		super(name,cost);
		this.element = nodeElem;
		outgoingEdges = (List<Edge<T>>) new ArrayList<Edge<T>>();
		incomingEdges = (List<Edge<T>>) new ArrayList<Edge<T>>();
		children = (List<Node<T>>) new ArrayList<Node<T>>();
		parents = (List<Node<T>>) new ArrayList<Node<T>>();
		checkRep();
	}
	
	/**
	 * @effects returns the element kept in this
	 * @modifies none
	 * @requires none
	 * @returns  this.element
	 */
	public T getElement() {
		return element;
	}
	
	/**
	 * @effects lists the children of this
	 * @modifies none
	 * @requires none
	 * @returns  a list containing the children of this
	 */
	public List<Node<T>> getChildren(){
		return children;
	}
	
	 
	/**
	 * @effects Search the outgoing edges looking for an edge between this and dest.
	 * @modifies none
	 * @requires none
	 * @param the destination Node dest
	 * @returns the relevant edge or null if there is none
	 */
	  public Edge<T> findEdge(Node<T> dest) {
		  for (Edge<T> e : outgoingEdges) {
			  if (e.getTo() == dest) return e;
		  }
		  return null;
	  }
	  
	  /**
	   * @effects Adds an edge to this. 
	   * If e.from is this, its an outgoing edge. 
	   * If e.to is this, its an incoming edge. 
	   * If neither e.from or e.to is this, the edge is not added.
	   * if there is already an existing edge between those nodes the edge is not added
	   * @modifies this
	   * @requires e != null
	   * @param the edge to add e
	   * @return true if the edge was added, false otherwise
	   */
	  public boolean addEdge(Edge<T> e) {
		if (e.getFrom() == this) {
			if(children.contains(e.getTo())) return false;
			outgoingEdges.add(e);
			children.add(e.getTo());
			checkRep();
			return true;
		}
		else if (e.getTo() == this) {
			if(parents.contains(e.getFrom())) return false;
			incomingEdges.add(e);
			parents.add(e.getFrom());
			checkRep();
			return true;
		}
		else return false;
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
	 	assert cost >= 0:
	 		"The cost should be non-negative";
	 	// TODO: color is either white grey or black
	 }
}
