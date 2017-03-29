package com.hrishikesh.practices.graph;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringJoiner;

import static com.hrishikesh.practices.graph.GraphDepthFirstTraversal.*;

/**
 * Problem:
 * Depth First Traversal for a Graph
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/implemente-depth-first-traversal-graph/
 */
public class GraphDepthFirstTraversal {

    public static class Graph {
        private int numberOfVertices;
        private List<Integer> adjacencyLists[];

        public Graph(int numberOfVertices) {
            this.numberOfVertices = numberOfVertices;
            init(numberOfVertices);
        }

        private void init(int numberOfVertices) {
            adjacencyLists = (List<Integer>[]) Array.newInstance(List.class, numberOfVertices);

            for (int i = 0; i < numberOfVertices; i++) {
                adjacencyLists[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination) {
            adjacencyLists[source].add(destination);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numberOfVertices; i++) {
                sb.append(i + " -> " + adjacencyLists[i] + "\n");
            }
            return sb.toString();
        }
    }

    /**
     * It assume that graph is connected graph
     *
     * @param graph
     * @return
     */
    public static String DFS(Graph graph) {
        /** Output string **/
        StringJoiner joiner = new StringJoiner(" -> ");

        /** Stack to store adjacent vertices to process later **/
        Stack<Integer> stack = new Stack<>();

        /** Array to maintain vertex is visited or not **/
        boolean[] visited = new boolean[graph.numberOfVertices];

        /** Pushing starting vertex to stack **/
        stack.push(0);

        /** Iterate till stack is not empty **/
        while (!stack.isEmpty()) {

            /** Pop top vertex from stack **/
            Integer currentVertex = stack.pop();

            /** If is not visited **/
            if (!visited[currentVertex]) {
                /** Add current vertex to output **/
                joiner.add(currentVertex.toString());

                /** Mark current vertex as visited **/
                visited[currentVertex] = true;

                /** Iterated adjacent vertex of current vertex **/
                for (Integer adjacentVertex : graph.adjacencyLists[currentVertex]) {

                    /** If current vertex is not visited then, push to stack **/
                    if (!visited[adjacentVertex]) {
                        stack.push(adjacentVertex);
                    }
                }
            }
        }

        /** Return output **/
        return joiner.toString();
    }


    public static String DFSRecursive(Graph graph) {
        StringJoiner joiner = new StringJoiner(" -> ");
        boolean[] visited = new boolean[graph.numberOfVertices];

        DFSRecursive(graph, joiner, visited, 0);

        return joiner.toString();
    }

    private static void DFSRecursive(Graph graph, StringJoiner output, boolean[] visited, Integer currentVertex) {

        /** If already visited then don't do anything **/
        if (visited[currentVertex]) {
            return;
        }

        /** Mark current vertex as visited **/
        visited[currentVertex] = true;

        /** Add current vertex to output **/
        output.add(currentVertex.toString());

        /** Iterate over adjacent vertices **/
        for (Integer adjacentVertex : graph.adjacencyLists[currentVertex]) {

            /** Recursively call method to visit unvisited adjacent vertex **/
            if (!visited[adjacentVertex]) {
                DFSRecursive(graph, output, visited, adjacentVertex);
            }
        }

    }
}

class GraphDepthFirstTraversalTest {
    public static void main(String[] args) {
        Graph graph = new Graph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("Graph:");
        System.out.println(graph);
        System.out.println("DFS: " + GraphDepthFirstTraversal.DFS(graph));
        System.out.println("DFS (Recursive): " + GraphDepthFirstTraversal.DFSRecursive(graph));
    }
}