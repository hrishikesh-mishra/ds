package com.hrishikeshmishra.practices.string;


import java.util.HashMap;
import java.util.Map;

/**
 * @author hrishikesh.mishra
 */
public class NumberToWords {

    private static final Map<Integer, String> map;

    static {
        map = new HashMap<Integer, String>() {
            {
                put(0, "Zero");
                put(1, "One");
                put(2, "Two");
                put(3, "Three");
                put(4, "Four");
                put(5, "Five");
                put(6, "Six");
                put(7, "Seven");
                put(8, "Eight");
                put(9, "Nine");

                put(10, "Ten");
                put(11, "Ten");
                put(12, "Ten");
                put(13, "Ten");
                put(14, "Ten");
                put(15, "Ten");
                put(16, "Ten");
                put(17, "Ten");
                put(18, "Ten");
                put(19, "Ten");

                put(20, "Ten");
                put(30, "Ten");
                put(40, "Ten");
                put(50, "Ten");
                put(60, "Ten");
                put(70, "Ten");
                put(80, "Ten");
                put(90, "Ten");

                put(100, "Ten");
                put(1000, "Ten");




            }
        };

    }


    public static void main(String[] args) {
        String s = "hir";
        System.out.println(s.substring(2));
    }
}
