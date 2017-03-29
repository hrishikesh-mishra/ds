package com.hrishikesh.ns.tree;

import com.hrishikesh.ns.queue.LinkedQueue;
import com.hrishikesh.ns.queue.Queue;

import java.util.Objects;

/**
 * <p>
 *     Generate mirror tree of binary tree.
 *
 *     Mirror of a tree is another tree with left and right children
 *     of all non-leaf nodes are interchanged.
 *
 *     @link http://hrishikeshmishra.com/generate-mirror-tree-of-binary-tree/
 *
 * </p>
 * Created by hrishikesh.mishra
 */
public class BinaryTreeMirrorTreeGenerator {

    public <E> BinaryTreeNode<E> getMirrorTree(BinaryTreeNode<E> root){
        if(Objects.isNull(root)) return null;

        Queue<BinaryTreeNode<E>> queue = new LinkedQueue<>();
        queue.enQueue(root);

        while (!queue.isEmpty()){
            BinaryTreeNode<E> node = queue.deQueue();
            BinaryTreeNode<E>  leftChild = node.getLeft(),
                               rightChild = node.getRight(),
                               tempNode = node.getLeft();

            node.setLeft(rightChild); node.setRight(tempNode);
            if(!Objects.isNull(node.getLeft())) queue.enQueue(node.getLeft());
            if(!Objects.isNull(node.getRight())) queue.enQueue(node.getRight());
        }
        return root;
    }

    public <E> BinaryTreeNode<E> getMirrorTreeRecursive(BinaryTreeNode<E> node){
        if(Objects.isNull(node)) return node;

        getMirrorTreeRecursive(node.getLeft());
        getMirrorTreeRecursive(node.getRight());

        BinaryTreeNode<E> tempNode = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(tempNode);
        return node;
    }
}


class BinaryTreeMirrorTreeGeneratorTest {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root1  = new BinaryTreeNode<>(1,
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

        BinaryTreePrinter printer = new BinaryTreePrinter();
        BinaryTreeMirrorTreeGenerator mirrorTreeGenerator = new BinaryTreeMirrorTreeGenerator();

        printer.print(root1);

        System.out.println("\n\n\nMirror Tree:");
        mirrorTreeGenerator.getMirrorTree(root1);

        printer.print(root1);

        System.out.println("\n\n\nMirror Tree (Recursive):");
        mirrorTreeGenerator.getMirrorTreeRecursive(root1);

        printer.print(root1);
    }
}

