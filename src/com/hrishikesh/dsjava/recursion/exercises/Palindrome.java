package com.hrishikesh.dsjava.recursion.exercises;

/**
 * C-5.18
 *
 * Write a short recursive Java method that determines if a string s is a palindrome,
 * that is, it is equal to its reverse.
 * Examples of palindromes include 'racecar' and 'gohangasalamiimalasagnahog'.
 *
 * Created by hrishikesh.mishra on 16/01/16.
 */
public class Palindrome {

    public static void main(String[] args) {
        System.out.println("Is Palindrom: racecar : " + check("racecar") );
        System.out.println("Is Palindrom: gohangasalamiimalasagnahog : " + check("gohangasalamiimalasagnahog") );
        System.out.println("Is Palindrom: a : " + check("a") );
        System.out.println("Is Palindrom: \"\" : " + check("") );
        System.out.println("Is Palindrom: aa : " + check("aa") );
        System.out.println("Is Palindrom: ab : " + check("ab") );
        System.out.println("Is Palindrom: abc : " + check("abc") );
        System.out.println("Is Palindrom: aba : " + check("aba") );
    }

    public static boolean check(String str){
        if(str.length() == 0 || str.length() ==1) return true;
        else return check(str, 0, str.length() - 1);
    }

    private static boolean check(String str, int start, int end){
        if(start >= end) return true;
        return str.charAt(start) == str.charAt(end) &&
                    check(str, start + 1, end - 1);
    }

}

