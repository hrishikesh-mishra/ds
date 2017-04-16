package com.hrishikeshmishra.ns.tree;

/**
 * Problem:
 * Create binary tree from InOrder and PreOrder and InOrder and PostOrder .
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/binary-tree-from-inorder-and-preorder-and-inorder-and-postorder/
 */
public class BinaryTreeGenerator {

    /**
     * <p>
     * Generate Tree from inorder and preorder
     * </p>
     *
     * @param inOrder
     * @param preOrder
     * @param <E>
     * @return
     */
    public <E> BinaryTreeNode<E> generateTreeFromInOrderAndPreOrder(E[] inOrder, E[] preOrder) {
        return generateTreeFromInOrderAndPreOrder(inOrder, preOrder, 0, preOrder.length - 1, 0, inOrder.length - 1);
    }

    private <E> BinaryTreeNode<E> generateTreeFromInOrderAndPreOrder(E[] inOrder, E[] preOrder,
                                                                     int preOrderStartIndex, int preOrderEndIndex, int inOrderStartIndex, int inOrderEndIndex) {

        if (inOrderStartIndex > inOrderEndIndex
                || preOrderStartIndex > preOrderEndIndex)
            return null;


        E element = preOrder[preOrderStartIndex];
        int currentElementPositionInInOrder = getPosition(inOrder, element);
        int numberOfElementsInLeftSubTree = currentElementPositionInInOrder - inOrderStartIndex;
        BinaryTreeNode<E> node = new BinaryTreeNode<E>(element);

        node.setLeft(
                generateTreeFromInOrderAndPreOrder(
                        inOrder,
                        preOrder,
                        preOrderStartIndex + 1,
                        preOrderStartIndex + numberOfElementsInLeftSubTree,
                        inOrderStartIndex,
                        currentElementPositionInInOrder - 1
                )
        );

        node.setRight(
                generateTreeFromInOrderAndPreOrder(
                        inOrder,
                        preOrder,
                        preOrderStartIndex + numberOfElementsInLeftSubTree + 1,
                        preOrderEndIndex,
                        currentElementPositionInInOrder + 1,
                        inOrderEndIndex
                )
        );

        return node;
    }


    /**
     * <p>
     * Generate tree from InOrder and PostOrder
     * </p>
     *
     * @param inOrder
     * @param postOrder
     * @param <E>
     * @return
     */
    public <E> BinaryTreeNode<E> generateTreeFromInOrderAndPostOrder(E[] inOrder, E[] postOrder) {
        return generateTreeFromInOrderAndPostOrder(inOrder, postOrder, 0, postOrder.length - 1, 0, inOrder.length - 1);
    }

    private <E> BinaryTreeNode<E> generateTreeFromInOrderAndPostOrder(E[] inOrder, E[] postOrder,
                                                                      int postOrderStartIndex, int postOrderEndIndex, int inOrderStartIndex, int inOrderEndIndex) {

        if (inOrderStartIndex > inOrderEndIndex
                || postOrderStartIndex > postOrderEndIndex)
            return null;

        E element = postOrder[postOrderEndIndex];
        int currentElementPositionInInOrder = getPosition(inOrder, element);
        int numberOfElementsInLeftSubTree = currentElementPositionInInOrder - inOrderStartIndex;
        BinaryTreeNode<E> node = new BinaryTreeNode<E>(element);

        node.setLeft(
                generateTreeFromInOrderAndPostOrder(
                        inOrder,
                        postOrder,
                        postOrderStartIndex,
                        postOrderStartIndex + numberOfElementsInLeftSubTree - 1,
                        inOrderStartIndex,
                        currentElementPositionInInOrder - 1
                )
        );

        node.setRight(
                generateTreeFromInOrderAndPostOrder(
                        inOrder,
                        postOrder,
                        postOrderStartIndex + numberOfElementsInLeftSubTree,
                        postOrderEndIndex - 1,
                        currentElementPositionInInOrder + 1,
                        inOrderEndIndex
                )
        );

        return node;
    }


    private <E> int getPosition(E[] inOrderList, E element) {
        int i = 0;
        for (; i < inOrderList.length; i++)
            if (inOrderList[i].equals(element)) break;
        return i;
    }
}

class BinaryTreeGeneratorTes {
    public static void main(String[] args) {

        String[] preOrder = {"F", "B", "A", "D", "C", "E", "G", "I", "H"},
                inOrder = {"A", "B", "C", "D", "E", "F", "G", "H", "I"},
                postOrder = {"A", "C", "E", "D", "B", "H", "I", "G", "F"};

        BinaryTreeGenerator treeGenerator = new BinaryTreeGenerator();
        BinaryTreeNode<String> root1 = treeGenerator.generateTreeFromInOrderAndPreOrder(inOrder, preOrder);
        BinaryTreeNode<String> root2 = treeGenerator.generateTreeFromInOrderAndPostOrder(inOrder, postOrder);

        BinaryTreePrinter printer = new BinaryTreePrinter();
        printer.print(root1);
        printer.print(root2);

    }
}