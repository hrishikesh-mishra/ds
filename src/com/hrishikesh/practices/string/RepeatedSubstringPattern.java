package com.hrishikesh.practices.string;

/**
 * Problem
 * Repeated SubString
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/repeated-substring-pattern/
 */
public class RepeatedSubstringPattern {


    public boolean isPatternExists(String str) {
        int len = str.length();

        /** If string is length one **/
        if (str.length() == 1) {
            return false;
        }


        /** Create all permutation of substring length below str.length() / 2 and check valid substring **/
        for (int i = 1; i <= len / 2; i++) {

            String fromStart = str.substring(0, len - i);
            String fromEnd = str.substring(i, len);

            if (fromStart.equals(fromEnd)) {
                String subString = str.substring(0, i);

                if (isValidSubString2(subString, str, i, len - i)) {
                    return true;
                }
            }
        }


        return false;
    }


    public boolean isPatternExistsBruteForce(String str) {

        /** If string is length one **/
        if (str.length() == 1) {
            return false;
        }

        /** Create all permutation of substring length below str.length() / 2 and check valid substring **/
        for (int i = 0; i <= str.length() / 2; i++) {
            if (isValidSubString(str.substring(0, i + 1), str)) {
                return true;
            }
        }
        return false;
    }


    private boolean isValidSubString(String subString, String string) {
        StringBuffer sb = new StringBuffer(subString);

        while (true) {


            if (!string.startsWith(sb.toString())) {
                return false;
            }

            sb.append(subString);

            if (sb.length() > string.length()) {
                return false;
            } else if (sb.length() == string.length() && sb.toString().equals(string)) {
                return true;
            }
        }

    }


    private boolean isValidSubString2(String subString, String string, int start, int end) {

        int lengthOfSubString = subString.length();
        int length = end - start;

        if (length % lengthOfSubString != 0) {
            return false;
        }
        int subStringIndex = 0;

        for (int i = start; i < end; i++) {
            if (subString.charAt(subStringIndex) != string.charAt(i)) {
                return false;
            }
            subStringIndex = (subStringIndex + 1) % lengthOfSubString;
        }
        return true;
    }

}


class RepeatedSubstringPatternTest {
    public static void main(String[] args) {

        String str1 = "abab";
        String str2 = "aba";
        String str3 = "abcabcabcabc";
        String str4 = "aabaaba";

        RepeatedSubstringPattern pattern = new RepeatedSubstringPattern();

        System.out.printf("String:%s, ? %b \n", str1, pattern.isPatternExists(str1));
        System.out.printf("String:%s, ? %b \n", str2, pattern.isPatternExists(str2));
        System.out.printf("String:%s, ? %b \n", str3, pattern.isPatternExists(str3));
        System.out.printf("String:%s, ? %b \n", str4, pattern.isPatternExists(str4));


    }
}