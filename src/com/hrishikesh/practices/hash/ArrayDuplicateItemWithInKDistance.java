package com.hrishikesh.practices.hash;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Problem:
 * Check if a given array contains duplicate elements within k distance from each other
 * Given an unsorted array that may contain duplicates. Also given a number k which is smaller
 * than size of array. Write a function that returns true if array contains duplicates within k distance.
 * ;
 * Example:
 * Input: k = 3, arr[] = {1, 2, 3, 4, 1, 2, 3, 4}
 * Output: false
 * All duplicates are more than k distance away.
 * ;
 * Input: k = 3, arr[] = {1, 2, 3, 1, 4, 5}
 * Output: true
 * 1 is repeated at distance 3.
 * ;
 * Input: k = 3, arr[] = {1, 2, 3, 4, 5}
 * Output: false
 * ;
 * Input: k = 3, arr[] = {1, 2, 3, 4, 4}
 * Output: true
 * ;
 * Solution:
 * - Task Hash (specially HashSet - so duplicate will not allowed)
 * - Iterate all element one by one in stack
 * - Put first k elements of array to set and check for if its already represent in set or not if yes then return true
 * - if loop counter greater than k then remove first inserted element (i - k th) from array.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/check-given-array-contains-duplicate-elements-within-k-distance/
 */
public class ArrayDuplicateItemWithInKDistance {

    public static boolean isDuplicateContains(int[] array, int k) {

        /** Base case: when K is greater than array size **/
        if (k > array.length || array.length == 1) {
            return false;
        }

        /** Hash Set to hold k element **/
        HashSet<Integer> hashSet = new HashSet<>();

        /** base element **/
        hashSet.add(array[0]);

        /** Iterate one by one of array element **/
        for (int i = 1; i < array.length; i++) {

            /** If duplicate contains **/
            if (hashSet.contains(array[i])) {
                return true;
            }

            /** Remove first inserted element from array **/
            if (i > k - 1) {
                hashSet.remove(array[i - k]);
            }

            /** Insert element to hash set **/
            hashSet.add(array[i]);
        }

        return false;
    }
}


class ArrayDuplicateItemWithInKDistanceTest {
    public static void main(String[] args) {

        int [] array   = {1, 2, 3, 4, 1, 2, 3, 4};
        System.out.println("Array : " + Arrays.toString(array) + ", K= 3 : " +
                ArrayDuplicateItemWithInKDistance.isDuplicateContains(array, 3));

        array   = new int[]{1, 2, 3, 1, 4, 5};
        System.out.println("Array : " + Arrays.toString(array) + ", K= 3 : " +
                ArrayDuplicateItemWithInKDistance.isDuplicateContains(array, 3));


        array   = new int[] {1, 2, 3, 4, 5};
        System.out.println("Array : " + Arrays.toString(array) + ", K= 3 : " +
                ArrayDuplicateItemWithInKDistance.isDuplicateContains(array, 3));


        array   = new int[] {1, 2, 3, 4, 4};
        System.out.println("Array : " + Arrays.toString(array) + ", K= 3 : " +
                ArrayDuplicateItemWithInKDistance.isDuplicateContains(array, 3));


    }
}