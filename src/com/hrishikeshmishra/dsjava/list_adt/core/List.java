package com.hrishikeshmishra.dsjava.list_adt.core;

/**
 *
 * A simplified version of the java.util.List interface
 * Created by hrishikesh.mishra on 24/03/16.
 */
public interface List<E> {

    /** Returns the number of element in this list **/
    int  size();

    /** Returns whether the list is empty. **/
    boolean isEmpty();

    /** Returns (but does not remove) the element at index i**/
    E get(int i) throws IndexOutOfBoundsException;

    /** Replaces the element at index i with e, and returns the replaced element. **/
    E set(int i, E e) throws IndexOutOfBoundsException;

    /** Insert element e to be at index i, shifting all subsequent element later **/
    void add(int i, E e) throws IndexOutOfBoundsException;

    /** Removes/returns the element at index i, shifting subsequent element earlier **/
    E remove(int i) throws IndexOutOfBoundsException;

}
