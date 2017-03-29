package com.hrishikesh.practices.string;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hrishikesh.practices.string.GroupAnagrams.getGroup;

/**
 * Problem
 * Group Anagrams
 * Given an array of strings, group anagrams together.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/group-anagrams/
 */
public class GroupAnagrams {

    public static List<List<String>> getGroup(String[] array) {
        /** Group Anagrams **/
        List<List<String>> result = new ArrayList<>();

        /** Group by Anagrams **/
        Map<String, List<String>> groups = new HashMap<>();

        /** Iterate all array element one by one**/
        for (String element : array) {

            /**
             * Convert element to unique key that will be same for all anagrams
             */
            String key = convertToUniqueString(element);

            /** Check key is present in map or not **/
            if (groups.containsKey(key)) {
                groups.get(key).add(element);
            } else {
                List<String> list = new ArrayList<>();
                list.add(element);
                groups.put(key, list);
            }
        }
        result.addAll(groups.values());
        return result;
    }

    public static String convertToUniqueString(String str) {
        char[] chars = new char[26];
        for (char c : str.toCharArray()) {
            chars[c - 'a']++;
        }
        return new String(chars);
    }
}


class GroupAnagramsTest {
    public static void main(String[] args) {
        String[] array = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.printf("Array : %s, Group: %s\n ", Arrays.toString(array), getGroup(array));

    }
}