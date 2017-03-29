package com.hrishikesh.practices.string;

import static com.hrishikesh.practices.string.ShortestPalindrome.findByKMP;

/**
 * Problem
 * Shortest Palindrome
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/shortest-palindrome/
 */
public class ShortestPalindrome {

    public static String findByKMP(String str) {

        /** Base case: when string is empty or size one **/
        if (str == "" || str.length() == 1) {
            return str;
        }

        String stringReverse = new StringBuffer(str).reverse().toString();


        /**
         * Creating str + "#" + str_reverse
         */
        String strAndReverseStr = new StringBuffer(str).append("#").append(stringReverse).toString();

        /** Table to hold kmp result **/
        int[] table = new int[strAndReverseStr.length()];

        int j = 0;
        for (int i = 1; i < strAndReverseStr.length(); i++) {

            if (strAndReverseStr.charAt(i) == strAndReverseStr.charAt(j)) {
                table[i] = j + 1;
                j++;
            } else {
                while (j > 0 && strAndReverseStr.charAt(i) != strAndReverseStr.charAt(j)) {
                    j = table[j - 1];
                }

                if (strAndReverseStr.charAt(i) == strAndReverseStr.charAt(j)) {
                    table[i] = j + 1;
                    j++;
                } else {
                    table[i] = j;
                }
            }
        }

        return stringReverse.substring(0, stringReverse.length() - table[table.length - 1]) + str;
    }


    public static String findByBruteForce(String str) {
        if (str == "" || str.length() == 1) {
            return str;
        }

        for (int right = str.length(); right >= 0; right--) {
            if (isPalindrome(str.substring(0, right))) {
                return new StringBuffer(str.substring(right)).reverse().append(str).toString();
            }
        }

        return null;
    }

    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

class ShortestPalindromeTest {

    public static void main(String[] args) {

        String str1 = "aacecaaa";
        String str2 = "abb";

        //System.out.printf("String : %s, Palindrome: %s\n",  str1, findByKMP(str1));
        System.out.printf("String : %s, Palindrome: %s\n", str2, findByKMP(str2));


    }
}
