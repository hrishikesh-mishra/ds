package com.hrishikesh.practices.string;

import java.util.Arrays;

import static com.hrishikesh.practices.string.SuffixArray.createSuffixArray;

/**
 * Problem:
 * Suffix Array Implementation
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/suffix-array-implementation/
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

        for (int i = 0; i < n; i++) {
            suffixRank[0][i] = s.charAt(i) - 'a';
        }

        Tuple[] tuples = new Tuple[n];
        for (int i = 0; i < n; i++) {
            tuples[i] = new Tuple();
        }

        for (int k = 1, step = 1; k < n; k *= 2, step++) {

            for (int i = 0; i < n; i++) {
                tuples[i].firstHalf = suffixRank[step - 1][i];
                tuples[i].secondHalf = i + k < n ? suffixRank[step - 1][i + k] : -1;
                tuples[i].originalIndex = i;
            }


            Arrays.sort(tuples);

            suffixRank[step][tuples[0].originalIndex] = 0;

            for (int i = 1, currRank = 0; i < n; ++i) {

                if (tuples[i - 1].firstHalf != tuples[i].firstHalf || tuples[i - 1].secondHalf != tuples[i].secondHalf) {
                    ++currRank;
                }

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

        System.out.println("Suffix Array: ");
        for (int i = 0; i < suffixArray.length; i++) {
            System.out.println(s.substring(suffixArray[i]));
        }


    }


}
