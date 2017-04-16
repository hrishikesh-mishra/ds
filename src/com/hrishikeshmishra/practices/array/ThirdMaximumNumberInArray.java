package com.hrishikeshmishra.practices.array;

import java.util.Arrays;
import java.util.Objects;

/**
 * Problem:
 * Find the third maximum number in array.
 * Given a non-empty array of integers, return the third maximum number in this array.
 * If it does not exist, return the maximum number, and algorithm must work in O(n).
 * ;
 * ;
 * Solution:
 * - Create three buckets for variable to hold first, second and third max values.
 * - When updating first bucket with new value then update second bucket with old first bucket value.
 * - When updating second bucket with new value then, update third bucket with old second bucket value.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-third-maximum-number-array/
 */
public class ThirdMaximumNumberInArray {

    private Integer firstMax;
    private Integer secondMax;
    private Integer thirdMax;

    public int find(int[] array) {

        for (int i = 0; i < array.length; i++) {

            if (isSmallerOrEqual(firstMax, array[i])) {
                updateFirstMax(array[i]);
            } else if (isSmallerOrEqual(secondMax, array[i])) {
                updateSecondMax(array[i]);
            } else if (isSmallerOrEqual(thirdMax, array[i])) {
                updateThirdMax(array[i]);
            }

        }

        return !Objects.isNull(thirdMax) ? thirdMax : firstMax;
    }

    private void updateFirstMax(int newValue) {
        if (isEqual(firstMax, newValue)) {
            return;
        }

        if (isSmallerOrEqual(firstMax, newValue)) {
            Integer temp = firstMax;
            firstMax = newValue;
            if (!Objects.isNull(temp)) {
                updateSecondMax(temp);
            }
        }
    }


    private void updateSecondMax(int newValue) {
        if (isEqual(secondMax, newValue)) {
            return;
        }

        if (isSmallerOrEqual(secondMax, newValue)) {
            Integer temp = secondMax;
            secondMax = newValue;
            if (!Objects.isNull(temp)) {
                updateThirdMax(temp);
            }
        }
    }

    private void updateThirdMax(int newValue) {
        if (isEqual(thirdMax, newValue)) {
            return;
        }

        if (isSmallerOrEqual(thirdMax, newValue)) {
            thirdMax = newValue;
        }
    }

    private boolean isSmallerOrEqual(Integer value1, Integer value2) {
        return Objects.isNull(value1) || value1 <= value2;
    }

    private boolean isEqual(Integer value1, Integer value2) {
        return !Objects.isNull(value1) && value1.equals(value2);
    }
}


class ThirdMaximumNumberInArrayTest {
    public static void main(String[] args) {
        int[] array1 = {3, 2, 1};
        int[] array2 = {-2147483648, -2147483648, -2147483648, -2147483648, 1, 1, 1};
        int[] array3 = {2, 2, 3, 1};

        System.out.println("Array : " + Arrays.toString(array1));
        ThirdMaximumNumberInArray o1 = new ThirdMaximumNumberInArray();
        System.out.println("Third Smallest: " + o1.find(array1));

        System.out.println("Array : " + Arrays.toString(array2));
        ThirdMaximumNumberInArray o2 = new ThirdMaximumNumberInArray();
        System.out.println("Third Smallest: " + o2.find(array2));

        System.out.println("Array : " + Arrays.toString(array3));
        ThirdMaximumNumberInArray o3 = new ThirdMaximumNumberInArray();
        System.out.println("Third Smallest: " + o3.find(array3));


    }
}