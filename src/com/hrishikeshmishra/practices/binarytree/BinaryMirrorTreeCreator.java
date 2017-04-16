package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Mirror Tree
 * Given a Binary Tree, convert it into its mirror
 * ;
 * ;
 * Algorithm:
 * - Traverse tree in PreOrder
 * - - If node is null then
 * - - - return null
 * - - Create mirror_node with original_node.data
 * - - mirror_node.left = recursively call with original_node.right
 * - - mirror_node.right = recursively call with original_node.left
 * - - return mirror_node
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/create-mirror-of-binary-tree/
 */
public class BinaryMirrorTreeCreator {

    public static BinaryTreeNode<Integer> createMirrorTree(BinaryTreeNode<Integer> node) {
        if (Objects.isNull(node)) {
            return null;
        }

        BinaryTreeNode<Integer> left = createMirrorTree(node.getLeft());
        BinaryTreeNode<Integer> right = createMirrorTree(node.getRight());
        node.setRight(left);
        node.setLeft(right);
        return node;
    }
}


class BinaryMirrorTreeCreatorTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(3),
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(5),
                        new BinaryTreeNode<>(4)));

        BTPrinter.print(root);
        root = BinaryMirrorTreeCreator.createMirrorTree(root);
        System.out.println("Mirror Tree: ");
        BTPrinter.print(root);
    }
}