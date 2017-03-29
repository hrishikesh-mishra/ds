package com.hrishikesh.dsjava.tree.example;

import com.hrishikesh.dsjava.list_adt.core.Position;
import com.hrishikesh.dsjava.tree.core.Tree;

/**
 * Created by hrishikesh.mishra on 25/03/16.
 *
 * Print like this:
 *
 * Paper
 *  Title
 *  Abstract
 *      $1
 *          $1.1
 *          $1.2
*       $2
 *          $2.2
 *      ...........
 */
public class IndentPrint {


    public static <E> void printPreorderIndent(Tree<E> T, Position<E> p, int d){
        System.out.println(space(2*d) +  p.getElement());
        for(Position<E> c : T.children(p))
            printPreorderIndent(T, c, d+1);
    }

    private static String space(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j=1; j<=i; j++)
            stringBuilder.append(" ");
        return stringBuilder.toString();
    }
}
