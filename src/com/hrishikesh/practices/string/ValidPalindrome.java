package com.hrishikesh.practices.string;

import static com.hrishikesh.practices.string.ValidPalindrome.isPalindrome;

/**
 * Problem:
 * Valid Palindrome
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/valid-palindrome/
 */
public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        /** Base case : When string is null **/
        if (s == null) {
            return false;
        }

        /** Get only alphanumeric characters from string **/
        String alphaNumericString = getOnlyAlphaNu(s);

        /** Check for valid palindrome **/
        return isPalindromeHelper(alphaNumericString.toUpperCase());
    }


    private static boolean isPalindromeHelper(String s) {
        for (int left = 0, right = s.length() - 1; left < right; left++, right--) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
        }
        return true;
    }


    private static String getOnlyAlphaNu(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c) || Character.isDigit(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}


class ValidPalindromeTest {
    public static void main(String[] args) {

        String s1 = "";
        String s2 = "A man, a plan, a canal: Panama";
        String s3 = "race a car";

        System.out.printf("String: %s, is palindrome: %b\n ", s1,  isPalindrome(s1));
        System.out.printf("String: %s, is palindrome: %b\n ", s2,  isPalindrome(s2));
        System.out.printf("String: %s, is palindrome: %b\n ", s3,  isPalindrome(s3));
    }
}