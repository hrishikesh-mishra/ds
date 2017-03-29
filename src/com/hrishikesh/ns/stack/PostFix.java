package com.hrishikesh.ns.stack;

/**
 *
 * Evaluate Postfix notation
 *
 * Created by hrishikesh.mishra
 */
public class PostFix {

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";

    public int evaluate(String[] tokens){
        Stack<Integer> stack = new LinkedStack<>();

        int operand1,operand2;
        for(String token : tokens){
            switch (token) {
                case PLUS:
                    stack.push(stack.pop() + stack.pop());
                    break;
                case MINUS:
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    stack.push(operand2 - operand1);
                    break;
                case MULTIPLY:
                    stack.push(stack.pop() * stack.pop());
                    break;
                case DIVIDE:
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    stack.push(operand2 / operand1);
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}


class PostFixTest {

    public static void main(String[] args) {
        PostFix postFix  = new PostFix();
        System.out.println("1 2 3 * + 5 -  :  " + postFix.evaluate(new String[]{"1", "2", "3", "*", "+", "5", "-"}));
    }
}