package com.hrishikeshmishra.practices.string;

/**
 * Problem:
 *  Lexicographically String Merge
 *
 * @author hrishikesh.mishra
 * @// TODO:  Need to Fix
 */
public class LexicographicallyStringMerge {

    private int i;
    private int j;


    private String merge(String str1, String str2) {


        StringBuilder sb = new StringBuilder();
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                int s1Start = i;
                int s2Start = j;
                moveTillCommon(str1, str2);

                addToOutput(str1, s1Start, i, str2, s2Start, j, sb);
            } else if (str1.charAt(i) < str2.charAt(j)) {
                sb.append(str1.charAt(i));
                i++;
            } else if (str1.charAt(i) > str2.charAt(j)) {
                sb.append(str2.charAt(j));
                j++;
            }
        }

        if (i < str1.length()) {
            sb.append(str1.substring(i));
        }

        if (j < str2.length()) {
            sb.append(str2.substring(j));
        }

        return sb.toString();
    }

    private void addToOutput(String s1, int s1Start, int s1End, String s2, int s2Start, int s2End, StringBuilder sb) {

        for (; s1Start < s1End && s2Start < s2End; ) {

            if (s1.charAt(s1Start) < s2.charAt(s2Start)) {
                sb.append(s1.charAt(s1Start));
                s1Start++;
            } else {
                sb.append(s2.charAt(s2Start));
                s2Start++;
            }
        }

        if (s1Start < s1End) {
            sb.append(s1.substring(s1Start, s1End));
        }

        if (s2Start < s2End) {
            sb.append(s2.substring(s2Start, s2End));
        }
    }

    private void moveTillCommon(String s1, String s2) {
        for (; i < s1.length() && j < s2.length() && s1.charAt(i) == s2.charAt(j); i++, j++) ;
    }

    public static void main(String[] args) {
        String A = "addbba";
        String B = "aabbdbac";



        LexicographicallyStringMerge stringMerge = new LexicographicallyStringMerge();
        System.out.println(stringMerge.merge(A, B));
    }
}



