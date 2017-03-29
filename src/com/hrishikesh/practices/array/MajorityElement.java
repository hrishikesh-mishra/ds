package com.hrishikesh.practices.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hrishikesh.practices.array.MajorityElement.majorityElement;

/**
 * Problem
 * Majority Element by ⌊ n/3 ⌋
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/majority-element-%e2%8c%8a-n3-%e2%8c%8b/
 */
public class MajorityElement {

    public static List<Integer> majorityElement(int[] nums) {
        Integer firstElement = null;
        int firstElementCount = 0;
        Integer secondElement = null;
        int secondElementCount = 0;

        for (int i = 0; i < nums.length; i++) {

            if (firstElementCount == 0 && (secondElement == null || secondElement != nums[i])) {
                firstElement = nums[i];
            } else if (secondElementCount == 0) {
                secondElement = nums[i];
            }

            if (firstElement == nums[i]) {
                firstElementCount++;
            } else if (secondElement == nums[i]) {
                secondElementCount++;
            } else {
                secondElementCount--;
                firstElementCount--;
            }


        }

        List<Integer> result = new ArrayList<>();
        if (firstElementCount > 0 && isOneThird(nums, firstElement)) {
            result.add(firstElement);
        }
        if (secondElementCount > 0 && isOneThird(nums, secondElement)) {
            result.add(secondElement);
        }

        return result;

    }

    private static boolean isOneThird(int[] array, int element) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (element == array[i]) {
                count++;
            }
        }

        return count > array.length / 3;
    }

}


class MajorityElementTest {
    public static void main(String[] args) {

        int[] array = {-1, 1, 1, 1, 2, 1};

        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("Majority:  " + majorityElement(array));

    }
}