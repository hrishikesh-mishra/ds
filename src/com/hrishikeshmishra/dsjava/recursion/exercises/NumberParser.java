package com.hrishikeshmishra.dsjava.recursion.exercises;

/**
 * Created by hrishikesh.mishra
 *
 * R-5.8
 *
 * Describe a recursive algorithm for converting a string of digits into the integer it represents.
 * For example, '13531' represents the integer 13, 531.
 */
public class NumberParser {

    public static void main(String[] args) {
        System.out.println(parse("1234", 0));
    }

    public static int parse(String strNumber, int level){
        if(strNumber.length() == 0) {
            return 0;
        } else {
            int lastChar = getLastCharacter(strNumber);
            int lastDigit = lastChar - '0';

            if(lastDigit <0 || lastDigit >10 ) {
                throw new RuntimeException("Invalid character found, all character should be [0-9]") ;
            }

            return parse(getSubstring(strNumber), level + 1) + lastDigit * (int) Math.pow(10, level);
        }
    }

    public static int getLastCharacter(String str){
        return str.charAt(str.length() -1) ;
    }

    public static String getSubstring(String str){
        return str.substring(0, str.length() - 1);
    }
}
