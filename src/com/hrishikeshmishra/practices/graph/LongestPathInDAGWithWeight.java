package com.hrishikeshmishra.practices.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static com.hrishikeshmishra.practices.graph.LongestPathInDAGWithWeight.Graph;
import static com.hrishikeshmishra.practices.graph.LongestPathInDAGWithWeight.getLongestPath;

/**
 * Problem:
 * Longest Path in a Directed Acyclic Graph with weight
 * ;
 * Longest Path  in a DAG represents minimum numbers of steps to list all vertices in group.
 * ;
 * *********************************************************************************
 * Equivalent to finding longest path in the DAG
 * - If inDegree (j) = 0 then, longest_path_to(j) = 0
 * - If InDegree (k) > 0 then, longest_path_to(k) is 1 + max{longest_path_to(j)}
 * among all incoming neighbours j to k
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * - To compute longest_path_to(k)
 * -  - Need longest_path_to(j) for all incoming neighbours of k
 * - If j is an incoming neighbour (j,k) in E
 * - - j is enumerated before k in topological order
 * - Hence, compute longest_path_to(i) in topological  order
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * - Let i1, i2, .... in be topological ordering of V
 * - All the neighbours of ik appears before it in this list
 * - From left to right, compute longest_path_to(ik) as  1 + max{longest_path_to(ij)}
 * among all incoming neighbours ij to ik
 * - Can combine this calculation with topological sort
 * *********************************************************************************
 *
 * @author hrishikesh.mishra
 * @link https://www.youtube.com/watch?v=nu88oy8U0Vo
 * @link https://www.youtube.com/watch?v=lRH0tax5dFA
 * @link http://hrishikeshmishra.com/longest-path-directed-acyclic-graph-weight/
 */
public class LongestPathInDAGWithWeight {

    public static class AdjacentNode {
        private Integer vertex;
        private int weight;

        public AdjacentNode(Integer vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return vertex + "(" + weight + ")";
        }
    }


    public static class Graph {
        private int numberOfVertices;
        private List<AdjacentNode> adjacencyLists[];

        public Graph(int numberOfVertices) {
            this.numberOfVertices = numberOfVertices;
            init(numberOfVertices);
        }

        private void init(int numberOfVertices) {
            adjacencyLists = (List<AdjacentNode>[]) Array.newInstance(List.class, numberOfVertices);

            for (int i = 0; i < numberOfVertices; i++) {
                adjacencyLists[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            AdjacentNode adjacentNode = new AdjacentNode(destination, weight);
            adjacencyLists[source].add(adjacentNode);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numberOfVertices; i++) {
                sb.append(i + " -> " + adjacencyLists[i] + "\n");
            }
            return sb.toString();
        }

        public int getNumberOfVertices() {
            return numberOfVertices;
        }
    }

    public static class TopologicalSort {

        public static List<Integer> sort(Graph g) {
            boolean[] visited = new boolean[g.numberOfVertices];
            Stack<Integer> stack = new Stack<>();

            for (Integer currentVertex = 0; currentVertex < g.numberOfVertices; currentVertex++) {
                if (!visited[currentVertex]) {
                    sort(g, currentVertex, visited, stack);
                }
            }

            List<Integer> topologicalSort = new ArrayList<>();

            while (!stack.isEmpty()) {
                topologicalSort.add(stack.pop());
            }

            return topologicalSort;
        }

        private static void sort(Graph g, Integer currentVertex, boolean[] visited, Stack<Integer> stack) {
            visited[currentVertex] = true;

            for (AdjacentNode adjacentVertex : g.adjacencyLists[currentVertex]) {
                if (!visited[adjacentVertex.vertex]) {
                    sort(g, adjacentVertex.vertex, visited, stack);
                }
            }

            stack.push(currentVertex);
        }
    }


    public static Integer[] getLongestPath(Graph graph, Integer source) {
        List<Integer> topologicalSort = TopologicalSort.sort(graph);
        Integer[] distances = new Integer[graph.numberOfVertices];

        for (Integer currentVertex = 0; currentVertex < graph.numberOfVertices; currentVertex++) {
            distances[currentVertex] = Integer.MIN_VALUE;
        }

        distances[source] = 0;

        for (Integer currentVertex : topologicalSort) {

            if (distances[currentVertex] != Integer.MIN_VALUE) {
                for (AdjacentNode adjacentVertex : graph.adjacencyLists[currentVertex]) {

                    if (distances[adjacentVertex.vertex] < distances[currentVertex] + adjacentVertex.weight) {
                        distances[adjacentVertex.vertex] = distances[currentVertex] + adjacentVertex.weight;
                    }
                }
            }
        }

        return distances;

    }

}


class LongestPathInDAGWithWeightTest {
    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 5, 1);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);

        System.out.println(g);
        Integer[] distances = getLongestPath(g, 1);
        for (Integer currentVertex = 0; currentVertex < g.getNumberOfVertices(); currentVertex++) {
            System.out.println("Vertex (" + currentVertex + ") -- distance = " + distances[currentVertex]);
        }
    }
}