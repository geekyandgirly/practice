package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;


public class Graph {
	Node root;
	HashMap<String, HashSet<Node>> adjacentGraph = new HashMap<String, HashSet<Node>>();
	
	public Graph() {

	  Node nA = new Node("A");
	  Node nB = new Node("B");
	  Node nC = new Node("C");
	  Node nD = new Node("D");
	  Node nE = new Node("E");
	  Node nF = new Node("F");
	  
	  root = nA;
	  
	  HashSet<Node> path = new HashSet<Node>();
	  path.add(nB);
	  path.add(nC);
	  path.add(nD);
	  adjacentGraph.put("A", path);

	  path = new HashSet<Node>();
	  path.add(nA);
	  path.add(nE);
	  path.add(nF);
	  adjacentGraph.put("B", path);

	  path = new HashSet<Node>();
	  path.add(nA);
	  path.add(nF);
	  adjacentGraph.put("C", path);

	  path = new HashSet<Node>();
	  path.add(nA);
	  adjacentGraph.put("D", path);

	  path = new HashSet<Node>();
	  path.add(nB);
	  adjacentGraph.put("E", path);

	  path = new HashSet<Node>();
	  path.add(nB);
	  path.add(nC);
	  adjacentGraph.put("F", path);
   }
	
	static class Node {
		public String label;
		public boolean visited=false;
		public Node(String l)
		{
			  this.label=l;
		}
	}
	
	public Node getUnvisitedChild(Node parent) {
	  if (adjacentGraph.get(parent.label) != null) {
		  HashSet<Node> set = adjacentGraph.get(parent.label);
		  Iterator<Node> iter = set.iterator();
		  while (iter.hasNext()) {
			Node node = iter.next();
			if (!node.visited) {
				node.visited = true;
				return node;
			}
		  }
		  return null;
	  }
	  return null;
	}
	
	public void dfs() {
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		root.visited = true;
		System.out.print(root.label + "-->");
		
		while (!stack.isEmpty()) {
			Node node = stack.peek();
			Node unvisitedChild = getUnvisitedChild(node);
			if (unvisitedChild != null) {
				stack.push(unvisitedChild);
				System.out.print(unvisitedChild.label + "-->");
			} else {
				stack.pop();
			}
		}
	}
	
	public void bfs() {
		LinkedList<Node> q = new LinkedList<Node>();
		q.add(root);
		root.visited = true;
		System.out.print(root.label + "-->");
		
		while (!q.isEmpty()) {
			Node node = q.remove();
		    Node child = getUnvisitedChild(node);
		    while (child != null) {
		    	q.add(child);
		    	child.visited = true;
				System.out.print(child.label + "-->");
		    	child = getUnvisitedChild(node);
		    }
		}
	}
	
     public static void main(String[] args) {  
          //Create the graph, add nodes, create edges between nodes
          Graph g=new Graph();
          
          //Perform the traversal of the graph
          System.out.println("DFS Traversal of a tree is ------------->");
          g.dfs();
          
          //Create the graph, add nodes, create edges between nodes
          g=new Graph();

          System.out.println("\nBFS Traversal of a tree is ------------->");
          g.bfs();
      }		
}
