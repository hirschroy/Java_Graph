package homework2;

public class Edge<T> {
	 private Node<T> from;
	 private Node<T> to;
	 
	 public Edge(Node<T> from, Node<T> to) {
		    this.from = from;
		    this.to = to;
		  }
	 
	 /**
	  * Get the ending vertex
	  * 
	  * @return ending vertex
	  */
	 public Node<T> getTo() {
	   return to;
	 }
	 public Node<T> getFrom() {
		   return from;
		 }
}
