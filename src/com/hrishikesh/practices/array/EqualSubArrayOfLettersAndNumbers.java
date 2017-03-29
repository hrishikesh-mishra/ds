package com.hrishikesh.practices.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.hrishikesh.practices.array.EqualSubArrayOfLettersAndNumbers.ElementType.LETTER;
import static com.hrishikesh.practices.array.EqualSubArrayOfLettersAndNumbers.getLongestSubArray;

/**
 * Problem:
 * Longest sub array of equal letters and numbers
 * ;
 * An array has both letters and numbers, find longest subarray with equal number of
 * letters and numbers.
 * ;
 * ;
 * Algorithm:
 * - Create an array diff_array of same size of given array
 * - Populate each element of diff_array with <code>total_number_found - total_letter_found</code> till current index
 * - Now Iterate diff_array to find longest sub-array of same size
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/longest-sub-array-equal-letters-numbers/
 */
public class EqualSubArrayOfLettersAndNumbers {

    public enum ElementType {
        LETTER,
        NUMBER
    }

    private static class DiffDetail {
        private int firstIndex;
        private int lastIndex;

        public DiffDetail() {
        }

    }

    public static char[] getLongestSubArray(char[] array) {
        int[] diffArray = populateDiffArray(array);
        DiffDetail diffDetail = getLongestSubArrayFromDiff(diffArray);
        return getArray(array, diffDetail.firstIndex + 1, diffDetail.lastIndex);
    }

    private static char[] getArray(char[] array, int start, int end) {
        char[] subarray = new char[end - start + 1];
        for (int i = start; i <= end; i++) {
            subarray[i - start] = array[i];
        }
        return subarray;
    }

    private static DiffDetail getLongestSubArrayFromDiff(int[] diffArray) {
        Map<Integer, Integer> map = new HashMap<>();
        DiffDetail diffDetail = new DiffDetail();

        for (int i = 0; i < diffArray.length; i++) {
            if (map.containsKey(diffArray[i])) {
                if (i - map.get(diffArray[i]) > diffDetail.lastIndex - diffDetail.firstIndex) {
                    diffDetail.firstIndex = map.get(diffArray[i]);
                    diffDetail.lastIndex = i;
                }
            } else {
                map.put(diffArray[i], i);
            }
        }

        return diffDetail;
    }

    private static int[] populateDiffArray(char[] array) {
        int diff = 0;
        int[] diffArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (getType(array[i]) == LETTER) {
                diff++;
            } else {
                diff--;
            }
            diffArray[i] = diff;
        }
        return diffArray;
    }

    private static ElementType getType(char c) {
        if (Character.isLetter(c)) {
            return LETTER;
        }

        if (Character.isDigit(c)) {
            return ElementType.NUMBER;
        }

        throw new RuntimeException("Invalid character");
    }


}

class EqualSubArrayOfLettersAndNumbersTest {
    public static void main(String[] args) {
        char[] array = {
                'a', 'a', 'a', 'a',
                '1', '1',
                'a',
                '1', '1',
                'a', 'a',
                '1',
                'a', 'a',
                '1',
                'a', 'a', 'a', 'a'
        };

        System.out.println("Array: " + Arrays.toString(array));
        char[] longestSubArray = getLongestSubArray(array);
        System.out.println("Longest SubArray: " + Arrays.toString(longestSubArray));
    }
}