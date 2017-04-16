package com.hrishikeshmishra.practices.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hrishikeshmishra.practices.array.DuplicatesNumbersInArray.findDuplicate;

/**
 * Problem:
 * Duplicates in an Array in O(n)
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear more than one time
 * and others appear once.
 * Find duplicate items.
 * ;
 * ;
 * Facts:
 * - Total size of array is n
 * - Element in array is between 1 and n
 * - Some element could be repeated twice
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/duplicates-in-an-array-in-on/
 */
public class DuplicatesNumbersInArray {

    public static List<Integer> findDuplicate(int[] array) {

        /** To hold duplicate elements **/
        List<Integer> result = new ArrayList<>();

        /** Length of array **/
        int n = array.length;

        /**
         * Iterate array from start to end
         * Mark convert each element to negative
         * Now, if element is already negative,
         * that means the pointer, which pointing to this element has seen before and
         * that means that is duplicate element
         */
        for (int i = 0; i < n; i++) {

            /** Check element is already converted to negative or not **/
            if (array[Math.abs(array[i]) - 1] >= 0) {
                /** If not converted to negative, means we are reading it first time **/
                array[Math.abs(array[i]) - 1] = -array[Math.abs(array[i]) - 1];
            } else {
                /** If element is negative then, pointer which point this element is duplicate **/
                result.add(Math.abs(array[i]));
            }

        }

        /** Return duplicate element **/
        return result;
    }
}

class DuplicatesNumbersInArrayTest {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 2, 4};
        int[] array2 = {4, 3, 2, 7, 8, 2, 3, 1};
        int[] array3 = {10, 2, 5, 10, 9, 1, 1, 4, 3, 7};

        System.out.println("Array : " + Arrays.toString(array1));
        System.out.println("Duplicate: " + findDuplicate(array1));

        System.out.println("Array : " + Arrays.toString(array2));
        System.out.println("Duplicate: " + findDuplicate(array2));

        System.out.println("Array : " + Arrays.toString(array3));
        System.out.println("Duplicate: " + findDuplicate(array3));
    }
}