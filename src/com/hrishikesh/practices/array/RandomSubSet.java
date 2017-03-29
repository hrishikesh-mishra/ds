package com.hrishikesh.practices.array;

import java.util.Arrays;

import static com.hrishikesh.practices.array.RandomSubSet.getRandomSubset;

/**
 * Problem:
 * Generate Random Subset from Set
 * From given set of size n generate random subset of size m, where each element must have equal
 * probability of being chosen.
 * ;
 * ;
 * Algorithm:
 * - Create an array of size m
 * - Copy m elements from original to random_subset_array
 * - Iterate original array from m+1 to n
 * - - Generate random number between 0 to current_index
 * - - If random < m then
 * - - - swap current_index element with random index in random_subset_array
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/generate-random-subset-set/
 */
public class RandomSubSet {

    public static int[] getRandomSubset(int[] array, int m) {
        if (m > array.length || m <= 0) {
            return null;
        }

        int[] randomSubset = new int[m];
        for (int i = 0; i < m; i++) {
            randomSubset[i] = array[i];
        }

        for (int i = m; i < array.length; i++) {
            int random = rand(0, i);
            if (random < m) {
                randomSubset[random] = array[i];
            }
        }

        return randomSubset;
    }

    private static int rand(int minimum, int maximum) {
        return minimum + Double.valueOf(Math.random() * (maximum - minimum)).intValue();
    }
}


class RandomSubSetTest {
    public static void main(String[] args) {
        int[] set = creatSet(10);
        System.out.println("Set: " + Arrays.toString(set));
        System.out.println("SubSet(3): " + Arrays.toString(getRandomSubset(set, 3)));
        System.out.println("SubSet(5): " + Arrays.toString(getRandomSubset(set, 5)));
        System.out.println("SubSet(4): " + Arrays.toString(getRandomSubset(set, 4)));
    }

    public static int[] creatSet(int size) {
        int[] card = new int[size];
        for (int i = 0; i < size; i++) {
            card[i] = i + 1;
        }
        return card;
    }
}