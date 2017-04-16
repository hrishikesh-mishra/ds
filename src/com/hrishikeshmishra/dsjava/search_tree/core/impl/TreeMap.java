package com.hrishikeshmishra.dsjava.search_tree.core.impl;

import com.hrishikeshmishra.dsjava.map_n_heap.core.AbstractSortedMap;
import com.hrishikeshmishra.dsjava.list_adt.core.Position;
import com.hrishikeshmishra.dsjava.tree.core.impl.LinkedBinaryTree;
import com.hrishikeshmishra.dsjava.head_n_priority_queue.core.Entry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Created by hrishikesh.mishra on 30/03/16.
 */
public class TreeMap <K,V> extends AbstractSortedMap<K,V>{


    /** class that we name BalanceableBinaryTree **/
    protected BalanceableBinaryTree<K,V> tree = new BalanceableBinaryTree<K,V>();
    /** ------------------------------ nested BalanceableBinaryTree end -------------------------------------**/


    /** To represent the underlying tree structure, we use a specialized subclass of the LinkedBinaryTree **/
    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public TreeMap(){
        super();
        tree.addRoot(null);
    }


    /**
     * Constructs an empty map using the given comparator to order keys.
     * @param comparator
     */
    public TreeMap(Comparator<K> comparator){
        super(comparator);
        tree.addRoot(null);
    }

    /**
     * Returns the numbers of entries in the map
     * @return
     */
    public int size(){
        return (tree.size() -1)/2;
    }

