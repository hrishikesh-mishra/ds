package com.hrishikeshmishra.ns.dynamicprogramming;

/**
 * Problem:
 * Longest Common Subsequence
 * ;
 * The longest common subsequence (LCS) problem is the problem of finding the longest subsequence common
 * to all sequences in a set of sequences.
 * ;
 * Recursive Formula:
 * - If (str1.charAt(i) == str2.charAt(j) then
 * - - return 1 + findLCS(str1, str2, i - 1, j - 1)
 * - Else
 * - - return Max(findLCS(str1, str2, i - 1, j), findLCS(str1, str2, i, j - 1))
 * ;
 * ;
 * Dynamic Programming Formula:
 * - If  (str1.charAt(i) == str2.charAt(j) then
 * - - Table[i][j] = 1 + Table[i-1][j-1]
 * - Else
 * - - Table[i][j] = Max (Table[i-1][j], Table[i][j-1])
 * ;
 * ;
 * ;
 * Recursive Algorithm:
 * - Base case:  if str1_index < 0 or str2_index < 0 then return 0
 * - If str1_current_index_char  ==  str2_current_index_char then
 * - - <code>return 1 + recursively call with str1_index - 1, str2_index - 1</code>
 * - Else
 * - - <code>Max (recursively call with str1_index - 1, str2_index,
 * recursively call with str1_index , str2_index - 1)</code>
 * ;
 * ;
 * Dynamic Programming Algorithm:
 * - Create DP Table of (str1.length + 1) X (str2.length + 1)
 * - Populate first column and first row of this table with 0
 * - Iterate from 1 to str1.length + 1
 * - - Iterate from 1 to str2.length + 1
 * - - - If str1_current_index_char  ==  str2_current_index_char then
 * - - - - <code>Table[str1_index][str2_index] = 1 + Table[str1_index-1][str1_index-1]</code>
 * - - - Else
 * - - - - <code>Table[str1_index][str2_index] = Max(Table[str1_index-1][str1_index], Table[str1_index][str1_index-1] ) </code>
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/longest-common-subsequence/
 * @wiki https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
 */
public class LongestCommonSubSequenceFinder {

    /**
     * Recursive
     *
     * @param str1
     * @param str2
     * @param n
     * @param m
     * @return
     */
    public int findLCS(String str1, String str2, int n, int m) {
        if (n < 0 || m < 0) return 0;
        if (str1.charAt(n) == str2.charAt(m))
            return (1 + findLCS(str1, str2, n - 1, m - 1));
        else
            return Math.max(findLCS(str1, str2, n - 1, m), findLCS(str1, str2, n, m - 1));
    }

    /**
     * LCS by using dynamic programming
     *
     * @param str1
     * @param str2
     * @return
     */
    public String findLCSByDP(String str1, String str2) {
        int n = str1.length(),
                m = str2.length();
        int[][] table = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    table[i + 1][j + 1] = table[i][j] + 1;
                } else {
                    table[i + 1][j + 1] = Math.max(table[i + 1][j], table[i][j + 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder(Math.max(n, m));
        for (int x = str1.length(), y = str2.length(); x != 0 && y != 0; ) {
            if (table[x][y] == table[x - 1][y])
                x--;
            else if (table[x][y] == table[x][y - 1])
                y--;
            else {
                sb.append(str1.charAt(x - 1));
                x--;
                y--;
            }
        }

        return sb.reverse().toString();
    }
}


class LongestCommonSubSequenceFinderTest {
    public static void main(String[] args) {
        String str1 = "ABCBDAD", str2 = "BDCABA";

        LongestCommonSubSequenceFinder lacFinder = new LongestCommonSubSequenceFinder();
        System.out.println("String 1 : " + str1);
        System.out.println("String 2 : " + str2);
        System.out.println("LCA : " + lacFinder.findLCS(str1, str2, str1.length() - 1, str2.length() - 1));
        System.out.println("LCA (using dynamic programming ): " + lacFinder.findLCSByDP(str1, str2));
    }
}