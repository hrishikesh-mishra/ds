package com.hrishikeshmishra.practices.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.hrishikeshmishra.practices.array.MissingNumbers.findMissingNumbers;

/**
 * Problem:
 * Find All Numbers Disappeared in an Array
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array),
 * some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-numbers-disappeared-array/
 */
public class MissingNumbers {

    public static List<Integer> findMissingNumbers(int[] array) {
        /** Result **/
        List<Integer> result = new ArrayList<>();

        /** Base case: When array is empty **/
        if (Objects.isNull(array) || array.length == 0) {
            return result;
        }

        /** Length of array **/
        int n = array.length;

        /** Sorting array **/
        Arrays.sort(array);

        /** Missing in first part **/
        for (int i = 1; i < array[0]; i++) {
            result.add(i);
        }

        /** Checking missing numebrs in array **/
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] - array[i] > 1) {
                addMissingNumbersBetween(array[i], array[i + 1], result);
            }
        }

        /** Missing from end **/
        for (int i = array[array.length - 1] + 1; i <= n; i++) {
            result.add(i);
        }


        return result;
    }

    private static void addMissingNumbersBetween(int start, int end, List<Integer> result) {
        for (int i = start + 1; i < end; i++) {
            result.add(i);
        }
    }

}


class MissingNumbersTest {
    public static void main(String[] args) {
        int[] array1 = {4, 3, 2, 7, 8, 2, 3, 1};
        int[] array2 = {1, 1};
        int[] array3 = {9,9,4,10,8,5,2,2,7,7};

        System.out.println("Array: " + Arrays.toString(array1));
        System.out.println("Missing number: " + findMissingNumbers(array1));

        System.out.println("Array: " + Arrays.toString(array2));
        System.out.println("Missing number: " + findMissingNumbers(array2));

        System.out.println("Array: " + Arrays.toString(array3));
        System.out.println("Missing number: " + findMissingNumbers(array3));

    }
}