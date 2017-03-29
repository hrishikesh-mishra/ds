package com.hrishikesh.practices.graph;

import java.util.Objects;

import static com.hrishikesh.practices.graph.FloydWarshallShortestPath.findShortestPath;

/**
 * Problem:
 * Floyd Warshall Algorithm to find  shortest path weighted directed Graph.
 * - Its an algorithm that finds the shortest path between every pair of vertices of graph
 * - Supports negative edges-weights but not n egative-weight cycles
 * - Runs in O(n^3) times
 * - Increases numbers of hops allowed by 1 with each iteration.
 * - Its a dynamic programming algorithm
 * - Application of Floyd Warshall Algorithm
 * - - Clean's algorithm
 * - - Transitive closure
 * - - Widest path in the graph
 * --------------------------------------------------------------------------------------------
 * Algorithm
 * - Need two matrix of same size of graph (Distance matrix and path matrix)
 * - Initially - We put all distances in distance matrix if there is any distance in graph
 * - And use infinity in distance matrix if there is no path
 * - It iterate for all vertices one by one
 * - - if d[i] [j] > d[i][k] + d[k][j] then update distance matrix and path matrix.
 * - - Here eveytime we're checking do we have a shortest path from i to j by take k as
 * intermediate vertex.
 * --------------------------------------------------------------------------------------------
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=KQ9zlKZ5Rzc
 * @video https://www.youtube.com/watch?v=LwJdNfdLF9s
 * @link http://hrishikeshmishra.com/floyd-warshall-algorithm-find-shortest-path-weighted-directed-graph/
 */
public class FloydWarshallShortestPath {


    public static void findShortestPath(Integer[][] graph) {
        /** To hold distance **/
        int[][] distance = new int[graph.length][graph[0].length];

        /** To hold path **/
        Integer[][] path = new Integer[graph.length][graph[0].length];

        /** If we use Integer.MAX_VALUE for arthematic operation then,
         *  there could be overflow and lead to incorrect result
         **/
        int maxValue = 999999;
        /** Initialize distance and path **/
        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {

                if (!Objects.isNull(graph[row][col])) {
                    distance[row][col] = graph[row][col];
                    path[row][col] = col;
                } else {
                    distance[row][col] = maxValue;
                    path[row][col] = null;
                }
            }
        }

        /** Calculate shortest path **/
        for (int k = 0; k < graph.length; k++) {
            for (int row = 0; row < graph.length; row++) {
                for (int col = 0; col < graph[row].length; col++) {
                    if (distance[row][col] > distance[row][k] + distance[k][col]) {
                        distance[row][col] = distance[row][k] + distance[k][col];
                        path[row][col] = path[k][col];
                    }
                }
            }
        }

        /** Printing shortest paths **/
        System.out.println("From\tTo\tDistance");
        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {

                System.out.printf("%d\t\t%d\t\t%s\n", row, col,
                        (distance[row][col] == maxValue) ? "∞" : String.valueOf(distance[row][col]));
            }
        }

    }

}


class FloydWarshallShortestPathTest {
    public static void main(String[] args) {

        /** Here we have take null for non existing edges **/
        Integer[][] graph = {
                {0, 3, 6, 15},
                {null, 0, -2, null},
                {null, null, 0, -2},
                {1, null, null, 0},
        };

        findShortestPath(graph);
    }
}