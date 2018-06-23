package comboperm;

import java.util.*;

public class Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        permute(list, new LinkedHashSet<Integer>(), nums);
        return list;
    }

    public static void permute(List<List<Integer>> list, LinkedHashSet<Integer> linkedHashSet, int [] nums){
        if(linkedHashSet.size() == nums.length){
            list.add(new ArrayList<>(linkedHashSet));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(linkedHashSet.add(nums[i])) {
                permute(list, linkedHashSet, nums);
                linkedHashSet.remove(nums[i]);
            }
        }
    }

    public static List<List<Integer>> perm(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        perm(list, nums, 0);
        return list;
    }

    private static void perm(List<List<Integer>> list, int[] nums, int index) {
        if (index == nums.length) {
            List<Integer> tempList = new ArrayList<>();
            for (int num : nums) {
                tempList.add(num);
            }
            list.add(tempList);
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            perm(list, nums, index + 1);
            swap(nums, i, index);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
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
        int[] nums = new int[]{1, 2, 3};
        Permutations.printResult(Permutations.perm(nums));
    }

}
