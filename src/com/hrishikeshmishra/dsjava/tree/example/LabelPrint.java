package com.hrishikeshmishra.dsjava.tree.example;

import com.hrishikeshmishra.dsjava.list_adt.core.Position;
import com.hrishikeshmishra.dsjava.tree.core.Tree;

import java.util.ArrayList;

/**
 * Prints labeled representation of subtree of T rooted
 * at p having depth d
 *
 * Created by hrishikesh.mishra on 25/03/16.
 */
public class LabelPrint {

    public static <E> void printPreorderLabeled(Tree<E> T, Position<E> p, ArrayList<Integer> path){
        int d = path.size(); //depth equals the length of the path
        System.out.println(space(2*d)); // print indentation, then label

        for(int j=0; j<d; j++)
            System.out.println(path.get(j) + (j == d-1? " " : "."));

        System.out.println(p.getElement());
        path.add(1);

        for(Position<E> c: T.children(p)){
            printPreorderLabeled(T, c, path);
            path.set(d, 1+path.get(d));
        }

        path.remove(d);

    }
    private static String space(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j=1; j<=i; j++)
            stringBuilder.append(" ");
        return stringBuilder.toString();
    }
}

