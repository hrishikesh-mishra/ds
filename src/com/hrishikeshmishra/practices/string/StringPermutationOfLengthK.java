package com.hrishikeshmishra.practices.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem:
 * Generate String Permutation of Length K with Duplicate Character
 * Generate all possible strings of length k that can formed from a string of length n.
 *
 * @author hrishikesh.mishra
 */
public class StringPermutationOfLengthK {

    public static List<String> generate(String str, int k) {
        List<String> result = new ArrayList<>();
        generateHelper(str, k, "", result);
        return result;
    }

    private static void generateHelper(String str, int k, String prefix, List<String> result) {

        if (prefix.length() == k) {
            result.add(prefix);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            String newPrefix = prefix + str.charAt(i);
            generateHelper(str, k, newPrefix, result);
        }
    }
}


class StringPermutationOfLengthKTest {
    public static void main(String[] args) {
        String str = "AB";

        System.out.printf("String: %s, K: %d, Permutation: %s\n", str, 3,
                StringPermutationOfLengthK.generate(str, 3));

        str = "ABCD";

        System.out.printf("String: %s, K: %d, Permutation: %s\n", str, 2,
                StringPermutationOfLengthK.generate(str, 2));

    }
}