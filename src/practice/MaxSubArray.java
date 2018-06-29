package practice;

/**
 * https://leetcode.com/problems/maximum-subarray/description/
 */
public class MaxSubArray {

    /**
     * Use two pointers. Keeping going on the right until sum becomes negative. Then move left pointer until sum
     * is positive again, or till left == right, at which point start moving the right pointer again.
     */
    public int[] maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return new int[] {0, 0 , 0};
        if (nums.length == 1) return new int[] {nums[0], 0, 1};

        int runningSum = 0, max = nums[0];
        int left = 0, right = 0;
         int maxLeft = left, maxRight = right;
        while (right < nums.length) {
            while ((runningSum >= 0 || left == right) && right < nums.length) {
                runningSum += nums[right];
                if (runningSum > max) {
                    max = runningSum;
                     maxLeft = left;
                     maxRight = right;
                }
                right++;
            }
            while (runningSum <= 0 && left < right && right < nums.length) {
                runningSum -= nums[left++];
                if (runningSum > max && left != right) {
                    max = runningSum;
                     maxLeft = left;
                     maxRight = right;
                }
            }
        }
        return new int[] {max, maxLeft, maxRight};
    }

    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int runningSum = 0, max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            max = Math.max(max, runningSum);

            if (runningSum < 0) {
                // Start over if running sum becomes negative.
                runningSum = 0;
            }

            // System.out.println("i: " + i + " sum: " + runningSum + " max:" + max);
        }
        return max;
    }
}
