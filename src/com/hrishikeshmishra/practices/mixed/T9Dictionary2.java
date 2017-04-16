package com.hrishikeshmishra.practices.mixed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * T9 Dictionary Using Pre-Computed map
 * ;
 * How can the T9 dictionary be implemented?
 * ;
 * Solution:
 * - We can use DFS to create all possible words
 * - Or, Tries for better optimisation
 * - Or, Pre-computed in hash map
 * ;
 * ;
 * Pre-Computed map Algorithm:
 * - During object create compute map to number to list of words
 * - And on method call return return word list just by lookup in map
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/t9-dictionary-using-pre-computed-map/
 */
public class T9Dictionary2 {

    private final static Map<Character, Character> t9Keyboard = new HashMap<Character, Character>() {
        {
            put('a', '2');
            put('b', '2');
            put('c', '2');

            put('d', '3');
            put('e', '3');
            put('f', '3');

            put('g', '4');
            put('h', '4');
            put('i', '4');

            put('j', '5');
            put('k', '5');
            put('l', '5');

            put('m', '6');
            put('n', '6');
            put('o', '6');

            put('p', '7');
            put('q', '7');
            put('r', '7');
            put('s', '7');

            put('t', '8');
            put('u', '8');
            put('v', '8');

            put('w', '9');
            put('x', '9');
            put('y', '9');
            put('z', '9');

        }
    };

    private final Map<String, List<String>> numberToWordsMap;

    public T9Dictionary2(String[] words) {
        numberToWordsMap = new HashMap<>();
        init(words);
    }

    public List<String> getValidT9Words(String number) {
        return numberToWordsMap.get(number);
    }

    private void init(String[] words) {
        for (String word : words) {
            String number = convertToT9Number(word);
            if (numberToWordsMap.containsKey(number)) {
                numberToWordsMap.get(number).add(word);
            } else {
                List<String> list = new ArrayList<>();
                list.add(word);
                numberToWordsMap.put(number, list);
            }
        }
    }

    private String convertToT9Number(String word) {
        StringBuffer sb = new StringBuffer(word.length());
        for (Character c : word.toCharArray()) {
            sb.append(t9Keyboard.get(c));
        }
        return sb.toString();
    }
}

class T9Dictionary2Test {
    public static void main(String[] args) {
        String[] words = {"alan", "tree", "turning", "used"};
        T9Dictionary2 dictionary = new T9Dictionary2(words);
        String number = "8733";

        System.out.println("For number :" + number + " Valid words :" + dictionary.getValidT9Words(number));

    }
}
