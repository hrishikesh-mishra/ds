package com.hrishikeshmishra.practices.array;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.array.RotateArray.rotate;

/**
 * Problem:
 * Rotate Array of Kth Position
 * Rotate an array of n elements to the right by k steps.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/rotate-array-kth-position/
 */
public class RotateArray {

    public static void rotate(int[] array, int k) {
        int n = array.length;

        /** In case k > n **/
        k = k % n;

        rotateByLength(0, n - 1, array);
        rotateByLength(0, k - 1, array);
        rotateByLength(k, n - 1, array);

    }

    private static void rotateByLength(int start, int end, int[] array) {
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}

class RotateArrayTest {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        System.out.println("Before Rotate: " + Arrays.toString(array));
        System.out.println("K: " + k);
        rotate(array, k);
        System.out.println("After Rotate: " + Arrays.toString(array));
    }
}
