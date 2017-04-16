package com.hrishikeshmishra.practices.array;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.array.ArrayPeakElement.findPeakElementIndex;

/**
 * Problem:
 * Array Peak Element
 * A peak element is an element that is greater than its neighbors, i.e. A[i-1] < A[i] > A[i+1]
 * For a given array find peak element and return index of that element.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/array-peak-element/
 */
public class ArrayPeakElement {

    public static int findPeakElementIndex(int[] array) {
        int n = array.length;

        /** Base case: for one  element **/
        if (array.length == 1) {
            return 0;
        }

        /** Base case: two elements **/
        if (array.length == 2) {

            if (array[1] == array[0]) {
                return -1;
            }

            return array[1] > array[0] ? 1 : 0;
        }

        for (int i = 0; i < n - 1; i++) {

            if (i == 0 && array[0] > array[1]) {
                return 0;
            } else if (i> 0&& array[i - 1] < array[i] && array[i] > array[i + 1]) {
                return i;
            }
        }

        return (array[n - 1] > array[n - 2]) ? n - 1 : -1;
    }
}

class ArrayPeakElementTest {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 1};
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Index: " + findPeakElementIndex(array));
    }
}
