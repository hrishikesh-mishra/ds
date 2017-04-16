package com.hrishikeshmishra.practices.stack;

/**
 * Problem:
 * Infix to postfix
 * ;
 * ;
 * Algorithm:
 * - Create a stack to operator
 * - Create empty postfix string named postfixStr
 * - Iterate expression string from start to end
 * - If currentCharacter is operand then,
 * - - postfixStr += currentCharacter
 * - Else If currentCharacter is opening bracket then,
 * - - stack.push(currentCharacter)
 * - Else If currentCharacter is closing bracket then,
 * - - Pop stack and add popped character to postfixStr till we find  opening bracket in stack
 * - Else
 * - - If stack.top operator precedence is higher or equal to currentCharacter precedence then
 * - - - postfixStr += stack.pop
 * - - - Repeat till previous condition not break
 * - - stack.push(currentCharacter)
 * - Return postfixStr
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/infix-to-postfix/
 */
public class InfixToPostfix {

    public String createPostfix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            /** if empty then continue **/
            if (infix.charAt(i) == ' ') {
                continue;
            }

            /** If operand then add to output **/
            if (isOperand(infix.charAt(i))) {
                sb.append(infix.charAt(i));

                /** If ( then, push to stack **/
            } else if (isOpenBracket(infix.charAt(i))) {
                stack.push(infix.charAt(i));

                /** If ) pop till (, and add element to output **/
            } else if (isCloseBracket(infix.charAt(i))) {
                while (!stack.isEmpty() && !isOpenBracket(stack.peek())) {
                    sb.append(stack.pop());
                }

                /** If stack is empty before getting open bracket **/
                if (stack.isEmpty()) {
                    throw new RuntimeException("Invalid expression");
                } else {
                    stack.pop();
                }

                /** If operator, pop until the precedence of stack operator less than or equal current operator **/
            } else {
                char currentOperator = infix.charAt(i);
                while (!stack.isEmpty() && getPrecedence(currentOperator) <= getPrecedence(stack.peek())) {
                    sb.append(stack.pop());
                }

                /** Push current operator to stack **/
                stack.push(currentOperator);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    private boolean isCloseBracket(char c) {
        return c == ')';
    }

    private boolean isOpenBracket(char c) {
        return c == '(';
    }


    private boolean isOperand(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private int getPrecedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;

            case '/':
            case '*':
                return 2;

            case '^':
                return 3;

            default:
                return -1;
        }
    }
}

class InfixToPostfixTest {

    public static void main(String[] args) {
        InfixToPostfix converter = new InfixToPostfix();
        String infix1 = "A + B * C + D";
        String infix2 = "(A + B) * (C + D)";
        String infix3 = "A * B + C * D";
        String infix4 = "A + B + C + D";
        String infix5 = "a+b*(c^d-e)^(f+g*h)-i";

        String posfix1 = converter.createPostfix(infix1);
        String posfix2 = converter.createPostfix(infix2);
        String posfix3 = converter.createPostfix(infix3);
        String posfix4 = converter.createPostfix(infix4);
        String posfix5 = converter.createPostfix(infix5);


        System.out.println(infix1 + "   " + posfix1);
        System.out.println(infix2 + "   " + posfix2);
        System.out.println(infix3 + "   " + posfix3);
        System.out.println(infix4 + "   " + posfix4);
        System.out.println(infix5 + "   " + posfix5);

    }

}


