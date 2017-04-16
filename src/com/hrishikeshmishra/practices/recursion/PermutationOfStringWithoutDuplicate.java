package com.hrishikeshmishra.practices.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.hrishikeshmishra.practices.recursion.PermutationOfStringWithoutDuplicate.getPermutations;

/**
 * Problem:
 * Permutation of a String Without Duplicate
 * All permutation of a string that has only unique characters.
 * ;
 * ;
 * Solution:
 * - It base on previous permutation for example:
 * - P(a1) = a1
 * - P(a1,a2) = P(a1) + a2 = a2a1 + a1a2
 * - P(a1,a2,a3) = P(a1,a2) + a3 =  (a2a1 + a1a2) + a3
 * = (a3a2a1 + a2a3a1 + a2a1a3 ) + (a3a1a2 + a1a3a2 + a1a2a3)
 * ;
 * ;
 * Algorithm:
 * - Base Case : if string is null then return null
 * - Create lists permutationList of all permutations
 * - If string is length = 0 then
 * - - add permutationList.add ("")
 * - Else
 * - - Get first character of string
 * - - Recursively call with remaining string
 * - - Iterate all words returned by previous call
 * - - - Add first character to all position of word from start to end
 * - - - Add altered word to permutationList
 * - Return permutationList
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/permutation-string-without-duplicate/
 */
public class PermutationOfStringWithoutDuplicate {

    public static List<String> getPermutations(String str) {
        /** Base case when string is null **/
        if (Objects.isNull(str)) {
            return null;
        }

        List<String> permutations = new ArrayList<>();
        if (str.length() == 0) {
            permutations.add("");
        } else {
            /** Get first character **/
            char firstChar = str.charAt(0);
            String remainingString = str.substring(1);
            List<String> words = getPermutations(remainingString);

            /** Add firstChar to all word **/
            for (String word : words) {
                /** Add firstChar to all positions of words **/
                for (int index = 0; index <= word.length(); index++) {
                    permutations.add(insertCharAt(word, index, firstChar));
                }
            }
        }

        return permutations;
    }

    private static String insertCharAt(String word, int index, char firstChar) {
        String firstPart = word.substring(0, index);
        String lastPart = word.substring(index);
        return firstPart + firstChar + lastPart;
    }

}


class PermutationOfStringWithoutDuplicateTest {

    public static void main(String[] args) {
        System.out.println("Permutation(a): " + getPermutations("a"));
        System.out.println("Permutation(ab): " + getPermutations("ab"));
        System.out.println("Permutation(abc): " + getPermutations("abc"));
    }
}