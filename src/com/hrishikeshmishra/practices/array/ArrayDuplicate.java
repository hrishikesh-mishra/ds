package com.hrishikeshmishra.practices.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Problem:
 * Contains Duplicate in array at most far k
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j
 * in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/contains-duplicate-array-far-k/
 */
public class ArrayDuplicate {

    public static boolean find(int[] array, int k) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {

            if (map.containsKey(array[i])) {
                Set<Integer> indices = map.get(array[i]);

                for (Integer index : indices) {
                    if (Math.abs(index - i) <= k) {
                        return true;
                    }
                }

                map.get(array[i]).add(i);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                map.put(array[i], set);
            }
        }

        return false;
    }

}

class ArrayDuplicateTest {
    public static void main(String[] args) {
        int[] array = {1, 3, 4, 2, 1, 5};
        int k = 5;
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("K : " + k);
        System.out.println("Is exists? " + ArrayDuplicate.find(array, k));
    }
}
