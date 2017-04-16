package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Diameter of a Binary Tree
 * The diameter of a tree (sometimes called the width) is the number of
 * nodes on the longest path between two leaves in the tree.
 * ;
 * Solution:
 * The diameter of a tree T is the largest of the following quantities:
 * - the diameter of T’s left subtree
 * - the diameter of T’s right subtree
 * - the longest path between leaves that goes through the root of T
 * ;
 * Time Complexity: O(n^2)
 * ;
 * ;
 * ;
 * Algorithm:
 * - Traverse tree in PostOrder
 * - - If node is null then
 * - - - return 0
 * - - diameterOfLeftSubTree = recursively call with node.left
 * - - diameterOfRightSubTree = recursively call with node.right
 * - - longestPathBetweenLeaves = heightOfLeftSubTree + heightOfRightSubTree + 1
 * - - return max (diameterOfLeftSubTree, diameterOfRightSubTree, longestPathBetweenLeaves)
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/diameter-of-a-binary-tree-2/
 * @video https://www.youtube.com/watch?v=i9nVJDr4HmA
 */
public class BinaryTreeDiameter {


    public static int calculateDiameter(BinaryTreeNode<Integer> node) {

        /** When node is empty **/
        if (Objects.isNull(node)) {
            return 0;
        }

        /** Diameter of left and right sub tree **/
        int diameterOfLeftSubTree = calculateDiameter(node.getLeft()),
                diameterOfRightSubTree = calculateDiameter(node.getRight());

        int heightOfLeftSubTree = getHeight(node.getLeft()),
                heightOfRightSubTree = getHeight(node.getRight());

        /** the longest path between leaves that goes through the root of T **/
        int longestPathBetweenLeaves = heightOfLeftSubTree + heightOfRightSubTree + 1;


        /** Max of above three things **/
        return Math.max(
                longestPathBetweenLeaves,
                Math.max(
                        diameterOfRightSubTree,
                        diameterOfLeftSubTree
                )
        );

    }

    private static int getHeight(BinaryTreeNode<Integer> root) {
        return getHeight(root, 0);
    }

    private static int getHeight(BinaryTreeNode<Integer> node, int height) {
        if (Objects.isNull(node)) {
            return 0;
        }
        return Math.max(getHeight(node.getLeft(), height),
                getHeight(node.getRight(), height))
                + 1;

    }
}


class BinaryTreeDiameterTest {
    public static void main(String[] args) {

        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(1,
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


        BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4,
                                new BinaryTreeNode<>(6),
                                new BinaryTreeNode<>(7)
                        ),
                        null

                ),
                new BinaryTreeNode<>(3,
                        null,
                        new BinaryTreeNode<>(5))
        );

        BTPrinter.print(root1);
        System.out.println("Diameter : " + BinaryTreeDiameter.calculateDiameter(root1));

        BTPrinter.print(root2);
        System.out.println("Diameter : " + BinaryTreeDiameter.calculateDiameter(root2));
    }
}

