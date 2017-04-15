package com.hrishikesh.practices.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.hrishikesh.practices.string.LongestNonRepeatingSubString.lengthOfLongestSubstring;
import static com.hrishikesh.practices.string.LongestNonRepeatingSubString.lengthOfLongestSubstring2;
import static com.hrishikesh.practices.string.LongestNonRepeatingSubString.lengthOfLongestSubstring3;

/**
 * Problem:
 * Longest Non Repeating SubString
 * Given a string, find the length of the longest substring without repeating characters.
 * ;
 * For example:
 * For "abcadd" is 4 i.e "abcd"
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/longest-non-repeating-substring/
 */
public class LongestNonRepeatingSubString {

    public static int lengthOfLongestSubstring(String s) {
        Map<String, Integer> cache = new HashMap<>();
        return lengthOfLongestSubstring(s, cache);
    }


    public static int lengthOfLongestSubstring(String s, Map<String, Integer> cache) {

        /** Base case : when string is empty of length is one **/
        if (s.length() <= 1) {
            return s.length();
        }

        /** Checking in cache **/
        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        /** Base case: when all characters of string is unique **/
        if (isAllUniqueCharacters(s)) {
            return s.length();
        }


        /**
         * Max between two substring
         * First substring : 1st character removed from string s
         * Second substring: last character removed from string s
         */
        int result = Math.max(
                lengthOfLongestSubstring(s.substring(1)),
                lengthOfLongestSubstring(s.substring(0, s.length() - 1))
        );

        cache.put(s, result);
        return result;

    }

    private static boolean isAllUniqueCharacters(String s) {
        Set<Character> set = new HashSet<>();
        int unqiueChar = 0;
        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);
            if (!set.contains(c)) {
                unqiueChar++;
                set.add(c);
            }
        }

        return s.length() == unqiueChar;
    }


    public static int lengthOfLongestSubstring2(String s) {
        if (Objects.isNull(s)) {
            return 0;
        }

        int previousLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (map.containsKey(c)) {
                previousLength = Math.max(previousLength, map.size());
                i = map.get(c);
                map.clear();
            } else {
                map.put(c, i);
            }
        }

        return Math.max(previousLength, map.size());
    }


    public static int lengthOfLongestSubstring3(String s) {
        if (Objects.isNull(s)) {
            return 0;
        }

        int maxLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0, j = 0; j < s.length(); j++) {

            char c = s.charAt(j);

            if (map.containsKey(c)) {

                maxLength = Math.max(maxLength, j - i);
                int duplicateIndex = map.get(c);

                /** Removing all element till duplicate from hash **/
                for (int index = i; index <= duplicateIndex; index++) {
                    map.remove(s.charAt(index));
                }

                i = duplicateIndex + 1;

                map.put(c, j);

            } else {
                map.put(c, j);
            }
        }

        return Math.max(maxLength, map.size());
    }
}

class LongestNonRepeatingSubStringTest {
    public static void main(String[] args) {
        String s1 = "hdgikkinyyzxycsekxoev";

        System.out.println("String : " + s1 + ", Longest Non Repeating SubString :" + lengthOfLongestSubstring2(s1));
        System.out.println("String : " + s1 + ", Longest Non Repeating SubString :" + lengthOfLongestSubstring(s1));
        System.out.println("String : " + s1 + ", Longest Non Repeating SubString :" + lengthOfLongestSubstring3(s1));
    }
}
