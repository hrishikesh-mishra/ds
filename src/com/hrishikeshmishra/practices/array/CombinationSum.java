package com.hrishikeshmishra.practices.array;

import java.util.ArrayList;
import java.util.List;

import static com.hrishikeshmishra.practices.array.CombinationSum.findSumCombination;

/**
 * Problem:
 * Combination Sum
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/combination-sum/
 */
public class CombinationSum {

    public static List<List<Integer>> findSumCombination(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        findSumCombination(k, n, 1, combinations, new ArrayList<>());
        return combinations;
    }

    private static void findSumCombination(int k, int n, int start, List<List<Integer>> combinations, List<Integer> combination) {

        /** Base case: when size greater than required **/
        if (k < combination.size()) {
            return;
        }

        if (k == combination.size() && sum(combination) == n) {
            /** Clone of found combination **/
            combinations.add(new ArrayList<>(combination));
        }


        for (int i = start; i <= 9; i++) {

            if (sum(combination) + i > n) {
                break;
            }

            combination.add(i);
            findSumCombination(k, n, i + 1, combinations, combination);

            /** Removing last one **/
            combination.remove(combination.size() - 1);

        }
    }

    private static int sum(List<Integer> combination) {
        int sum = 0;
        for (int element : combination) {
            sum += element;
        }
        return sum;
    }
}


class CombinationSumTest {
    public static void main(String[] args) {
        int k = 3, n = 7;
        System.out.println("When k=" + k + ", n=" + n);
        System.out.println("Combination: " + findSumCombination(k, n));

        k = 3;
        n = 9;
        System.out.println("\nWhen k=" + k + ", n=" + n);
        System.out.println("Combination: " + findSumCombination(k, n));
    }
}