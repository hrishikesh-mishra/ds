package com.hrishikeshmishra.practices.dynamicprogramming;

import static com.hrishikeshmishra.practices.dynamicprogramming.MinimumEditDistance.findByDP;
import static com.hrishikeshmishra.practices.dynamicprogramming.MinimumEditDistance.findByRecursion;

/**
 * Problem:
 * Minimum Edit Distance
 * Given two strings s1 and s2 and ADD, DELETE, UPDATE operations that can performed on s1.
 * Find minimum number of operations required to convert s1 into s2.
 * ;
 * ;
 * Recursive Formula:
 * - If s1_char == s2_char then
 * - - return s1 -1 , s2 -1
 * - Else
 * - - return 1 + Min { (s1, s2 -1), (s1 -1, s2), (s1-1, s2-1) }
 * ;
 * ;
 * Dynamic Programming Formula:
 * - If s1_ith_char == s2_jth_char then
 * - - T[i][j] = T[i-1][j-1]
 * - Else
 * - - T[i][j] = Min { T[i][j-1], T[i-1][j], T[i-1][j-1]}
 * ;
 * ;
 * ;
 * Dynamic Programming Algorithm:
 * - Create DP table  of size (s1.len +1 ) X (s2.len +1 )
 * - Populate table for base cases i.e. when s1 or s2 is empty
 * - Iterate i from 1 to s1.len
 * - - Iterate j from 1 to s2.len
 * - - - If s1_ith_char == s2_jth_char then
 * - - - - T[i][j] = T[i-1][j-1]
 * - - - Else
 * - - - - T[i][j] = Min { T[i][j-1], T[i-1][j], T[i-1][j-1]}
 *
 * @author hrishikesh.mishra
 * @wiki https://en.wikipedia.org/wiki/Edit_distance
 * @video https://www.youtube.com/watch?v=We3YDTzNXEk
 * @link http://hrishikeshmishra.com/minimum-edit-distance/
 */
public class MinimumEditDistance {

    public static int findByRecursion(String s1, String s2) {
        return findByRecursionHelper(s1, s2, s1.length(), s2.length());
    }

    private static int findByRecursionHelper(String s1, String s2, int s1Index, int s2Index) {

        /** Base case: When one of string is empty **/
        if (s1Index == 0 || s2Index == 0) {
            return s1Index == 0 ? s2Index : s1Index;
        }

        /** Base case: when characters are equal **/
        if (s1.charAt(s1Index - 1) == s2.charAt(s2Index - 1)) {
            return findByRecursionHelper(s1, s2, s1Index - 1, s2Index - 1);
        }

        return 1 + Math.min(
                /** ADD: When one character add from s2 add to s1 **/
                findByRecursionHelper(s1, s2, s1Index, s2Index - 1),
                Math.min(
                        /** REMOVE: When one character from s1 remove  **/
                        findByRecursionHelper(s1, s2, s1Index - 1, s2Index),

                        /** UPDATE: When one character from s2 to s1 copied **/
                        findByRecursionHelper(s1, s2, s1Index - 1, s2Index - 1)
                )
        );

    }

    public static int findByDP(String s1, String s2) {
        int[][] table = new int[s1.length() + 1][s2.length() + 1];

        /** Base case: When s2 is empty **/
        for (int i = 0; i < s1.length() + 1; i++) {
            table[i][0] = i;
        }

        /** Base case: When s1 is empty **/
        for (int j = 0; j < s2.length() + 1; j++) {
            table[0][j] = j;
        }

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {

                /** When characters are equal **/
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1];
                }
                /** When characters are not equal **/
                else {
                    table[i][j] = 1 + Math.min(
                            /** ADD: When one character add from s2 add to s1 **/
                            table[i][j - 1],
                            Math.min(
                                    /** REMOVE: When one character from s1 remove  **/
                                    table[i - 1][j],
                                    /** UPDATE: When one character from s2 to s1 copied **/
                                    table[i - 1][j - 1]
                            )
                    );
                }
            }
        }

        printDPTable(table);
        return table[s1.length()][s2.length()];
    }

    private static void printDPTable(int[][] table) {
        System.out.println("\n\nDP Table: ");
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.printf("%d ", table[i][j]);
            }
            System.out.println();
        }

        System.out.println();
    }
}

class MinimumEditDistanceTest {
    public static void main(String[] args) {
        String s1 = "abde";
        String s2 = "agbde";
        System.out.println("String1 : " + s1);
        System.out.println("String2: " + s2);

        System.out.println("Min Operation: " + findByRecursion(s1, s2));
        System.out.println("Min Operation: " + findByDP(s1, s2));


    }
}