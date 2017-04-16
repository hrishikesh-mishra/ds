package com.hrishikeshmishra.practices.array;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.array.SortSubArray.findIndices;

/**
 * Sort sub array
 * Given an array of integers, find indices m and n such that if you sorted elements m through n,
 * the entire array would be sorted. Minimize n-m. i.e. find smallest sequence.
 * ;
 * ;
 * Algorithm:
 * - Iterate from left to right till it increasing (suppose index to leftIndexEnd)
 * - Iterate from right to left till it decrease (suppose index to  rightIndexStart)
 * - Find index minIndex between leftIndexEnd and rightIndexStart which has minimum value
 * - Find index maxIndex between leftIndexEnd and rightIndexStart which has maximum value
 * - Find leftIndex between 0 to leftIndexEnd which comes just before array[minIndex]
 * - Find rightIndex size - 1 to rightIndexStart which comes just before array[maxIndex]
 * - Return leftIndex and rightIndex
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/sort-sub-array/
 */
public class SortSubArray {

    public static class Result {
        private int m;
        private int n;

        public Result(int m, int n) {
            this.m = m;
            this.n = n;
        }

        public int getM() {
            return m;
        }

        public int getN() {
            return n;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "m=" + m +
                    ", n=" + n +
                    '}';
        }
    }

    public static Result findIndices(int[] array) {
        int leftIndexEnd = getLeftIndexEnd(array);
        int rightIndexStart = getRightIndexStart(array);
        int minIndex = leftIndexEnd;
        int maxIndex = rightIndexStart;

        for (int index = leftIndexEnd + 1; index <= rightIndexStart; index++) {
            if (array[index] < array[minIndex]) {
                minIndex = index;
            }

            if (array[index] > array[maxIndex]) {
                maxIndex = index;
            }
        }

        int leftIndex = shrinkLeft(array, leftIndexEnd, minIndex);
        int rightIndex = shrinkRight(array, rightIndexStart, maxIndex);

        return new Result(leftIndex, rightIndex);
    }

    private static int shrinkLeft(int[] array, int leftIndexEnd, int minIndex) {
        int index = leftIndexEnd;
        for (; index >= 0 && array[index] > array[minIndex]; index--) ;
        return index + 1;
    }

    private static int shrinkRight(int[] array, int rightIndexStart, int maxIndex) {
        int index = rightIndexStart;
        for (; index < array.length && array[index] < array[maxIndex]; index++) ;
        return index - 1;
    }

    private static int getLeftIndexEnd(int[] array) {
        int index = 0;
        for (; index < array.length && array[index] <= array[index + 1]; index++) ;
        return index;
    }

    private static int getRightIndexStart(int[] array) {
        int index = array.length - 1;
        for (; index >= 0 && array[index] >= array[index - 1]; index--) ;
        return index;
    }
}

class SortSubArrayTest {
    public static void main(String[] args) {
        int[] array = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        SortSubArray.Result result = findIndices(array);
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Result: " + result);
    }
}