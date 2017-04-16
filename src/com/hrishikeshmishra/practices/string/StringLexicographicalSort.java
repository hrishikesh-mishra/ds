package com.hrishikeshmishra.practices.string;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Problem:
 * String  lexicographical String
 * ;
 * Given a list of string, sort them in lexicographical order, with following constraints:
 * - If two words have same spelling, lowercase characters take higher priority.
 * - The words can have spaces in them. A space takes higher priority over any alphabet.
 *
 * @author hrishikesh.mishra
 */
public class StringLexicographicalSort {

    private static class StringLexicographicalComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            int i = 0, j = 0;

            for (; i < o1.length() && j < o2.length(); i++, j++) {

                char c1 = o1.charAt(i);
                char c2 = o2.charAt(j);

                /** If character are not equal then **/
                if (c1 != c2) {

                    /** Both are same but different case **/
                    if (Math.abs(c1 - c2) == 32) {

                        /** Lowercase has higher priority **/
                        return c1 > c2 ? -1 : 1;
                    }
                    /** If one of has space character **/
                    else if (Character.isSpaceChar(c1) || Character.isSpaceChar(c2)) {
                        return Character.isSpaceChar(c1) ? -1 : 1;
                    }
                    /** Other character are equal **/
                    else {
                        return Character.compare(c1, c2);
                    }
                }

            }

            /** When both strings are equal **/
            if (o1.length() == o2.length()) {
                return 0;
            }

            return i == o1.length() ? -1 : 1;
        }
    }

    public static void sort(String[] strings) {
        Arrays.sort(strings, new StringLexicographicalComparator());
    }

}


class StringLexicographicalSortTest {
    public static void main(String[] args) {
        String[] strings = {"hrishikesh", "hrishi", "Hrishi", "HrisHikesh", "hris hi"};

        System.out.printf("Strings before sorting : %s\n", Arrays.toString(strings));
        StringLexicographicalSort.sort(strings);
        System.out.printf("Strings after sorting : %s\n", Arrays.toString(strings));

    }
}