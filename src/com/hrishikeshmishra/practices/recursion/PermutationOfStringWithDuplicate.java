package com.hrishikeshmishra.practices.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.hrishikeshmishra.practices.recursion.PermutationOfStringWithDuplicate.getPermutations;

/**
 * Problem:
 * Permutation of a String Duplicate
 * All permutation of a string that has duplicate characters and lexicographic order.
 * ;
 * ;
 * Algorithm:
 * - If level is equal to size string then
 * - - add character array to permutation list
 * - - return
 * - Iterate character count map
 * - - If count of character is greater than zero then
 * - - - add character to character array
 * - - - decrease character count
 * - - - recursively call with level + 1
 * - - - reset character count
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=nYFd7VHKyWQ
 * @link http://hrishikeshmishra.com/permutation-string-duplicate/
 */
public class PermutationOfStringWithDuplicate {

    public static List<String> getPermutations(String string) {
        Map<Character, Integer> characterCounts = getCharacterCounts(string);
        List<String> permutations = new ArrayList<>();
        getPermutations(characterCounts, 0, new char[string.length()], string.length(), permutations);
        return permutations;

    }

    private static void getPermutations(Map<Character, Integer> characterCounts, int level, char[] characterArray,
                                        int size, List<String> permutations) {

        if (level == size) {
            permutations.add(String.copyValueOf(characterArray));
            return;
        }
        for (Map.Entry<Character, Integer> entry : characterCounts.entrySet()) {

            if (entry.getValue() > 0) {
                characterArray[level] = entry.getKey();
                entry.setValue(entry.getValue() - 1);
                getPermutations(characterCounts, level + 1, characterArray, size, permutations);
                entry.setValue(entry.getValue() + 1);
            }
        }
    }

    private static Map<Character, Integer> getCharacterCounts(String string) {
        Map<Character, Integer> characterCounts = new TreeMap<>();
        for (Character character : string.toCharArray()) {
            if (characterCounts.containsKey(character)) {
                characterCounts.put(character, characterCounts.get(character) + 1);
            } else {
                characterCounts.put(character, 1);
            }
        }

        return characterCounts;
    }


}


class PermutationOfStringWithDuplicateTest {
    public static void main(String[] args) {
        System.out.println("Permutations of \"CAAB\": " + getPermutations("CAAB"));
    }
}