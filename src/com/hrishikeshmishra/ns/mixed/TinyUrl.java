package com.hrishikeshmishra.ns.mixed;

/**
 * Problem:
 * Integer to character and vice-versa
 * Implement two method that used in tiny url generation and parsing, first method will convert string to number
 * and second one is convert same number to string.
 * ;
 * ;
 * Number to String Algorithm:
 * - Set char_base = 26
 * - Set char_map = "abc...zABC..Z01..9"
 * - Create output_string
 * - Iterate till number is greater than zero
 * - - add <code>output_string += char_map.charAg(number % char_map)</code>
 * - reverse output_string
 * - return output_string
 * ;
 * ;
 * String to Number Algorithm:
 * - Set char_base = 26
 * - Set char_map = "abc...zABC..Z01..9"
 * - Set number = 0
 * - Iterate from 0 to string.length
 * - - number += string.charAt(i) * char_base ^ (string.length - (index + 1))
 * - Return number
 *
 * @author hrishikesh.mishra
 *         http://hrishikeshmishra.com/tinyurl-bidirectional-function/
 */
public class TinyUrl {

    private final String characterMap = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int charBase = characterMap.length();

    public String covertToCharacter(int num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            sb.append(characterMap.charAt(num % charBase));
            num /= charBase;
        }

        return sb.reverse().toString();
    }

    public int covertToInteger(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++)
            num += characterMap.indexOf(str.charAt(i)) * Math.pow(charBase, (str.length() - (i + 1)));

        return num;
    }
}

class TinyUrlTest {

    public static void main(String[] args) {
        TinyUrl tinyUrl = new TinyUrl();
        int num = 122312215;
        String url = tinyUrl.covertToCharacter(num);
        System.out.println("Tiny url:  " + url);
        System.out.println("Id: " + tinyUrl.covertToInteger(url));
    }
}
