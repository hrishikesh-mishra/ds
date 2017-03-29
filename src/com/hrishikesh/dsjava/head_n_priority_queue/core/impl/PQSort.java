package com.hrishikesh.dsjava.head_n_priority_queue.core.impl;

import com.hrishikesh.dsjava.list_adt.core.impl.PositionalList;
import com.hrishikesh.dsjava.head_n_priority_queue.core.PriorityQueue;


/**
 *
 * ------------------------------------------------------------------------------------------
 * The algorithm for sorting a sequence S with a priority queue P is quiet simple and
 * consists of the following two phases:
 * ------------------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------------------
 * 1. In the first phase, we insert the elements of S as keys into an initially emtpy priority
 * queue P by means of a series of n insert operations, one for each element.
 * ------------------------------------------------------------------------------------------
 * 2. In second phase, we extract the elements from P in nondecreasing order by means of a
 * series of n removeMin operations, putting them back into S in that order.
 * ------------------------------------------------------------------------------------------
 *
 *
 * Created by hrishikesh.mishra on 26/03/16.
 */
public class PQSort {

    /**
     * Sorts sequences S, using initially empty priority queue
     * P to produce the order
     *
     * @param S
     * @param P
     * @param <E>
     */
    public static <E> void sort(PositionalList<E> S, PriorityQueue<E, ?> P){
        int n = S.size();
        for(int j =0; j<n ; j++){
            E element = S.remove(S.first());
            /** Element is key; null value**/
            P.insert(element, null);
        }

        for (int j=0; j<n; j++){
            E element = P.removeMin().getKey();
            /** the smallest key in P is next placed in S **/
            S.addLast(element);
        }
    }

}
