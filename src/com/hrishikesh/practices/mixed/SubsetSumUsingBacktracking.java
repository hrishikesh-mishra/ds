package com.hrishikesh.practices.mixed;

import java.util.StringJoiner;

import static com.hrishikesh.practices.mixed.SubsetSumUsingBacktracking.findSumSubSet;

/**
 * Problem:
 * Subset Sum using Backtracking
 * ;
 * Given a set (i.e. an array) of n distinct positive numbers, find a subset whose sum of elements is m.
 * <code>SumOfSubsetUsingDP</code>
 * ;
 * ;
 * Algorithm:
 * - if index == array.length then
 * - - return false
 * - if array[index] == sum then
 * - - return true
 * - Iterate given array from index to array.length
 * - - If array[i] > sum then
 * - - - don't do anything take next element from array
 * - - If array[i] == sum then
 * - - - return true
 * - - Recursively call with with index +1 and sum - array[i]
 * - - If last recursive call was success then
 * - - - return true
 * - return false
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/subset-sum-using-backtracking/
 */
public class SubsetSumUsingBacktracking {

    public static void findSumSubSet(int[] array, int sum) {

        int[] subset = new int[array.length];

        boolean isSubSetFound = findSubSet(array, subset, sum, 0, 0);
        if (isSubSetFound) {
            System.out.print("\nSum subset for " + sum + " : ");
            StringJoiner sj = new StringJoiner(", ", "[", "]");

            for (int temp = 0, i = 0; temp != sum; temp += subset[i], i++) {
                sj.add(String.valueOf(subset[i]));
            }

            System.out.print(sj.toString());
        } else {
            System.out.println("\nSubset not found for sum : " + sum);
        }
    }

    private static boolean findSubSet(int[] array, int[] subset, int sum, int index, int subSetIndex) {
        if (index == array.length) {
            return false;
        }

        if (array[index] == sum) {
            subset[subSetIndex] = array[index];
            return true;
        }

        for (int i = index; i < array.length; i++) {

            if (array[i] > sum) {
                continue;
            }

            if (array[i] == sum) {
                subset[subSetIndex] = array[i];
                return true;
            }

            subset[subSetIndex] = array[i];
            if (findSubSet(array, subset, sum - array[i], i + 1, subSetIndex + 1)) {
                return true;
            }

        }

        return false;
    }
}

class SubsetSumUsingBacktrackingTest {
    public static void main(String[] args) {
        int[] array = {8, 3, 2, 10, 7};
        findSumSubSet(array, 5);
        findSumSubSet(array, 12);
        findSumSubSet(array, 25);
        findSumSubSet(array, 27);
        findSumSubSet(array, 50);
        findSumSubSet(array, 20);

    }
}