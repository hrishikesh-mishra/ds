package com.hrishikesh.dsjava.list_adt.core;

/**
 * Created by hrishikesh.mishra on 24/03/16.
 */
public interface Position<E> {

    /**
     * Returns the element stored at this position
     *
     *
     * @return the stored element
     * @throws IllegalStateException if position no longer valid
     */
    E getElement() throws IllegalStateException;
}

