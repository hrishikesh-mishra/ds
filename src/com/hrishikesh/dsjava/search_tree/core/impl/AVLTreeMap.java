package com.hrishikesh.dsjava.search_tree.core.impl;

import com.hrishikesh.dsjava.list_adt.core.Position;
import com.hrishikesh.dsjava.head_n_priority_queue.core.Entry;

import java.util.Comparator;

/**
 *
 * An implementation of a sorted map using AVL tree
 *
 * Created by hrishikesh.mishra
 */
public class AVLTreeMap<K,V> extends TreeMap<K,V> {

    public AVLTreeMap(){
        super();
    }

    public AVLTreeMap(Comparator<K> comparator) {
        super(comparator);
    }

    protected int height(Position<Entry<K,V>> p){
        return tree.getAux(p);
    }

    /**
     * Recomputes the height of the given position based on its children's height
     * @param p
     */
    protected void recomputeHeight(Position<Entry<K,V>> p){
        tree.setAux(p, 1 + Math.max(height(left(p)), height(right(p))));
    }

    /**
     * Returns whether a position has balance factor between -1 and 1 inclusive.
     * @param p
     * @return
     */
    protected boolean isBalanced(Position<Entry<K,V>> p){
        return Math.abs(height(left(p)) - height(right(p))) <= 1;
    }

    /**
     * Returns a child of p with height no smaller than that of the other child
     * @param p
     * @return
     */
    protected Position<Entry<K, V>> tallerChild(Position<Entry<K,V>> p){
        if(height(left(p)) > height(right(p))) return left(p);
        if(height(right(p)) > height(left(p))) return right(p);
        if(isRoot(p)) return left(p);
        if(p == left(parent(p))) return left(p);
        else return right(p);
    }


    /**
     * Utility used to rebalance after an insert or removal operation. This traverses the path
     * upward from p, performing a trinode restructuring when imbalance is found,
     * continue util balance is restored.
     * @param p
     */
    protected void rebalanced(Position<Entry<K,V>> p){
        int oldHeight, newHeight;

        do {
            oldHeight = height(p);
            if(!isBalanced(p)){
                /**
                 * perform trinode restructuring, setting p to resulting root,
                 * and recompute new local height after restructuring.
                 *
                 *
                 * ----------------------------------------------------------------------------------------------
                 * restructure after insert
                 * ----------------------------------------------------------------------------------------------
                 *  let z be the first position we encounter in going up from p toward the root of T
                 *  such that z is unbalanced Also let y denote the child of z with greater height (and note that y
                 *  must be an ancestor of p). Finally, let x be the child of y with greater height (there cannot be
                 *  a tie and position x must also be ancestor of p, possibly p itself). We rebalance the subtree
                 *  rooted at z by calling the trinode restructuring method <code>restructure(x)</code>
                 * ----------------------------------------------------------------------------------------------
                 *
                 *
                 *
                 * ----------------------------------------------------------------------------------------------
                 * restructure after delete
                 * ----------------------------------------------------------------------------------------------
                 * Let z be the first unbalanced position encountered going up from p toward the root of T, and let
                 * y be child of z with greater height (y will not be an ancestor of p). Furthermore, let x be the
                 * child of y defined as follows: if one of the children of y is taller than the other, let x be the
                 * taller child of y; else (both children of y have the same height), let x be the child of y on the
                 * same side as y (that is, if y is the left child of z, let x be the left child of y, else let x be
                 * the right child of y). We then perform a <code>restructure(x)</code> operation.
                 * ----------------------------------------------------------------------------------------------
                 */
                p =  tree.restructure(tallerChild(tallerChild(p)));
                recomputeHeight(left(p));
                recomputeHeight(right(p));
            }
            recomputeHeight(p);
            newHeight = height(p);
            p = parent(p);
        }while (oldHeight != newHeight && p != null);
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after insertion
     * @param p
     */
    protected void rebalanceInsert(Position<Entry<K,V>> p){
        rebalanced(p);
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after deletion
     * @param p
     */
    protected void rebalanceDelete(Position<Entry<K,V>> p){
        if(!isRoot(p)){
            rebalanced(p);
        }
    }

}
