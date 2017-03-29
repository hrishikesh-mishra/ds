package com.hrishikesh.practices.string;


import java.util.HashMap;
import java.util.Map;

/**
 * Problem:
 * Minimum Window Substring In O(n)
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=wTdClP36Bx4
 * @link http://hrishikeshmishra.com/minimum-window-substring/
 */
public class MinimumWindowSubstring {

    public static String get(String mainString, String searchString) {
        Map<Character, Integer> searchCharCountMap = getCountMap(searchString);

        /** Taking two pointer **/
        int leftPointer = 0;
        int rightPointer = 0;

        /** Found character counts  **/
        int count = 0;

        /** Minimum length **/
        int minLength = mainString.length();

        /** Sub string that contains all character of search searching  **/
        String minSubString = "";

        Map<Character, Integer> foundCharsMap = new HashMap<>();

        for (; rightPointer < mainString.length(); rightPointer++) {
            char c = mainString.charAt(rightPointer);

            /** If its search string character **/
            if (searchCharCountMap.containsKey(c)) {
                Integer charCount = foundCharsMap.getOrDefault(c, 0);

                /** When character occurred first time or count of char is less than search string char count **/
                if (charCount < searchCharCountMap.get(c)) {
                    foundCharsMap.put(c, charCount + 1);
                    count++;
                }
                /** When character is already found and reached same count of search char **/
                else {
                    foundCharsMap.put(c, charCount + 1);
                }
            }

            /** When all character found **/
            if (count == searchString.length()) {

                /** Computing minimum substring length **/
                char leftChar = mainString.charAt(leftPointer);

                /**
                 * Increase left pointer till
                 * - Left char not in search map
                 * - Or, Left char count is greater than required count in search map
                 */
                while (!foundCharsMap.containsKey(leftChar) ||
                        (foundCharsMap.get(leftChar) > searchCharCountMap.get(leftChar))) {

                    /** If left char present in search string **/
                    if (foundCharsMap.containsKey(leftChar)) {
                        foundCharsMap.put(leftChar, foundCharsMap.get(leftChar) - 1);
                    }

                    /** Increment left pointer **/
                    leftPointer++;

                    /** New left character **/
                    leftChar = mainString.charAt(leftPointer);
                }

                /** Update minimum window substring **/
                if (rightPointer - leftPointer + 1 <= minLength) {
                    minLength = rightPointer - leftPointer + 1;
                    minSubString = mainString.substring(leftPointer, rightPointer + 1);
                }
            }
        }

        return minSubString;
    }

    public static Map<Character, Integer> getCountMap(String string) {
        return new HashMap<Character, Integer>() {
            {
                for (Character c : string.toCharArray()) {
                    if (containsKey(c)) {
                        put(c, get(c) + 1);
                    } else {
                        put(c, 1);
                    }
                }
            }
        };
    }
}


class MinimumWindowSubstringTest {
    public static void main(String[] args) {

        String mainString = "this is a test string";
        String searchString = "tist";
        System.out.printf("Main String : %s, Search String: %s, Minimum window substring: %s\n",
                mainString, searchString, MinimumWindowSubstring.get(mainString, searchString));

        mainString = "ADOBECODEBANC";
        searchString = "ABC";
        System.out.printf("Main String : %s, Search String: %s, Minimum window substring: %s\n",
                mainString, searchString, MinimumWindowSubstring.get(mainString, searchString));

        mainString = "A";
        searchString = "A";
        System.out.printf("Main String : %s, Search String: %s, Minimum window substring: %s\n",
                mainString, searchString, MinimumWindowSubstring.get(mainString, searchString));
    }
}
