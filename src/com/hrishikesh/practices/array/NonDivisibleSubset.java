package com.hrishikesh.practices.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem:
 * Given a set, S , of  n distinct integers, print the size of a maximal subset, S1 , of  S where the sum of any 2
 * numbers in S1 is not evenly divisible by k.
 *
 * @author hrishikesh.mishra
 */
public class NonDivisibleSubset {

    public static int getCount(int[] array, int k) {

        /** Map to hold remainder after dividing by k, i.e. 0, 1, 2, ...... k **/
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int remainder = array[i] % k;

            int count = map.getOrDefault(remainder, 0) + 1;

            /** Hack for remainder zero **/
            if (remainder == 0) {

                /** We can maximum take only one **/
                count = 1;
            }
            map.put(remainder, count);
        }

        /** For remainder zero **/
        int setCount = map.getOrDefault(0, 0);
        int i = 1;

        for (; i * 2 < k; i++) {
            setCount = setCount + Math.max(map.getOrDefault(i, 0), map.getOrDefault(k - i, 0));
        }

        /** Half remainder, here also we can maximum take only one **/
        if (2 * i == k) {
            setCount++;
        }

        return setCount;
    }
}

class NonDivisibleSubsetTest {

    public static void main(String[] args) {
        int array[] = {1, 7, 2, 4};
        int k = 3;

        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("K: " + k);
        System.out.println("Subset size : " + NonDivisibleSubset.getCount(array, k));
    }
}
