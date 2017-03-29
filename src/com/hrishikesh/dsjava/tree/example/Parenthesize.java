package com.hrishikesh.dsjava.tree.example;

import com.hrishikesh.dsjava.list_adt.core.Position;
import com.hrishikesh.dsjava.tree.core.Tree;

/**
 *
 * Prints parenthesized representation of subtree of T
 * rooted at p.
 *
 * Created by hrishikesh.mishra on 25/03/16.
 */
public class Parenthesize {

    public static <E> void parenthesize(Tree<E> T,  Position<E> p){
        System.out.println(p.getElement());
        if(T.isInternal(p)){
            boolean firstTime = true;
            for(Position<E> c: T.children(p)){
                System.out.println(firstTime? " (": ", ");
                firstTime = false;
                parenthesize(T, c);
            }
            System.out.println(")");
        }
    }
}
