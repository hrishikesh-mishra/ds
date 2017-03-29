package com.hrishikesh.practices.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.hrishikesh.practices.array.ArrayRanges.getRange;

/**
 * Problem:
 * Array Ranges
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"]
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/array-ranges/
 */
public class ArrayRanges {

    public static List<String> getRange(int[] array) {
        List<String> ranges = new ArrayList<>();

        for (int i = 0; i < array.length; ) {
            i = findRange(array, i, ranges);
        }

        return ranges;
    }

    private static int findRange(int[] array, int i, List<String> ranges) {
        int start = array[i];
        Integer end = null;
        int temp = start;
        int j = i + 1;
        while (j < array.length && temp + 1 == array[j]) {
            temp = end = array[j];
            j++;
        }


        if (Objects.isNull(end)) {
            ranges.add(String.valueOf(start));
        } else {
            ranges.add(start + "->" + end);
        }

        return j;

    }
}


class ArrayRangesTest {
    public static void main(String[] args) {
        int[] array = {0, 1, 2, 4, 5, 7};
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Range: " + getRange(array));
    }
}