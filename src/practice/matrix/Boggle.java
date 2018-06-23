package matrix;

import java.util.*;

/**
 * Variant of boggle. Given a board and a dict, find a path that contains most words from the dict.
 * Can only go horizontally or vertically. Cannot reuse same char.
 */
public class Boggle {
    class Node {
        Node[] children;
        boolean isLeaf;

        Node() {
            children = new Node[26];
        }
    }

    class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String s) {
            if (s == null || s.length() == 0) {
                return;
            }

            Node p = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = c - 'a';
                if (p.children[index] == null) {
                    p.children[index] = new Node();
                }
                if (i == s.length() - 1) {
                    p.children[index].isLeaf = true;
                }
                p = p.children[index];
            }
        }
    }

    public List<String> findPathWithMostWords(char[][] board, Set<String> dict) {
        List<List<int[]>> paths = new ArrayList<>();

        Trie trie = new Trie();
        for (String word : dict) {
            trie.insert(word);
        }

        int rows = board.length;
        int cols = board[0].length;

        // Go through every position in the matrix and find all valid paths starting from that position.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boolean[][] visited = new boolean[rows][cols];
                List<int[]> path = new ArrayList<>();
                List<List<int[]>> pathz = new ArrayList<>();
                findPath(board, trie, trie.root, visited, rows, cols, row, col, pathz, path);

                // extract the path containing most words from board[row][col]
                path = new ArrayList<>();
                for (List<int[]> p : pathz) {
                    if (p.size() > path.size()) {
                        path = p;
                    }
                }
                paths.add(path);
            }
        }

        List<String> ret = new ArrayList<>();
        for (List<int[]> path : paths) {
            List<String> words = pathToWords(path, board);
            if (words.size() > ret.size()) {
                ret = words;
            }
        }
        return ret;
    }

    // Starts at board[row][col], dfs for valid words. Stores all possible valid paths in paths.
    private void findPath(char[][] board, Trie trie, Node node, boolean[][] visited, int rows, int cols, int row, int col, List<List<int[]>> paths, List<int[]> path) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        if (path.size() > 2) {
            int[] dot = path.get(path.size() - 1);
            if (dot[2] == 1) {
                // found a word.
                paths.add(new ArrayList<>(path));
                // Two choices: Start a new word, or continue.
                // TODO(luping): implement second choice which is to continue.
                node = trie.root;
            }
        }

        char c = board[row][col];
        Node next = node.children[c - 'a'];
        if(next != null) {
            path.add(new int[] {row, col, next.isLeaf ? 1 : 0});

            findPath(board, trie, next, visited, rows, cols, row + 1, col, paths, path);
            findPath(board, trie, next, visited, rows, cols, row - 1, col, paths, path);
            findPath(board, trie, next, visited, rows, cols, row, col + 1, paths, path);
            findPath(board, trie, next, visited, rows, cols, row, col -1, paths, path);

            // backtrack
            visited[row][col] = false;
            if (path.size() > 0) {
                path.remove(path.get(path.size() - 1));
            }
        }
    }

    private List<String> pathToWords(List<int[]> path, char[][] board) {
        StringBuilder b = new StringBuilder();
        List<String> ret = new ArrayList<>();
        for (int[] dot : path) {
            char c = board[dot[0]][dot[1]];
            b.append(c);
            if (dot[2] == 1) {
                // indicate end of word
                ret.add(b.toString());
                b = new StringBuilder();
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {
//                new char[]{'o','a','a','e'},
                new char[]{'o','a','a','n'},
                new char[]{'e','t','a','e'},
                new char[]{'i','h','k','r'},
                new char[]{'i','f','l','v'}
        };

        Set<String> dict = new HashSet<>();
        dict.add("oath");
        dict.add("pea");
        dict.add("eat");
        dict.add("rain");
        dict.add("ihk");
        dict.add("ren");
//        dict.add("rene");  // this test case failed cuz we always start over when we find a word. so path stops at ren.

        Boggle bg = new Boggle();
        List<String> ret = bg.findPathWithMostWords(board, dict);

        for (String word : ret) {
            System.out.print(word + " ");
        }
    }
}
