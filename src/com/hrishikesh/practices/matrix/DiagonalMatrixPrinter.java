package com.hrishikesh.practices.matrix;

/**
 * Problem:
 * Print matrix in diagonal pattern
 * ;
 * Given a matrix of n*n size, the task is to print its elements in diagonally pattern.
 *
 * @author hrishikesh.mishra
 * @TODO incomplete
 */
public class DiagonalMatrixPrinter {

    private enum Flow {
        TOP_DOWN,
        BOTTOM_UP
    }

    private enum Part {
        FIRST_PART,
        SECOND_PART
    }

    public static void print(int[][] matrix) {
        int n = matrix.length;

        Flow currentFlow = Flow.BOTTOM_UP;
        Part part = Part.FIRST_PART;

        int row = 0;
        int col = 0;


        for (int i = 0; i < n * n; ) {

            while ((part == Part.FIRST_PART && currentFlow == Flow.BOTTOM_UP && row >= 0) ||
                    (part == Part.FIRST_PART && currentFlow == Flow.TOP_DOWN && col >= 0) ||
                    (part == Part.SECOND_PART && currentFlow == Flow.BOTTOM_UP && col <= n - 1) ||
                    (part == Part.SECOND_PART && currentFlow == Flow.TOP_DOWN && row <= n - 1)) {


                System.out.printf("(%d, %d)", row, col);

                if (currentFlow == Flow.BOTTOM_UP) {
                    row--;
                    col++;
                } else {
                    row++;
                    col--;
                }

                i++;
            }


            /** Rest counter **/
            if (part == Part.FIRST_PART) {
                if (currentFlow == Flow.BOTTOM_UP) {
                    row++;
                    currentFlow = Flow.TOP_DOWN;
                } else {
                    col++;
                    currentFlow = Flow.BOTTOM_UP;
                }
            } else {
                if (currentFlow == Flow.BOTTOM_UP) {
                    col--;
                    row += 2;
                    currentFlow = Flow.TOP_DOWN;
                } else {
                    row--;
                    col += 2;
                    currentFlow = Flow.BOTTOM_UP;
                }
            }

            if (part == Part.FIRST_PART && col == n) {
                part = Part.SECOND_PART;
                col = n - 1;
                row = 1;
            } else if (part == Part.FIRST_PART && row == n) {
                part = Part.SECOND_PART;
                row = n - 1;
                col = 1;
            }

            System.out.println();

        }


    }

}

class DiagonalMatrixPrinterTest {
    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3, 1},
                {4, 5, 6, 3},
                {7, 8, 9, 4}
        };

        DiagonalMatrixPrinter.print(matrix);

    }
}
