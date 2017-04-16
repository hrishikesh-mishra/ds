package com.hrishikeshmishra.practices.graph;

import static com.hrishikeshmishra.practices.graph.IslandsFinder.getNumberIslands;

/**
 * Problem:
 * Find the number of islands.
 * Given a boolean 2D matrix, find the number of islands.
 * This is an variation of the standard problem: "Counting number of connected components in a undirected graph".
 * ;
 * A connected component - connected component of an undirected graph is a subgraph in which every
 * two vertices are connected to each other by a path(s), and which is connected to no other vertices
 * outside the subgraph.
 * ;
 * ;
 * Solution:
 * - This problem can be solved by DFS
 * ;
 * Algorithm
 * - Set numberOfIsLands = 0
 * - Create visitVertex boolean array
 * - Iterate rows of graph from 0 to n-1
 * - - Iterate cols of graph from 0 to n-1
 * - - - If graph[row][col] is not visited then
 * - - - - Set numberOfIsLands = numberOfIsLands + 1
 * - - - - Call modified DFS with graph, current row, current column and visitVertex array
 * - return numberOfIsLands
 *
 * @author hrishikesh.mishra
 * @link https://en.wikipedia.org/wiki/Connected_component_(graph_theory)
 * @link https://www.youtube.com/watch?v=CGMNePwovA0
 * @link http://hrishikeshmishra.com/find-number-islands/
 */
public class IslandsFinder {

    public static int getNumberIslands(int[][] graph) {
        int numberOfIslands = 0;

        boolean[][] visitedVertices = new boolean[graph.length][graph.length];

        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {

                if (graph[row][col] == 1 && !visitedVertices[row][col]) {
                    numberOfIslands++;
                    DFS(graph, visitedVertices, row, col);
                }
            }
        }

        return numberOfIslands;
    }

    private static boolean isValidMove(int[][] graph, boolean[][] visited,
                                       int row, int col, int rowMax, int colMax) {
        return (row >= 0 && col >= 0) &&
                (row < rowMax && col < colMax) &&
                (graph[row][col] == 1) &&
                (!visited[row][col]);
    }

    public static void DFS(int[][] graph, boolean[][] visitedVertices, int row, int col) {

        /** Mark current visiting vertex as visited **/
        visitedVertices[row][col] = true;

        /** All 8 possible moves **/
        int[] possibleRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] possibleCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        /** Iterate all possible moves **/
        for (int i = 0; i < 8; i++) {
            if (isValidMove(graph, visitedVertices, row + possibleRow[i],
                    col + possibleCol[i], graph.length, graph[row].length)) {

                DFS(graph, visitedVertices, row + possibleRow[i], col + possibleCol[i]);
            }
        }
    }

}


class IslandsFinderTest {
    public static void main(String[] args) {
        int[][] graph = {
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        System.out.println("Number of Islands are: " + getNumberIslands(graph));
    }
}