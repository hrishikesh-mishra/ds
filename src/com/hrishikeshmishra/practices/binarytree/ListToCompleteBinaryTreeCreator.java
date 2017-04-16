package com.hrishikeshmishra.practices.binarytree;


import java.util.LinkedList;
import java.util.List;

/**
 * Problem:
 * Make Binary Tree
 * Given Linked List Representation of Complete Binary Tree, construct the Binary tree.
 * ;
 * Note : The complete binary tree is represented as an linked list in a way where If root node
 * is stored at position i, its left, and right children are stored at position 2*i+1, 2*i+2 respectively.
 * Solution:
 * - Create tree in post order
 * ;
 * ;
 * Algorithm:
 * - Create tree in PostOrder traversal
 * - If index >= list.size then,
 * - - return null
 * - Create node with list.get(index)
 * - node.left = Call recursively to create tree with 2 * index + 1
 * - node.right = Call recursively to create tree with 2 * index + 2
 * - Return node
 *
 * @author hrishikesh.mishra
 * @link hrishikeshmishra.com/given-linked-list-representation-of-complete-binary-tree-construct-the-binary-tree/
 */
public class ListToCompleteBinaryTreeCreator {

    public static BinaryTreeNode<Integer> convert(List<Integer> list) {

        return convert(list, 0);
    }

    private static BinaryTreeNode<Integer> convert(List<Integer> list, int index) {

        /** If index is greater than or equal to list size **/
        if (index >= list.size()) {
            return null;
        }

        /** Working in post order fashion **/

        /** Create left child with at list index 2*i + 1 **/
        BinaryTreeNode<Integer> leftChild = convert(list, 2 * index + 1);

        /** Create right  child with at list index 2*i + 2 **/
        BinaryTreeNode<Integer> rightChild = convert(list, 2 * index + 2);

        /** Create node **/
        return new BinaryTreeNode<>(list.get(index), leftChild, rightChild);
    }

}


class ListToCompleteBinaryTreeCreatorTest {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>() {
            {
                add(10);
                add(12);
                add(15);
                add(25);
                add(30);
                add(36);
            }
        };

        System.out.println("List is : " + list);
        System.out.println("Tree is : ");
        BinaryTreeNode<Integer> root = ListToCompleteBinaryTreeCreator.convert(list);
        BTPrinter.print(root);

    }
}


