package com.hrishikeshmishra.practices.string;

/**
 * Problem:
 * Number of Segments in a String
 * Count the number of segments in a string, where a segment
 * is defined to be a contiguous sequence of non-space characters
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/number-segments-string/
 */
public class NumberOfSegmentsInAString {

    public static int count(String s) {

        /** Base case : When string  is equal **/
        if (s == null || s.equals("")) {
            return 0;
        }

        int count = 0;
        Character prevChar = null;

        /** Iterating all characters one by one **/
        for (int i = 0; i < s.length(); i++) {

            /** If current character is space **/
            if (s.charAt(i) == ' ') {

                /** And previous character was not space or null **/
                if (prevChar != null && prevChar != ' ') {
                    count++;
                }
            }
            prevChar = s.charAt(i);
        }

        /** For last character **/
        if (s.charAt(s.length() - 1) != ' ') {
            count++;
        }

        /** return count **/
        return count;

    }
}


class NumberOfSegmentsInAStringTest {
    public static void main(String[] args) {
        String str1 = "Hello, world";
        String str2 = "";
        String str3 = ", , , ,        a, eaefa";
        String str4 = "    foo    bar";


        System.out.printf("String : %s , segments: %d\n", str1, NumberOfSegmentsInAString.count(str1));
        System.out.printf("String : %s , segments: %d\n", str2, NumberOfSegmentsInAString.count(str2));
        System.out.printf("String : %s , segments: %d\n", str3, NumberOfSegmentsInAString.count(str3));
        System.out.printf("String : %s , segments: %d\n", str4, NumberOfSegmentsInAString.count(str4));

    }
}
