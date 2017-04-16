package com.hrishikeshmishra.practices.dynamicprogramming;

import static com.hrishikeshmishra.practices.dynamicprogramming.LongestPalindromicSubsequence.solveByDP;
import static com.hrishikeshmishra.practices.dynamicprogramming.LongestPalindromicSubsequence.solveByRecursive;

/**
 * Problem:
 * Longest Palindromic Subsequence
 * Given a string, find a longest palin­dromic sub­se­quence in it.
 * ;
 * ;
 * Recursive Formula:
 * - If ith and jth characters are same then,
 * - - 2 + recursive call for (i +1) and (j -1)
 * - Else
 * -  Max of {(recursive call of i, j-1 ) and (recursive call i+1, j)}
 * ;
 * ;
 * DP Formula:
 * - If ith and jth characters are same then
 * - - T[i][j] = 2 + T[i+1][j-1]
 * - Else
 * - - T[i][j] = Max {T[i+1][j],  T[i][j-1]}
 * ;
 * Solution:
 * - Here we start work from sub string of length 1, 2, 3, ..... string.length.
 * - For string length 1, Longest Palindromic Subsequence is 1.
 * - Suppose for sub-string BABCB
 * - - Here first and last characters are equal
 * - - So, LPS = 2 + LPS("ABC")
 * - But for sub-string BABCA
 * - - Here first and last characters are not equal,
 * - - So, LPS = Max {LPS("BABC"), LPS("ABCA") },
 * - - i.e. for we will try to find LPS of length 4, if first and last character of string of length is not equal.
 * ;
 * ;
 * Dynamic Programming Algorithm:
 * - Create dp table of (str.length) X (str.length)
 * - Update table for substring length 1
 * - Iterate substring length 2 to str.length
 * - - Iterate all subStrings of length i
 * - - - If first and last character of substring is equal then
 * - - - - Set table[subStringStartIndex][subStringEndIndex] = 2 + table[subStringStartIndex + 1][subStringEndIndex -1]
 * - - - Else
 * - - -  - Set table[subStringStartIndex][subStringEndIndex] = Max (
 * table[subStringStartIndex + 1][subStringEndIndex ], table[subStringStartIndex ][subStringEndIndex -1 ])
 * - Return table[0][str.length - 1]
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=_nCsPn7_OgI
 * @link http://hrishikeshmishra.com/longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence {


    public static int solveByRecursive(String str) {
        return solveRecursiveHelper(str, 0, str.length() - 1);
    }

    private static int solveRecursiveHelper(String str, int startIndex, int lastIndex) {

        /** Base case: when indices are out of range **/
        if (startIndex > lastIndex) {
            return 0;
        }

        /** Base case: When only one character **/
        if (startIndex == lastIndex) {
            return 1;
        }

        /** When first and last characters are same **/
        if (str.charAt(startIndex) == str.charAt(lastIndex)) {
            return 2 + solveRecursiveHelper(str, startIndex + 1, lastIndex - 1);
        }

        /** If first and last characters are not same **/
        return Math.max(
                solveRecursiveHelper(str, startIndex + 1, lastIndex),
                solveRecursiveHelper(str, startIndex, lastIndex - 1)
        );
    }

    public static int solveByDP(String str) {
        int[][] table = new int[str.length()][str.length()];

        /** Base case : of length = 1   **/
        for (int i = 0; i < str.length(); i++) {
            table[i][i] = 1;
        }

        /** For length greater than or equal to 2 **/
        for (int subStringLength = 2; subStringLength <= str.length(); subStringLength++) {

            /** Check all substring of length subStringLength **/
            for (int startIndexOfSubString = 0; startIndexOfSubString <= str.length() - subStringLength;
                 startIndexOfSubString++) {

                int lastIndexOfSubString = startIndexOfSubString + subStringLength - 1;

                /** If first and last characters of substring are equal **/
                if (str.charAt(startIndexOfSubString) == str.charAt(lastIndexOfSubString)) {

                    if (subStringLength == 2) {
                        table[startIndexOfSubString][lastIndexOfSubString] = 2;
                    } else {
                        table[startIndexOfSubString][lastIndexOfSubString] = 2 +
                                table[startIndexOfSubString + 1][lastIndexOfSubString - 1];
                    }

                } else {
                    /** If first and last characters of substring are not equal **/
                    table[startIndexOfSubString][lastIndexOfSubString] = Math.max(
                            table[startIndexOfSubString + 1][lastIndexOfSubString],
                            table[startIndexOfSubString][lastIndexOfSubString - 1]
                    );
                }
            }
        }

        printMatrix(str, table);

        return table[0][str.length() - 1];
    }

    private static void printMatrix(String str, int[][] table) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }


}

class LongestPalindromicSubsequenceTest {
    public static void main(String[] args) {
        String str1 = "BBABCBCAB";
        System.out.println("String: " + str1);
        System.out.println("Longest Palindromic Subsequence Length: (Recursive) " + solveByRecursive(str1));
        System.out.println("Longest Palindromic Subsequence Length: (By DP) " + solveByDP(str1));
    }
}
