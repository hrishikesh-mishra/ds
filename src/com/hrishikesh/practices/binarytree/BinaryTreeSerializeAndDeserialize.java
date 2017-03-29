package com.hrishikesh.practices.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Problem:
 * Serialize and Deserialize a Binary Tree;
 * ;
 * Serialization is to store a tree in an array so that it can be later restored and
 * Deserialization is reading tree back from the array
 * ;
 * Note: The structure of tree must be maintained.
 * Solution:
 * - Process in PreOrder and mark null child with special marker (like -1)
 * ;
 * Algorithm for serialize:
 * - Traverse tree in preOrder
 * - - If node is null then,
 * - - - list.add(-1)
 * - - list.add(node.data);
 * - - Recursively call node left sub tree
 * - - Recursively call node right sub tree
 * ;
 * ;
 * Algorithm for deserialize:
 * - Create tree in PreOrder
 * - - If counter >= list.size or list.get(counter) == -1 then
 * - - - return null
 * - - Create node with list.get(counter)
 * - - leftSubTree = recursively call with counter + 1
 * - - rightSubTree = recursively call with counter + 2
 * - - node.left = leftSubTree
 * - - node.right = rightSubTree
 * - - return node
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=jwzo6IsMAFQ
 * @link http://hrishikeshmishra.com/serialize-and-deserialize-a-binary-tree/
 */
public class BinaryTreeSerializeAndDeserialize {

    public static class Counter {
        private int index;

        public Counter(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Counter increment() {
            this.index++;
            return this;
        }
    }

    public static List<Integer> serialize(BinaryTreeNode<Integer> root) {

        /** List to hold tree data **/
        List<Integer> list = new LinkedList<>();

        /** Call method populate data in list **/
        serialize(root, list);
        return list;
    }


    private static void serialize(BinaryTreeNode<Integer> node, List<Integer> list) {

        /** Base case when **/
        if (Objects.isNull(node)) {
            /** Null marker **/
            list.add(-1);
            return;
        }

        /** Process in preorder **/

        /** Add node value to list **/
        list.add(node.getData());

        /** Recursively call left sub tree **/
        serialize(node.getLeft(), list);

        /** Recursively call right sub tree **/
        serialize(node.getRight(), list);
    }


    public static BinaryTreeNode<Integer> deserialize(List<Integer> list) {

        /** Counter hold current index **/
        Counter counter = new Counter(0);
        return deserialize(list, counter);
    }

    private static BinaryTreeNode<Integer> deserialize(List<Integer> list, Counter counter) {

        /** If current index out of limit or null **/
        if (counter.getIndex() >= list.size() ||
                list.get(counter.getIndex()) == -1) {
            return null;
        }

        /** Recursively create left and right child **/
        return new BinaryTreeNode(
                list.get(counter.getIndex()),
                deserialize(list, counter.increment()),
                deserialize(list, counter.increment())
        );


    }


}


class BinaryTreeSerializeAndDeserializeTest {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(25,
                new BinaryTreeNode<>(18,
                        new BinaryTreeNode<>(19,
                                null,
                                new BinaryTreeNode<>(15)),
                        new BinaryTreeNode<>(20,
                                new BinaryTreeNode<>(18),
                                new BinaryTreeNode<>(25))),
                new BinaryTreeNode<>(50,
                        new BinaryTreeNode<>(35,
                                new BinaryTreeNode<>(20,
                                        null,
                                        new BinaryTreeNode<>(25)),
                                new BinaryTreeNode<>(40)),
                        new BinaryTreeNode<>(60,
                                new BinaryTreeNode<>(55),
                                new BinaryTreeNode<>(70)))
        );


        System.out.println("Binary Tree : ");
        BTPrinter.print(root);
        List<Integer> list = BinaryTreeSerializeAndDeserialize.serialize(root);
        System.out.println("\nSerialized Tree :" + list);


        BinaryTreeNode<Integer> tree = BinaryTreeSerializeAndDeserialize.deserialize(list);
        System.out.println("\nDeserialize Tree :");
        BTPrinter.print(tree);

    }
}
