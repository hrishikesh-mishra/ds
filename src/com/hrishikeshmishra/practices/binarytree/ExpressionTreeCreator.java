package com.hrishikeshmishra.practices.binarytree;

import java.util.Stack;

/**
 * Problem:
 * Create Expression Tree
 * Expression tree is a binary tree in which each internal node corresponds to operator and
 * each leaf node corresponds to operand.
 * ;
 * Solution:
 * - Fist convert infix to postfix
 * - Iterate postfix expression with stack
 * ;
 * ;
 * Algorithm:
 * - Create stack
 * - Iterate expression string from start to end
 * - - If currentCharacter is operand then
 * - - - Create Binary Tree Node with this operand
 * - - - stack.push(node)
 * - - Else
 * - - - operand1 = stack.pop
 * - - - operand2 = stack.pop
 * - - - create node with currentCharacter
 * - - - node.left = operand2
 * - - - node.right = operand1
 * - - -stack.push(node)
 *
 * @author hrishikesh.mishra
 * @link https://en.wikipedia.org/wiki/Binary_expression_tree
 * @link https://www.youtube.com/watch?v=nVcGsgsOlGU
 * @link http://hrishikeshmishra.com/create-expression-tree/
 */
public class ExpressionTreeCreator {

    public static BinaryTreeNode<Character> createTree(String infixExpression) {
        BinaryTreeNode<Character> temp, left, right;

        /** Convert infix to post fix **/
        String postfixExpression = createPostfix(infixExpression);

        /** Stack to hold binary tree node **/
        Stack<BinaryTreeNode<Character>> stack = new Stack<>();

        /** Iterate characters one by one  **/
        for (int i = 0; i < postfixExpression.length(); i++) {

            /** Get current character **/
            char c = postfixExpression.charAt(i);

            /** If current character is operand then push to stack **/
            if (isOperand(c)) {
                temp = new BinaryTreeNode<>(c);
                stack.push(temp);
            } else {

                /** If current character is operator then pop two operand from stack and create BT node **/
                right = stack.pop();
                left = stack.pop();
                temp = new BinaryTreeNode<>(c, left, right);

                /** Push new node back to stack **/
                stack.push(temp);
            }
        }

        /** Return root of tree that is on top of stack **/
        return stack.pop();
    }

    private static String createPostfix(String infixExpression) {

        /** String Builder to hold characters **/
        StringBuilder sb = new StringBuilder(infixExpression.length());

        /** Stack to hold operator **/
        Stack<Character> stack = new Stack<>();

        /** Iterate infix expression character one by one from left to right **/
        for (int i = 0; i < infixExpression.length(); i++) {

            /** Get current character **/
            char c = infixExpression.charAt(i);

            /** If current character is empty **/
            if (c == ' ') {
                continue;
            } else if (isOperand(c)) {
                /** If current char is operand then push to stack **/
                sb.append(c);
            } else if (c == '(') {
                /** If current char is opening bracket, then push to stack  **/
                stack.push('(');
            } else if (c == ')') {
                /** If current char is closing bracket then, pop all till top is ) **/
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }

                /** flush open bracket **/
                stack.pop();
            } else {

                /** Pop all lower precedence operator from stack and to output string  **/
                //@// TODO: 26/11/16 this can create problem with brackets comes inside another bracket
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                    sb.append(stack.pop());
                }

                /** Push current character to stack **/
                stack.push(c);
            }
        }

        /** Flush all operators from stack **/
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    private static boolean isOperand(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z');
    }

    private static int getPrecedence(char operator) {
        switch (operator) {
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

class ExpressionTreeCreatorTest {
    public static void main(String[] args) {

        String infix1 = "A + B * C + D";
        String infix2 = "(A + B) * (C + D)";
        String infix3 = "A * B + C * D";
        String infix4 = "A + B + C + D";
        String infix5 = "a+b*(c^d-e)^(f+g*h)-i";

        System.out.println("Infix: " + infix1);
        BinaryTreeNode<Character> root1 = ExpressionTreeCreator.createTree(infix1);
        System.out.println("Expression Tree ");
        BTPrinter.print(root1);

        System.out.println("\nInfix: " + infix2);
        BinaryTreeNode<Character> root2 = ExpressionTreeCreator.createTree(infix2);
        System.out.println("Expression Tree ");
        BTPrinter.print(root2);

        System.out.println("\nInfix: " + infix3);
        BinaryTreeNode<Character> root3 = ExpressionTreeCreator.createTree(infix3);
        System.out.println("Expression Tree ");
        BTPrinter.print(root3);

        System.out.println("\nInfix: " + infix4);
        BinaryTreeNode<Character> root4 = ExpressionTreeCreator.createTree(infix4);
        System.out.println("Expression Tree ");
        BTPrinter.print(root4);

        System.out.println("\nInfix: " + infix5);
        BinaryTreeNode<Character> root5 = ExpressionTreeCreator.createTree(infix5);
        System.out.println("Expression Tree ");
        BTPrinter.print(root5);

    }
}


