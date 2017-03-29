package com.hrishikesh.practices.binarytree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Print node a binary tree given height
 *
 * @author hrishikesh.mishra
 */
public class BinaryTreePrintNodeOfHeight {

    public static void print(BinaryTreeNode<Integer> root, int height) {

        if (Objects.isNull(root)) {
            return;
        }

        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();


        int currentHeight = 0;
        queue.add(root);

        queue.add(null);

        while (!queue.isEmpty()) {

            BinaryTreeNode<Integer> node = queue.remove();

            if (!Objects.isNull(node)) {

                /** When height is equal to current height **/
                if (height == currentHeight) {
                    System.out.print(node.getData() + " ");
                }

                /** Pushing left child to queue **/
                if (!Objects.isNull(node.getLeft())) {
                    queue.add(node.getLeft());
                }

                /** Pushing right child to queue **/
                if (!Objects.isNull(node.getRight())) {
                    queue.add(node.getRight());
                }

            } else {

                if (!queue.isEmpty()) {
                    /** Next height marker **/
                    queue.add(null);
                    currentHeight++;
                }
            }

            /** Don't process higher than current heights  **/
            if (currentHeight > height) {
                break;
            }
        }

    }
}


class BinaryTreePrintNodeOfHeightTest {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4,
                                new BinaryTreeNode<>(8),
                                null
                        ),
                        new BinaryTreeNode<>(5,
                                new BinaryTreeNode<>(6,
                                        null,
                                        new BinaryTreeNode<>(7)

                                ),
                                null
                        )
                ),
                new BinaryTreeNode<>(3)
        );


        BTPrinter.print(root);

        BinaryTreePrintNodeOfHeight.print(root, 2);

    }
}