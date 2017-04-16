package com.hrishikeshmishra.practices.binarytree;

import java.util.Objects;
import java.util.TreeMap;

/**
 * Problem:
 * ;*
 * Bottom View of Binary Tree
 * Given a Binary Tree,  print the bottom view from left to right.
 * A node x is there in output if x is the bottommost node at its horizontal distance from root.
 * Horizontal distance of left child of a node x is equal to horizontal distance of x minus 1, and
 * that of right child is horizontal distance of x plus 1.
 * ;
 * Solution:
 * ;
 * - Traverse tree in level order and use sorted map to hold  horizontal distance
 * - Or, Process in PreOrder and add node in sorted map based on horizontal distance and depth of node
 * ;
 * Algorithm For 2nd Solution:
 * - Create a class to hold depth and node
 * - We need sorted map, key of this map would be horizontal distance and value would be above class
 * - When we are put in map, we'll do in this way
 * - - If key not present in map, we just add new key and value
 * - - Else, if depth of current node is high than only we update in map.
 * - Traverse tree in PreOrder
 * - - If node is null then return
 * - - Add current node in sorted map with horizontal distance and depth
 * - - Recursively call left subtree with depth + 1 and horizontal distance - 1
 * - - Recursively call right subtree with depth + 1 and horizontal distance + 1
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/bottom-view-of-binary-tree/
 */
public class BottomViewOfBinaryTree {

    private static class ResultSet {
        private int data;
        private int depth;

        public ResultSet(int depth, int data) {
            this.data = data;
            this.depth = depth;
        }
    }

    public static void printBottomView(BinaryTreeNode<Integer> root) {

        /** Sorted map ot hold horizontal distance and data **/
        TreeMap<Integer, ResultSet> sortedMap = new TreeMap<>();

        /** Populate Sorted map based on horizontal distance and depth of node **/
        populateSortedMapBasedOnHorizontalDistance(root, 0, 1, sortedMap);

        /** Print element of sorted map **/
        for (ResultSet resultSet : sortedMap.values()) {
            System.out.print(resultSet.data + " ");
        }

    }

    private static void populateSortedMapBasedOnHorizontalDistance(BinaryTreeNode<Integer> node, int horizontalDistance,
                                                                   int depth, TreeMap<Integer, ResultSet> sortedMap) {

        /** If node is null then do nothing **/
        if (Objects.isNull(node)) {
            return;
        }

        /** Process in PreOder fashion **/

        /** First add node in treeMap **/
        addOrReplace(horizontalDistance, new ResultSet(depth, node.getData()), sortedMap);

        /** Move to left subtree with  horizontalDistance - 1 and  depth + 1 **/
        populateSortedMapBasedOnHorizontalDistance(node.getLeft(), horizontalDistance - 1, depth + 1, sortedMap);

        /** Move to right subtree with horizontalDistance + 1 and depth + 1 **/
        populateSortedMapBasedOnHorizontalDistance(node.getRight(), horizontalDistance + 1, depth + 1, sortedMap);
    }

    private static void addOrReplace(int horizontalDistance,
                                     ResultSet resultSet, TreeMap<Integer, ResultSet> sortedMap) {

        if (sortedMap.containsKey(horizontalDistance)) {

            /** If horizontal distance exits in map then check depth and then replace **/
            if (sortedMap.get(horizontalDistance).depth <= resultSet.depth) {
                sortedMap.put(horizontalDistance, resultSet);
            }
        } else {
            /** If not exits then added **/
            sortedMap.put(horizontalDistance, resultSet);
        }

    }
}

class BottomViewOfBinaryTreeTest {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(20,
                new BinaryTreeNode<>(8,
                        new BinaryTreeNode<>(5),
                        new BinaryTreeNode<>(3,
                                new BinaryTreeNode<>(10),
                                new BinaryTreeNode<>(14))),
                new BinaryTreeNode<>(22,
                        null,
                        new BinaryTreeNode<>(25)));

        BTPrinter.print(root);
        System.out.print("\nBottom View: ");
        BottomViewOfBinaryTree.printBottomView(root);

    }
}