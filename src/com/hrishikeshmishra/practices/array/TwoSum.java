package com.hrishikeshmishra.practices.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.hrishikeshmishra.practices.array.TwoSum.find;

/**
 * Problem:
 * Two Sum
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 *
 * @author hrishikesh.mishra
 * @link hrishikeshmishra.com/twosum/
 */
public class TwoSum {

    public static int[] find(int[] array, int sum) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {

            if (map.containsKey(array[i])) {
                int index = map.get(array[i]);
                return new int[]{index, i};

            } else {
                map.put(sum - array[i], i);
            }
        }

        return null;
    }
}

class TwoSumTest {

    public static void main(String[] args) {
        int[] array = {2, 3, 5, 1, 6, 18};
        int sum = 19;

        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Sum: " + sum);
        System.out.println("Indices: " + Arrays.toString(find(array, sum)));
    }
}