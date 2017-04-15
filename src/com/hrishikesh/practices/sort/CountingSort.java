package com.hrishikesh.practices.sort;

import java.util.Arrays;

/**
 * Problem:
 * Counting Sort
 * ;
 * Counting Sorting assumes that for each of the n input elements is an integer in the range 0 to k, for some integer k.
 * When k=O(n) the sort runs in Θ(n) time.
 * ;
 * Counting sort determines, for each input element x, the number of elements less than x. It uses this information
 * to place element x directly into its position in the output array.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/counting-sort/
 */
public class CountingSort {

    public static int[] sort(int[] input) {
        int max = findMax(input);
        int n = input.length;
        int[] output = new int[n];
        int[] temp = new int[max + 1];


        /** Counting number of occurrence of elements **/
        for (int i = 0; i < n; i++) {
            temp[input[i]]++;
        }

        /** updating number of elements less than or equal to i **/
        for (int i = 1; i <= max; i++) {
            temp[i] = temp[i] + temp[i - 1];
        }

        /** Updating in  output array **/
        for (int i = 0; i < n; i++) {
            output[temp[input[i]] - 1] = input[i];
            temp[input[i]] = temp[input[i]] - 1;
        }

        return output;
    }

    public static int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

}

class CountingSortTest {
    public static void main(String[] args) {
        int[] input = {2, 5, 3, 0, 2, 3, 0, 3};
        System.out.println("Input : " + Arrays.toString(input));
        int[] output = CountingSort.sort(input);
        System.out.println("Output : " + Arrays.toString(output));
    }
}
