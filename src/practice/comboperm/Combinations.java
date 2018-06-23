package comboperm;

import java.util.*;

public class Combinations {

    public static List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        subsets(nums, lists, list, 0);
        return lists;
    }

    private static void subsets(int[] nums, List<List<Integer>> lists, List<Integer> list, int index) {
        lists.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            subsets(nums, lists, list, i + 1);
            list.remove(list.size() -1);
        }
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        subsetsWithDup(nums, lists, list, 0);
        return lists;
    }

    private static void subsetsWithDup(int[] nums, List<List<Integer>> lists, List<Integer> list, int index) {
        lists.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i-1]) {
                continue;
            }
            list.add(nums[i]);
            subsets(nums, lists, list, i + 1);
            list.remove(list.size() -1);
        }
    }

    public static List<List<Integer>> sum(int[] nums, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        Arrays.sort(nums);
        sum(nums, lists, list, sum, 0);
        return lists;
    }

    private static void sum(int[] nums, List<List<Integer>> lists, List<Integer> list, int sum, int index) {
        if (sum == 0) {
            lists.add(new ArrayList<>(list));
            return;
        }
        if (sum < 0) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (nums[i] > sum) {
                break;
            }
            list.add(nums[i]);
            sum(nums, lists, list, sum - nums[i], i);
            list.remove(list.size() -1);
        }
    }

    public static int sumMemo(int[] nums, int sum) {
        Map<String, Integer> memo = new HashMap<>();

        Arrays.sort(nums);
        return sumMemo(nums, memo, sum, 0);
    }

    private static int sumMemo(int[] nums, Map<String, Integer> memo, int remaining, int index) {
        System.out.println("remaining: " + remaining + ", index: " + index);
        if (remaining == 0) {
            return 1;
        }
        if (index >= nums.length) {
            return 0;
        }

        String key = remaining + "+" + index;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int ways = 0;
        int sumSoFar = 0;

        while (sumSoFar <= remaining) {
            ways += sumMemo(nums, memo, remaining - sumSoFar, index + 1);
            sumSoFar += nums[index];
        }

        memo.put(key, ways);
        return ways;
    }

    public static List<List<Integer>> sumNoDup(int[] nums, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        Arrays.sort(nums);
        sumNoDup(nums, lists, list, sum, 0);
        return lists;
    }

    private static void sumNoDup(int[] nums, List<List<Integer>> lists, List<Integer> list, int sum, int index) {
        if (sum < 0) {
            return;
        }

        if (sum == 0) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > sum) {
                break;
            }
            if(i > index && nums[i] == nums[i-1]) continue;
            list.add(nums[i]);
            sumNoDup(nums, lists, list, sum - nums[i], i + 1);
            list.remove(list.size() -1);
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
        int[] nums = new int[] {4,3,1,2,7,6};
        Combinations.printResult(Combinations.sum(nums, 7));

        int ways = Combinations.sumMemo(nums, 7);
        System.out.println("total ways: " + ways);
    }
}
