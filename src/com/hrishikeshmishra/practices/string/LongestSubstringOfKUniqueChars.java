package com.hrishikeshmishra.practices.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Problem:
 * Longest substring with k unique characters.
 * For a given string find longest substring that can made of k unique characters
 *
 * @author hrishikesh.mishra
 */
public class LongestSubstringOfKUniqueChars {

    public static String find(String str, int k) {

        /** **/
        if (!isFeasible(str, k)) {
            throw new RuntimeException("Not possible to create.");
        }


        String maxSubString = "";

        /** Map to hold character and their count **/
        Map<Character, Integer> map = new HashMap<>();

        /** Iterate string from start to end one by one character **/
        for (int i = 0, j = 0; i < str.length(); i++) {

            /** Get current character **/
            char c = str.charAt(i);

            /** Add to current character to map **/
            addToMap(map, c);

            /** Validate is character count increase to K. **/
            if (map.size() > k) {

                /** In case increase to K then remove from map **/
                while (map.size() > k) {
                    char temp = str.charAt(j);
                    removeFromMap(map, temp);
                    j++;
                }
            }

            /** Update longest substring when current unique character count reaches to K. **/
            if (map.size() == k &&
                    maxSubString.length() < str.substring(j, i + 1).length()) {
                maxSubString = str.substring(j, i + 1);
            }
        }

        return maxSubString;
    }

    private static void addToMap(Map<Character, Integer> map, Character c) {
        Integer occurrence = map.getOrDefault(c, 0) + 1;
        map.put(c, occurrence);

    }

    private static void removeFromMap(Map<Character, Integer> map, Character c) {
        /** Remove from map when count reaches to zero **/
        Integer occurrence = map.get(c) - 1;
        if (occurrence > 0) {
            map.put(c, occurrence);
        } else {
            map.remove(c);
        }
    }


    private static boolean isFeasible(String s, int k) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }

        return set.size() >= k;
    }
}


class LongestSubstringOfKUniqueCharsTest {
    public static void main(String[] args) {
        String str = "aabbcc";
        int k = 3;
        System.out.printf("String: %s , K: %d, Longest SubString: %s\n", str, k, LongestSubstringOfKUniqueChars.find(str, k));

    }
}