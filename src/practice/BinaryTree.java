package practice;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

	private class Node {
		private final int value;
		private Node left;
		private Node right;
		private int level;
		
		public Node(int v) {
			this.value = v;
		}
	}
	
	public Node buildTree(int height) {
		int i = 0;
		Node root = new Node(i++);
		root.level = 0;
		
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		
		while (!q.isEmpty()) {
			Node n = q.poll();
			
			if (n.level == height)
				return root;
			
			n.left = new Node(i++);
			n.left.level = n.level + 1;
			n.right = new Node(i++);
			n.right.level = n.level + 1;
			q.add(n.left);
			q.add(n.right);
		}
		
		return root;
	}
		
	public void printTree(Node root) {
		Queue<Node> q = new LinkedList();
		q.add(root);
		
		int level = 0;
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.left != null) {
				q.add(n.left);
			}
			if (n.right != null) {
				q.add(n.right);
			}
			
			if (n.level > level) {
				System.out.println();
				level = n.level;
			}
			System.out.print(n.value + "\t");
		}
	}
	
	/**
	 * Least Common Anccester
	 */
   public Node findLCA(Node root, int n1, int n2) {
	   boolean[] found = new boolean[2];
	   Node lca = findLCAInternal(root, n1, n2, found);
	   
	   System.out.println("foundN1=" + found[0] + " foundN2=" + found[1]);
	   if ((found[0] && found[1])
			   || (found[0] && findChildNode(lca, n2)) || (found[1] && findChildNode(lca, n1))) {
		   return lca;
	   }
	   
	   return null;
   }
   
   private Node findLCAInternal(Node root, int n1, int n2, boolean[] found) {
	   if (root == null) {
		   return null;
	   }
	   
	   System.out.print(root.value + "  ");
	   if (root.value == n1) {
		   found[0] = true;
		   System.out.println("foundN1=" + found[0]);
		   return root;
	   } else if (root.value == n2) {
		   found[1] = true;
		   System.out.println("foundN2=" + found[1]);
		   return root;
	   }
	   
	   Node left = findLCAInternal(root.left, n1, n2, found);	 
	   System.out.println(" done left " + (left != null ? left.value : "null"));
	   Node right = findLCAInternal(root.right, n1, n2, found);
	   System.out.println(" done right " + (right != null ? right.value : "null"));
	   
	   if (left != null && right != null) {
		   System.out.println("found left and right " + left.value + " " + right.value);
		   return root;
	   }
	   
	   if (left != null) {
		   System.out.println("found left: " + left.value);
		   return left;
	   } else {
		   System.out.println("found right " + (right != null ? right.value : "null"));
		   return right;
	   }
   }
   
   private static boolean findChildNode(Node root, int value) {
	   if (root == null) return false;
	   if (root.value == value) return true;
	   return findChildNode(root.left, value) || findChildNode(root.right, value);
   }
   
   public static void main(String[] args) {
	   BinaryTree tree = new BinaryTree();
	   Node root = tree.buildTree(3);
	   //tree.printTree(root);
	   
	   System.out.println();
	   
	   Node lca = tree.findLCA(root, 4, 4);
	   System.out.println("4, 4->" + (lca != null ? lca.value : "null"));
   }
}
