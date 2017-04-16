package com.hrishikeshmishra.dsjava.tree.core;

import com.hrishikeshmishra.dsjava.list_adt.core.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by hrishikesh.mishra on 25/03/16.
 */
public abstract class AbstractBinaryTree <E> extends AbstractTree<E> implements BinaryTree<E>{

    public Position<E> sibling(Position<E> p){
        Position<E> parent = parent(p);
        if(Objects.isNull(parent)) return null;
        if(p == left(parent)) return right(parent);
        else return left(parent);
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        int count =0 ;
        if(!Objects.isNull(left(p))) count++;
        if(!Objects.isNull(right(p))) count++;
        return count;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        List<Position<E>> snapshot = new ArrayList<>(2);
        if(!Objects.isNull(left(p))) snapshot.add(left(p));
        if(!Objects.isNull(right(p))) snapshot.add(right(p));
        return snapshot;
    }


    /**
     * Adds positions fo the subtree rooted at Position p to the given snapshot
     * @param p
     * @param snapshot
     */
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot){
        if(left(p) != null) inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if(right(p) != null) inorderSubtree(right(p), snapshot);
    }

    /**
     * Returns an iterable collection of position of the tree,
     * reported in order
     * @return
     */
    public Iterable<Position<E>> inorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()) inorderSubtree(root(), snapshot);
        return snapshot;
    }

    /**
     * Overrides position to make inorder the default order
     * for binary trees.
     * @return
     */
    public Iterable<Position<E>> positions(){
        return inorder();
    }
}
