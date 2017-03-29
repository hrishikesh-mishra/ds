package com.hrishikesh.practices.array;

import java.util.ArrayList;
import java.util.List;

import static com.hrishikesh.practices.array.PascalsTriangle.generate;

/**
 * Problem
 * Pascal's Triangle
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/pascals-triangle/
 */
public class PascalsTriangle {

    public static List<List<Integer>> generate(int rows) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> topRow = new ArrayList<>();
        topRow.add(1);
        triangle.add(topRow);

        for (int i = 2; i <= rows; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i) {
                    row.add(1);
                } else {
                    row.add(triangle.get(i - 2).get(j - 2) + triangle.get(i - 2).get(j - 1));
                }
            }

            triangle.add(row);
        }

        return triangle;
    }


    public List<Integer> getRow(int rowNumber) {
        List<Integer> row = new ArrayList<>();
        if (rowNumber == 0) {
            return row;
        }

        row.add(1);

        if (rowNumber == 1) {
            return row;
        }

        for (int i = 2; i <= rowNumber; i++) {
            for (int j = row.size() - 2; j >= 0; j--) {
                row.set(j + 1, row.get(j) + row.get(j + 1));
            }
            row.add(1);
        }

        return row;
    }
}


class PascalsTriangleTest {
    public static void main(String[] args) {
        List<List<Integer>> triangle = generate(7);
        print(triangle);
    }

    private static void print(List<List<Integer>> triangle) {
        System.out.println("Triangle:");
        for (int i = 0; i < triangle.size(); i++) {
            System.out.println(triangle.get(i));
        }
    }
}