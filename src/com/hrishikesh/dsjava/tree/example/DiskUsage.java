package com.hrishikesh.dsjava.tree.example;

import com.hrishikesh.dsjava.list_adt.core.Position;
import com.hrishikesh.dsjava.tree.core.Tree;

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
