package com.hrishikeshmishra.practices.recursion;

import java.util.ArrayList;
import java.util.List;

import static com.hrishikeshmishra.practices.recursion.ParenthesesProblem.generateParentheses;

/**
 * Problem:
 * Parentheses Problem:
 * Generate all valid combination of n pairs of parentheses.
 * ;
 * ;
 * Algorithm:
 * - If opening bracket and closing brackets equal to total parentheses then
 * - - Add characters array to parentheses list
 * - Else
 * - - If opening brackets less than number of pairs then
 * - - - Add '(' to character array
 * - - - recursively call with opening bracket + 1
 * - - If closing brackets less than number of pairs then
 * - - - Add ')' to character array
 * - - - recursively call the closing bracket + 1
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=pD2VNU2x8w8
 * @link http://hrishikeshmishra.com/parentheses-problem/
 */
public class ParenthesesProblem {

    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';

    public static List<String> generateParentheses(int n) {
        char[] parentheses = new char[2 * n];
        List<String> list = new ArrayList<>();
        addParentheses(0, 0, list, parentheses, n, 0);
        return list;
    }

    private static void addParentheses(int openedBracket, int closedBracket,
                                       List<String> list, char[] parentheses,
                                       int pairs, int count) {
        /** When case **/
        if (openedBracket == pairs && closedBracket == pairs) {
            list.add(String.copyValueOf(parentheses));
        } else {

            /** Opening bracket could be larger than closing like (((), its valid **/
            if (openedBracket < pairs) {
                parentheses[count] = OPEN_BRACKET;
                addParentheses(openedBracket + 1, closedBracket, list, parentheses, pairs, count + 1);
            }

            /** But closing bracket must not be larger than opening like ))(, its invalid **/
            if (closedBracket < openedBracket) {
                parentheses[count] = CLOSE_BRACKET;
                addParentheses(openedBracket, closedBracket + 1, list, parentheses, pairs, count + 1);
            }

        }
    }
}

class ParenthesesProblemTest {
    public static void main(String[] args) {
        System.out.println("Parentheses (1): " + generateParentheses(1));
        System.out.println("Parentheses (2): " + generateParentheses(2));
        System.out.println("Parentheses (3): " + generateParentheses(3));
        System.out.println("Parentheses (4): " + generateParentheses(4));
    }
}


