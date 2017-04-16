package com.hrishikeshmishra.practices.mixed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem:
 * Longest Word Made of Other Words
 * Find the longest word made of other words
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/longest-word-made-words/
 */
public class LongestWord {

    public static String find(List<String> dictionary) {
        Map<String, Boolean> isVisited = getWordMap(dictionary);
        Collections.sort(dictionary, new LengthComparator());
        for (String word : dictionary) {

            if (isLongestWord(word, true, isVisited)) {
                return word;
            }
        }
        return null;
    }

    private static boolean isLongestWord(String word, boolean isOriginalWord, Map<String, Boolean> map) {
        if (!isOriginalWord && map.containsKey(word)) {
            return map.get(word);
        }

        for (int i = 1; i < word.length(); i++) {
            String left = word.substring(0, i);
            String right = word.substring(i);

            if (map.containsKey(left) &&
                    map.get(left) &&
                    isLongestWord(right, false, map)) {
                return true;
            }

        }

        map.put(word, false);
        return false;
    }

    private static HashMap<String, Boolean> getWordMap(final List<String> dictionary) {
        return new HashMap<String, Boolean>() {
            {
                for (String word : dictionary) {
                    put(word, true);
                }
            }
        };
    }


    private static class LengthComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(o2.length(), o1.length());
        }
    }
}

class LongestWordTest {
    public static void main(String[] args) {
        List<String> dictionary = new ArrayList<String>() {
            {
                add("test");
                add("tester");
                add("testertest");
                add("testing");
                add("testingtester");

            }
        };

        System.out.println("Dictionary : " + dictionary);
        System.out.println("Longest Word: " + LongestWord.find(dictionary));
    }
}