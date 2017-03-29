package com.hrishikesh.ns.tree;

/**
 * Generate binary tree from sorted array
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/generate-binary-tree-from-sorted-array/
 */
public class BinarySearchTreeGenerator {

    public <E> BinaryTreeNode<E> generate(E[] array) {
        return generate(array, 0, array.length - 1);
    }

    private <E> BinaryTreeNode<E> generate(E[] array, int start, int end) {
        if (start > end) return null;
        if (start == end) {
            return new BinaryTreeNode<>(array[start]);
        } else {
            int mid = (start + end) / 2;
            BinaryTreeNode<E> node = new BinaryTreeNode<>(array[mid]);
            node.setLeft(generate(array, start, mid - 1));
            node.setRight(generate(array, mid + 1, end));
            return node;
        }
    }
}

class BinarySearchTreeGeneratorTest {
    public static void main(String[] args) {
        Integer[] array = {2, 5, 8, 16, 20};
        BinarySearchTreeGenerator generator = new BinarySearchTreeGenerator();
        BinaryTreePrinter printer = new BinaryTreePrinter();
        printer.print(generator.generate(array));
    }
}

