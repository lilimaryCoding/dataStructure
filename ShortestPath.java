package dataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*You've declared the class Thing as a non-static inner class. That means it must be associated with an instance of the Hello class.

In your code, you're trying to create an instance of Thing from a static context. That is what the compiler is complaining about.

There are a few possible solutions. Which solution to use depends on what you want to achieve.

Change Thing to be a static nested class.
static class Thing
Create an instance of Hello, then create an instance of Thing.
public static void main(String[] args)
{
    Hello h = new Hello();
    Thing thing1 = h.new Thing(); // hope this syntax is right, typing on the fly :P
}*/


public class ShortestPath {
	
	static class GraphNode{
		int val;
		List<GraphNode> neighbors;
		GraphNode(int x){
			val = x;
			neighbors = new ArrayList<GraphNode>();
		}
	}
	
	//BFS. use a hashset to store if the node has been visited or not since they all have different value, 
	//and a hashmap to store the node and its previous node.
	//and use a queue to go through each nodes in the graph
	public static List<GraphNode> shortestPath(GraphNode start, GraphNode end){
		List<GraphNode> res = new LinkedList<>();
		HashSet<GraphNode> visited = new HashSet<>();
		HashMap<GraphNode, GraphNode> path = new HashMap<>();
		Queue<GraphNode> q = new LinkedList<>();
		GraphNode node = start;
		visited.add(node);
		path.put(node, null);
		q.offer(node);
		while(!q.isEmpty()){
			node = q.poll();
			if (node.val == end.val){//find the path
				break;
			}else{
				for(GraphNode neighbor: node.neighbors){
					if (!visited.contains(neighbor)){
						visited.add(neighbor);
						path.put(neighbor, node);
						q.offer(neighbor);
					}
				}
			}
		}
		if (node.val != end.val){
			System.out.println("Can't find the shortest path");
			return new ArrayList<>();
		}else{
			for(GraphNode curr = end; curr != null; curr = path.get(curr)){
				res.add(0,curr);
			}
			return res;
		}
	}
	
	public static void main(String[] args){
		GraphNode n1 = new GraphNode(1);
		GraphNode n2 = new GraphNode(2);
		GraphNode n5 = new GraphNode(5);
		GraphNode n3 = new GraphNode(3);
		GraphNode n4 = new GraphNode(4);
		GraphNode n6 = new GraphNode(6);
		n3.neighbors.addAll(Arrays.asList(n2, n1, n5));
		n2.neighbors.add(n4);
		n5.neighbors.add(n6);
		n6.neighbors.add(n4);
		n1.neighbors.add(n2);
		List<GraphNode> list = shortestPath(n3, n4);
		for(GraphNode node: list){
			System.out.println(String.valueOf(node.val));
		}
		//System.out.println(list);
		
	}
}
