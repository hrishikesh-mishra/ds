package com.hrishikeshmishra.practices.recursion;

import java.util.Arrays;

/**
 * Problem:
 * A magic index in an array A[0....N-1] is defined to an index such that A[i] = i. Given a sorted array
 * of distinct integers. Find that index if it exists in array and values of array are not distinct.
 * ;
 * ;
 * Solution:
 * - Similar to binary search but complexity increased when it mentioned that array has duplicate element.
 * - So, find middleIndex = (start + end) /2
 * - If middleIndex  == A[middleIndex] then return middleIndex
 * - Else search left portion of array by computing left Index till leftIndex >=0
 * - -leftIndex = min(middleIndex - 1, A[middleIndex])
 * - Else search right portion of array by computing right Index till right <= end
 * - - rightIndex = max(middleIndex + 1, A[middleIndex])
 * ;
 * ;
 * ;
 * Algorithm:
 * - If end < start then
 * - - return - 1
 * - Compute middleIndex = (start + end ) / 2
 * - If middleIndex  == A[middleIndex] then
 * - - return middleIndex
 * - Compute leftIndex = min(middleIndex -1, A[middleIndex])
 * - Recursively Call for leftPortion with start and leftIndex
 * - If left < 0
 * - - return left
 * - Compute rightIndex = max (middleIndex +1, A[middleIndex ])
 * - Recursively call for rightPortion with rightIndex and end
 * - return right
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/magic-index/
 */
public class MagicIndex {

    public static int findMagicIndex(int[] array, int start, int end) {
        if (end < start) {
            return -1;
        }

        int middleIndex = (start + end) / 2;
        if (middleIndex == array[middleIndex]) {
            return middleIndex;
        }

        /** Search in left portion **/
        int leftIndex = Math.min(middleIndex - 1, array[middleIndex]);
        int left = findMagicIndex(array, start, leftIndex);
        if (left >= 0) {
            return left;
        }

        /** Search in right portion **/
        int rightIndex = Math.max(middleIndex + 1, array[middleIndex]);
        int right = findMagicIndex(array, rightIndex, end);
        return right;
    }
}

class MagicIndexTest {

    public static void main(String[] args) {
        int[] array1 = {3, 8, 10, 17, 18, 30, 100};
        int[] array2 = {-2, -1, 2, 2, 2, 6, 7};
        int[] array3 = {-3, -2, -1, 0, 4, 6, 7, 10};

        System.out.println("Array: " + Arrays.toString(array1) + " -> Magic Index : " +
                MagicIndex.findMagicIndex(array1, 0, array1.length - 1));

        System.out.println("Array: " + Arrays.toString(array2) + " -> Magic Index : " +
                MagicIndex.findMagicIndex(array2, 0, array2.length - 1));

        System.out.println("Array: " + Arrays.toString(array3) + " -> Magic Index : " +
                MagicIndex.findMagicIndex(array3, 0, array3.length - 1));

    }
}