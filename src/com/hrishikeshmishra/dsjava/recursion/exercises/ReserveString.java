package com.hrishikeshmishra.dsjava.recursion.exercises;

/**
 * Created by hrishikesh.mishra on 16/01/16.
 */
public class ReserveString {

    public static void main(String[] args) {
        System.out.println("Reserve abc : " + reverse("abc"));
        System.out.println("Reserve abc : " + reverse("abcd"));
        System.out.println("Reserve a : " + reverse("a"));
        System.out.println("Reserve ab : " + reverse("ab"));
        System.out.println("Reserve pots&pans : " + reverse("pots&pans"));
    }

    public static String reverse(String str){
        return reverse(str, str.length());
    }

    private static String reverse(String str, int index){
        if(index <= 0 ) return "";
        return str.charAt(index -1) + reverse(str, index -1);
    }
}
