package com.hrishikesh.ns.tree;

import java.util.Objects;

/**
 * <p>
 *     Binary Search Tree ADT
 *
 *     Link: http://hrishikeshmishra.com/binary-search-tree-adt/
 * </p>
 * Created by hrishikesh.mishra
 */
public class BinarySearchTree<E extends Comparable> {

    private BinaryTreeNode<E> root;

    public BinarySearchTree() {

    }

    public BinarySearchTree(BinaryTreeNode<E> root) {
        this.root = root;
    }

    public BinaryTreeNode<E> findRecursive(E data) {
        return findRecursive(this.root, data);
    }

    private BinaryTreeNode<E> findRecursive(BinaryTreeNode<E> node, E data){
        if(Objects.isNull(node)) return null;

        if(node.getData().compareTo(data) == 0 ) return node;
        else if(node.getData().compareTo(data) < 0) return findRecursive(node.getRight(), data);
        else return findRecursive(node.getLeft(), data);

    }

    public BinaryTreeNode<E> findNonRecursive(E data){
        return findNonRecursive(this.root, data);
    }

    private BinaryTreeNode<E> findNonRecursive(BinaryTreeNode<E> node, E data){
        if(Objects.isNull(node)) return null;

        while (!Objects.isNull(node)){
            if(node.getData().compareTo(data) == 0) return node;
            else if(node.getData().compareTo(data) < 0) node = node.getRight();
            else node = node.getLeft();
        }
        return null;
    }

    public BinaryTreeNode<E> findMin(){
        return findMin(this.root);
    }

    private BinaryTreeNode<E> findMin(BinaryTreeNode<E> node){
        if(Objects.isNull(node) || Objects.isNull(node.getLeft())) return node;
        return findMin(node.getLeft());
    }

    public BinaryTreeNode<E> findMax(){
        return findMax(this.root);
    }

    public BinaryTreeNode<E> findMax(BinaryTreeNode<E> node){
        if(Objects.isNull(node) || Objects.isNull(node.getRight())) return node;
        return findMax(node.getRight());
    }

    public BinaryTreeNode<E> insert(E data) {
        if(Objects.isNull(this.root))
            return this.root =  insert(this.root, data);
        else
            return insert(this.root, data);
    }

    private BinaryTreeNode<E> insert(BinaryTreeNode<E> node, E data){
        if(Objects.isNull(node)) node = new BinaryTreeNode<E>(data);
        else if (node.getData().compareTo(data) < 0 )
            node.setRight(insert(node.getRight(), data));
        else if(node.getData().compareTo(data) > 0)
            node.setLeft(insert(node.getLeft(), data));
        return node;
    }

    public BinaryTreeNode<E> delete(E data) {
        if(Objects.isNull(this.root)) return null;
        else return delete(this.root, data);
    }

    private BinaryTreeNode<E> delete(BinaryTreeNode<E> node, E data){
        if(Objects.isNull(node)) return null;
        if(node.getData().compareTo(data) < 0) node.setRight(delete(node.getRight(), data));
        else if (node.getData().compareTo(data) > 0) node.setLeft(delete(node.getLeft(), data));
        else {
            /** if node has both children **/
            if (!Objects.isNull(node.getLeft()) && !Objects.isNull(node.getRight())){

                BinaryTreeNode<E> predecessor = findMax(node.getLeft());
                node.setData(predecessor.getData());
                node.setLeft(delete(node.getLeft(), node.getData()));

            } else { /** If node has only one child **/

                /** If node has no left child **/
                if (Objects.isNull(node.getLeft()))
                    node = node.getRight();

                /** If node has no right child **/
                else if (Objects.isNull(node.getRight()))
                   node = node.getLeft();
            }
        }
        return node;
    }

    public BinaryTreeNode<E> getRoot() {
        return root;
    }
}


class BinarySearchTreeTest{
    public static void main(String[] args) {
        BinaryTreePrinter printer = new BinaryTreePrinter();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        System.out.println("Insert 50 :");
        bst.insert(42);
        printer.print(bst.getRoot());

        System.out.println("Insert 40 :");
        bst.insert(40);
        printer.print(bst.getRoot());

        System.out.println("Insert 32, 45, 65, 55, 66, 7, 42,87 :");
        bst.insert(32); bst.insert(45); bst.insert(65);
        bst.insert(55); bst.insert(66); bst.insert(7);
        bst.insert(42); bst.insert(87);
        printer.print(bst.getRoot());

        System.out.println("Min : " + bst.findMin());
        System.out.println("Max : " + bst.findMax());

        System.out.println("Find 55 :" + bst.findNonRecursive(55));
        System.out.println("Find 87 :" + bst.findRecursive(87));
        System.out.println("Find 76 :" + bst.findRecursive(76));

        System.out.println("Delete 87: \n");
        bst.delete(87);
        printer.print(bst.getRoot());

        System.out.println("Delete 32:\n");
        bst.delete(32);
        printer.print(bst.getRoot());

        System.out.println("Delete 40:\n");
        bst.delete(40);
        printer.print(bst.getRoot());

        System.out.println("Delete 65:\n");
        bst.delete(65);
        printer.print(bst.getRoot());

        System.out.println("Delete 50:\n");
        bst.delete(50);
        printer.print(bst.getRoot());

        System.out.println("Delete 45, 7, 55, 42, 66 :\n");
        bst.delete(45); bst.delete(7);
        bst.delete(55); bst.delete(42);
        bst.delete(66);
        printer.print(bst.getRoot());

    }
}

