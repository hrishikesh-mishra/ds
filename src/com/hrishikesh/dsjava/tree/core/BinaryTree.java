package com.hrishikesh.dsjava.tree.core;

import com.hrishikesh.dsjava.list_adt.core.Position;

/**
 * Created by hrishikesh.mishra on 25/03/16.
 */
public interface BinaryTree<E> extends Tree<E> {

    /**
     * Returns the Position of p's left child (or null if no child exists).
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> left(Position<E> p ) throws IllegalArgumentException;

    /**
     * Return the Position of p's right child (or null if no child exists).
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> right(Position<E> p) throws IllegalArgumentException;

    /**
     * Return the Position of p's sibling (or null if no sibling exists).
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
