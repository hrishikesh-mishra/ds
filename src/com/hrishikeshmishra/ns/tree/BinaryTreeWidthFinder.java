package com.hrishikeshmishra.ns.tree;

import com.hrishikeshmishra.ns.queue.LinkedQueue;
import com.hrishikeshmishra.ns.queue.Queue;

import java.util.Objects;

/**
 * <p>
 *     Problem:
 *     Find width of binary tree.
 *     The width of a tree is maximum number of nodes at any level (or depth) in tree.
 *
 *     Solution
 *     1. Recursive
 *     1. BFS using queue
 *
 *  @link http://hrishikeshmishra.com/find-width-of-binary-tree/
 * </p>
 * Created by hrishikesh.mishra
 */
public class BinaryTreeWidthFinder {

    /**
     * <p>
     *  Recursive solution fo finding widht of each node.
     * </p>
     *
     * @param root
     * @param <E>
     * @return
     */
    public  <E> int getWidth(BinaryTreeNode<E> root){
        if(Objects.isNull(root)) return 0;

        int depth = getDepth(root);
        int width = 0;
        for(int i = 0; i <= depth; i++ )
            width = Math.max(getWidth(root, i), width);
        return width;
    }

    /**
     * <p>
     *     Second approach based on BFS
     * </p>
     *
     * @param root
     * @param <E>
     * @return
     */
    public  <E> int getWidth2(BinaryTreeNode<E> root){
        if(Objects.isNull(root)) return 0;

        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        queue.enQueue(root); queue.enQueue(null);

        int maxWidth = 0 , levelWidth = 0;
        BinaryTreeNode<E> node;

        while(!queue.isEmpty()){
            node = queue.deQueue();
            if(!Objects.isNull(node)){
                levelWidth ++;
                if(!Objects.isNull(node.getLeft())) queue.enQueue(node.getLeft());
                if(!Objects.isNull(node.getRight())) queue.enQueue(node.getRight());
            } else {
                if(!queue.isEmpty()){
                    maxWidth = Math.max(levelWidth, maxWidth);
                    levelWidth = 0;
                    queue.enQueue(null);
                }
            }
        }

        return maxWidth;
    }

    public <E> int getWidth(BinaryTreeNode<E> node, int depth){
        if(Objects.isNull(node)) return 0;
        if(depth == 0) return 1;
        return getWidth(node.getLeft(), depth - 1 ) + getWidth(node.getRight(), depth - 1 );
    }

    private <E> int getDepth(BinaryTreeNode<E> node){
        if(Objects.isNull(node)) return 0;
        return 1 + Math.max(getDepth(node.getLeft()), getDepth(node.getRight()));
    }
}


class BinaryTreeWidthFinderTest{
    public static void main(String[] args) {
        BinaryTreeNode<String> root1  = new BinaryTreeNode<>("1",
                new BinaryTreeNode<>("2",
                        new BinaryTreeNode<>("4",
                                new BinaryTreeNode<>("8"),
                                null
                        ),
                        new BinaryTreeNode<>("5",
                                new BinaryTreeNode<>("6",
                                        null,
                                        new BinaryTreeNode<>("7")

                                ),
                                null
                        )
                ),
                new BinaryTreeNode<>("3",
                        new BinaryTreeNode<>("6"),
                        new BinaryTreeNode<>("7")
                )
        );



        BinaryTreeNode<String> root2  = new BinaryTreeNode<>("1",
                new BinaryTreeNode<>("2",
                        new BinaryTreeNode<>("4",
                                new BinaryTreeNode<>("6"),
                                new BinaryTreeNode<>("7")
                        ),
                        null

                ),
                new BinaryTreeNode<>("3",
                        null,
                        new BinaryTreeNode<>("5"))
        );


        BinaryTreePrinter printer = new BinaryTreePrinter();
        BinaryTreeWidthFinder widthFinder = new BinaryTreeWidthFinder();

        printer.print(root1);
        System.out.println("Width is : " + widthFinder.getWidth(root1));
        System.out.println("Width is (non-recursive): " + widthFinder.getWidth2(root1));


        printer.print(root2);
        System.out.println("\n\nWidth is : " + widthFinder.getWidth(root2));
        System.out.println("Width is (non-recursive): " + widthFinder.getWidth2(root2));

    }
}