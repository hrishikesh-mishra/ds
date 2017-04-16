package com.hrishikeshmishra.dsjava.list_adt.sample;

import com.hrishikeshmishra.dsjava.list_adt.core.List;
import com.hrishikeshmishra.dsjava.list_adt.core.impl.ArrayList;

import java.util.Arrays;

/**
 * Created by hrishikesh.mishra on 24/03/16.
 */
public class ArrayListTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(5);
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        System.out.println("List: " + list);
        list.add(1, 4);
        System.out.println("List: " + list);
        System.out.println("List removed index 2 : " + list.remove(2));
        System.out.println("After removable, List: " + list);
        list.add(3, 5);
        System.out.println("List: " + Arrays.asList(list));
        list.add(4, 15);
        System.out.println("List: " + Arrays.asList(list));
        list.add(4, 15);
        System.out.println("List: " + Arrays.asList(list));
    }
}
