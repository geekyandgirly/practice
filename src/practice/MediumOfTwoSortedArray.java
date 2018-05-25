package practice;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * 
 * This is similar to finding the kth element in both sorted array.
 *
 */
		
public class MediumOfTwoSortedArray {
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		

    }
	
	public static void main(String[] args) {
		int[] nums1 = new int[] {3,5,7,8,10,13,15};
		int[] nums2 = new int[] {7,9,16,19,20,21};
		
		double median = findMedianSortedArrays(nums1, nums2);
		System.out.println("median: " + median);

		int[] nums11 = new int[] {1,3};
		int[] nums22 = new int[] {2,4};
		
		double median1 = findMedianSortedArrays(nums11, nums22);
		System.out.println("median: " + median1);
}
}
