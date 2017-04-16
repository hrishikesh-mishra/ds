package com.hrishikeshmishra.practices.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem:
 * In an array find subArray whose sum is give.
 *
 * @author hrishikesh.mishra
 */
public class SubArrayWithGivenSum {

    public static int[] find(int[] array, int sum) {
        Map<Integer, Integer> map = new HashMap<>();

        int arraySum = 0;
        for (int i = 0; i < array.length; i++) {
            arraySum += array[i];

            if (arraySum == sum) {
                return Arrays.copyOf(array, i + 1);
            }

            int remaining = arraySum - sum;
            if (map.containsKey(remaining)) {
                int arrayLen = (i - map.get(remaining));
                int[] subArray = new int[arrayLen];
                System.arraycopy(array, map.get(remaining) + 1, subArray, 0, arrayLen);
                return subArray;
            }

            map.put(arraySum, i);
        }

        return null;
    }
}

class SubArrayWithGivenSumTest {
    public static void main(String[] args) {
        int[] array = {1, 4, 20, 3, 10, 5};
        int[] subArray = SubArrayWithGivenSum.find(array, 33);
        System.out.printf("Array: %s, Sum: %d, SubArray: %s ", Arrays.toString(array), 33, Arrays.toString(subArray));
    }
}