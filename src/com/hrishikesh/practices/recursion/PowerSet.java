package com.hrishikesh.practices.recursion;

import java.util.ArrayList;
import java.util.List;

import static com.hrishikesh.practices.recursion.PowerSet.getPowerSetByRecursion;
import static com.hrishikesh.practices.recursion.PowerSet.getPowerSetIterative;

/**
 * Problem:
 * Power Set
 * The power set (or powerset) of any set S is the set of all subsets of S, including the empty set and S itself.
 * ;
 * ;
 * Solution:
 * - Recursive Algorithm: A Power set of P(n) can be  computer from P(n-1) by added nth element in each set of P(n-1)
 * - Iterative Algorithm: Based on binary representation
 * ;
 * Recursive Algorithm:
 * - If index == set.size then
 * - - Create setsOfSet
 * - - Add empty set to setsOfSet1
 * - Else
 * - - Recursively call with index + 1
 * - - get ithElement from Set
 * - - Create empty setsOfSet2
 * - - Iterate all sets S for setsOfSet1
 * - - - Create newSet
 * - - - Add elements of set S to newSet
 * - - - Add ithElement to  newSet
 * - - - Add newSet to setsOfSet2
 * - - Add all sets setsOfSet2 to setsOfSet1
 * - Return setsOfSet1
 * ;
 * ;
 * Iterative Algorithm:
 * - Compute totalNumberSubset i.e 2^set.size
 * - Iterate 0 to totalNumberSubset
 * - - Covert each number to binary representation
 * - - Create subset based on binary representation
 *
 * @author hrishikesh.mishra
 * @wiki https://en.wikipedia.org/wiki/Power_set
 * @link http://hrishikeshmishra.com/power-set-power-set-powerset-set-s-set-subsets-s-including-empty-set-s/
 */
public class PowerSet {

    public static List<List<Integer>> getPowerSetByRecursion(List<Integer> set, int index) {
        List<List<Integer>> allSubsets;

        if (index == set.size()) {
            allSubsets = new ArrayList<>();

            /** Adding empty set **/
            allSubsets.add(new ArrayList<>());
        } else {
            allSubsets = getPowerSetByRecursion(set, index + 1);
            int ithElement = set.get(index);

            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> subSet : allSubsets) {
                List<Integer> newSubset = new ArrayList<>();
                newSubset.addAll(subSet);
                newSubset.add(ithElement);
                newSubsets.add(newSubset);
            }

            allSubsets.addAll(newSubsets);
        }

        return allSubsets;
    }


    public static List<List<Integer>> getPowerSetIterative(List<Integer> set) {
        List<List<Integer>> powerSet = new ArrayList<>();
        int totalNumberOfSubSets = (int) Math.pow(2, set.size());

        for (int i = 0; i < totalNumberOfSubSets; i++) {
            powerSet.add(getSubset(set, i));
        }

        return powerSet;
    }

    private static List<Integer> getSubset(List<Integer> set, int i) {
        List<Integer> subset = new ArrayList<>();

        for (int k = i, index = 0; k > 0; k = k >> 1, index++) {
            if ((k & 1) == 1) {
                subset.add(set.get(index));
            }
        }

        return subset;
    }

}

class PowerSetTest {
    public static void main(String[] args) {
        List<Integer> set1 = new ArrayList<Integer>() {
            {
                add(1);
                add(3);
                add(2);
            }
        };

        List<List<Integer>> powerSetByRecursion = getPowerSetByRecursion(set1, 0);
        System.out.println("Power of " + set1 + " (Recursive): " + powerSetByRecursion);
        System.out.println("Power of " + set1 + " (Iterative): " + getPowerSetIterative(set1));


    }


}

