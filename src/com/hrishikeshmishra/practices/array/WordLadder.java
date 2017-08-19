package com.hrishikeshmishra.practices.array;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import static com.hrishikeshmishra.practices.array.WordLadder.find;

/**
 * Problem:
 * Word Ladder
 * Given two words (startWord and endWord), and a dictionary's word list,
 * Find the length of shortest transformation sequence from beginWord to endWord.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/word-ladder/
 */
public class WordLadder {


    public static int findByTwoEndedBFS(String startWord, String endWord, List<String> dictionary) {

        /** Base case: When dictionary is emtpy **/
        if (Objects.isNull(dictionary) || dictionary.size() == 0) {
            return 0;
        }

        /** Base case: when both words are same **/
        if (startWord.equals(endWord)) {
            return 1;
        }

        /** Base case: when words not available in dictionary **/
        if (!dictionary.contains(startWord) || !dictionary.contains(endWord)) {
            return 0;
        }


        int length = 1;
        /** Visited nodes (here words)**/
        Set<String> visitedNodes = new HashSet<>();

        /** Search from start side **/
        Set<String> frontEndSearch = new HashSet<>();

        /** Search from end side **/
        Set<String> backEndSearch = new HashSet<>();

        /** Adding starting and ending word to respective sets **/
        frontEndSearch.add(startWord);
        backEndSearch.add(endWord);

        /** Iterate till both (start and end ) sets are not empty. **/
        while (!frontEndSearch.isEmpty() && !backEndSearch.isEmpty()) {

            /** Always process smaller set **/
            if (frontEndSearch.size() > backEndSearch.size()) {
                Set<String> temp = frontEndSearch;
                frontEndSearch = backEndSearch;
                backEndSearch = temp;
            }

            /** Temporary set to hold new word **/
            Set<String> newSet = new HashSet<>();

            /** Iterate all word from one set and generate possible next words **/
            for (String word : frontEndSearch) {
                char[] chars = word.toCharArray();

                /** Generate all possible words **/
                for (int i = 0; i < chars.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {

                        char oldChar = chars[i];
                        chars[i] = c;

                        String newWord = new String(chars);

                        /** Check newly created word is in another set or not. **/
                        if (backEndSearch.contains(newWord)) {
                            return length + 1;
                        }

                        /** Add new word to set visited and new set **/
                        if (!visitedNodes.contains(newWord) && dictionary.contains(newWord)) {
                            newSet.add(newWord);
                            visitedNodes.add(newWord);
                        }
                        chars[i] = oldChar;
                    }
                }
            }
            frontEndSearch = newSet;
            length++;

        }

        return 0;
    }


    public static int find(String startWord, String endWord, List<String> dictionary) {

        /** Base case: When dictionary is emtpy **/
        if (Objects.isNull(dictionary) || dictionary.size() == 0) {
            return 0;
        }

        /** Base case: when both words are same **/
        if (startWord.equals(endWord)) {
            return 1;
        }

        Set<String> processedWord = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int length = 1;
        processedWord.add(startWord);
        queue.add(startWord);

        while (!queue.isEmpty()) {
            length++;
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                String word = queue.remove();
                List<String> nextPossibleWords = getValidPossibleWords(word, dictionary);

                for (String possibleWord : nextPossibleWords) {

                    /** Already processed **/
                    if (processedWord.contains(possibleWord)) {
                        continue;
                    }

                    if (possibleWord.equals(endWord)) {
                        return length;
                    }

                    processedWord.add(possibleWord);
                    queue.add(possibleWord);
                }
            }
        }

        return 0;
    }

    private static List<String> getValidPossibleWords(String word, List<String> dictionary) {
        List<String> possibleWords = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {

                if (c == word.charAt(i)) {
                    continue;
                }

                String possibleWord = replace(word, i, c);

                if (dictionary.contains(possibleWord)) {
                    possibleWords.add(possibleWord);
                }

            }
        }
        return possibleWords;
    }

    private static String replace(String word, int i, char c) {
        char[] chars = word.toCharArray();
        chars[i] = c;
        return new String(chars);
    }

}

class WordLadderTest {
    public static void main(String[] args) {

        String start = "hit";
        String end = "cog";
        List<String> dictionary = new ArrayList<String>() {
            {
                add("hot");
                add("dot");
                add("dog");
                add("lot");
                add("log");
                add("cog");
            }
        };

        System.out.println("Start : " + start);
        System.out.println("End: " + end);
        System.out.println("Dictionary: " + dictionary);
        System.out.println("Minimum step: " + find(start, end, dictionary));

    }


}
