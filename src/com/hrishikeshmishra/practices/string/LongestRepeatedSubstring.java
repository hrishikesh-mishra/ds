package com.hrishikeshmishra.practices.string;

import java.util.Arrays;

/**
 * Problem:
 * Longest Repeated Substring (using Suffix Array)
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/longest-repeated-substring-using-suffix-array/
 */
public class LongestRepeatedSubstring {

    public static String get(String s) {
        int n = s.length();

        /** Creating suffix array **/
        String[] suffies = new String[n];
        for (int i = 0; i < n; i++) {
            suffies[i] = s.substring(i);
        }

        /** Sorting suffix array **/
        Arrays.sort(suffies);

        for (int i =0 ; i < suffies.length; i++){
            System.out.println(i + " :: " + suffies[i] );
        }
        String result = "";

        /** Finding longest common prefix **/
        for (int i = 0; i < n - 1; i++) {
            int len = getLongestCommonPrefix(suffies[i], suffies[i + 1]);

            if (len > result.length()) {
                result = suffies[i].substring(0, len);
            }
        }

        return result;
    }

    private static int getLongestCommonPrefix(String s1, String s2) {
        int len = 0;
        for (int i = 0, j = 0; i < s1.length() && j < s2.length(); i++, j++) {
            if (s1.charAt(i) != s2.charAt(j)){
                break;
            }
            len++;
        }

        return len;
    }
}


class LongestRepeatedSubstringTest {
    public static void main(String[] args) {
        String s = "Such a funny, sporty, gamy, jesty, joky, hoky-poky lad, is the Ocean, oh!";
        String longestSuffix = LongestRepeatedSubstring.get(s);

        System.out.println("String :" + s);
        System.out.println("Longest Suffix String: " + longestSuffix);
    }
}