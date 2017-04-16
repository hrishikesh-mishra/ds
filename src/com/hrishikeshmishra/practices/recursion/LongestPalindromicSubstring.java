package com.hrishikeshmishra.practices.recursion;

import java.util.Objects;

/**
 * Problem:
 * Longest Palindromic Substring
 * Given a string, find the longest substring which is palindrome.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

    public String getByDP(String string) {

        /** Base case: When string is null or empty **/
        if (Objects.isNull(string) || string.length() <= 1) {
            return string;
        }

        /** Length of given string **/
        int size = string.length();

        /** Maximum length of palindrom substring, by default it would be 1 **/
        int maxPalindromLength = 1;
        String palindromSubString = string.substring(0,1);

        /** DP table to hold result **/
        boolean[][] table = new boolean[size][size];

        /** For substring of length 1, that always be palindrom **/
        for (int i = 0; i < size; i++) {
            table[i][i] = true;
        }

        /** For substring length 2, true iff S[i] == S[i+1] **/
        for (int i = 0; i + 1 < size; i++) {
            if (string.charAt(i) == string.charAt(i + 1)){
                table[i][i + 1] = true;
                maxPalindromLength = 2;
                palindromSubString = string.substring(i, i+2);
            }else {
                table[i][i + 1] = false;
            }

        }

        /************************************************************************************************

         For substring length > 3 , would be palindrom only iff,
         -> If first and last character are are
         -> And substring without these two character is also palindrom

         Suppose for substring: cabbac
         here, first character is: c
         last character is: c
         and substring without first and last character: abba
         which also palindrom

         i.e.
         table[i][j] = { (table[i+1][j-1] == ture) AND S[i] == S[j] }
         Here,
         i and j => some indices of given string where i < j
         table[i+1][j-1] => substring without first and last character

         ************************************************************************************************/
        for (int len = 3; len <= size; len++) {
            for (int i = 0; i <= size - len; i++) {
                int j = i + len - 1;

                if (table[i + 1][j - 1] && string.charAt(i) == string.charAt(j)) {
                    table[i][j] = true;

                    if (maxPalindromLength < len) {
                        maxPalindromLength = len;
                        palindromSubString = string.substring(i, j + 1);
                    }

                } else {
                    table[i][j] = false;
                }
            }
        }
        return palindromSubString;
    }

    public String getRecursive(String string) {

        /** Base case: When string is empty **/
        if (string.length() == 0) {
            return "";
        }

        /** Base case: When string is already Palindrom **/
        if (isPalindromStr(string)) {
            return string;
        }

        /** Otherwise, generating all permutation of substring of length n-1 **/
        String subString1 = getRecursive(string.substring(1));
        String subString2 = getRecursive(string.substring(0, string.length() - 1));

        /** Returning max size substring  **/
        return getLongestString(subString1, subString2);

    }


    /**
     * Utility method to check string is palindrom or not.
     *
     * @param str
     * @return
     */
    private boolean isPalindromStr(String str) {
        if (str.length() <= 1) {
            return true;
        }
        for (int index = 0, endIndex = str.length() - 1;
             index < endIndex; index++, endIndex--) {
            if (str.charAt(index) != str.charAt(endIndex)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return max size string between two strings
     *
     * @param str1
     * @param str2
     * @return
     */
    private String getLongestString(String str1, String str2) {
        return str1.length() > str2.length() ? str1 : str2;
    }
}


class LongestPalindromicSubstringTest {
    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String string = "babad";
        String string2 = "cbbd";
        System.out.println("String : " + string);
        System.out.println("Longest Palindrom SubString: " + longestPalindromicSubstring.getRecursive(string));
        System.out.println("Longest Palindrom SubString: " + longestPalindromicSubstring.getByDP(string));

        System.out.println("String: " + string2);
        System.out.println("Longest Palindrom SubString: " + longestPalindromicSubstring.getByDP(string2));
    }
}