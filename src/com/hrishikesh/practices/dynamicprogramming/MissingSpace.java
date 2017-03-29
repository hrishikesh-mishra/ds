package com.hrishikesh.practices.dynamicprogramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Problem:
 * Missing Space Problem
 * ;
 * Given a string (all space removed from this) and a dictionary, design an algorithm to
 * add space in ths string in a way that minimizes the number of unrecognized characters.
 * ;
 * ;
 * Algorithm:
 * - Call recursive method with original_string, start_index = 0 and empty memo (as cache)
 * - - if start_index >= original_string.length then
 * - - - return bestInvalidChar = 0 and bestParsing = ""
 * - - else if memo contain index then return from memo
 * - - Set partial = "", bestInvalidChar = INFINITY and  bestParsing = null
 * - - Iterate from start_index to end of string
 * - - - - Add current index character of original_string to partial
 * - - - - Check partial_string is in dictionary or not
 * - - - - update invalid_char based on availability of partial in dictionary
 * - - - - if invalid_char is less than bestInvalidChar then explore recursively all next substring
 * - - - - Update bestInvalidChar and bestParsing based on recursive call
 * - - Update memo (i.e. cache)
 * - - Return result
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/missing-space-problem/
 */
public class MissingSpace {

    private static class Result {
        private String bestParsing;
        private int bestInvalidChar;

        public Result(String bestParsing, int bestInvalidChar) {
            this.bestParsing = bestParsing;
            this.bestInvalidChar = bestInvalidChar;
        }
    }

    public static String fixIt(String str, Set<String> dictionary) {
        Result result = addSpace(str, 0, dictionary, new HashMap<>());
        return result.bestParsing;
    }

    public static Result addSpace(String originalString, int index, Set<String> dictionary,
                                  Map<Integer, Result> memo) {
        if (index >= originalString.length()) {
            return new Result("", 0);
        } else if (memo.containsKey(index)) {
            return memo.remove(index);
        }


        String partialString = "";
        int bestInvalidChar = Integer.MAX_VALUE;
        String bestParsing = null;

        for (int i = index; i < originalString.length(); i++) {
            char c = originalString.charAt(i);
            partialString += c;
            int invalid = dictionary.contains(partialString) ? 0 : partialString.length();

            /** If Current invalid character less than best known invalid character, then only call recursively **/
            if (invalid < bestInvalidChar) {
                Result result = addSpace(originalString, i + 1, dictionary, memo);
                if (invalid + result.bestInvalidChar < bestInvalidChar) {
                    bestInvalidChar = invalid + result.bestInvalidChar;
                    bestParsing = partialString + " " + result.bestParsing;

                    if (bestInvalidChar == 0) {
                        return new Result(bestParsing, bestInvalidChar);

                    }
                }
            }
        }

        memo.put(index, new Result(bestParsing, bestInvalidChar));
        return memo.get(index);
    }
}

class MissingSpaceTest {
    public static void main(String[] args) {
        String str1 = "itisacat";

        Set<String> dictionay1 = new HashSet<String>() {
            {
                add("hrishi");
                add("it");
                add("i");
                add("a");
                add("is");
                add("cat");
            }
        };

        System.out.println(MissingSpace.fixIt(str1, dictionay1));

    }
}