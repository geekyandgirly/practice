package graph;

import java.util.*;

/**
 * https://leetcode.com/problems/alien-dictionary/description/
 *
 * DFS fashion using adjacent lists. But the adjacent list is actually a set c1 -> {c2, c22, c222}
 *
 * This solution does not pass ["zy","zx"] test case on leetcode.
 * I believe answer should be "yx" not "zyx" as stated on leetcode.
 */
public class AlienDict {
    public String alienOrder(String[] words) {
        if (words.length == 0) {
            return "";
        }

        String prev = words[0];
        if (words.length == 1) {
            if (prev.length() == 0) return "";
            return prev.substring(0,1);
        }

        Map<Character, Set<Character>> ord = new HashMap<>();
        Map<Character, Set<Character>> rev = new HashMap<>();
        LinkedList<Character> list = new LinkedList<>();

        int i = 1;
        String next;
        while (i < words.length) {
            next = words[i];
            int len = Math.min(prev.length(), next.length());
            for (int j = 0; j < len; j++) {
                char c1 = prev.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    // in order
                    // first check if there already exists c2 > c1 which means invalid mapping.
                    if (ord.containsKey(c2)) {
                        Set<Character> opo = ord.get(c2);
                        if (opo.contains(c1)) {
                            return "";
                        }
                    }
                    Set<Character> set1 = ord.get(c1);
                    if (set1 == null) {
                        set1 = new HashSet<>();
                    }
                    set1.add(c2);
                    ord.put(c1, set1);

                    // reverse order
                    Set<Character> set2 = rev.get(c2);
                    if (set2 == null) {
                        set2 = new HashSet<>();
                    }
                    set2.add(c1);
                    rev.put(c2, set2);
                }
            }
            prev = next;
            i++;
        }

        // the case where every word is the same.
        if (ord.isEmpty()) {
            String w = words[0];
            return w.length() == 0 ? "" : w.substring(0,1);
        }

        Set<Character> ordKeys = ord.keySet();
        Set<Character> revKeys = rev.keySet();

        for (Character c1 : ordKeys) {
            Set<Character> set1 = ord.get(c1);
            if (list.isEmpty()) {
                list.add(c1);
            }

            for (Character c2 : set1) {
                if (list.getLast() == c1) {
                    list.add(c2);
                    break;
                }
            }

            if (revKeys.contains(c1)) {
                Set<Character> set2 = rev.get(c1);
                for (Character c0 : set2) {
                    if (list.getFirst() == c1) {
                        list.addFirst(c0);
                        break;
                    }
                }
            }
        }

        StringBuilder b = new StringBuilder();
        for (Character c : list) {
            b.append(c);
        }
        return b.toString();
    }
}
