package homework2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class DfsAlgorithm<T> {
	
	public static <T> NodeCountingPath DFS (Node<T> start){
		
		return DFS(start,null);
	}
	
	public static <T> NodeCountingPath DFS (Node<T> start, Node<T> end){
		
		NodeCountingPath dfs_tree = new NodeCountingPath(start);
		//check if start=end
		boolean end_node = (end != null);
		if (end_node && start.equals(end)) {
			return dfs_tree;
		}
		
		 List<Node<T>> touchedNodes = new ArrayList<>();
		 Stack<Node<T>> nodeStack = new Stack<>();
		 
		 //init
		 start.setColor("Gray");
		 touchedNodes.add(start);
		 nodeStack.add(start);
		 
		 //DFS loop
		 while(!nodeStack.isEmpty()) {
			 Node current = nodeStack.pop();
			 if(current.getColor()=="Black") //skip already visited nodes
				 continue;
			 if(!current.equals(start))
				 dfs_tree = dfs_tree.extend(current);
			 
			 //check if reached end
			 if(end_node && current.equals(end)) {
				 current.setColor("White"); //reset
				 return dfs_tree;
			 }
			 @SuppressWarnings("unchecked")
			List<Node<T>> children = current.getChildren();
				Collections.sort(children, new Comparator<Node<T>>() {  
				    @Override  
				    public int compare(Node<T> p1, Node<T> p2) {  
				        int sizeCmp = p1.getCost() - p2.getCost();
				        if (sizeCmp != 0) {  
				            return sizeCmp;  
				        }
				        return p1.getName().compareTo(p2.getName());
				    }  
				});
				
				//check if there are no more children to check
				for(Node<T> c : children) {
					if(c.getColor()=="White") {
						nodeStack.add(c);
						touchedNodes.add(c);
					}else { //child is still being visited - backward edge found
						c.setDiscoveredByBackwardEdge(true);
					}
					c.setColor("Gray");
				}
		 }
		 
		for(Node<T> n : touchedNodes) {
			n.setColor("White");
		}
		return dfs_tree;
	}

	

}
