package com.hrishikesh.practices.matrix;

import static com.hrishikesh.practices.matrix.RotateMatrixBy90Degree.rotateBy90Degree;

/**
 * Problem:
 * Rotate given matrix by 90 degree but rotation must be in place rotation
 * Conditions:
 * - In place rotation
 * - 2 D matrix
 * - And clockwise rotation
 * ;
 * ;
 * Solution:
 * - We'll rotate layer by layer
 * - Total number layer required to rotate = n / 2
 * - We will swap following cells values for every layer rotation:
 * - - TopLeft -> TopRight
 * - - TopRight - BottomRight
 * - - BottomRight -> BottomLeft
 * - - BottomLeft -> TopRight
 * - - Cells
 * - - - TopLeft = layer, layer
 * - - - TopRight = n - layer - 1, layer
 * - - - BottomLeft = layer, n - layer - 1
 * - - - BottomRight = n - layer - 1, n - layer - 1
 * ;
 * ;
 * Algorithm:
 * - First check matrix is square or not
 * - Iterate layer from 0 to n/2
 * - - Set FirstCell = rotation
 * - - Set LastCell = n - layer - 1
 * - - Iterate cell from FirstCell to LastCell
 * - - - Set offset = i - FirstCell
 * - - - Set topLeftValue = matrix[FirstCell][i]
 * - - - Set matrix[FirstCell][i] = matrix [last-offset][FirstCell]
 * - - - Set matrix [LastCell - offset][FirstCell] =  matrix [last-offset] [LastCell]
 * - - - Set matrix [LastCell - offset][LastCell] = matrix [i][LastCell]
 * - - - Set matrix [i][LastCell] = topLeftValue
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=aClxtDcdpsQ
 * @link http://hrishikeshmishra.com/rotate-matrix-90-degree/
 */
public class RotateMatrixBy90Degree {

    public static void rotateBy90Degree(int[][] matrix) {

        /** Checking N X N matrix **/
        if (matrix.length != matrix[0].length) {
            throw new RuntimeException("Invalid matrix");
        }

        for (int layer = 0; layer < matrix.length / 2; layer++) {
            int firstCell = layer;
            int lastCell = matrix.length - layer - 1;

            for (int i = firstCell; i < lastCell; i++) {
                int offset = i - firstCell;

                /** Extracting top left element **/
                int topLeftElement = matrix[firstCell][i];

                /** TopLeft <- BottomLeft **/
                matrix[firstCell][i] = matrix[lastCell - offset][firstCell];

                /** BottomLeft <- BottomRight **/
                matrix[lastCell - offset][firstCell] = matrix[lastCell][lastCell - offset];

                /** BottomRight <- TopRight **/
                matrix[lastCell][lastCell - offset] = matrix[i][lastCell];

                /** TopRight <- TopLeft **/
                matrix[i][lastCell] = topLeftElement;
            }

        }
    }
}

class RotateMatrixBy90DegreeTest {
    public static void main(String[] args) {
        int [][] matrix3X3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        rotateBy90Degree(matrix3X3);
        printMatrix(matrix3X3);
    }


    private static void printMatrix(int [][] matrix){
        for (int i=0; i< matrix.length; i++){
            for (int j=0; j <matrix.length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
