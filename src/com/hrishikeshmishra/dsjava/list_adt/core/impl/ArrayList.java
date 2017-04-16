package com.hrishikeshmishra.dsjava.list_adt.core.impl;


import com.hrishikeshmishra.dsjava.list_adt.core.List;

import java.util.Arrays;

/**
 * Created by hrishikesh.mishra on 24/03/16.
 */
public class ArrayList<E> implements List<E> {

    private static final int CAPACITY = 50;
    private E [] data;
    private int size = 0;

    public ArrayList() {
        this(CAPACITY);
    }

    public ArrayList(int capacity){
        data = (E []) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E old = data[i];
        data[i] = e;
        return old;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size+1);
        if(size == data.length)
            throw new IllegalStateException("Array is full.");

        for(int j = size-1; j >= i; j--)
            data[j+1] = data[j];

        data[i] = e;
        size++;

    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        for(int j=i; j<size-1; j++)
            data[j] = data[j+1];

        E temp= data[size-1];
        data[size-1] = null;
        size--;
        return temp;
    }

    private void checkIndex(int i, int n){
        if(i <0 || i >= n) throw new IndexOutOfBoundsException("Illegal index : " + i);
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}
