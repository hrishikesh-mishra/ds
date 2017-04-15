package com.hrishikesh.practices.binarytree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Problem:
 * Mirror image of binary tree without Recursion
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/mirror-image-binary-tree-without-recursion/
 */
public class BinaryTreeMirrorWithoutRecursion {

    public static void mirror(BinaryTreeNode<String> root) {

        Queue<BinaryTreeNode<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            BinaryTreeNode<String> node = queue.remove();

            BinaryTreeNode<String> leftChild = node.getLeft();
            BinaryTreeNode<String> rightChild = node.getRight();

            /** Swap children **/
            node.setLeft(rightChild);
            node.setRight(leftChild);

            /** Push to queue **/
            if (!Objects.isNull(leftChild)) {
                queue.add(leftChild);
            }

            if (!Objects.isNull(rightChild)) {
                queue.add(rightChild);
            }
        }
    }

}


class BinaryTreeMirrorWithoutRecursionTest {
    public static void main(String[] args) {

        BinaryTreeNode<String> root1 = new BinaryTreeNode<>("A",
                new BinaryTreeNode<>("B",
                        new BinaryTreeNode<>("D"),
                        new BinaryTreeNode<>("E",
                                null,
                                new BinaryTreeNode<>("H"))),
                new BinaryTreeNode<>("C",
                        new BinaryTreeNode<>("F"),
                        new BinaryTreeNode<>("G")));

        System.out.println("Binary Tree: ");
        BTPrinter.print(root1);

        BinaryTreeMirrorWithoutRecursion.mirror(root1);
        System.out.println("Mirror Tree: ");
        BTPrinter.print(root1);


    }
}
