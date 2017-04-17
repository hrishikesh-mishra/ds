package com.hrishikeshmishra.practices.string;

import java.util.Arrays;

import static com.hrishikeshmishra.practices.string.SuffixArray.createSuffixArray;

/**
 * Problem:
 * Suffix Array Implementation (Manber & Myers)
 * Application:
 * - Given a string of N characters, find the longest repeated substring.
 * - Visualize repetition - bewitched.com
 *
 * Manber & Myers Algorithm:
 * - Phase 0: sort on first character using key-indexed counting sort.
 * - Phase i: given array of suffixes sorted of first 2^(i-1) characters,
 *  create array of suffixes sorted on first 2^i characters.
 *
 * Worst-Case running time: N logN
 *  - Finishes after logN phases
 *  - Can perform a phase in linear time
 *
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/suffix-array-implementation/
 * @link https://en.wikipedia.org/wiki/Suffix_array
 * @video https://www.youtube.com/watch?v=HKPrVm5FWvg
 *
 */
public class SuffixArray {

    private static class Tuple implements Comparable<Tuple> {
        int originalIndex;
        int firstHalf;
        int secondHalf;

        @Override
        public int compareTo(Tuple o) {
            if (this.firstHalf == o.firstHalf) {
                return Integer.compare(this.secondHalf, o.secondHalf);
            } else {
                return Integer.compare(this.firstHalf, o.firstHalf);
            }
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "originalIndex=" + originalIndex +
                    ", firstHalf=" + firstHalf +
                    ", secondHalf=" + secondHalf +
                    '}';
        }
    }

    public static int[] createSuffixArray(String s) {
        int n = s.length();

        int[][] suffixRank = new int[20][n];

        /** Phase 0: sort on first character using key-indexed counting sort. **/
        for (int i = 0; i < n; i++) {
            suffixRank[0][i] = s.charAt(i) - 'a';
        }

        Tuple[] tuples = new Tuple[n];
        for (int i = 0; i < n; i++) {
            tuples[i] = new Tuple();
        }

        /** Phase 1 to N. **/
        for (int k = 1, step = 1; k < n; k *= 2, step++) {

            for (int i = 0; i < n; i++) {
                tuples[i].firstHalf = suffixRank[step - 1][i];
                tuples[i].secondHalf = i + k < n ? suffixRank[step - 1][i + k] : -1;
                tuples[i].originalIndex = i;
            }

            //System.out.println(Arrays.toString(tuples));
            Arrays.sort(tuples);


            /** Rank for first element **/
            suffixRank[step][tuples[0].originalIndex] = 0;

            /** Updating rank of all other elements **/
            for (int i = 1, currRank = 0; i < n; ++i) {

                /** If first or second half not equal then increase rank **/
                if (tuples[i - 1].firstHalf != tuples[i].firstHalf || tuples[i - 1].secondHalf != tuples[i].secondHalf) {
                    ++currRank;
                }

                /** Update rank **/
                suffixRank[step][tuples[i].originalIndex] = currRank;
            }
        }

        int[] suffixArray = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArray[i] = tuples[i].originalIndex;
        }

        return suffixArray;
    }

}

class SuffixArrayTest {

    public static void main(String[] args) {
        String s = "banana";
        int[] suffixArray = createSuffixArray(s);

        System.out.println("Suffix Array: " + Arrays.toString(suffixArray));
        for (int i = 0; i < suffixArray.length; i++) {
            System.out.println(s.substring(suffixArray[i]));
        }

    }
}
