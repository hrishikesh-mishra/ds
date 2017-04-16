package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Evaluate expression tree.
 * Given a simple expression tree, which is also a full binary tree consisting of
 * basic binary operators i.e., + , – ,* and / and some integers.
 * ;
 * ;
 * Algorithm:
 * - Traverse Given Binary Tree in PostOrder
 * - If node is null then
 * - - return 0
 * - If node is leaf node then
 * - - return node.data
 * - leftValue = recursively call left substree with node.left
 * - rightValue = recursively call right substree with node.righ
 * - value = perform binary arithmetic operation on leftValue and rightValue with using node.data
 * - return value
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/evaluate-expression-tree/
 */
public class EvaluateExpressionTree {

    public static int evalTree(BinaryTreeNode<String> root) {
        /** Base case : When root is empty **/
        if (Objects.isNull(root)) {
            return 0;
        }

        /** When node is leaf node **/
        if (isLeafNode(root)) {
            return getIntegerValue(root.getData());
        }

        /** Evaluate left and right subtree recursively **/
        int leftSubTreeValue = evalTree(root.getLeft()),
                rightSubTreeValue = evalTree(root.getRight());

        /** Perform operation on left and right subtree **/
        return performOperation(root.getData(), leftSubTreeValue, rightSubTreeValue);
    }

    private static boolean isLeafNode(BinaryTreeNode<String> node) {
        /** If both children are null then its leaf node **/
        return Objects.isNull(node.getLeft()) &&
                Objects.isNull(node.getRight());

    }

    private static int getIntegerValue(String data) {
        /** Convert String to Integer **/
        return Integer.parseInt(data);
    }

    private static int performOperation(String operator, int operand1, int operand2) {
        /** Perform arithmetic operation **/
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                throw new UnknownError("Unknown operator " + operator);
        }
    }
}

class EvaluateExpressionTreeTest {
    public static void main(String[] args) {
        BinaryTreeNode<String> root = new BinaryTreeNode<>("+",
                new BinaryTreeNode<>("+",
                        new BinaryTreeNode<>("30"),
                        new BinaryTreeNode<>("*",
                                new BinaryTreeNode<>("10"),
                                new BinaryTreeNode<>("20"))),
                new BinaryTreeNode<>("10"));

        BTPrinter.print(root);

        System.out.println("Value is : " + EvaluateExpressionTree.evalTree(root));
    }
}
