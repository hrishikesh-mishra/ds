package com.hrishikesh.practices.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.hrishikesh.practices.array.SumSwap.find;

/**
 * Problem:
 * Sum Swap
 * ;
 * Given tow arrays of integers, find a pair of values (one value from each array) that you can swap to give
 * the two arrays the same sum.
 * ;
 * Fact :
 * - Suppose sum of  array 1 is Sum1 and sum of array 2 is Sum2
 * - then for a pair of element from each array x and y
 * - Sum1 - x + y = Sum2 + x - y
 * - i.e. Sum1 - Sum2 = 2x - 2y = 2(x-y)
 * - i.e. x - y = (Sum1 - Sum2) / 2
 * - So, the diff between both element is (Sum1 - Sum2) / 2
 * ;
 * ;
 * Algorithm:
 * - Find sum (sum1) of array1
 * - Find sum (sum2) of array2
 * - Calculate target = (sum1 - sum2) / 2
 * - Convert array2 to set2
 * - Iterate array1 from start to end
 * - - Compute complement = array1[i] - target
 * - - Look up complement element in set (i.e array2)
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/equal-sum-of-two-arrays/
 */
public class SumSwap {

    public static class Result {
        private int element1;
        private int element2;

        public Result(int element1, int element2) {
            this.element1 = element1;
            this.element2 = element2;
        }

        public int getElement1() {
            return element1;
        }

        public int getElement2() {
            return element2;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "element1=" + element1 +
                    ", element2=" + element2 +
                    '}';
        }
    }

    public static Result find(int[] array1, int[] array2) {
        int sum1 = sum(array1);
        int sum2 = sum(array2);
        int target = (sum1 - sum2) / 2;

        /** Convert array2 to set for fast lookup  **/
        Set<Integer> array2Set = arrayToSet(array2);
        for (int i = 0; i < array1.length; i++) {
            int complement = array1[i] - target;

            if (array2Set.contains(complement)) {
                return new Result(array1[i], complement);
            }
        }

        return null;
    }


    private static int sum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        return sum;
    }

    private static Set<Integer> arrayToSet(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        return set;
    }
}


class SumSwapTest {
    public static void main(String[] args) {
        int array1[] = {4, 1, 2, 1, 1, 2};
        int array2[] = {3, 6, 3, 3};

        System.out.println("Array1: " + Arrays.toString(array1));
        System.out.println("Array2: " + Arrays.toString(array2));
        System.out.println("Sum Swap : " + find(array1, array2));
    }
}