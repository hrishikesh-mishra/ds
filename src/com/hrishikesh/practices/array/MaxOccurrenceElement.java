package com.hrishikesh.practices.array;

import java.util.Arrays;

import static com.hrishikesh.practices.array.MaxOccurrenceElement.getElement;

/**
 * Problem:
 * Maximum Occurrence Element
 * Find maximum occurrence element in an array in O(n) time and O(1) extra space
 * Note: Maximum occurrence element which occurred more than half of array.
 * ;
 * ;
 * Solution:
 * - Set count = 0
 * - Iterate all array element from start to end
 * - - If count == 0 then
 * - - - Set maxiOccurrenceElement = array[index]
 * - - - Set increase count (i.e. count++)
 * - - Else If maxiOccurrenceElement == array[index] then
 * - - - Set increase count (i.e. count++)
 * - - Else
 * - - - Set decrease count (i.e. count--)
 * - Validate maxiOccurrenceElement occurred more than half of array.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/maximum-occurrence-element-in-an-array/
 */
public class MaxOccurrenceElement {

    public static Integer getElement(int[] array) {
        int count = 0;
        Integer maxiOccurrenceElement = null;

        for (int i = 0; i < array.length; i++) {

            if (count == 0) {
                count++;
                maxiOccurrenceElement = array[i];
            } else if (maxiOccurrenceElement == array[i]) {
                count++;
            } else {
                count--;
            }
        }

        return isMaximumOccurrenceElement(array, maxiOccurrenceElement) ?
                maxiOccurrenceElement : null;
    }

    private static boolean isMaximumOccurrenceElement(int[] array, int maxiOccurrenceElement) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == maxiOccurrenceElement) {
                count++;
            }
        }

        return count > array.length / 2;
    }

}


class MaxOccurrenceElementTest {
    public static void main(String[] args) {
        int[] array = {1, 2, 1, 3, 1, 5, 1, 6, 1, 2, 2, 1, 4, 1, 1};

        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Max occurrence element: " + getElement(array));
    }
}