package com.hrishikeshmishra.dsjava.search_tree.core.impl;

import com.hrishikeshmishra.dsjava.list_adt.core.Position;
import com.hrishikeshmishra.dsjava.head_n_priority_queue.core.Entry;

import java.util.Comparator;

/**
 * Created by hrishikesh.mishra on 22/04/16.
 */
public class RBTreeMap<K,V> extends TreeMap<K,V> {

    private static final int BLACK = 0;
    private static final int RED = 0;
    public RBTreeMap(Comparator<K> comparator) {
        super(comparator);
    }

    private boolean isBlack(Position<Entry<K,V>> p){
        return tree.getAux(p) == BLACK;
    }

    private boolean isRed(Position<Entry<K,V>> p){
        return tree.getAux(p) == RED;
    }

    private void makeBlack(Position<Entry<K,V>> p){
        tree.setAux(p, BLACK);
    }

    private void makeRed(Position<Entry<K,V>> p){
        tree.setAux(p, RED);
    }

    private void setColor(Position<Entry<K,V>> p, boolean toRed){
        tree.setAux(p, toRed ? RED : BLACK);
    }

    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        if(!isRoot(p)){
            makeRed(p);
            resolveRed(p);
        }
    }

    /**
     *
     * Remedies potential double-red violation above red position p.
     *
     * @param p
     */
    private void resolveRed(Position<Entry<K, V>> p) {

        Position<Entry<K,V>> parent, uncle, middle, grand;
        parent  = parent(p);
        if(isRed(parent)){
            uncle = sibling(parent);
            if(isBlack(uncle)){
                middle = tree.restructure(p);
                makeBlack(middle);
                makeRed(left(middle));
                makeRed(right(middle));
            }else {
                makeBlack(parent);
                makeBlack(uncle);
                grand = parent(parent);
                if(!isRoot(grand)){
                    makeRed(grand);
                    resolveRed(grand);
                }
            }
        }
    }

    @Override
    protected void rebalanceDelete(Position<Entry<K, V>> p) {
        if(isRed(p)){
            makeBlack(p);
        }else if(!isRoot(p)){
            Position<Entry<K,V>> sib = sibling(p);
            if(isInternal(sib) && (isBlack(sib) || isInternal(left(sib)))){
                /** sib's subtree has nonzero black height **/
                remedyDoubleBlack(p);
            }
        }
    }

    private void remedyDoubleBlack(Position<Entry<K, V>> p) {
        Position<Entry<K,V>> z = parent(p);
        Position<Entry<K,V>> y = sibling(p);
        if(isBlack(y)){
            /**
             * Case 1: trinode restructuring
             */
            if(isRed(left(y)) || isRed(right(y))){
                Position<Entry<K,V>> x = isRed(left(y))?left(y) : right(y);
                Position<Entry<K,V>> middle = tree.restructure(x);

                /** root of restructure subtree get z's old color **/
                setColor(middle, isRed(z));
                makeBlack(left(middle));
                makeBlack(right(middle));
            } else { /** Case 2: recoloring  **/
                makeRed(y);
                if(isRed(z))
                    makeBlack(z); /** problem is solved **/
                else if(!isRoot(z))
                    remedyDoubleBlack(z); /** Propagate the problem **/
            }
        }else { /** Case 3: reorient 3-node **/
            tree.rotate(y);
            makeBlack(y);
            makeRed(z);
            remedyDoubleBlack(p); /** restart the process at p **/
        }
    }
}
