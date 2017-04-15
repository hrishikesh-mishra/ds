package com.hrishikesh.practices.binarytree;

import java.util.Objects;

/**
 * Problem:
 * Binary Tree Distance between Two Nodes
 * ;
 * Find the Distance between Two Nodes of a Binary Tree.
 *
 * @author hrishikesh.mishra
 */
public class BinaryTreeDistanceBetweenTwoNodes {

    public static int find(BinaryTreeNode<String> root, String node1, String node2) {

        /**
         *
         * ---------------------- IMPORTANT -----------------------------
         * THIS ALGORITHM ASSUMES THAT BOTH SEARCH NODE PRESENT IN TREE
         * --------------------------------------------------------------
         *
         **/

        BinaryTreeNode<String> lca = lca(root, node1, node2);

        int heightOfNode1FromRoot = getHeightOfANode(root, node1, 0);
        int heightOfNode2FromRoot = getHeightOfANode(root, node2, 0);
        int heightOfLCANodeFromRoot = getHeightOfANode(root, lca.getData(), 0);

        return heightOfNode1FromRoot + heightOfNode2FromRoot - 2 * heightOfLCANodeFromRoot;


    }

    private static int getHeightOfANode(BinaryTreeNode<String> root, String node, int height) {
        if (Objects.isNull(root)) {
            return 0;
        }

        if (root.getData().equals(node)) {
            return height;
        }

        return Math.max(getHeightOfANode(root.getLeft(), node, height + 1),
                getHeightOfANode(root.getRight(), node, height + 1));

    }

    private static BinaryTreeNode<String> lca(BinaryTreeNode<String> node, String node1, String node2) {
        /**
         *
         * ---------------------- IMPORTANT -----------------------------
         * THIS ALGORITHM ASSUMES THAT BOTH SEARCH NODE PRESENT IN TREE
         * --------------------------------------------------------------
         *
         **/

        /** Base case when node is empty **/
        if (Objects.isNull(node)) {
            return null;
        }

        /** In case current node is equal to one searching node **/
        if (node.getData().equals(node1) || node.getData().equals(node2)) {
            return node;
        }

        BinaryTreeNode<String> leftSearch = lca(node.getLeft(), node1, node2);
        BinaryTreeNode<String> rightSearch = lca(node.getRight(), node1, node2);

        /** In case when both search node found **/
        if (!Objects.isNull(leftSearch) && !Objects.isNull(rightSearch)) {
            return node;
        } else {
            return !Objects.isNull(leftSearch) ? leftSearch : rightSearch;
        }

    }

}


class BinaryTreeDistanceBetweenTwoNodesTest {
    public static void main(String[] args) {

        BinaryTreeNode<String> root = new BinaryTreeNode<>("A",
                new BinaryTreeNode<>("B",
                        new BinaryTreeNode<>("D"),
                        new BinaryTreeNode<>("E",
                                null,
                                new BinaryTreeNode<>("H"))),
                new BinaryTreeNode<>("C",
                        new BinaryTreeNode<>("F"),
                        new BinaryTreeNode<>("G")));

        BTPrinter.print(root);

        System.out.printf("Distance between nodes 'D' - 'E' : %d\n", BinaryTreeDistanceBetweenTwoNodes.find(root, "D", "E"));
        System.out.printf("Distance between nodes 'D' - 'H' : %d\n", BinaryTreeDistanceBetweenTwoNodes.find(root, "D", "H"));
        System.out.printf("Distance between nodes 'D' - 'G' : %d\n", BinaryTreeDistanceBetweenTwoNodes.find(root, "D", "G"));
        System.out.printf("Distance between nodes 'D' - 'B' : %d\n", BinaryTreeDistanceBetweenTwoNodes.find(root, "D", "B"));
        System.out.printf("Distance between nodes 'D' - 'A' : %d\n", BinaryTreeDistanceBetweenTwoNodes.find(root, "D", "A"));



    }
}