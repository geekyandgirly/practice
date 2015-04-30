package practice;

import java.util.Enumeration;
import java.util.Stack;
import java.util.Vector;

public class BinarySearchStree {
	private Node root;
	
	public static class Node {
    private int value;
    private Node left;
    private Node right;
    
    public Node(int value) {
    	this.value = value;
    	left = null;
    	right = null;
    }
  }
	
	public BinarySearchStree() {
		root = null;
	}
	
  public void insert(int value) {
  	Node newNode = new Node(value);
  	if (root == null) {
  		root = newNode;
  	} else {
    	insertInternal(root, newNode);
  	}
  }
  
  private void insertInternal(Node subRoot, Node newNode) {
    if (newNode.value < subRoot.value) {
      if (subRoot.left == null) {
    	  subRoot.left = newNode;
    	  return;
      }
  	  insertInternal(subRoot.left, newNode);
	} else {
		if (subRoot.right == null) {
			subRoot.right = newNode;
			
			return;
		}
		insertInternal(subRoot.right, newNode);
	}
  }
  
  public Node find(int value) {
  	return findInternal(root, value);
  }
  
  private Node findInternal(Node subRoot, int value) {
  	if (subRoot == null) {
  		return null;
  	}
  	if (subRoot.value == value) {
  		return subRoot;
  	}
  	if (value < subRoot.value) {
  		return findInternal(subRoot.left, value);
  	} else {
  		return findInternal(subRoot.right, value);
  	}
  }
  
  public int size() {
  	return size(root);
  }
  
  private int size(Node root) {
  	if (root == null) {
  		return 0;
  	}
  	return size(root.left) + 1 + size(root.right);
  }
  
  public int maxDepth() {
  	return maxDepth(root);
  }
  
  public int maxDepth(Node root) {
  	if (root == null) {
  		return 0;
  	}
  	return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
  }
  
  public void printInOrder() {
  	printInOrder(root);
  }
  
  private void printInOrder(Node root) {
  	if (root == null) {
  		return;
  	}
  	printInOrder(root.left);
  	System.out.print(root.value + " ");
  	printInOrder(root.right);
  }
  
  public boolean hasPathSum(int sum) {
  	return hasPathSum(root, sum);
  }
  
  private boolean hasPathSum(Node root, int sum) {
  	if (root == null) {
  		return (sum == 0);
  	}
  	
  	int subSum = sum - root.value;
  	return (hasPathSum(root.left, sum) || hasPathSum(root.right, sum));
  }
  
  Node result;
  public Node findNthSmallest(int n) {
  	findNthSmallest(root, n);
  	return result;
  }
  
  private void findNthSmallest(Node root, int n) {
  	if (root == null || n == 0) {
  		result = root;
  		return;
  	}
  	
  	if (n > 0)
   	  findNthSmallest(root.left, n-1);
  	if (n == 0) {
  		result = root;
  	  return;
  	}
  	if (n > 0)
     	findNthSmallest(root.right, n-1);
  }
	
  public void printAllPaths() {
    Vector<Integer> vector = new Vector<Integer>();
  	printAllPaths(root, vector);
  }
  
  private void printAllPaths(Node root, Vector<Integer> vector) {
  	if (root == null) {
  		return;
  	}
  	
  	vector.add(root.value);
  	if (root.left == null && root.right == null) {
  		printVector(vector);
  	  vector.remove(vector.size() -1);
  	}
  	
  	printAllPaths(root.left, vector);
  	printAllPaths(root.right, vector);
  }
  
  private void printVector(Vector vector) {
  	for (int i = 0; i < vector.size(); i++) {
  		int element = (Integer) vector.elementAt(i);
  		System.out.print(element);
  	}
  	System.out.println();
  }
  
  public boolean sameTree(Node otherRoot) {
  	return sameTree(root, otherRoot);
  }
  
  private boolean sameTree(Node root, Node otherRoot) {
  	if (root == null && otherRoot == null) {
  		return true;
  	}
    if (root != null & otherRoot != null) {
    	if (root.value != otherRoot.value) {
	      	return false;
	    }
    } else {
    	return false;
    }
    
    return sameTree(root.left, otherRoot.left) && sameTree(root.right, otherRoot.right);
  }
  
	public static void main(String[] args) {
		int[] input = new int[] {5,8,3,9,7,5,2,6,4,1,0,6};
		BinarySearchStree bst = new BinarySearchStree();
		for (int i = 0; i < input.length; i++) {
			bst.insert(input[i]);
		}
		
		bst.printInOrder(bst.root);
		System.out.println("root: " + bst.root.value);
		
		bst.find(6);
		bst.findNthSmallest(bst.root, 7);
		System.out.println("7th smallest:" + bst.result.value);
	}
}
