package com.hrishikesh.practices.array;

/**
 * Problem:
 * Median of Two Sorted Arrays
 * Given two sorted arrays of size m and n, find the median of the array obtained by merging these two arrays.
 * The overall run time complexity should be O(log (m+n)).
 *
 * @author hrishikesh.mishra
 * @todo for
 * [4]
 * [1,2,3]
 * @link http://articles.leetcode.com/median-of-two-sorted-arrays#comment-1053
 * @link https://www.youtube.com/watch?v=3jJ6gojbr0A
 */
public class MedianOfTwoSortedArrays {

    public double findMedian(int[] A, int[] B, int n, int m) {

        if (n == 0 && m == 0) {
            return -1;
        }

        if (n == 0) {
            return findMedianInOneArray(B);
        } else if (m == 0) {
            return findMedianInOneArray(A);
        }


        if (n < m) {
            return findMedian(A, B, 0, n - 1, n, m);
        } else {
            return findMedian(B, A, 0, m - 1, m, n);
        }
    }


    private double findMedian(int[] nums1, int[] nums2, int low, int high, int n, int m) {

        /** In reached end of one array **/
        if (low > high) {
            return findMedian(nums2, nums1,
                    Math.max(0, (n + m) / 2 - n),
                    Math.min(m, (n + m) / 2),
                    m, n);
        }

        /** Suppose median is in array A, i.e. A[i] **/
        int i = (low + high) / 2;

        /** Compute index in j **/
        int j = ((n + m) / 2) - i - 1;

        /**
         * Now, A[i] is median then it will hold following equality
         * B[j] ≤ A[i] ≤ B[j+1]
         */

        /** In case, median is smaller **/
        if (j >= 0 && nums1[i] < nums2[j]) {

            return findMedian(nums1, nums2, i + 1, high, n, m);

        }
        /** In case, median is greater **/
        else if (j < m - 1 && nums1[i] > nums2[j + 1]) {

            return findMedian(nums1, nums2, low, i - 1, n, m);

        }

        /** In case, median is found **/
        else {

            /** Total odd number of element **/
            if ((n + m) % 2 == 1) {

                return nums1[i];

            } else if (i > 0) {

                return (nums1[i] + Math.max(nums2[j], nums1[i - 1])) / 2.0;

            } else {
                return (nums1[i] + nums2[j]) / 2.0;
            }
        }
    }

    private double findMedianInOneArray(int[] array) {
        int size = array.length;
        if (size % 2 == 1) {
            return array[size / 2];
        }

        int mid = size / 2;
        return 1.0 * (array[mid] + array[mid - 1]) / 2;
    }
}
