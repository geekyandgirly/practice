package binsearch;

/** NOT WORKING YET **/
public class MedianOfTwoSortedArray {

    public static double findMedian(int[] nums1, int[] nums2) {
        return findMedian(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1);
    }

    private static double findMedian(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2) {
        System.out.println("\ns1=" + s1 + " e1=" + e1 + " s2=" + s2 + " e2=" + e2);
        int mid1 = s1 + (e1 - s1 ) / 2;
        int mid2 = s2 + (e2 - s2) / 2;
        int median1 = nums1[mid1];
        int median2 = nums2[mid2];
        System.out.println("mid1=" + mid1 + " median1=" + median1 +  " mid2=" + mid2 + " median2=" + median2);

        System.out.println("s1 == e1: " + (s1 == e1) + " mid1 == e1: " + (mid1 == e1) + " s2 == e2: " + (s2 == e2) + " mid2 == e2: " + (mid2 == e2));
        if ((e1-s1 == 0) && (e2-s2 == 0)) {

            int lowCount = mid1+mid2;
            int highCount = (nums1.length - mid1) + (nums2.length - mid2);
            System.out.println(" highCount=" + highCount + " lowCount=" + lowCount);
            if (lowCount == highCount) {
                return (nums1[mid1] + nums2[mid2]) / 2;
            }

           if (lowCount - highCount == 1) {
                return median1 < median2 ? median1 : median2;
            }
            if (highCount - lowCount == 1) {
                return median1 < median2 ? median2: median1;
            }
        }

        if (median1 < median2) {
            return findMedian(nums1, mid1, e1, nums2, s2, mid2);
        } else {
            return findMedian(nums1, s1, mid1, nums2, mid2, e2);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {3,5,7,8,10,13};
        int[] nums2 = new int[] {7,9,15,16,19,20,21};

        double median = findMedian(nums1, nums2);
        System.out.println("median: " + median);

//        int[] nums11 = new int[] {1, 2, 3, 4};
//        int[] nums22 = new int[] {3, 4, 7, 8, 9, 10, 11};
//
//        double median1 = findMedian(nums11, nums22);
//        System.out.println("median: " + median1);
    }
}
