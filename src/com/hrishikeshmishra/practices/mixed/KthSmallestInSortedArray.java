package com.hrishikeshmishra.practices.mixed;

/**
 * Problem:
 * How to find the kth smallest element in the union of two sorted arrays?
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/kth-smallest-element-union-two-sorted-arrays/
 */
public class KthSmallestInSortedArray {

    public static int findKthElement(int a1[], int a2[], int k) {

        /** Checking k must less than sum of length of both array **/
        if (a1.length + a2.length < k) {
            throw new IllegalArgumentException();
        }

        /** K must be greater than zero **/
        if (k <= 0) {
            throw new IllegalArgumentException();
        }

        /**
         * Finding begin, l and end such that
         * begin <= l < end
         * a1[0].....a1[l-1] and
         * a2[0]....a2[k-l-1] are the smallest k numbers
         */
        int begin = Math.max(0, k - a2.length);
        int end = Math.min(a1.length, k);

        while (begin < end) {
            int l = begin + (end - begin) / 2;

            /** Can we include a1[l] in the k smallest numbers */
            if ((l < a1.length) &&
                    (k - l > 0) &&
                    (a1[l] < a2[k - l - 1])) {

                begin = l + 1;

            } else if ((l > 0) &&
                    (k - l < a2.length) &&
                    (a1[l - 1] > a2[k - 1])) {

                /**
                 * This is the case where we can discard
                 * a[l-1] from the set of k smallest numbers
                 */
                end = l;

            } else {

                /**
                 * We found our answer since both inequalities were
                 * false
                 */
                begin = l;
                break;
            }
        }

        if (begin == 0) {
            return a2[k - 1];
        } else if (begin == k) {
            return a1[k - 1];
        } else {
            return Math.max(a1[begin - 1], a2[k - begin - 1]);
        }
    }
}


class KthSmallestInSortedArrayTest {
    public static void main(String[] args) {
        int a1[] = {2, 3, 10, 11, 43, 56},
                a2[] = {120, 13, 14, 24, 34, 36},
                k = 4;

        System.out.println(KthSmallestInSortedArray.findKthElement(a1, a2, k));
    }
}