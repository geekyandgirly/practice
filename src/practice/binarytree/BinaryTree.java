package binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    private Node root;

    private static class Node {
        private Node left, right;
        private int value;

        Node(int val) {
            value = val;
            left = null;
            right = null;
        }
    }

    public BinaryTree() {
        root = null;
    }

    public void buildTree(int level) {
        int v = 0;
        int l = 0;
        root = new Node(v++);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            node.left = new Node(v++);
            if (l == level) {
                return;
            }
            node.right = new Node(v++);
            q.offer(node.left);
            q.offer(node.right);
            l++;
        }
    }


    public void printAllPath() {
        LinkedList<Node> path = new LinkedList<>();

        printAllPaths(root, path);
    }

    private void printAllPaths(Node root, LinkedList<Node> path) {
        if (root == null) {
            return;
        }
        path.add(root);

        if (root.left == null && root.right == null) {
            // reach leaf
            for (Node n : path) {
                System.out.print(n.value + " -> ");
            }
            System.out.println();
        }

        printAllPaths(root.left, path);
        printAllPaths(root.right, path);

        // backtrack
        if (!path.isEmpty()) {
            path.removeLast();
        }
    }

    private boolean hasPathSum(int sum) {
        return hasPathSum(root, sum);
    }

    private boolean hasPathSum(Node root, int sum) {
        if (sum < 0) {
            return false;
        }

        if (root != null && root.value == sum  && root.left == null && root.right == null) {
            return true;
        }

        if (root == null) {
            return false;
        }

        return hasPathSum(root.left, sum - root.value) || hasPathSum(root.right, sum - root.value);
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.buildTree(4);

//        bt.printAllPath();
//
//        System.out.println("Has path sum 12: " + bt.hasPathSum(12));
//        System.out.println("Has path sum 8: " + bt.hasPathSum(8));
//        System.out.println("Has path sum 14: " + bt.hasPathSum(14));
//        System.out.println("Has path sum 5: " + bt.hasPathSum(5));
//        System.out.println("Has path sum 4: " + bt.hasPathSum(4));

   }
}
