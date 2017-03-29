package com.hrishikesh.practices.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Problem:
 * Longest Consecutive Sequence In Array
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence in O(n).
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/longest-consecutive-sequence-array/
 */
public class LongestConsecutiveSequenceInArray {

    public static int findSize(int[] array) {

        /** Base case: when array is empty **/
        if (Objects.isNull(array) || array.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }

        int maxLength = 0;
        for (int i = 0; i < array.length; i++) {
            int previousElement = array[i] - 1;
            int nextElement = array[i] + 1;
            int count = 1;

            while (set.contains(previousElement)) {
                count++;
                set.remove(previousElement);
                previousElement--;
            }


            while (set.contains(nextElement)) {
                count++;
                set.remove(nextElement);
                nextElement++;
            }

            maxLength = Math.max(maxLength, count);

        }

        return maxLength;
    }
}

class LongestConsecutiveSequenceInArrayTest {
    public static void main(String[] args) {
        int[] array = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42};
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Max length: " + LongestConsecutiveSequenceInArray.findSize(array));
    }
}
