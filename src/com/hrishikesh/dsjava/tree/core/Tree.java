package com.hrishikesh.dsjava.tree.core;

import com.hrishikesh.dsjava.list_adt.core.Position;

import java.util.Iterator;

/**
 * Created by hrishikesh.mishra on 25/03/16.
 */
public interface Tree<E> extends  Iterable<E> {

    /**
     * Returns the position of the root of the tree  (or null if empty)
     * @return
     */
    Position<E> root();

    /**
     * Returns the position of the parent of position p (or null if p is the root)
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> parent(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns an iterable collection containing the children of position p (if any)
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the number the children of the position p
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    int numChildren(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns true if position p has at least one child
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    boolean isInternal(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns true if position p doesn't have any children
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    boolean isExternal(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns true if position p is the root of the tree.
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    boolean isRoot(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the number of position (and hence elements) that are contained in the tree
     * @return
     */
    int size();

    /**
     * Returns true if the tree does not contain any positions (and thus no elements)
     * @return
     */
    boolean isEmpty();

    /**
     * Returns an iterator for all elements in the tree (so that the tree itself is Iterable)
     * @return
     */
    Iterator<E> iterator();

    /**
     * Returns an iterable collection of all positions of the tree.
     *
     * @return
     */
    Iterable<Position<E>> positions();
}
