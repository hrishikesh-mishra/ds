package com.hrishikeshmishra.practices.stack;

import java.util.Objects;

/**
 * Problem:
 * PreOrder to PostOrder
 * Given an array representing preOrder traversal of BST, print its postOrder traversal.
 * ;
 * Solution:
 * - First create BST from give preOrder array
 * - Second traverse the traverse create BST in post order
 * - The main of this problem is creation of BST from preorder array
 * - That we can do by recursive and non recursive ways
 * ;
 * ;
 * Recursive Algorithm:
 * - Recursive function with argument - array, start index and end index
 * - - if start > end then return null
 * - - Set root = Root(array[start])
 * - - Find position i in array from start to end index such that array[i] > array[start]
 * - - Set leftChild = Recursive Call (array, start + 1, i-1)
 * - - Set rightChild = Recursive Call (array, i, end)
 * - - Update root left and right children
 * - - Return root
 * ;
 * ;
 * ;
 * Non-Recursive Algorithm:
 * - Create root_node with array[0]
 * - Create stack to hold smaller node
 * - Push array[0] in stack
 * -  Iterate array from index 1 to N
 * - - Create tree_node with array [i]
 * - - Set temp = null
 * - - If stack.top < array[i] and stack is not empty then.
 * - - - temp = stack.pop
 * - - If temp == null then,
 * - - - temp = stack.top
 * - - - temp.left = tree_node
 * - - - temp = temp.left
 * - - else
 * - - - temp.right = tree_node
 * - - - temp = temp.right
 * - - stack.push(temp)
 * - return root_node
 *
 * @author hrishikesh.mishra
 * @link https://www.youtube.com/watch?v=0QOtVxTVj4w
 * @link http://hrishikeshmishra.com/bst-preorder-to-postorder/
 */
public class BSTPreOrderToPostOrder {

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node(" + data + ')';
        }
    }

    public static Node constructNonRecursive(int[] preOrder) {
        /** Base case when no element in array **/
        if (preOrder.length == 0) {
            return null;
        }

        /** Create a root
         * **/
        Node root = new Node(preOrder[0], null, null);
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        /** Iterate all element one by one **/
        for (int i = 1; i < preOrder.length; i++) {

            /** Get current element from array **/
            int currentElement = preOrder[i];

            /** Temp node pointer to hold current position **/
            Node temp = null;

            /** Find a position for current element, by popping all higher element **/
            while (!stack.isEmpty() && currentElement > stack.peek().getData()) {
                temp = stack.pop();
            }


            if (!Objects.isNull(temp)) {
                temp.setRight(new Node(currentElement, null, null));
                temp = temp.getRight();
            } else {
                temp = stack.peek();
                temp.setLeft(new Node(currentElement, null, null));
                temp = temp.getLeft();
            }
            stack.push(temp);
        }

        return root;
    }

    public static Node constructRecursive(int[] preOrder, int startIndex, int endIndex) {

        /** Base case when no element in array **/
        if (preOrder.length == 0) {
            return null;
        }

        /** When startIndex crossed endIndex **/
        if (startIndex > endIndex) {
            return null;
        }

        int lastLeftChildIndex = findLastLeftIndex(preOrder, startIndex, endIndex);

        Node node = new Node(preOrder[startIndex],
                constructRecursive(preOrder, startIndex + 1, lastLeftChildIndex - 1),
                constructRecursive(preOrder, lastLeftChildIndex, endIndex)
        );
        return node;
    }

    private static int findLastLeftIndex(int[] preOrder, int startIndex, int endIndex) {
        int rootData = preOrder[startIndex];
        int lastLeftChildIndex = startIndex + 1;

        while (lastLeftChildIndex <= endIndex &&
                rootData >= preOrder[lastLeftChildIndex]
                ) {
            lastLeftChildIndex++;
        }

        return lastLeftChildIndex;
    }

    public static void printPostOrder(Node root) {

        if (Objects.isNull(root)) {
            return;
        }
        printPostOrder(root.getLeft());
        printPostOrder(root.getRight());
        System.out.print(root.getData() + " ");
    }
}

class BSTPreOrderToPostOrderTest {

    public static void main(String[] args) {
        int[] preOrder1 = {40, 30, 35, 80, 100};
        BSTPreOrderToPostOrder.Node root1 = BSTPreOrderToPostOrder.constructRecursive(preOrder1, 0, preOrder1.length - 1);
        BSTPreOrderToPostOrder.printPostOrder(root1);
        System.out.println();
        BSTPreOrderToPostOrder.Node root11 = BSTPreOrderToPostOrder.constructNonRecursive(preOrder1);
        BSTPreOrderToPostOrder.printPostOrder(root11);


        System.out.println("\n");
        int[] preOrder2 = {10, 8, 4, 9, 15, 11};
        BSTPreOrderToPostOrder.Node root2 = BSTPreOrderToPostOrder.constructRecursive(preOrder2, 0, preOrder2.length - 1);
        BSTPreOrderToPostOrder.printPostOrder(root2);
        System.out.println();
        BSTPreOrderToPostOrder.Node root21 = BSTPreOrderToPostOrder.constructNonRecursive(preOrder2);
        BSTPreOrderToPostOrder.printPostOrder(root21);


        System.out.println("\n");
        int[] preOrder3 = {40, 30, 32, 35, 80, 90, 100, 120};
        BSTPreOrderToPostOrder.Node root3 = BSTPreOrderToPostOrder.constructRecursive(preOrder3, 0, preOrder3.length - 1);
        BSTPreOrderToPostOrder.printPostOrder(root3);
        System.out.println();
        BSTPreOrderToPostOrder.Node root31 = BSTPreOrderToPostOrder.constructNonRecursive(preOrder3);
        BSTPreOrderToPostOrder.printPostOrder(root31);

    }
}

