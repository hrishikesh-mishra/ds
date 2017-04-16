package com.hrishikeshmishra.dsjava.list_adt.core.impl;

import com.hrishikeshmishra.dsjava.list_adt.core.Position;

/**
 * Created by hrishikesh.mishra on 24/03/16.
 */
public interface PositionalList<E> {

    /**
     * Return the number of elements in the list.
     * @return
     */
    int size();

    /**
     * Tests whether the list is empty.
     * @return
     */
    boolean isEmpty();

    /**
     * Returns the first Position in the list (or null, if empty)
     * @return
     */
    Position<E> first();

    /**
     * Returns the last Position in the list (or null, if empty)
     * @return
     */
    Position<E> last();


    /**
     * Returns the Position immediately before Position p (or null, if p is first)
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> before(Position<E> p )throws IllegalArgumentException;

    /**
     * Returns the Position immediately after Position P (or null, if p is last)
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    /**
     * Insert element e at the front of the list and returns its new Position.
     * @param e
     * @return
     */
    Position<E> addFirst(E e);

    /**
     * Insert element e at the back of the list and returns its new Position
     * @param e
     * @return
     */
    Position<E> addLast(E e);

    /**
     * Insert element e immediately before Position p and returns its new Position.
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Insert element e immediately after Position p and return its new Position
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Replaces the element stored at Position p and returns the replaced element
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    E set(Position<E> p, E e) throws  IllegalArgumentException;

    /**
     * Removes the element stored at Position p and returns its (invalidating p).
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    E remove(Position<E> p) throws  IllegalArgumentException;

    Iterable<Position<E>> positions();
}
