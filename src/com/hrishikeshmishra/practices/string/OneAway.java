package com.hrishikeshmishra.practices.string;

/**
 * Problem:
 * One Away:
 * There are three type of edits that can be performed on strings:
 * - insert a character
 * - remove a character
 * - replace a character
 * ;
 * Given two strings, write a function to check if they are on edit (or zero edit) away.
 *
 * @author hrishikesh.mishra
 * @see <p>Cracking The Coding Interview Question: 1.5</p>
 * @link http://hrishikeshmishra.com/given-two-strings-write-a-function-to-check-if-they-are-on-edit-or-zero-edit-away/
 */
public class OneAway {

    public static boolean isOneAway(String str1, String str2) {

        /** Get length of both strings **/
        int len1 = str1.length();
        int len2 = str2.length();

        /** Get size difference **/
        int sizeDiff = Math.abs(len1 - len2);

        /** Counter for number of operations **/
        int numberOfOperations = 0;

        /** If size diff is already greater than 1 then return false **/
        if (sizeDiff > 1) {
            return false;
        }

        int index1 = 0;
        int index2 = 0;

        /** Iterate all strings **/
        while (index1 < len1 && index2 < len2) {

            /** Get characters **/
            char c1 = str1.charAt(index1);
            char c2 = str2.charAt(index2);

            /** If both characters are same then, increase both pointers **/
            if (c1 == c2) {
                index1++;
                index2++;
            } else if (sizeDiff == 1) {
                /**
                 *
                 * If they are not same and size is also not same then, increase larger string index
                 * For example
                 * string1 = pale
                 * string2 = ple
                 */
                if (len1 > len2) {
                    index1++;
                } else {
                    index2++;
                }
                /** Update operation counter **/
                numberOfOperations++;

            } else {
                /** If characters are not same but strings are same length **/
                index1++;
                index2++;
                numberOfOperations++;
            }

            /** If number of operation is greater than or equal to 2 then false **/
            if (numberOfOperations >= 2) {
                return false;
            }
        }

        /** Default true **/
        return true;
    }


}

class OneAwayTest {
    public static void main(String[] args) {
        String str1 = "pale";
        String str2 = "ple";
        System.out.println(str1 + ", " + str2 + " => " + OneAway.isOneAway(str1, str2));

        str1 = "pales";
        str2 = "pale";
        System.out.println(str1 + ", " + str2 + " => " + OneAway.isOneAway(str1, str2));

        str1 = "pale";
        str2 = "bale";
        System.out.println(str1 + ", " + str2 + " => " + OneAway.isOneAway(str1, str2));

        str1 = "pale";
        str2 = "bake";
        System.out.println(str1 + ", " + str2 + " => " + OneAway.isOneAway(str1, str2));


    }
}