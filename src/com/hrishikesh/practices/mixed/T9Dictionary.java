package com.hrishikesh.practices.mixed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.hrishikesh.practices.mixed.T9Dictionary.getT9ValidWords;

/**
 * Problem:
 * T9 Dictionary Using DFS
 * ;
 * How can the T9 dictionary be implemented?
 * ;
 * Solution:
 * - We can use DFS to create all possible words
 * - Or, Tries for better optimisation
 * - Or, Pre-computed  in hash map
 * ;
 * ;
 * ;
 * DFS Algorithm:
 * - Traverse the keyword character array for each digit
 * - Generate all possible word
 * - Valid generated word with dictionary
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/t9-dictionary-using-dfs/
 */
public class T9Dictionary {

    private final static char[][] t9Letters = {
            null, //0
            null, //1
            {'a', 'b', 'c'}, //2
            {'d', 'e', 'f'}, //3
            {'g', 'h', 'i'}, //4
            {'j', 'k', 'l'}, //5
            {'m', 'n', 'o'}, //6
            {'p', 'q', 'r', 's'}, //7
            {'t', 'u', 'v'}, //8
            {'w', 'x', 'y', 'z'} //9
    };

    public static List<String> getT9ValidWords(String number, Set<String> dictionary) {
        List<String> result = new ArrayList<>();
        getWords(number, dictionary, result, 0, "");
        return result;
    }

    private static void getWords(String number, Set<String> dictionary, List<String> result, int index, String prefix) {

        /** Base case: adding word to result when its valid **/
        if (index == number.length()) {
            if (dictionary.contains(prefix)) {
                result.add(prefix);
            }
            return;
        }

        /** Get keyboard character array for a digit **/
        char character = number.charAt(index);
        int digit = convertCharacterToNumber(character);
        char[] keyboardCharacters = getKeyboardCharacters(digit);

        /** Iterate all characters one by one **/
        if (!Objects.isNull(keyboardCharacters)) {
            for (Character c : keyboardCharacters) {
                getWords(number, dictionary, result, index + 1, prefix + c);
            }
        }
    }

    private static char[] getKeyboardCharacters(int digit) {
        return t9Letters[digit];
    }

    private static int convertCharacterToNumber(char c) {
        return Character.getNumericValue(c) - Character.getNumericValue('0');
    }

}

class T9DictionaryTest {
    public static void main(String[] args) {
        String number = "8733";
        Set<String> dictionary = new HashSet<String>() {
            {
                add("alan");
                add("tree");
                add("turning");
                add("used");
            }
        };

        System.out.println("For number :" + number + " Valid words :" + getT9ValidWords(number, dictionary));


    }
}
