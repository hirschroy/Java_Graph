package homework2;

import org.junit.Test;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}

	// TODO: add additional white box tests
	@Test
	public void testOne() throws Throwable{
		Graph<String> dut = new Graph<String>("g1");
		Graph<String> emptyGraph = new Graph<String>("g2");
		Node<String> n1 = new Node<String>("myString", "n1", 1 );
		Node<String> n2 = new Node<String>("myString", "n2", 2 );
		Node<String> n3 = new Node<String>("myString", "n3", 3 );
		Node<String> n4 = new Node<String>("myString", "n4", 4 );
		
		
		// Checking the method addNode: checking the if statement
		Boolean added = dut.addNode(n1);
		assert (added == true);
		added = dut.addNode(n1);
		assert (added == false);
		added = dut.addNode(n2);
		
		// Checking the method addEdge: checking the 
		// Checking if parent node is not in graph:
		try {
		dut.addEdge(n3, n1);
		}catch (IllegalArgumentException e) {
			assert(e.getMessage().equals("nodes not found in graph"));
		}
		
		// Checking if child is not in graph
		try {
			dut.addEdge(n1, n4);
		}catch (IllegalArgumentException e) {
			assert(e.getMessage().equals("nodes not found in graph"));
		}
		// assert(e == "nodes not found in graph");
		
		// checking if there already is an edge between the nodes
		dut.addEdge(n1, n2);
		try {
			dut.addEdge(n1, n2);
		}catch (IllegalArgumentException e) {
			assert(e.getMessage().equals("tried to add an already existing edge"));
		}
		
		// Checking getChildren method:
		assert dut.getNodeChildren("n1").equals(n1.getChildren());
	}
	
	

}
