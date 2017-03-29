package com.hrishikesh.practices.array;

import java.util.Arrays;

import static com.hrishikesh.practices.array.MaxConsecutiveOnes.count;

/**
 * Problem:
 * Max Consecutive Ones
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/max-consecutive-ones/
 */
public class MaxConsecutiveOnes {

    public static int count(int[] array) {
        int maxCount = 0;
        int oneStartIndex = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                if (oneStartIndex == -1) {
                    maxCount = maxCount == 0 ? 1 : maxCount;
                    oneStartIndex = i;
                } else {
                    int length = i - oneStartIndex + 1;
                    maxCount = maxCount < length ? length : maxCount;
                }

            } else {
                oneStartIndex = -1;
            }
        }

        return maxCount;
    }
}

class MaxConsecutiveOnesTest {
    public static void main(String[] args) {
        int a1[] = {1, 1, 0, 1, 1, 1};
        int a2[] = {1, 1, 1, 1, 1, 1};
        int a3[] = {0, 0, 0};

        System.out.println("Array: " + Arrays.toString(a1));
        System.out.println("Count: " + count(a1));
        System.out.println("Array: " + Arrays.toString(a2));
        System.out.println("Count: " + count(a2));
        System.out.println("Array: " + Arrays.toString(a3));
        System.out.println("Count: " + count(a3));
    }
}