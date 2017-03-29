package com.hrishikesh.practices.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.hrishikesh.practices.graph.GraphStronglyConnectedComponent.getGraphStronglyConnectedComponent;

/**
 * Problem:
 * Strongly Connected Components by Kosaraju’s algorithm
 * A directed graph is strongly connected if there is a path between all pairs of vertices.
 * A strongly connected component (SCC) of a directed graph is a maximal strongly connected subgraph.
 * ;
 * Algorithm
 * - Create a Stack.
 * - Traverse Graph in DFS order
 * - - Put visited vertex in stack only when all adjacent vertex of that vertex visited
 * - Reverse the edges of graph
 * - And traverse graph using vertices that in stack
 *
 * @author hrishikesh.mishra
 * @video https://www.youtube.com/watch?v=RpgcYiky7uw
 * @Link http://hrishikeshmishra.com/strongly-connected-components-kosarajus-algorithm/
 */
public class GraphStronglyConnectedComponent {

    public static List<List<Integer>> getGraphStronglyConnectedComponent(int[][] graph) {
        boolean[] visitedVertices = new boolean[graph.length];

        /** To put vertices in finish time by decreasing order **/
        Stack<Integer> stack = new Stack<>();

        /** DFS and put data in stack **/
        for (int vertex = 0; vertex < graph.length; vertex++) {
            if (!visitedVertices[vertex]) {
                DFSForGraph(graph, visitedVertices, stack, vertex);
            }
        }

        /** Create reverse stack **/
        int[][] reverseGraph = getReverseGraph(graph);

        /** List of all strongly connected component **/
        List<List<Integer>> components = new ArrayList<>();

        /** New array for visited vertices in reverse stack **/
        visitedVertices = new boolean[graph.length];

        /** Process till stack is not empty **/
        while (!stack.isEmpty()) {
            /** Pop element from stack **/
            int vertex = stack.pop();
            /** if its not visited and call DFS on reverse graph **/
            if (!visitedVertices[vertex]) {
                List<Integer> component = new ArrayList<>();
                DFSForReverseGraph(reverseGraph, visitedVertices, component, vertex);

                components.add(component);
            }
        }

        return components;
    }

    private static void DFSForGraph(int[][] graph, boolean[] visitedVertices, Stack<Integer> stack, int vertex) {
        /** Mark vertex as visited **/
        visitedVertices[vertex] = true;

        /** Iterate over all adjacent vertices  **/
        for (Integer adjacentVertex : getAdjacentVertices(graph, vertex)) {
            if (!visitedVertices[adjacentVertex]) {
                DFSForGraph(graph, visitedVertices, stack, adjacentVertex);
            }
        }

        /** When all adjacent vertices process then add vertex to stack **/
        stack.push(vertex);
    }

    private static void DFSForReverseGraph(int[][] graph, boolean[] visitedVertices, List<Integer> list, int vertex) {
        visitedVertices[vertex] = true;
        list.add(vertex);

        for (Integer adjacentVertex : getAdjacentVertices(graph, vertex)) {
            if (!visitedVertices[adjacentVertex]) {
                DFSForReverseGraph(graph, visitedVertices, list, adjacentVertex);
            }
        }

    }

    private static List<Integer> getAdjacentVertices(int[][] graph, int source) {
        List<Integer> adjacentVertices = new ArrayList<>();

        for (int vertex = 0; vertex < graph.length; vertex++) {
            if (graph[source][vertex] == 1) {
                adjacentVertices.add(vertex);
            }
        }

        return adjacentVertices;
    }

    private static int[][] getReverseGraph(int[][] graph) {
        int[][] reverseGraph = new int[graph.length][graph.length];

        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {

                if (graph[row][col] == 1) {
                    reverseGraph[col][row] = 1;
                }
            }
        }

        return reverseGraph;
    }
}


class GraphStronglyConnectedComponentTest {

    public static void main(String[] args) {
        int[][] graph = {
                {0, 0, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
        };

        List<List<Integer>> components = getGraphStronglyConnectedComponent(graph);

        System.out.println("Components are : ");

        for (List<Integer> component : components) {
            System.out.println(component);
        }

    }
}