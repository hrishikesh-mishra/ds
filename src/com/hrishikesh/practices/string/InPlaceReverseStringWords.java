package com.hrishikesh.practices.string;

import static com.hrishikesh.practices.string.InPlaceReverseStringWords.reverse;

/**
 * Problem:
 * In-Place Reverse String Words
 *
 * @author hrishikesh.mishra
 * @Link http://hrishikeshmishra.com/place-reverse-string-words/
 */
public class InPlaceReverseStringWords {

    public static String reverse(String s) {
        /** Base case: when string  is null or empty **/
        if (s == null || s.length() == 0) {
            return s;
        }

        /** Word starting index marker **/
        Integer wordStartIndex = null;

        /** End string marker **/
        String endWord = "#$!$#";

        /** String + " " + end_word_marker  **/
        s = s + " " + endWord;

        /** String last index **/
        int lastIndex = s.length();

        /** Iterate all characters one by one **/
        for (int i = 0; i < lastIndex; i++) {

            /** When new non-space word starts **/
            if (wordStartIndex == null && s.charAt(i) != ' ') {
                wordStartIndex = i;

            }
            /** When a word ends **/
            else if (wordStartIndex != null && s.charAt(i) == ' ') {

                /** Extract word from string **/
                String word = s.substring(wordStartIndex, i);

                /** If word is equal to end word **/
                if (word.equals(endWord)) {
                    break;
                }

                /** Left part of string **/
                String left = s.substring(0, lastIndex);

                /** Right part of string **/
                String right = s.substring(lastIndex);

                /** When there is no right words in right **/
                if (right.length() == 0) {
                    s = left + word;
                } else {
                    s = left + word + " " + right;
                }

                /** Reset new word start end **/
                wordStartIndex = null;

            }
        }

        /** Return reversed words **/
        return s.substring(lastIndex);
    }
}


class InPlaceReverseStringWordsTest {
    public static void main(String[] args) {
        String s = " the      sky is blue ";
        String s2 = "a   ";
        System.out.printf("String: %s\nReverse: %s\n\n", s, reverse(s));
        System.out.printf("String: %s\nReverse: %s\n\n", s2, reverse(s2));
    }
}