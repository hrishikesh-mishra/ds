package com.hrishikeshmishra.practices.queue;

import java.util.Objects;

import static com.hrishikeshmishra.practices.queue.CompleteBinaryTreeChecker.*;

/**
 * Problem:
 * Check whether a given Binary Tree is Complete or not.
 * ;
 * Complete Binary Tree:
 * ;
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled,
 * and all nodes are as far left as possible.
 * ;
 * Solution:
 * The approach is to do a level order traversal starting from root. In the traversal,
 * once a node is found which is NOT a Full Node, all the following nodes must be leaf nodes.
 * Also, one more thing needs to be checked to handle the below case: If a node has empty left child,
 * then the right child must be empty.
 * ;
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/check-whether-a-given-binary-tree-is-complete-or-not/
 */
public class CompleteBinaryTreeChecker {

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

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

    public static boolean isCompleteBinaryTree(Node root) {

        /** Base case when tree is empty **/
        if (Objects.isNull(root)) {
            return true;
        }

        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);

        /** Level marker **/
        queue.enqueue(null);

        int level = 0;
        int nodesCounter = 0;
        boolean isLeftChildMissing = false;

        while (!queue.isEmpty()) {

            Node node = queue.dequeue();

            /** Node is not emtpy **/
            if (!Objects.isNull(node)) {
                nodesCounter++;

                /** Push left child to queue, if it present **/
                if (!Objects.isNull(node.getLeft())) {
                    queue.enqueue(node.getLeft());
                } else {
                    isLeftChildMissing = true;
                }

                /** Push right child to queue, if it present **/
                if (!Objects.isNull(node.getRight())) {

                    /** In case left child is empty but right child is not. **/
                    if (isLeftChildMissing) {
                        return false;
                    }

                    queue.enqueue(node.getRight());
                }

            } else {

                /** Is it not last level **/
                if (!queue.isEmpty()) {


                    int maxNumberOfNode = (int) Math.pow(2, level);
                    /** Validate number of node at previous level **/
                    if (maxNumberOfNode != nodesCounter) {
                        return false;
                    }

                    /** Push null for next level marker **/
                    queue.enqueue(null);

                    /** Increase level counter **/
                    level++;

                    /** rest node counter for next level **/
                    nodesCounter = 0;

                } else {


                    /** If its last level then no need to validate number of node at this level **/
                    return true;
                }
            }
        }

        /** fallback case **/
        return false;

    }

}


class CompleteBinaryTreeCheckerTest {

    public static void main(String[] args) {
//
//                     1
//                   /   \
//                  2     3
//
        Node root1 = new Node(1,
                new Node(2),
                new Node(3));

//                1
//             /    \
//           2       3
//          /
//         4
        Node root2 = new Node(1,
                new Node(2,
                        new Node(4),
                        null),
                new Node(3));


//        1
//      /    \
//     2      3
//    /  \    /
//   4    5  6
        Node root3 = new Node(1,
                new Node(2,
                        new Node(4),
                        new Node(5)),
                new Node(3,
                        new Node(6),
                        null));

//    1
//      \
//        3
        Node root4 = new Node(1,
                null,
                new Node(3));

//           1
//        /    \
//       2       3
//        \     /  \
//         4   5    6

        Node root5 = new Node(1,
                new Node(2,
                        null,
                        new Node(4)),
                new Node(3,
                        new Node(5),
                        new Node(6)));

//          1
//        /    \
//       2      3
//             /  \
//            4    5

        Node root6 = new Node(1,
                new Node(2),
                new Node(3,
                        new Node(4),
                        new Node(5)));
// 1
        Node root7 = new Node(1);


        System.out.println("Is Tree 1 complete binary tree  : " + CompleteBinaryTreeChecker.isCompleteBinaryTree(root1));
        System.out.println("Is Tree 2 complete binary tree  : " + CompleteBinaryTreeChecker.isCompleteBinaryTree(root2));
        System.out.println("Is Tree 3 complete binary tree  : " + CompleteBinaryTreeChecker.isCompleteBinaryTree(root3));
        System.out.println("Is Tree 4 complete binary tree  : " + CompleteBinaryTreeChecker.isCompleteBinaryTree(root4));
        System.out.println("Is Tree 5 complete bin  ary tree  : " + CompleteBinaryTreeChecker.isCompleteBinaryTree(root5));
        System.out.println("Is Tree 6 complete binary tree  : " + CompleteBinaryTreeChecker.isCompleteBinaryTree(root6));
        System.out.println("Is Tree 7 complete binary tree  : " + CompleteBinaryTreeChecker.isCompleteBinaryTree(root7));

    }
}