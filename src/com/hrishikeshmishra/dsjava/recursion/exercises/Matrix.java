package com.hrishikeshmishra.dsjava.recursion.exercises;

/**
 * Created by hrishikesh.mishra
 *
 *  R-5.10
 *  Describe a way to use recursion to compute the sum of all the elements in
 *  an n × n (two-dimensional) array of integers.
 */
public class Matrix {

    public static void main(String[] args) {
        int [] [] matrix = {
                {1, 2, 3 },
                {4, 5, 6 },
                {7, 8, 9 },
        };

        System.out.println(sum(matrix, 0, 0));
    }

    public static int sum(int [][] matrix, int row, int col){
        if(row >= matrix.length || col >= matrix[0].length) {
            return 0;
        } else {

            int cell = matrix[row][col ++];

            if(col == matrix[0].length){
                col = 0;
                row++;
            }

            return  cell + sum(matrix, row, col);
        }
    }
}
