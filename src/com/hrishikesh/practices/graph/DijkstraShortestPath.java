package com.hrishikesh.practices.graph;

import java.util.ArrayList;
import java.util.List;

import static com.hrishikesh.practices.graph.DijkstraShortestPath.findShortestPathByDijkstra;

/**
 * Problem:
 * Implementing Dijkstra
 * Given an adjacency matrix (graph), the task is to find the shortest
 * distance of all the vertex's from the source vertex.
 * ;
 * ;
 * Solution:
 * - It is very similar to {@link PrimMinimumSpanningTree}, in prim we just take edge weight but
 * here we take summation for edge's weight from source.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - -- - - - - - - -
 * Algorithm
 * - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - -- - - - - - - -
 * - Let the distance of start vertex from start vertex  = 0
 * - Let the distance for all other vertex from start vertex = INFINITY
 * -
 * - Repeat
 * - - Visit the unvisited vertex with the smallest known distance from the start vertex
 * - - For the current vertex, examine its unvisited neighbours
 * - - For the current vertex, calculate distance of each neighbours from start vertex
 * - - If the calculated distance of a vertex is less than known distance, update the shortest distance
 * - - Update the previous vertex for each of the updated distances
 * - - Add the current vertex to the list of the visited vertices
 * - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - - - - - - -- - - - - - - -
 *
 * @author hrishikesh.mishra
 * @vide https://www.youtube.com/watch?v=pVfj6mxhdMw
 * @link https://en.wikipedia.org/wiki/Dijkstra's_algorithm
 * @link http://hrishikeshmishra.com/implement-dijkstra/
 */
public class DijkstraShortestPath {

    public static void findShortestPathByDijkstra(int[][] graph, int sourceVertex) {
        /** To hold vertex is visited or not **/
        boolean[] visitedVertex = new boolean[graph.length];

        /** To hold parent of a vertex **/
        int[] parents = new int[graph.length];

        /** To hold distances from source vertex to all other vertices **/
        int[] distances = new int[graph.length];

        /** Updating distance to INFINITY for all vertices **/
        for (int vertex = 0; vertex < graph.length; vertex++) {
            distances[vertex] = Integer.MAX_VALUE;
        }

        /** Setting  distance for start vertex  **/
        distances[sourceVertex] = 0;

        /** parent of source vertex is invalid **/
        parents[sourceVertex] = -1;

        /** Process all vertices **/
        for (int counter = 0; counter < graph.length - 1; counter++) {

            /** Get unvisited vertex that has minimum distance from source vertex **/
            int minimumDistanceVertex = getMinimumDistanceVertex(visitedVertex, distances, graph.length);

            /** Mark this vertex as visited **/
            visitedVertex[minimumDistanceVertex] = true;

            /** Iterate over all adjacent vertices of  minimumDistanceVertex **/
            for (Integer adjacentVertex : getAdjacentVertices(graph, minimumDistanceVertex)) {

                /** If adjacentVertex is not visited and new distance is less than known distance  **/
                if (!visitedVertex[adjacentVertex] && distances[adjacentVertex] >
                        graph[minimumDistanceVertex][adjacentVertex] + distances[minimumDistanceVertex]) {

                    /** the udpate parent and distance **/
                    parents[adjacentVertex] = minimumDistanceVertex;
                    distances[adjacentVertex] = graph[minimumDistanceVertex][adjacentVertex] +
                            distances[minimumDistanceVertex];
                }
            }
        }

        printShortestPath(parents, distances, graph);

    }

    private static void printShortestPath(int[] parents, int[] distances, int[][] graph) {

        System.out.println("Vertex\tDistance\tParent");
        for (int vertex = 0; vertex < graph.length; vertex++) {
            System.out.printf("%d\t\t%d\t\t\t%d\n", vertex, distances[vertex], parents[vertex]);
        }
    }

    private static List<Integer> getAdjacentVertices(int[][] graph, int vertex) {
        List<Integer> adjacentVertices = new ArrayList<>();

        for (int adjacentVertex = 0; adjacentVertex < graph[vertex].length; adjacentVertex++) {
            if (graph[vertex][adjacentVertex] != 0) {
                adjacentVertices.add(adjacentVertex);
            }
        }
        return adjacentVertices;
    }

    private static int getMinimumDistanceVertex(boolean[] visitedVertex, int[] distances, int totalVertex) {
        /**
         * ******************************************************************************
         *
         * This code we can optimised by using priority queue or some other data structure
         * {@link com.hrishikesh.ns.graph.DijkstraShortestPathFinder}
         *
         * ******************************************************************************
         */

        /** Dummy value for distance and vertex **/
        int minimumDistance = Integer.MAX_VALUE;
        int minimumDistanceVertex = Integer.MAX_VALUE;

        for (int vertex = 0; vertex < totalVertex; vertex++) {
            if (!visitedVertex[vertex] && distances[vertex] < minimumDistance) {
                minimumDistance = distances[vertex];
                minimumDistanceVertex = vertex;
            }
        }
        return minimumDistanceVertex;
    }
}


class DijkstraShortestPathTest {
    public static void main(String[] args) {

        int graph[][] = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        findShortestPathByDijkstra(graph, 0);
    }
}

