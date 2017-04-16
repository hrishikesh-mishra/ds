package com.hrishikeshmishra.practices.stack;

/**
 * Problem:
 * Evaluation of Postfix Expression
 * ;
 * Algorithm:
 * - Create stack to hold operands and result
 * - Iterate String from start to end
 * - - If currentCharacter is digit then,
 * - - - stack.push(currentCharacter)
 * - - Else
 * - - - int operand1 = stack.pop
 * - - - int operand2 = stack.pop
 * - - - int result = operand1 currentCharacter operand2
 * - - - stack.push(result)
 * - return stack.pop
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/evaluation-of-postfix-expression/
 */
public class PostfixExpressionEvaluation {

    public static int evaluate(String postfixExp) {

        Stack<Integer> stack = new Stack<>();

        /** Iterate all characters of postfix expression one by one **/
        for (int i = 0; i < postfixExp.length(); i++) {
            char character = postfixExp.charAt(i);

            /** If empty then continue **/
            if (isEmpty(character)) {
                continue;
            }

            /** If digit push to stack **/
            if (isDigit(character)) {
                stack.push(getDigit(character));
            } else {
                /** If operator pop two operand, perform operation and push result to stack **/
                int operand1 = stack.pop();
                int operand2 = stack.pop();
                int result = performOperation(operand1, operand2, character);
                stack.push(result);
            }
        }

        /** Pop final result from stack **/
        return stack.pop();
    }

    private static int performOperation(int operand1, int operand2, char operator) {

        switch (operator) {
            case '+':
                return operand2 + operand1;
            case '-':
                return operand2 - operand1;
            case '*':
                return operand2 * operand1;
            case '/':
                return operand2 / operand1;
            default:
                throw new RuntimeException("Invalid operator");
        }
    }

    private static boolean isDigit(char character) {
        return character >= '0' && character <= '9';
    }

    private static boolean isEmpty(char character) {
        return character == ' ';
    }

    private static int getDigit(char character) {
        return character - '0';
    }

}

class PostfixExpressionEvaluationTest {

    public static void main(String[] args) {
        String exp = "2 3 1 * + 9 -";
        int value = PostfixExpressionEvaluation.evaluate(exp);
        System.out.println(exp + " = " + value);
    }
}