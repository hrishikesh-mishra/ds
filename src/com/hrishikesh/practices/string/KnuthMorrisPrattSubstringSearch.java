package com.hrishikesh.practices.string;

import static com.hrishikesh.practices.string.KnuthMorrisPrattSubstringSearch.isSubStringFound;

/**
 * Problem:
 * Knuth–Morris–Pratt(KMP) Substring search
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/knuth-morris-prattkmp-substring-search/
 */
public class KnuthMorrisPrattSubstringSearch {

    public static boolean isSubStringFound(String string, String pattern) {
        int[] prefixSuffixArray = buildPrefixSufixArray(pattern);
        int i = 0, j = 0;

        while (i < string.length() && j < pattern.length()) {

            if (string.charAt(i) == pattern.charAt(j)) {
                j++;
            } else {
                while (j > 0 && pattern.charAt(j) != string.charAt(i)) {
                    j = prefixSuffixArray[j - 1];
                }

                if (pattern.charAt(j) == string.charAt(i)) {
                    j++;
                }
            }

            i++;
        }

        /** Checking have we consumed all pattern characters or not. **/
        return (j == pattern.length());
    }

    private static int[] buildPrefixSufixArray(String pattern) {
        int[] array = new int[pattern.length()];

        array[0] = 0;

        for (int j = 0, i = 1; i < pattern.length(); i++) {

            /** When jth and ith characters are same **/
            if (pattern.charAt(j) == pattern.charAt(i)) {
                array[i] = j + 1;
                j++;
            }
            /** When jth and ith characters are not equal **/
            else {

                while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                    j = array[j - 1];
                }

                if (pattern.charAt(j) == pattern.charAt(i)) {
                    array[i] = j + 1;
                    j++;
                } else {
                    array[i] = j;
                }
            }
        }

        return array;

    }

}


class KnuthMorrisPrattSubstringSearchTest {
    public static void main(String[] args) {
        String string = "abcxabcdabcdabcy";
        String pattern = "abcdabcy";

        System.out.println("String : " + string);
        System.out.println("Pattern: " + pattern);
        System.out.println("Found? " + isSubStringFound(string, pattern));
        ;
    }
}