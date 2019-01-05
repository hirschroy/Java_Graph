package homework2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PathFinder<T> {
	
	public static <T> NodeCountingPath findMinPath (List<Node<T>> start,
			List<Node<T>> end){
		
		Collections.sort(start, new Comparator<Node<T>>() {  
		    @Override  
		    public int compare(Node<T> p1, Node<T> p2) {  
		        int sizeCmp = p1.getCost() - p2.getCost();
		        if (sizeCmp != 0) {  
		            return sizeCmp;  
		        }
		        return p1.getName().compareTo(p2.getName());
		    }  
		});
		Collections.sort(end, new Comparator<Node<T>>() {  
		    @Override  
		    public int compare(Node<T> p1, Node<T> p2) {  
		        int sizeCmp = p1.getCost() - p2.getCost();
		        if (sizeCmp != 0) {  
		            return sizeCmp;  
		        }
		        return p1.getName().compareTo(p2.getName());
		    }  
		});
		
		NodeCountingPath min_path = new NodeCountingPath();
  		NodeCountingPath current_path = null;
  		
  		for (Node<T> src : start) {
  			if(end.isEmpty()) {
  			current_path = DfsAlgorithm.DFS(src);
  			min_path = current_path.compareTo(min_path) < 0 ? current_path : min_path;
  			return min_path;
  			}
  			for (Node<T> dst : end) {
  				current_path = DfsAlgorithm.DFS(src,dst);
  	  			min_path = current_path.compareTo(min_path) < 0 ? current_path : min_path;
  			}
  		}
  		return min_path;
		
	}
	
}

