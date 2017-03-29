package com.hrishikesh.practices.dynamicprogramming;

import static com.hrishikesh.practices.dynamicprogramming.BooleanExpression.countWays;

/**
 * Problem:
 * Boolean Expression:
 * A boolean expression could have following symbols:
 * - 0 => false
 * - 1 => true
 * - & => and
 * - | => or
 * - ^ => xor
 * and desired result.
 * Implement a function to count the number of ways of parenthesizing the expression such that
 * it evaluates to desired result.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/boolean-expression/
 */
public class BooleanExpression {

    private static final char XOR = '^';
    private static final char OR = '|';
    private static final char AND = '&';

    public static int countWays(String expression, boolean result) {
        if (expression.length() == 0) {
            return 0;
        }

        if (expression.length() == 1) {
            return strToBool(expression) == result ? 1 : 0;
        }

        int ways = 0;
        for (int i = 1; i < expression.length(); i += 2) {

            char character = expression.charAt(i);

            String left = expression.substring(0, i);
            String right = expression.substring(i + 1, expression.length());

            int leftTrue = countWays(left, true);
            int leftFalse = countWays(left, false);

            int rightTrue = countWays(right, true);
            int rightFalse = countWays(right, false);

            int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);

            int totalTrue = 0;
            if (character == XOR) {
                totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
            } else if (character == AND) {
                totalTrue = leftTrue * rightTrue;
            } else if (character == OR) {
                totalTrue = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
            }

            int subWays = result ? totalTrue : total - totalTrue;
            ways += subWays;

        }

        return ways;

    }

    private static boolean strToBool(String expression) {
        return expression.equals("1");

    }
}

class BooleanExpressionTest {
    public static void main(String[] args) {
        String expression = "1^0|0|1";
        System.out.println(expression + " : ways = " + countWays(expression, false));
    }
}