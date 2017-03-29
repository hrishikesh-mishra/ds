package com.hrishikesh.dsjava.tree.core;

import com.hrishikesh.dsjava.stack.core.LinkedQueue;
import com.hrishikesh.dsjava.stack.core.Queue;
import com.hrishikesh.dsjava.list_adt.core.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by hrishikesh.mishra on 25/03/16.
 */
public abstract class AbstractTree<E> implements Tree<E> {

    public class ElementIterator<E> implements Iterator<E> {

        //@todo fix this .
        Iterator<Position<E>> positionIterator = (Iterator) positions().iterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }

        @Override
        public void remove() {
            positionIterator.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {

        }
    }

    public boolean isInternal(Position<E> p ) {
        return numChildren(p)> 0 ;
    }

    public boolean isExternal(Position<E> p){
        return numChildren(p) == 0;
    }

    public boolean isRoot(Position<E> p){
        return p == root();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int depth(Position<E> p){
        if(isRoot(p)) return 0;
        else return 1 + depth(parent(p));
    }

    /**
     * Returns the height of the tree
     * @return
     */
    private int heightBad(){
        int h = 0;
        for(Position<E> p: positions()){
            if(isExternal(p)) h = Math.max(h, depth(p));
        }
        return h;
    }

    public int hegiht(Position<E> p){
        int h =0 ;
        for(Position<E> c : children(p)){
            h = Math.max(h, hegiht(c));
        }
        return h;
    }

    public Iterator<E> iterator(){
        return new ElementIterator<>();
    }

    public Iterable<Position<E>> positions(){
        return preorder();
    }

    /**
     * Adds position of the subtree at Position p to the given
     * snapshot
     *
     * @param p
     * @param snapshot
     */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot){
        snapshot.add(p);
        for(Position<E> c: children(p))
            preorderSubtree(c, snapshot);
    }

    /**
     * Returns an iterable collection of position of the tree,
     * reported in preorder
     * @return
     */
    public Iterable<Position<E>> preorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            preorderSubtree(root(), snapshot);
        return snapshot;
    }

    /**
     * Adds position of the subtree rooted at Position p
     * to the given snapshot
     *
     * @param p
     * @param snapshot
     */
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot){
        for(Position<E> c : children(p))
            preorderSubtree(c, snapshot);
        snapshot.add(p);
    }

    /**
     * Returns an iterable collection of position of the tree,
     * reported in postorder
     * @return
     */
    public Iterable<Position<E>> postorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()) postorderSubtree(root(), snapshot);
        return snapshot;
    }

    /**
     * Return an iterable collection of position of the tree
     * in the breadth-first order
     * @return
     */
    public Iterable<Position<E>> breathfirst(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()){
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root());
            while (!fringe.isEmpty()){
                Position<E> p = fringe.dequeue();
                snapshot.add(p);
                for (Position<E> c : children(p))
                    fringe.enqueue(c);
            }
        }
        return snapshot;
    }


}