    private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry){
        /** Store new entry at p **/
        tree.set(p,entry);

        /** add new sentinel leaves as children **/
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }

    protected Position<Entry<K,V>> root(){
        return tree.root();
    }

    protected boolean isExternal(Position<Entry<K,V>> p){
        return tree.isExternal(p);
    }

    protected boolean isInternal(Position<Entry<K,V>> p) {
        return tree.isInternal(p);
    }

    protected boolean isRoot(Position<Entry<K,V>> p){
        return tree.isRoot(p);
    }

    protected Position<Entry<K,V>> left(Position<Entry<K,V>> p){
        return tree.left(p);
    }

    protected Position<Entry<K,V>> right(Position<Entry<K,V>> p){
        return tree.right(p);
    }

    protected Position<Entry<K,V>> sibling(Position<Entry<K,V>> p){
        return tree.sibling(p);
    }

    protected Position<Entry<K,V>> parent(Position<Entry<K,V>> p){
        return tree.parent(p);
    }

    protected Entry<K,V> remove(Position<Entry<K,V>> p){
        return tree.remove(p);
    }

    protected Entry<K,V> set(Position<Entry<K,V>> p, Entry<K,V> e){
        return tree.set(p, e);
    }

    /**
     * Hook to customized later
     * @param p
     */
    protected void rebalanceAccess(Position<Entry<K,V>> p){
        //
    }

    /**
     * Hook to customized later
     * @param p
     */
    protected void rebalanceInsert(Position<Entry<K,V>> p){
        //
    }

    /**
     * Hook to customized later
     * @param p
     */
    protected void rebalanceDelete(Position<Entry<K,V>> p){

    }

    /**
     * Returns the position in p's subtree having given
     * key (or else the terminal leaf).
     * @param p
     * @param key
     * @return
     */
    private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key){
        /** key not found, return the final leaf **/
        if(isExternal(p)) return p;

        int comp = compare(key, p.getElement());

        /** Key found; return its position **/
        if(comp == 0)return p ;
        /** search left subtree **/
        else if(comp < 0) return treeSearch(left(p), key);
        /** search right subtree **/
        else return treeSearch(right(p), key);
    }

    /**
     * Returns the value associated with the specified key (or else null)
     * @param key
     * @return
     * @throws IllegalArgumentException
     */
    public V get(K key) throws IllegalArgumentException{
       checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);

        /** hook for balanced tree subclass **/
        rebalanceAccess(p);
        if(isExternal(p)) return null;
        return p.getElement().getValue();
    }

    /**
     * Associates the given value with the given key, returning any overridden value
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value){
        checkKey(key);
        Entry<K,V> newEntry = new MapEntry<>(key, value);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        /** Key is new **/
        if(isExternal(p)){
            expandExternal(p, newEntry);
            /** hook for balanced tree subtree **/
            rebalanceInsert(p);
            return null;
        }else {
            V old = p.getElement().getValue();
            set(p, newEntry);
            /** hook for balanced tree subtree **/
            rebalanceAccess(p);
            return old;
        }
    }

    /**
     * Removes the entry having key k (if any) and returns its associated values
     * @param key
     * @return
     * @throws IllegalArgumentException
     */
    public V remove(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);

        if(isExternal(p)){ /** key not found **/
            /** hook for balanced tree subtree **/
            rebalanceAccess(p);
            return null;
        }else {
            V old = p.getElement().getValue();

            /** both children are internal **/
            if(isInternal(left(p)) && isExternal(right(p))){
                Position<Entry<K,V>> replacement = treeMax(left(p));
                set(p, replacement.getElement());
                p = replacement;
            }
            /** Now p has at most one child that is an internal node **/
            Position<Entry<K,V>> leaf = (isExternal(left(p)))? left(p): right(p);
            Position<Entry<K,V>> sib = sibling(leaf);
            remove(leaf);
            /** sib is promoted in p's place **/
            remove(p);

            /** hook for balanced tree subtree **/
            rebalanceDelete(p);

            return old;
        }
    }

    /**
     * Returns the position with the maximum key in subtree rooted at Position p.
     * @param p
     * @return
     */
    protected Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p){
        Position<Entry<K,V>> walk = p;
        while (isInternal(walk))
            walk = right(walk);

        /** we want the parent of the leaf **/
        return parent(walk);
    }

    /**
     * Returns the entry having the greatest key (or null if map if empty)
     * @return
     */
    public Entry<K,V> lastEntry(){
        if(isEmpty()) return null;
        return treeMax(root()).getElement();
    }

    public Entry<K,V> floorEntry(K key) throws IllegalArgumentException{
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);

        /** Exact match **/
        if(isInternal(p)) return p.getElement();

        while (!isRoot(p)) {
            /** parent has next lesser key **/
            if(p == right(parent(p))) return parent(p).getElement();
            else p = parent(p);
        }
        /** no such lesser key exists **/
        return null;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map
     * @return
     */
    public Iterable<Entry<K,V>> entrySet() {
        List<Entry<K,V>> buffer = new ArrayList<>(size());
        for(Position<Entry<K,V>> p: tree.inorder())
            if(isInternal(p)) buffer.add(p.getElement());

        return buffer;
    }

    public Iterable<Entry<K,V>> subMap(K fromKey, K toKey){
        List<Entry<K,V>> buffer = new ArrayList<>(size());

        /** ensure that fromKe < toKey **/
        if(compare(fromKey, toKey) < 0)
            subMapRecurse(fromKey, toKey, root(), buffer);

        return buffer;
    }

    private void subMapRecurse(K fromKey, K toKey, Position<Entry<K,V>> p, List<Entry<K,V>> buffer){
        if(isInternal(p))
            if(compare(p.getElement(), fromKey) < 0)
                /** p's key less than fromKey, so any relevant entries are to the right **/
                subMapRecurse(fromKey, toKey, right(p), buffer);
            else {
                /** first consider left subtree **/
                subMapRecurse(fromKey, toKey, left(p), buffer);

                /** p is within range **/
                if(compare(p.getElement(), toKey) <0){

                    /** so add it to buffer, and consider right subtree as well **/
                    buffer.add(p.getElement());
                    subMapRecurse(fromKey, toKey, right(p), buffer);
                }
            }

    }

    /** @todo implementation **/
    @Override
    public Entry<K, V> firstEntry() {
        return null;
    }

    /** @todo implementation **/
    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        return null;
    }

    /** @todo implementation **/
    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        return null;
    }

    /** @todo implementation **/
    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        return null;
    }

    /** ------------------------------ nested BalanceableBinaryTree -------------------------------------**/
    protected static class BalanceableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K,V>> {

        public int getAux(Position<Entry<K, V>> p) {
            return ((BalanceableBinaryTree.BSTNode<Entry<K, V>>) p).getAux();
        }

        public void setAux(Position<Entry<K, V>> p, int value) {
            ((BalanceableBinaryTree.BSTNode<Entry<K, V>>) p).setAux(value);
        }

        protected Node<Entry<K, V>> createNode(Entry<K, V> e, Node<Entry<K, V>> parent, Node<Entry<K, V>> leftChild, Node<Entry<K, V>> rightChild) {
            return new BalanceableBinaryTree.BSTNode<Entry<K, V>>(e, parent, leftChild, rightChild);
        }

        /**
         * Relink a parent node with oriented child node
         *
         * @param parent
         * @param child
         * @param makeLeftChild
         */
        private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
            child.setParent(parent);
            if (makeLeftChild) parent.setLeft(child);
            else parent.setRight(child);
        }

        public void rotate(Position<Entry<K, V>> p) {
            Node<Entry<K, V>> x = validate(p);

            /** We assume this exists **/
            Node<Entry<K,V>> y = x.getParent();

            /** grandparent (possible null) **/
            Node<Entry<K,V>> z = y.getParent();

            /** x becomes direct child of z **/
            if(Objects.isNull(z)){
                root = x;
                x.setParent(null);
            }else {
                relink(z, x, y == z.getLeft());
            }

            /** Now rotate x and y, including transfer to middle subtree **/
            if(x == y.getLeft()){
                /** x's right child becomes y's left **/
                relink(y,x.getRight(), true);

                /** y becomes x's right child **/
                relink(x,y,false);
            }else {
                /** x's left child becomes y's right **/
                relink(y,x.getLeft(), false);

                /** y becomes left child of x **/
                relink(x,y,true);
            }
        }

        /**
         * Performs trinode restructuring of Position x with its parent/grandparent.
         * @param x
         * @return
         */
        public Position<Entry<K,V>> restructure(Position<Entry<K,V>> x){
            Position<Entry<K,V>> y = parent(x);
            Position<Entry<K,V>> z = parent(y);

            /** matching alignments **/
            if((x == right(y)) == (y == right(z))){
                /** Single rotation of (y) **/
                rotate(y);

                /** y is new subtree root **/
                return y;
            }else {
                /** double rotation of (x) **/
                rotate(x);
                rotate(x);

                /** x is new subtree root **/
                return x;
            }
        }

        /**
         * nested BSTNode class *
         */

        protected static class BSTNode<E> extends Node<E> {
            int aux = 0;

            BSTNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
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
}
