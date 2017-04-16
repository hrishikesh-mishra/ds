package com.hrishikeshmishra.dsjava.tree.example;

import com.hrishikeshmishra.dsjava.list_adt.core.Position;
import com.hrishikeshmishra.dsjava.tree.core.Tree;

/**
 * Created by hrishikesh.mishra on 25/03/16.
 */
public class DiskUsage {

    /**
     *  Returns total disk space for
     *  subtree of T rooted at p.
     *
     * @param T
     * @param p
     * @return
     */
    public static int diskSpace(Tree<Integer> T, Position<Integer> p){
       int subtotal = p.getElement();
       for(Position<Integer> c : T.children(p))
           subtotal += diskSpace(T, c);
       return subtotal;
    }
}
