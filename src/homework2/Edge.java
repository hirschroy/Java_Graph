package homework2;

public class Edge<T> {
	 private Node<T> from;
	 private Node<T> to;
	 
	 // Representation invariant:
	 // An Edge e is an edge in the graph connecting the node e.from to the node e.to
	 
	 // Abstraction function for every Edge e:
	 // e.from != null && e.to != null
	 
	 /**
	  * @effects creates the Edge
	  * @modifies this
	  * @requires from != null && to != null
	  * @param Node from
	  * @param Node to
	  */
	public Edge(Node<T> from, Node<T> to) {
		 this.from = from;
		 this.to = to;
		 checkRep();
 	}
	 
	 /**
	  * @effects gets the Node at the end of the edge
	  * @modifies none
	  * @requires none
	  * @return the Node at the end of the edge
	  */
	 public Node<T> getTo() {
		 return to;
	 }
	 
	 /**
	  * @effects gets the Node at the beginning of this
	  * @modifies none
	  * @requires none
	  * @return the Node at the beginning of this
	  */
	 public Node<T> getFrom() {
		 return from;
	 }
	 
	 /**
  	 * Checks to see if the representation invariant is being violated
  	 * @throws AssertionError if the representation invariant is violated 
  	 **/
	 private void checkRep() {
		assert this.from != null:
			"from node is null";
		assert this.to != null:
			"to node is null";
	 }
}
