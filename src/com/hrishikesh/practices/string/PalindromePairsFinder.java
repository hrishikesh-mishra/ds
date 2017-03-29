package com.hrishikesh.practices.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hrishikesh.practices.string.PalindromePairsFinder.find;

/**
 * Problem
 * Find Palindrome Pairs in Array of Strings
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-palindrome-pairs-array-strings/
 */
public class PalindromePairsFinder {

    public static List<List<Integer>> find(String[] words) {

        /** Array to hold result **/
        List<List<Integer>> result = new ArrayList<>();

        /** Map words and index **/
        Map<String, Integer> map = new HashMap<>();

        /** Puting words in map **/
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }


        /** Iterating all words one by one **/
        for (int i = 0; i < words.length; i++) {

            /** Checking all substrings of a word **/
            for (int j = 0; j <= words[i].length(); j++) {

                /** Substring from start **/
                String leftPart = words[i].substring(0, j);

                /** Substring from end **/
                String rightPart = words[i].substring(j);

                /** If left part is palindrome **/
                if (isPalindrome(leftPart)) {

                    /** Reverse right part **/
                    String rightPartReverse = new StringBuffer(rightPart).reverse().toString();

                    /**
                     * Check reversed right part is in map or not, because
                     *  left is already palindrome and
                     *  right + reverse_of_right => will be palindrome
                     *  So, final palindrome will be
                     *  reverse_of_right + left_part + right_part
                     */
                    if (map.containsKey(rightPartReverse) && map.get(rightPartReverse) != i) {
                        result.add(Arrays.asList(map.get(rightPartReverse), i));
                    }
                }

                /** If right is palindrome **/
                if (isPalindrome(rightPart) && rightPart.length() != 0) {
                    /** Reverse left part **/
                    String leftPartReverse = new StringBuffer(leftPart).reverse().toString();

                    /**
                     * Check reversed_left_part is in map or not, because
                     * right part is already palindrome and
                     * left + left_reverse => will be palindrome
                     * So, final palindrome will be
                     * left + right + reverse_of_left_part
                     */
                    if (map.containsKey(leftPartReverse) && map.get(leftPartReverse) != i) {
                        result.add(Arrays.asList(i, map.get(leftPartReverse)));
                    }

                }

            }
        }

        return result;
    }


    private static boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;

        while (left <= right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}


class PalindromePairsFinderTest {
    public static void main(String[] args) {
        String[] words1 = {"abcd", "dcba", "lls", "s", "sssll"};
        String[] words2 = {"a", ""};
        String[] words3 = {"bat", "tab", "cat"};

        System.out.printf("Words:  %s, Palindrome Pairs : %s\n", Arrays.toString(words1), find(words1));
        System.out.printf("Words:  %s, Palindrome Pairs : %s\n", Arrays.toString(words2), find(words2));
        System.out.printf("Words:  %s, Palindrome Pairs : %s\n", Arrays.toString(words3), find(words3));

    }
}