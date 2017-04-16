package com.hrishikeshmishra.dsjava.search_tree.core.impl;

import com.hrishikeshmishra.dsjava.list_adt.core.Position;
import com.hrishikeshmishra.dsjava.tree.core.impl.LinkedBinaryTree;
import com.hrishikeshmishra.dsjava.head_n_priority_queue.core.Entry;

import java.util.Objects;

/**
 * Created by hrishikesh.mishra on 21/04/16.
 */
public class BalanceableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K,V>> {

    /**---------------------- END BSTNode ---------------------------- **/

    public int getAux(Position<Entry<K,V>> p ){
        return ((BSTNode<Entry<K,V>>) p).getAux();
    }

    public void setAux(Position<Entry<K,V>> p, int value){
        ((BSTNode<Entry<K,V>>) p).setAux(value);
    }

    protected Node<Entry<K,V>> createNode(Entry<K,V> e, Node<Entry<K,V>> parent, Node<Entry<K,V>> leftChild, Node<Entry<K,V>> rightChild){
        return new BSTNode<>(e, parent, leftChild, rightChild);
    }

    private void relink(Node<Entry<K,V>> parent, Node<Entry<K,V>> child, boolean makeLeftChild){
        child.setParent(parent);
        if(makeLeftChild)
            parent.setLeft(child);
        else
            parent.setRight(child);
    }

    public void rotate(Position<Entry<K,V>> p){
        Node<Entry<K,V>> x  = validate(p);
        Node<Entry<K,V>> y = x.getParent();
        Node<Entry<K,V>> z = y.getParent();

        if(Objects.isNull(z)){
            root = x;
            x.setParent(null);
        }else {
            relink(z, x, y == z.getLeft());
        }

        if(x == y.getLeft()){
            relink(y, x.getRight(), true);
            relink(x, y, false);
        }else {
            relink(y, x.getLeft(), false);
            relink(x, y, true);
        }
    }

    public Position<Entry<K,V>> restructure(Position<Entry<K,V>> x){
        Position<Entry<K,V>> y = parent(x);
        Position<Entry<K,V>> z = parent(y);

        if((x == right(y)) == (y == right(z))){
            rotate(y);
            return y;
        }else {
            rotate(x);
            rotate(x);
            return x;
        }
    }

    /** ---------------------------------  nested BSTNode class ----------------------------- **/
    protected static class BSTNode<E> extends Node<E>{
        int aux = 0;

        BSTNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild){
            super(e, parent, leftChild, rightChild);
        }

        public int getAux() {
            return aux;
        }

        public void setAux(int aux) {
            this.aux = aux;
        }
    }

}
