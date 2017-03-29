package com.hrishikesh.practices.array;

import java.util.Arrays;

import static com.hrishikesh.practices.array.OneDuplicateNumber.find;

/**
 * Problem:
 * One Duplicate Number
 * An array of size n+1, such that 1 ≤ array[i] ≤ n, then find duplicate element.
 * But using O(1) extra memory and less than in O(n^2).
 *
 * @author hrishikesh.mishra
 * @link  http://hrishikeshmishra.com/one-duplicate-number/
 */
public class OneDuplicateNumber {

    public static int find(int[] array) {
        int slow = 0;
        int fast = 0;
        do {

            slow = array[slow];
            fast = array[array[fast]];
        } while (slow != fast);

        int find = 0;

        while (find != slow) {
            find = array[find];
            slow = array[slow];
        }

        return find;
    }
}

class OneDuplicateNumberTest {
    public static void main(String[] args) {
        int[] array = {1, 2, 2};
        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("Duplicate Element: " + find(array));
    }
}
