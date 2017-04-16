package com.hrishikeshmishra.practices.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * Problem:
 * Subset
 * Given a set of distinct integers, nums, return all possible subsets
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/subsets/
 */
public class Subsets {

    public static List<List<Integer>> getNonRecursive(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return new ArrayList<>();
        }


        List<List<Integer>> subsets = new ArrayList<>();

        for (int i = 0; i < (1 << array.length); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < array.length; j++) {

                //@// TODO:
                if ((i & (1 << j)) != 0) {
                    subset.add(array[j]);
                }
            }

            subsets.add(subset);
        }

        return subsets;
    }

    public static List<List<Integer>> get2(int[] array) {
        List<List<Integer>> result = new ArrayList<>();

        if (Objects.isNull(array) || array.length == 0) {
            return result;
        }


        subsetHelper2(array, 0, result, new ArrayList<>());
        return result;

    }

    private static void subsetHelper2(int[] array, int index, List<List<Integer>> result, List<Integer> list) {

        result.add(new ArrayList<>(list));

        for (int i = index; i < array.length; i++) {
            list.add(array[i]);
            subsetHelper2(array, i + 1, result, list);
            list.remove(list.size() - 1);
        }
    }

    public static List<List<Integer>> get(int[] array) {

        if (Objects.isNull(array) || array.length == 0) {
            return new ArrayList<>();
        }

        return subsetHelper(array, 0);
    }

    private static List<List<Integer>> subsetHelper(int[] array, int index) {
        List<List<Integer>> subsets = new ArrayList<>();

        if (index >= array.length) {
            subsets.add(new ArrayList<>());
            return subsets;
        }

        int firstElement = array[index];

        for (List<Integer> subset : subsetHelper(array, index + 1)) {
            List<Integer> newSet = new ArrayList<>();
            newSet.addAll(subset);
            newSet.add(firstElement);

            subsets.add(newSet);
            subsets.add(subset);
        }

        return subsets;
    }
}

class SubsetsTest {
    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Subsets: " + Subsets.get(array));
        System.out.println("Subsets2: " + Subsets.get2(array));
        System.out.println("Subsets3: " + Subsets.getNonRecursive(array));
    }
}