package com.hrishikesh.practices.array;

import java.util.Arrays;

import static com.hrishikesh.practices.array.ZeroAlignmentToEnd.align;

/**
 * Problem:
 * Align all zero to end of array.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/align-zero-end-array/
 */
public class ZeroAlignmentToEnd {

    public static void align(int[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                int numberOfZero = findContigiousZero(i, array);
                moveZeroToEnd(i, numberOfZero, array);

            }
        }
    }

    private static int findContigiousZero(int startIndex, int[] array) {
        int counter = 0;
        while (startIndex < array.length && array[startIndex] == 0 ) {
            counter++;
            startIndex++;
        }

        return counter;
    }

    private static void moveZeroToEnd(int i, int numberOfZero, int[] array) {
        while (i < array.length - numberOfZero) {
            array[i] = array[i + numberOfZero];
            i++;
        }

        for (int j = 0; j < numberOfZero; j++) {
            array[j + i] = 0;
        }

    }
}

class ZeroAlignmentToEndTest {
    public static void main(String[] args) {
        int[] array = {0, 0, 1};

        System.out.println("Array: " + Arrays.toString(array));
        align(array);
        System.out.println("After alignment: " + Arrays.toString(array));
    }
}
