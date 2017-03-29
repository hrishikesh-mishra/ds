package com.hrishikesh.practices.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static com.hrishikesh.practices.graph.MaximumFlow.find;

/**
 * Problem:
 * Find the maximum flow using Ford Fulkerson Method Edmond Karp's Algorithm.
 * Given a graph which represents a flow network where every edge has a capacity.
 * Also given two vertices source and sink in the graph. Find the maximum possible flow from source to sink.
 * ;
 * Algorithm
     * - Create residualGraph from capacityGraph just by coping value from there to here
     * - - Set maxFlow = 0
     * - Iterate till augmenting path exists in residualGraph (for augmenting path use BFS for DFS)
     * - - Compute bottleneck capacity of augmenting path
     * - - Decrease bottleneck capacity from each edge of augmenting path
         * - - Increase bottleneck capacity from each reverse edge of augmenting path
         * - - Add bottleneck capacity to maxFlow
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=GiN3jRdgxU4
 * @video https://www.youtube.com/watch?v=-8MwfgB-lyM
 * @link http://hrishikeshmishra.com/find-maximum-flow-using-ford-fulkerson-method-edmond-karps-algorithm/
 */
public class MaximumFlow {

    public static void find(int[][] capacityGraph, int source, int sink) {
        /** Creating  residual graph from capacity graph by just coping data **/
        int[][] residualGraph = getResidualGraph(capacityGraph);

        /** Visited vertex **/
        boolean[] visitedVertex = new boolean[capacityGraph.length];

        /** augmented paths from source to destination **/
        List<List<Integer>> augmentedPaths = new ArrayList<>();

        /** Parent child relationship map **/
        Map<Integer, Integer> parents = new HashMap<>();

        /** Max flow **/
        int maxFlow = 0;


        while (BFS(residualGraph, visitedVertex, parents, source, sink)) {

            /** augmented path from source to destination that will **/
            List<Integer> augmentedPath = new ArrayList<>();

            int bottleneckCapacity = Integer.MAX_VALUE;

            /** populate augmented path with max flow with path **/
            int v = sink;
            augmentedPath.add(sink);
            while (v != source) {
                int u = parents.get(v);
                if (bottleneckCapacity > residualGraph[u][v]) {
                    bottleneckCapacity = residualGraph[u][v];
                }
                augmentedPath.add(u);
                v = u;
            }

            /** Reverse augmented path **/
            Collections.sort(augmentedPath);

            /** current augmented path to augmented paths list **/
            augmentedPaths.add(augmentedPath);

            /** Update max flow with current flow **/
            maxFlow += bottleneckCapacity;

            /** Update residualGraph with current flow  **/
            v = sink;
            while (v != source) {
                int u = parents.get(v);
                /** Subtract flow from augmented path's edge **/
                residualGraph[u][v] -= bottleneckCapacity;

                /** Add flow to reverse edge **/
                /**
                 * Why we need this.
                 * http://stackoverflow.com/questions/19453217/why-are-back-edges-required-in-the-ford-fulkerson-algorithm
                 */
                residualGraph[v][u] += bottleneckCapacity;
                v = u;
            }


            /** Clear parent map **/
            parents.clear();

            /** Created new visited vertex for next iteration **/
            visitedVertex = new boolean[capacityGraph.length];

        }

        System.out.println("Paths are: ");
        for (List<Integer> p : augmentedPaths) {
            System.out.println(p);
        }
        System.out.println("Max flow: " + maxFlow);
    }

    private static boolean BFS(int[][] redisualGraph, boolean[] visitedVertex,
                               Map<Integer, Integer> parents, int source, int destination) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            Integer vertex = queue.remove();
            visitedVertex[vertex] = true;

            for (Integer adjacentVertex : getAdjacentVertex(redisualGraph, vertex)) {
                if (!visitedVertex[adjacentVertex] &&
                        redisualGraph[vertex][adjacentVertex] > 0) {

                    parents.put(adjacentVertex, vertex);
                    if (adjacentVertex == destination) {
                        return true;
                    } else {
                        queue.add(adjacentVertex);
                    }
                }
            }
        }

        return false;

    }

    private static List<Integer> getAdjacentVertex(int[][] redisualGraph, int source) {
        List<Integer> adjacentVertex = new ArrayList<>();

        for (int col = 0; col < redisualGraph[source].length; col++) {
            if (redisualGraph[source][col] != 0) {
                adjacentVertex.add(col);
            }
        }
        return adjacentVertex;
    }


    private static int[][] getResidualGraph(int[][] graph) {
        int[][] residualGraph = new int[graph.length][graph[0].length];

        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {
                residualGraph[row][col] = graph[row][col];
            }
        }
        return residualGraph;
    }
}


class MaximumFlowTest {
    public static void main(String[] args) {
        int[][] graph =

                {
                        {0, 3, 0, 3, 0, 0, 0},
                        {0, 0, 4, 0, 0, 0, 0},
                        {3, 0, 0, 1, 2, 0, 0},
                        {0, 0, 0, 0, 2, 6, 0},
                        {0, 1, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 9},
                        {0, 0, 0, 0, 0, 0, 0}
                };

        find(graph, 0, 6);

    }
}