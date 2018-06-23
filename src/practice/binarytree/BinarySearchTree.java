package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree {
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

    private BinarySearchTree() {
        root = null;
    }

    private void insert(int val) {
        Node newNode = new Node(val);
        if (root == null) {
            root = newNode;
        } else {
            insert(root, newNode);
        }
    }

    private void insert(Node root, Node newNode) {
        if (newNode.value < root.value) {
            if (root.left == null) {
                root.left = newNode;
                return;
            }
            insert(root.left, newNode);
        } else {
            if (root.right == null) {
                root.right = newNode;
                return;
            }
            insert(root.right, newNode);
        }
    }

    private int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }


    private void printInOrder() {
        System.out.println("In Order traverse at root: " + root.value);
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.value + " ");
        printInOrder(root.right);
    }

    private void printPreOrder() {
        System.out.println("Pre Order traverse at root: " + root.value);
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    public void printPostOrder() {
        System.out.println("Post Order traverse at root: " + root.value);
        printPostOrder(root);
        System.out.println();

    }

    private void printPostOrder(Node root) {
        if (root == null) {
            return;
        }
        printPostOrder(root.left);
        printPostOrder(root.right);
        System.out.print(root.value + " ");
    }

    public void printLevelOrder() {
        printLevelOrder(root);
    }

    public void printLevelOrder(Node root){
        Queue<Node> q = new LinkedList<Node>();
        q.offer(root);
        System.out.println();
        while (!q.isEmpty()) {
            Node node = q.poll();
            System.out.print(node.value + " ");
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
        System.out.println();
    }

    private int nth = 0;
    private Node ret = null;
    private void findNthSmallest(int n) {
        ret = null;
        nth = 0;
        findNthSmallest(root, n);
    }

    private void findNthSmallest(Node root, int n) {
        if (root == null) return;

        findNthSmallest(root.left, n);
        nth ++;
        if (nth == n) {
            ret = root;
            return;
        }
        findNthSmallest(root.right, n);
    }

    private ArrayList<Node> toList(Node root) {
        ArrayList<Node> list = new ArrayList();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            list.add(node);
            if (node != null) {
                q.offer(node.left);
                q.offer(node.right);
            }
        }

        System.out.println("toList: ");
        for (Node n : list) {
            if (n != null) {
                System.out.print(n.value + " ");
            } else {
                System.out.print("null ");
            }
        }
        System.out.println();
        return list;
    }

    private Node fromList(ArrayList<Node> nodeList) {
        Queue<Node> q = new LinkedList<>();
        Node root = nodeList.remove(0);
        q.offer(root);

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node != null) {
              Node left = null;
                if (!nodeList.isEmpty()) {
                    left = nodeList.remove(0);
                }
                Node right = null;
                if (!nodeList.isEmpty()) {
                    right = nodeList.remove(0);
                }
                node.left = left;
                node.right = right;
                q.offer(left);
                q.offer(right);
            }
        }

        return root;
    }

    // assume the tree is full.
    public List<List<Integer>> levelOrderNestedList() {
        return levelOrderNestedList(root);
    }

    public List<List<Integer>> levelOrderNestedList(Node root){
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int level = 0;
        int numNodesAtCurrentLevel = 1;
        boolean entireLevelNull = true;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node != null) {
                entireLevelNull = false;
                list.add(node.value);
            }
            numNodesAtCurrentLevel--;

            if (numNodesAtCurrentLevel == 0) {
                if (entireLevelNull) {
                    break;
                }
                lists.add(new ArrayList<>(list));
                list.clear();
                level ++;
                numNodesAtCurrentLevel = (int) Math.pow(2, level);
                entireLevelNull = true;
            }

            if (node != null) {
                q.offer(node.left);
                q.offer(node.right);
            } else {
                q.offer(null);
                q.offer(null);
            }
        }
        return lists;
    }


    private Node findLCA(Node root, int v1, int v2) {
        if (root == null || root.value == v1 || root.value == v2) {
            return root;
        }

        Node foundLeft = findLCA(root.left, v1, v2);
        Node foundRight = findLCA(root.right, v1, v2);

        if (foundLeft != null && foundRight != null) {
            return root;
        } else if (foundLeft == null) {
            return foundRight;
        } else {
            return foundLeft;
        }
    }

    private static void printResult(List<List<Integer>> lists) {
        for (List<Integer> list : lists) {
            System.out.print("[");
            for (Integer i : list) {
                System.out.print(i + ", ");
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        int[] input = new int[] {5,8,3,9,7,5,2,6,4,1,0,6};
        BinarySearchTree bst = new BinarySearchTree();
        for (int anInput : input) {
            bst.insert(anInput);
        }

//        System.out.println("size of tree: " + bst.size() + ", input length: " + input.length + "\n");
//
//        bst.printInOrder();
//        bst.printPreOrder();
//        bst.printPostOrder();
//        bst.printLevelOrder();

//        bst.findNthSmallest(10);
//        System.out.println("10th smallest:" + bst.ret.value);

//        ArrayList<Node> list = bst.toList(bst.root);
//        Node root = bst.fromList(list);
//
//        System.out.println("From List: ");
//        bst.printLevelOrder(root);

//        Node lca = bst.findLCA(bst.root, 5, 6);
//        System.out.print("5,6 -> " + lca.value);
//        System.out.println();
//
//        lca = bst.findLCA(bst.root, 4,7);
//        System.out.print("4,7 -> " + lca.value);
//        System.out.println();
//
//        lca = bst.findLCA(bst.root, 1,3);
//        System.out.print("1,3 -> " + lca.value);
//        System.out.println();
//
//        lca = bst.findLCA(bst.root, 3,4);
//        System.out.print("3,4 -> " + lca.value);

        printResult(bst.levelOrderNestedList());
    }

}
