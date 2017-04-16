package com.hrishikeshmishra.practices.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hrishikesh.mishra
 */
public class BTPrinter {

    public static <E> void print(BinaryTreeNode<E> root) {
        if (Objects.isNull(root)) return;
        List<BinaryTreeNode<E>> list = new ArrayList<>();
        list.add(root);
        print(list, 1, getTreeHeight(root));

    }

    public static <E> void print(List<BinaryTreeNode<E>> nodes, int currentLevel, int maxLevel) {
        if (nodes.isEmpty() || isAllEmpty(nodes)) return;

        List<BinaryTreeNode<E>> newNodes = new ArrayList<>();

        /** Magic number are here **/
        int diff = maxLevel - currentLevel;
        int edgeLines = (int) Math.pow(2, (Math.max(diff - 1, 0)));
        int leftSpaces = (int) Math.pow(2, (diff)) - 1;
        int whiteSpaceBetweenNode = (int) Math.pow(2, (diff + 1)) - 1;

        /** print left spaces **/
        printWhitespaces(leftSpaces);

        /** Print nodes of level "currentLevel" **/
        for (BinaryTreeNode<E> node : nodes) {
            if (!Objects.isNull(node)) {
                System.out.print(node.getData());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                System.out.print(" ");
                newNodes.add(null);
                newNodes.add(null);
            }
            printWhitespaces(whiteSpaceBetweenNode);
        }

        /** clear current line **/
        System.out.println("");

        /** printing edges for next level **/
        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(leftSpaces - i);

                if (Objects.isNull(nodes.get(j))) {
                    printWhitespaces(2 * edgeLines + i + 1);
                    continue;
                }

                if (!Objects.isNull(nodes.get(j).getLeft()))
                    System.out.print("/");
                else
                    printWhitespaces(1);


                printWhitespaces(i + i - 1);

                if (!Objects.isNull(nodes.get(j).getRight()))
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(2 * edgeLines - i);
            }

            System.out.println("");
        }

        print(newNodes, currentLevel + 1, maxLevel);

    }

    private static <E> boolean isAllEmpty(List<BinaryTreeNode<E>> nodes) {
        for (BinaryTreeNode<E> node : nodes)
            if (!Objects.isNull(node)) return false;
        return true;
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    public static <E> int getTreeHeight(BinaryTreeNode<E> node) {
        if (Objects.isNull(node)) return 0;
        return 1 + Math.max(getTreeHeight(node.getLeft()), getTreeHeight(node.getRight()));
    }


}
