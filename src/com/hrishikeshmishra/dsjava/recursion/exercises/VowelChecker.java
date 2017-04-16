package com.hrishikeshmishra.dsjava.recursion.exercises;

import java.util.Arrays;
import java.util.List;

/**
 * C-5.19
 *
 * Use recursion to write a Java method for determining
 * if a string s has more vowels than consonants.
 *
 * Created by hrishikesh.mishra on 16/01/16.
 */
public class VowelChecker {


    private static final List<Character> vowels = Arrays.asList(new Character[]{'A', 'E', 'I', 'O', 'U'});

    public static void main(String[] args) {
        System.out.println("Is vowel greater than consonant in  \"hrishi\": " + isVowelGreaterThanConsonant("hrishi"));
        System.out.println("Is vowel greater than consonant in  \"kumar\": " + isVowelGreaterThanConsonant("kumar"));
        System.out.println("Is vowel greater than consonant in  \"india\": : " + isVowelGreaterThanConsonant("india"));
    }

    private static boolean isVowelGreaterThanConsonant(String str){
        return isVowelGreaterThanConsonant(str.toUpperCase(), 0,  0, 0);
    }
    private static boolean isVowelGreaterThanConsonant(String str, int index,  int vowelCount, int consoCount){
        if(index >= str.length()) return vowelCount > consoCount ? true : false;
        if(isVowel(str.charAt(index++))) vowelCount++;
        else consoCount++;
        return isVowelGreaterThanConsonant(str, index , vowelCount, consoCount);
    }

    private static boolean isVowel(char letter){
        return vowels.contains(letter);
    }
}
