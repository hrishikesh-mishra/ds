package com.hrishikeshmishra.practices.graph;

import java.util.ArrayList;
import java.util.List;

import static com.hrishikeshmishra.practices.graph.PrimMinimumSpanningTree.getMSTByPrimAlgorithm;

/**
 * Problem:
 * Prim’s Minimum Spanning Tree
 * ;
 * Spanning Tree - A tree that spans all the vertices of a connected and undirected graph .
 * Spanning Tree  Properties
 * - for graph G(V, E), it contains exact (V-1) edges
 * - Remove (E-V+1) edges
 * - Maximally acyclic - Means after adding an edge will be cycle
 * - Minimally connected - means removable of an edge from spinning tree it will give more than one components
 * - maximum possible spinning tree is V ^ (V-2)
 * ;
 * Solution:
 * - It's a greedy algorithm
 * - It can be implemented in various ways -like
 * - - By priority queue {@link com.hrishikeshmishra.ns.graph.PrimMinimumSpanningTree}
 * - - By fibonaci heap
 * - - By Binary heap
 * - - Here we'll implement in simple way though its not optimised
 * - The Prim algorithm work this ways -
 * - - Maintain two collections of vertices one that added to output and another that still not process.
 * - - Take distance map for each vertex any add infinity for each vertices
 * - - Take any random vertex and and assign distance to this is 0.
 * - - Now iterate till all vertices not added to MST collection.
 * - - And a vertices from distance map who has smallest distance and that vertex is not added in MST collection
 * - - Process all adjacent vertices at are not added in MST collection and distance is lesser
 * - - All these vertices update parent map and distance map
 * - - Repeat this till all vertices not added to MST collection
 * ;
 * ;
 * Algorithm:
 * - Create distance array and assign INFINITY for vertex
 * - Create parent array
 * - Create visited boolean array
 * - Take any random vertex for example v0
 * - Set distance[v0]=0
 * - Set parent [v0] = -1 or null based parent data type
 * - Now Iterate from V0 to Vn-1
 * - - Take vertex u which has smaller distance in distance array and not visited yet
 * - - Set visited[u] = true
 * - - Iterate all adjacent vertices(v) of vertex u
 * - - - If visited[v] = false and distance[v] < graph[u][v] then
 * - - - - distance[v] = graph[u][v]
 * - - - - parent[v] = u
 *
 * @author hrishikesh.mishra
 * @link https://www.youtube.com/watch?v=z1L3rMzG1_A
 * @link http://hrishikeshmishra.com/prims-minimum-spanning-tree-2/
 */
public class PrimMinimumSpanningTree {

    public static void getMSTByPrimAlgorithm(int[][] graph) {

        /** Distance mpa **/
        int[] distances = new int[graph.length];

        /** Parent of vertices **/
        int[] parents = new int[graph.length];

        /** Vertices that are processed for MST, default value will false for all vertices **/
        boolean[] processedVertices = new boolean[graph.length];

        for (int currentVertex = 0; currentVertex < graph.length; currentVertex++) {
            distances[currentVertex] = Integer.MAX_VALUE;
        }

        /** Taking a random vertex that is vertex (0) **/
        /** Setting parent for Vertex(0) **/
        parents[0] = -1;
        /** Setting distance for vertex(0) **/
        distances[0] = 0;

        for (int counter = 0; counter < graph.length - 1; counter++) {

            /** Get the smallest distance vertex**/
            int smallestDistanceVertex = getSmallestDistanceVertexFromUnProcessed(distances,
                    processedVertices, graph.length);

            /** Add vertex to processed**/
            processedVertices[smallestDistanceVertex] = true;

            for (Integer adjacentVertex : getAdjacentVertex(graph, smallestDistanceVertex)) {

                /** If adjacent vertex is not processed and distance is smallest than map value **/
                if (processedVertices[adjacentVertex] == false &&
                        graph[smallestDistanceVertex][adjacentVertex] < distances[adjacentVertex]) {

                    /** Update parent **/
                    parents[adjacentVertex] = smallestDistanceVertex;

                    /** Update distance **/
                    distances[adjacentVertex] = graph[smallestDistanceVertex][adjacentVertex];
                }
            }
        }

        printMST(parents, graph);
    }

    private static void printMST(int[] parents, int[][] graph) {

        System.out.println("Edge   Weight");
        for (int vertex = 1; vertex < graph.length; vertex++) {
            System.out.println(parents[vertex] + " - " + vertex + " -- " + graph[vertex][parents[vertex]]);
        }
    }


    public static List<Integer> getAdjacentVertex(int[][] graph, int sourceVertex) {
        List<Integer> adjacentVertices = new ArrayList<>();

        for (int destinationVertex = 0; destinationVertex < graph.length; destinationVertex++) {
            if (graph[sourceVertex][destinationVertex] != 0) {
                adjacentVertices.add(destinationVertex);
            }
        }
        return adjacentVertices;
    }

    private static int getSmallestDistanceVertexFromUnProcessed(int[] distances, boolean[] processedVertices,
                                                                int totalVertices) {
        /**
         * **********************************************************************************************
         *
         *  This part is not optimized, for this we can use modified binary heap or priority queue
         *
         * **********************************************************************************************
         */

        /** Dummy initialization **/
        int smallestDistanceVertex = Integer.MAX_VALUE;
        int smallestDistance = Integer.MAX_VALUE;

        /** Iterating over all vertices **/
        for (int vertex = 0; vertex < totalVertices; vertex++) {

            /** Updating smallest distance vertex that not been processed **/
            if (processedVertices[vertex] == false && distances[vertex] < smallestDistance) {
                smallestDistance = distances[vertex];
                smallestDistanceVertex = vertex;
            }
        }

        return smallestDistanceVertex;
    }
}

class PrimMinimumSpanningTreeTest {

    public static void main(String[] args) {
        int[][] graph = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };

        getMSTByPrimAlgorithm(graph);
    }
}