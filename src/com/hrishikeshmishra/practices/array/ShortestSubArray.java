package com.hrishikeshmishra.practices.array;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.array.ShortestSubArray.getShortestSubArray;

/**
 * Problem:
 * Shortest Sub Array:
 * Given two array (longer and shorter),
 * Find shortest subarray in longest array that has all elements of shorter
 * array.
 * ;
 * ;
 * Algorithm:
 * - Create a array with size  shorter_array_size X longer_array_size
 * - It will last occurrence of each elements of shorter_array in longer_array
 * - Create a closure array from array
 * - And in closure array find min difference
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/shortest-sub-array/
 */
public class ShortestSubArray {

    public static class Result {
        private int start;
        private int end;

        public Result(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static Result getShortestSubArray(int[] smallArray, int[] bigArray) {
        int[][] nextElements = getNextElements(smallArray, bigArray);
        int[] closures = getClosures(nextElements);
        return getSubArray(closures);
    }

    private static Result getSubArray(int[] closures) {
        int start = -1;
        int end = -1;
        for (int i = 0; i < closures.length; i++) {

            if (closures[i] == -1) {
                break;
            }

            int current = closures[i] - i;
            if (start == -1 || current < end - start) {
                start = i;
                end = closures[i];
            }
        }

        return new Result(start, end);
    }

    private static int[] getClosures(int[][] nextElements) {
        int[] maxNextElement = new int[nextElements[0].length];

        for (int i = 0; i < nextElements[0].length; i++) {
            maxNextElement[i] = getMaxElements(nextElements, i);
        }

        return maxNextElement;
    }

    private static int getMaxElements(int[][] nextElements, int index) {
        int max = -1;
        for (int i = 0; i < nextElements.length; i++) {
            if (nextElements[i][index] == -1) {
                return -1;
            }
            max = Math.max(max, nextElements[i][index]);
        }
        return max;
    }


    private static int[][] getNextElements(int[] smallArray, int[] bigArray) {
        int[][] nextElements = new int[smallArray.length][bigArray.length];
        for (int i = 0; i < smallArray.length; i++) {
            nextElements[i] = getNextElements(bigArray, smallArray[i]);
        }
        return nextElements;
    }

    private static int[] getNextElements(int[] bigArray, int value) {
        int next = -1;
        int[] maxNextElement = new int[bigArray.length];
        for (int i = bigArray.length - 1; i >= 0; i--) {
            if (bigArray[i] == value) {
                next = i;
            }
            maxNextElement[i] = next;
        }
        return maxNextElement;
    }
}

class ShortestSubArrayTest {
    public static void main(String[] args) {
        int[] bigArray = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
        int[] smallArray = {1, 5, 9};
        System.out.println("Big Array: " + Arrays.toString(bigArray));
        System.out.println("Small Array: " + Arrays.toString(smallArray));
        System.out.println("Shortest Sub Array : " + getShortestSubArray(smallArray, bigArray));
    }


}