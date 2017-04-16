package com.hrishikeshmishra.practices.binaryheap;

import java.util.Comparator;
import java.util.Objects;

/**
 * Problem:
 * Kth smallest element in a row-wise and column-wise sorted Matrix
 * Given an n x n matrix, where every row and column is sorted in non-decreasing order.
 * Find the kth smallest element in the given 2D array.Your task is to complete the function kthSmallest
 * which takes two arguments the first is a matrix (mat) and sec argument is the order of the matrix (n) and
 * the function returns the kth smallest element in the matrix.
 * ;
 * For example, consider the following 2D array.
 * 10, 20, 30, 40
 * 15, 25, 35, 45
 * 24, 29, 37, 48
 * 32, 33, 39, 50
 * The 3rd smallest element is 20 and 7th smallest element is 30
 * ;
 * ;
 * Solution
 * - Question is based on Tournament Tree (Winner Tree).
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/kth-smallest-element-row-wise-column-wise-sorted-matrix/
 */
public class KthSmallestElementInMatrix {

    private static class Node {
        private int value;
        private int row;
        private int column;

        public Node() {
        }

        public Node(int value, int row, int column) {
            this.value = value;
            this.row = row;
            this.column = column;
        }


        @Override
        public String toString() {
            return "(" +
                    "v=" + value +
                    ", R=" + row +
                    ", C=" + column +
                    ')';
        }
    }

    private static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.value, o2.value);
        }
    }


    public static int findKthSmallest(int[][] matrix, int k) {

        int totalNumberOfElementsInMatrix = matrix.length * matrix.length;

        if (k > totalNumberOfElementsInMatrix) {
            throw new RuntimeException("K out of range of provided matrix.");
        }

        /** Create a Binary Head **/
        ArrayBinaryHeap<Node> binaryHeap = new ArrayBinaryHeap<>(matrix.length * matrix.length, new NodeComparator());

        /** Array to hold current head of each columns of matrix **/
        int[] currentHeadPointers = new int[matrix.length];

        /** Inserting top head of each columns of matrix in binary tree  **/
        for (int i = 0; i < matrix.length; i++) {
            binaryHeap.insert(new Node(matrix[0][i], 0, i));

            /** Updating current pointer to inserted head of each column **/
            currentHeadPointers[i] = 0;
        }


        Node minNode;

        /** Iterate till i < k **/
        for (int i = 1; i < k; i++) {
            minNode = binaryHeap.extractMin();

            /** Get next element from matrix to insert in binary heap **/
            Node elementToInsert = getElementToInsert(matrix, currentHeadPointers, minNode);

            /** If next element found **/
            if (!Objects.isNull(elementToInsert)) {
                currentHeadPointers[elementToInsert.column] = elementToInsert.row;
                binaryHeap.insert(elementToInsert);
            }
        }

        /** i == k **/
        minNode = binaryHeap.extractMin();

        return minNode.value;

    }

    private static Node getElementToInsert(int[][] matrix, int[] currentHeadPointers, Node lastRemovedNode) {
        int minNodeColumn = lastRemovedNode.column;

        /** Is there element available in column **/
        if (isElementAvailableInColumn(matrix, currentHeadPointers, minNodeColumn)) {

            /** Return next element from last min column **/
            return new Node(matrix[currentHeadPointers[minNodeColumn] + 1][minNodeColumn],
                    currentHeadPointers[minNodeColumn] + 1, minNodeColumn);

        } else {

            /** If no element left in last min column, then try get it from other column which have smaller value **/
            Node temp = null;

            /** Iterate all columns of matrix **/

            for (int i = 0; i < matrix.length; i++) {

                /** If no element available for current column, then don't do anything **/
                if (!isElementAvailableInColumn(matrix, currentHeadPointers, i)) {
                    continue;
                }

                /** Swap if smaller than previous one **/
                if (Objects.isNull(temp) || temp.value < matrix[currentHeadPointers[i] + 1][i]) {
                    temp = new Node(matrix[currentHeadPointers[i] + 1][i], currentHeadPointers[i] + 1, i);
                }

            }

            return temp;
        }

    }

    private static boolean isElementAvailableInColumn(int[][] matrix, int[] currentHeadPointers, int column) {
        return (currentHeadPointers[column] < matrix.length - 1);
    }
}


class KthSmallestElementInMatrixTest {
    public static void main(String[] args) {

        int[][] matrix = {
                {10, 20, 30, 40},
                {15, 25, 35, 45},
                {24, 29, 37, 48},
                {32, 33, 39, 50}
        };

        System.out.println("When k == 1 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 1));
        System.out.println("When k == 2 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 2));
        System.out.println("When k == 3 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 3));
        System.out.println("When k == 4 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 4));
        System.out.println("When k == 5 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 5));
        System.out.println("When k == 6 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 6));
        System.out.println("When k == 7 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 7));
        System.out.println("When k == 8 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 8));
        System.out.println("When k == 9 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 9));
        System.out.println("When k == 10 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 10));
        System.out.println("When k == 11 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 11));
        System.out.println("When k == 12then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 12));
        System.out.println("When k == 13 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 13));
        System.out.println("When k == 14 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 14));
        System.out.println("When k == 15 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 15));
        System.out.println("When k == 16 then : " + KthSmallestElementInMatrix.findKthSmallest(matrix, 16));



    }

}