package comboperm;

import java.util.*;

public class Caps {

    /**
     * Memo DOES NOT work in back tracking.
     */
    public static int countWays(List<List<Integer>> collections) {
        Map<String, Integer> memo = new HashMap<>();
        return countWays(collections, collections.size(), memo, new LinkedHashSet<Integer>(), 0);
    }

    /**
     * Memo DOES NOT work back tracking.
     */
    public static int countWays(List<List<Integer>> collections, int size, Map<String, Integer> memo, LinkedHashSet<Integer> way, int i) {
        System.out.println("countWays");
        if (way.size() == size) {
            return 1;
        }

        if (i == size) {
            return 0;
        }

        String key = convertToKey(way, i);
        if (memo.containsKey(key)) {
            System.out.println("countWays memo");
            return memo.get(key);
        }

        int ways = 0;
        List<Integer> collection = collections.get(i);
        for (int j = 0; j < collection.size(); j++) {
            if (way.add(collection.get(j))) {
                key = convertToKey(way, i);
                System.out.println("inner key:" + key);
                ways += countWays(collections, size, memo, way, i + 1);
                memo.put(key, ways);
                // backtrack
                way.remove(collection.get(j));
            }
        }
        key = convertToKey(way, i);
        System.out.println("outter key:" + key);
        memo.put(key, ways);
        return ways;
    }

    private static String convertToKey(LinkedHashSet<Integer> way, int i) {
        StringBuilder key = new StringBuilder(i + ":");
        for (int w : way) {
            key.append(w).append("-");
        }
        return key.toString();
    }

    public static List<List<Integer>> getCapWays(List<List<Integer>> collections) {
        List<List<Integer>> ways = new ArrayList<>();

        getWays(collections, collections.size(), ways, new LinkedHashSet<Integer>(), 0);

        return ways;
    }

    public static void getWays(List<List<Integer>> collections, int size, List<List<Integer>> ways, LinkedHashSet<Integer> way, int i) {
//        System.out.println("getWays");

        if (way.size() == size) {
            List<Integer> tmpList = new ArrayList<>(way);
            ways.add(tmpList);
//            printList(tmpList);
            return;
        }

        if (i == size) {
            return;
        }

        List<Integer> collection = collections.get(i);
        for (int j = 0; j < collection.size(); j++) {
            if (way.add(collection.get(j))) {
                getWays(collections, size, ways, way, i + 1);

                // backtrack
                way.remove(collection.get(j));
            }
        }
    }

    private static void printListOfLists(List<List<Integer>> lists) {
        for (List<Integer> list : lists) {
            printList(list);
        }
    }

    private static void printList(List<Integer> list) {
        System.out.print("[");
        for (Integer i : list) {
            System.out.print(i + ", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        List<List<Integer>> collections = new ArrayList<>();
        collections.add(new ArrayList(Arrays.asList(new Integer[] {1,2,3})));
        collections.add(new ArrayList(Arrays.asList(new Integer[] {2,3})));
        collections.add(new ArrayList(Arrays.asList(new Integer[] {1,4,5})));

        System.out.println();
        printListOfLists(getCapWays(collections));

        System.out.println("counted ways: " + countWays(collections));
    }
}
