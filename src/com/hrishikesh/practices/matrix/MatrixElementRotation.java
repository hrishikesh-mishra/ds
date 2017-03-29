package com.hrishikesh.practices.matrix;


import java.util.LinkedList;
import java.util.Queue;

import static com.hrishikesh.practices.matrix.MatrixElementRotation.rotate2;

/**
 * <p>
 * 1   2   3   4
 * 5   6   7   8
 * 9  10  11  12
 * 13  14  15  16
 * ;
 * ;
 * to
 * ;
 * ;
 * 2   3   4   8
 * 1   7  11  12
 * 5   6  10  16
 * 9  13  14  15
 * </p>
 *
 * @author hrishikesh.mishra
 */
public class MatrixElementRotation {

    public static void rotate2(int[][] matrix, int rotations) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int level = 0;

        for (int i = 0; i < rows / 2; i++) {
            getArrays(rows, cols, i, matrix, rotations);
        }

    }

    private static void getArrays(int rows, int cols, int level, int[][] matrix, int rotations) {
        int row = rows - (2 * level);
        int col = cols - (2 * level);

        Queue<Integer> queue = new LinkedList<>();

        int startRow = level;
        int startCol = level;
        int endRow = level + row - 1;
        int colEnd = level + col - 1;


        /** Top Rows **/
        for (int i = startCol; i <= colEnd; i++) {
            queue.add(matrix[startRow][i]);
        }

        /** Right Column **/
        for (int i = startRow + 1; i < endRow; i++) {
            queue.add(matrix[i][colEnd]);
        }

        /** Bottom Rows **/
        for (int i = colEnd; i >= startCol; i--) {
            queue.add(matrix[endRow][i]);
        }

        /** Left Column **/
        for (int i = endRow - 1; i > startRow; i--) {
            queue.add(matrix[i][startCol]);
        }

        System.out.println(queue);

        for (int i = 0; i < rotations; i++) {
            queue.add(queue.remove());
        }


        /** Top Rows **/
        for (int i = startCol; i <= colEnd; i++) {
            matrix[startRow][i] = queue.remove();
        }

        /** Right Column **/
        for (int i = startRow + 1; i < endRow; i++) {
            matrix[i][colEnd] = queue.remove();
        }

        /** Bottom Rows **/
        for (int i = colEnd; i >= startCol; i--) {
            matrix[endRow][i] = queue.remove();
        }

        /** Left Column **/
        for (int i = endRow - 1; i > startRow; i--) {
            matrix[i][startCol] = queue.remove();
        }


    }

}

class MatrixElementRotationTest {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        System.out.println("Before Rotation:");
        print(matrix);
        rotate2(matrix, 1);
        System.out.println("\n\n");
        print(matrix);

    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }

            System.out.println();
        }
    }
}