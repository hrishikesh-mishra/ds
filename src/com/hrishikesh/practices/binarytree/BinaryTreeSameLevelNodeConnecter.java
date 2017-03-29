package com.hrishikesh.practices.binarytree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import static com.hrishikesh.practices.binarytree.BinaryTreeSameLevelNodeConnecter.*;


/**
 * Problem:
 * Connect Nodes at Same Level
 * Write a function to connect all the adjacent nodes at the same
 * ;
 * Solution:
 * - Use queue for level order traversal with some level seperator
 * ;
 * Algorithm:
 * - Take a queue to travers in level order
 * - And previous node to point previous element of same level
 * - Traverse tree in level order
 * - - node = queue.dequeue
 * - - if previous is not null then
 * - - - previous.next = node
 * - - enqueue node children in  queue
 * - - if level ends then
 * - - - - reset previous to null
 *
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/write-a-function-to-connect-all-the-adjacent-nodes-at-the-same/
 */
public class BinaryTreeSameLevelNodeConnecter {

    public static class BinaryTreeNodeExtended extends BinaryTreeNode<Integer> {

        protected BinaryTreeNodeExtended nextRight;

        public BinaryTreeNodeExtended(Integer data) {
            super(data);
        }

        public BinaryTreeNodeExtended(Integer data, BinaryTreeNode<Integer> left,
                                      BinaryTreeNode<Integer> right) {
            super(data, left, right);
        }

        public BinaryTreeNodeExtended(Integer data, BinaryTreeNode<Integer> left,
                                      BinaryTreeNode<Integer> right, BinaryTreeNodeExtended nextRight) {
            super(data, left, right);
            this.nextRight = nextRight;
        }

        public BinaryTreeNodeExtended getNextRight() {
            return nextRight;
        }

        public void setNextRight(BinaryTreeNodeExtended nextRight) {
            this.nextRight = nextRight;
        }
    }


    public static void connectNodes(BinaryTreeNodeExtended root) {

        /** Base case when root is null **/
        if (Objects.isNull(root)) {
            return;
        }

        Queue<BinaryTreeNodeExtended> queue = new LinkedList<>();
        queue.add(root);

        /** Level separator **/
        queue.add(null);

        BinaryTreeNodeExtended temp, previousNode = null;

        while (!queue.isEmpty()) {
            temp = queue.remove();

            /** Is it a new level **/
            if (!Objects.isNull(temp)) {

                /** Update previous node next right node, only if preiovus node is not null **/
                if (!Objects.isNull(previousNode)) {
                    previousNode.setNextRight(temp);
                }

                /** Update previous node pointer **/
                previousNode = temp;

                /** Push left and right child to queue **/
                addIfNotNull(queue, (BinaryTreeNodeExtended) temp.getLeft());
                addIfNotNull(queue, (BinaryTreeNodeExtended) temp.getRight());
            } else {
                /** Previous node set null for next lev**/
                previousNode = null;

                /** Add marker to next level **/
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }

    }

    public static void addIfNotNull(Queue<BinaryTreeNodeExtended> queue, BinaryTreeNodeExtended node) {
        if (!Objects.isNull(node)) {
            queue.add(node);
        }
    }

    public static void printByLevel(BinaryTreeNodeExtended node) {

        BinaryTreeNodeExtended temp = null;
        while (!Objects.isNull(node)) {

            temp = node;
            while (!Objects.isNull(temp)) {
                System.out.print(temp.getData() + " ");
                temp = temp.getNextRight();
            }
            System.out.println();

            //@// TODO: 28/11/16 This has some limitation
            node = (BinaryTreeNodeExtended) node.getLeft();
        }
    }

}

class BinaryTreeSameLevelNodeConnecterTest {
    public static void main(String[] args) {
        BinaryTreeNodeExtended root = new BinaryTreeNodeExtended(10,
                new BinaryTreeNodeExtended(3,
                        new BinaryTreeNodeExtended(4),
                        new BinaryTreeNodeExtended(1)),
                new BinaryTreeNodeExtended(5,
                        null,
                        new BinaryTreeNodeExtended(2))
        );

        BTPrinter.print(root);
        connectNodes(root);
        System.out.println("Printing level by level : ");
        printByLevel(root);


    }
}