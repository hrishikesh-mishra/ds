package com.hrishikeshmishra.practices.stack;

/**
 * Problem:
 * Curly brackets Reversals Count Finder
 * :
 * Given a string S consisting only of opening and closing curly brackets '{' and '}'
 * find out the minimum number of reversals required to make a balanced expression.
 * If it cannot be balanced, then print -1.
 * ;
 * Examples
 * }{{}}{{{ - 3
 * {{}}}} - 1
 * {{}{{{}{{}}{{ - -1
 * {{{{}}}} - 0
 * ;
 * ;
 * Algorithm:
 * - Base case: When string length is odd return -1
 * - Take a stack to keep opening and invalid brackets
 * - Iterate string from beginning to end
 * - - If stack.top == '{' and string[i] == '}' then,
 * - - - stack.pop
 * - - Else
 * - - - stack.push(string[i])
 * - Set countOfOpenedBrackets = 0 and countOfClosedBrackets
 * - Iterate till stack is not empty
 * - - If stack.pop == '{' then
 * - - - countOfOpenedBrackets++
 * - - Else
 * - - - countOfClosedBrackets++
 * - Return (Math.ceil(countOfClosedBrackets / 2) + Math.ceil(countOfOpenedBrackets / 2))
 * - Why we need Math.ceil, think for this "}{"
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/curly-brackets-reversals-count-finder/
 */


public class CountTheReversals {

    public static int getCount(String str) {
        /** Base case : when string is empty **/
        if (str.length() == 0) {
            return 0;
        }

        /** Base case : when string is odd length **/
        if (str.length() % 2 != 0) {
            return -1;
        }


        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {

            /** Push opening bracket to stack **/
            if (str.charAt(i) == '{') {
                stack.push('{');
            } else {
                /** Remove from stack if stack top is { and current character is } **/
                if (!stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                } else {
                    /** Else push to stack **/
                    stack.push('}');
                }
            }
        }

        /** At the end of above loop we'll get stack containing invalid bracket **/

        int countOfOpenedBrackets = 0,
                countOfClosedBrackets = 0;

        /** Count opening and closed brackets **/
        while (!stack.isEmpty()) {
            char character = stack.pop();
            if (character == '{') {
                countOfOpenedBrackets++;
            } else {
                countOfClosedBrackets++;
            }
        }

        /** return count **/
        return getCeil(countOfOpenedBrackets, 2) + getCeil(countOfClosedBrackets, 2);
    }


    private static int getCeil(int a, int b) {
        return (int) Math.ceil((double) a / b);
    }
}

class CountTheReversalsTest {
    public static void main(String[] args) {
        String s1 = "}{{}}{{{",
                s2 = "{{}}}}",
                s3 = "{{}{{{}{{}}{{",
                s4 = "{{{{}}}}";

        System.out.println(" Count The Reversals of " + s1 + " : " + CountTheReversals.getCount(s1));
        System.out.println(" Count The Reversals of " + s2 + " : " + CountTheReversals.getCount(s2));
        System.out.println(" Count The Reversals of " + s3 + " : " + CountTheReversals.getCount(s3));
        System.out.println(" Count The Reversals of " + s4 + " : " + CountTheReversals.getCount(s4));
    }
}
