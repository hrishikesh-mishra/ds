package com.hrishikeshmishra.practices.string;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hrishikeshmishra.practices.string.ConcatenationSubstring.getIndices;

/**
 * Problem
 * Substring with Concatenation of All Words
 * For given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once
 * and without any intervening characters.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/substring-concatenation-words/
 */
public class ConcatenationSubstring {

    public static List<Integer> getIndices(String str, String[] words) {

        /** To hold indices **/
        List<Integer> result = new ArrayList<>();

        /** Words to search **/
        Map<String, Integer> wordsToSearch = new HashMap<>();

        /** Words found **/
        Map<String, Integer> wordsFound = new HashMap<>();

        /** Total words **/
        int totalWords = words.length;

        /** Length of one word **/
        int wordLength = words[0].length();

        /** Adding all words to map for fast lookup **/
        for (String word : words) {
            Integer count = wordsToSearch.getOrDefault(word, 0);
            wordsToSearch.put(word, count + 1);
        }

        for (int index = 0; index <= str.length() - totalWords * wordLength; index++) {
            /** All found words **/
            wordsFound.clear();

            /** Get current word **/
            String currentWord = str.substring(index, index + wordLength);

            /** If current word is not substring word, then don't do anything just move right pointer **/
            if (!wordsToSearch.containsKey(currentWord)) {
                continue;
            }

            /** Word counter **/
            int wordCounter = 0;

            /** Iterating all words **/
            for (; wordCounter < totalWords; wordCounter++) {
                /** Start index of word **/
                int startIndex = index + wordLength * wordCounter;
                /** End word index **/
                int endIndex = index + wordLength * (wordCounter + 1);
                /** Current word **/
                currentWord = str.substring(startIndex, endIndex);

                int wordToSearchCount = wordsToSearch.getOrDefault(currentWord, 0);
                int wordFoundCount = wordsFound.getOrDefault(currentWord, 0);

                /** Invalid word **/
                if (wordToSearchCount == 0 || wordFoundCount >= wordToSearchCount) {
                    break;
                }

                wordsFound.put(currentWord, wordFoundCount + 1);
            }

            /** When all words founds **/
            if (wordCounter == totalWords) {
                /** Adding index to result **/
                result.add(index);

                ///** Forwarding index **/
                //index = index + wordLength * wordCounter - 1;
            }
        }
        return result;
    }
}


class ConcatenationSubstringTest {
    public static void main(String[] args) {
        String str = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        System.out.printf("String : %s, Words: %s, Indices: %s\n", str, words, getIndices(str, words));

        String str2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word", "good", "best", "good"};
        System.out.printf("String : %s, Words: %s, Indices: %s\n", str2, words2, getIndices(str2, words2));

    }
}