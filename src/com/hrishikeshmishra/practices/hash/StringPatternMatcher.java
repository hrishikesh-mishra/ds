package com.hrishikeshmishra.practices.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem:
 * Find all strings that match specific pattern in a dictionary
 * Given a dictionary of words, find all strings that matches the given pattern where every
 * character in the pattern is uniquely mapped to a character in the dictionary.
 * ;
 * Example:
 * Input:
 * dict = ["abb", "abc", "xyz", "xyy"];
 * pattern = "foo"
 * Output: [xyy abb]
 * Explanation:
 * xyy and abb have same character at index 1 and 2 like the pattern
 * ;
 * Input:
 * dict = ["abb", "abc", "xyz", "xyy"];
 * pat = "mno"
 * Output: [abc xyz]
 * Explanation:
 * abc and xyz have all distinct characters, similar to the pattern
 * ;
 * Input:
 * dict = ["abb", "abc", "xyz", "xyy"];
 * pattern = "aba"
 * Output: []
 * Explanation:
 * Pattern has same character at index 0 and 2.
 * No word in dictionary follows the pattern.
 * ;
 * Input:
 * dict = ["abab", "aba", "xyz", "xyx"];
 * pattern = "aba"
 * Output: [aba xyx]
 * Explanation:
 * aba and xyx have same character at index 0 and 2 like the pattern
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-strings-match-specific-pattern-dictionary/
 */
public class StringPatternMatcher {

    public static List<String> match(String[] dictionary, String pattern) {

        String hash = getHash(pattern);
        List<String> matchedWord = new ArrayList<>();

        for (String word : dictionary) {

            /** Length and Hash must be equal to pattern **/
            if (pattern.length() == word.length() &&
                    hash.equals(getHash(word))) {
                matchedWord.add(word);
            }
        }

        return matchedWord;
    }

    private static String getHash(String str) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();

        int index = 0;
        for (char c : str.toCharArray()) {

            if (!map.containsKey(c)) {
                map.put(c, index++);
            }
            sb.append(map.get(c));
        }

        return sb.toString();
    }

}


class StringPatternMatcherTest {
    public static void main(String[] args) {

        String[] dictionary = {"abb", "abc", "xyz", "xyy"};
        String pattern = "foo";
        System.out.println("Dictionary: " + Arrays.toString(dictionary));
        System.out.println("Pattern: " + pattern);
        System.out.println("Matches:" + StringPatternMatcher.match(dictionary, pattern));

        dictionary = new String[] {"abb", "abc", "xyz", "xyy"};
        pattern = "mno";
        System.out.println("\n\nDictionary: " + Arrays.toString(dictionary));
        System.out.println("Pattern: " + pattern);
        System.out.println("Matches:" + StringPatternMatcher.match(dictionary, pattern));

        dictionary = new String[] {"abb", "abc", "xyz", "xyy"};
        pattern = "aba";
        System.out.println("\n\nDictionary: " + Arrays.toString(dictionary));
        System.out.println("Pattern: " + pattern);
        System.out.println("Matches:" + StringPatternMatcher.match(dictionary, pattern));

        dictionary = new String[] {"abab", "aba", "xyz", "xyx"};
        pattern = "aba";
        System.out.println("\n\nDictionary: " + Arrays.toString(dictionary));
        System.out.println("Pattern: " + pattern);
        System.out.println("Matches:" + StringPatternMatcher.match(dictionary, pattern));

    }
}