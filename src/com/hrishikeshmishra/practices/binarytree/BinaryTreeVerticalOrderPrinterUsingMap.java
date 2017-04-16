package com.hrishikeshmishra.practices.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Problem:
 * Binary Tree in Vertical Order Printer Using Map
 *
 * @author hrishikesh.mishra
 */
public class BinaryTreeVerticalOrderPrinterUsingMap {

    public static void printVertical(BinaryTreeNode<Integer> root) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        printVerticalHelper(root, map, 0);

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private static void printVerticalHelper(BinaryTreeNode<Integer> node, Map<Integer,
            List<Integer>> map, int horizontalDistance) {

        /** Base case: When object is null **/
        if (Objects.isNull(node)) {
            return;
        }

        List<Integer> values = map.getOrDefault(horizontalDistance, new LinkedList<>());
        values.add(node.getData());
        map.put(horizontalDistance, values);

        printVerticalHelper(node.getLeft(), map, horizontalDistance - 1);
        printVerticalHelper(node.getRight(), map, horizontalDistance + 1);

    }
}


class BinaryTreeVerticalOrderPrinterUsingHashMapTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(4),
                        new BinaryTreeNode<>(5)),
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(6,
                                null,
                                new BinaryTreeNode<>(8)),
                        new BinaryTreeNode<>(7,
                                null,
                                new BinaryTreeNode<>(9))));


        BTPrinter.print(root);
        System.out.println("Vertical Printing : ");
        BinaryTreeVerticalOrderPrinterUsingMap.printVertical(root);

    }
}