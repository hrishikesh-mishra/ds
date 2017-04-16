package com.hrishikeshmishra.ns.tree;

import java.util.Objects;

/**
 * D - Problem:
 * Diameter Of a Binary Tree:
 * A longest path between any two leaves in tree.
 * ( The Path may or may not go through route. )
 * <p>
 * Solution:
 * <p>
 * Recursive Steps:
 * 1. The diameter of T's left subtree
 * 2. The diameter of T's right subtree
 * 3. The longest path between leaves that goes through the root of T
 * (i.e height of T's left subtree + T's right subtree + 1 =  longest path between leaves that goes through the root of T)
 * 4. Max of above three.
 * <p>
 * Created by hrishikesh.mishra
 */
public class DiameterOfBinaryTree {

    public <E> int findDiameter(BinaryTreeNode<E> node) {
        if (Objects.isNull(node)) return 0;

        int diameterOfLeftSubtree = findDiameter(node.getLeft());
        int diameterOfRightSubtree = findDiameter(node.getRight());
        int routeThroughRoot = getHeight(node.getLeft()) + getHeight(node.getRight()) + 1;
        return Math.max(routeThroughRoot, Math.max(diameterOfLeftSubtree, diameterOfRightSubtree));

    }

    private <E> int getHeight(BinaryTreeNode<E> node) {
        if (Objects.isNull(node)) return 0;
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }
}

class DiameterOfBinaryTreeTest {
    public static void main(String[] args) {
        BinaryTreeNode<String> root1 = new BinaryTreeNode<>("1",
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
                new BinaryTreeNode<>("3")
        );


        BinaryTreeNode<String> root2 = new BinaryTreeNode<>("1",
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
        DiameterOfBinaryTree diameterFinder = new DiameterOfBinaryTree();

        printer.print(root1);
        System.out.println("Diameter is : " + diameterFinder.findDiameter(root1));

        System.out.println("\n\n");
        printer.print(root2);
        System.out.println("Diameter is : " + diameterFinder.findDiameter(root2));

    }

}