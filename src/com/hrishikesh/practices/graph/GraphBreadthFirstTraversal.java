package com.hrishikesh.practices.graph;


import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringJoiner;

import static com.hrishikesh.practices.graph.GraphBreadthFirstTraversal.Graph;


/**
 * Problem:
 * Breadth First Traversal for a Graph
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/breadth-first-traversal-graph/
 */
public class GraphBreadthFirstTraversal {

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
     * @param graph
     * @return
     */
    public static String BFS(Graph graph) {

        StringJoiner joiner = new StringJoiner(" -> ");

        /** Queue to hold adjacent vertices of a vertex **/
        Queue<Integer> queue = new LinkedList<>();

        /** Visited array to mark node a visited **/
        boolean[] visited = new boolean[graph.numberOfVertices];

        /** Adding first node to queue to start traversal **/
        queue.add(0);

        /** Iterating till queue is not empty **/
        while (!queue.isEmpty()) {

            /** Get first inserted node from queue **/
            Integer currentVertex = queue.remove();

            /** If current vertex is not visited then process, else don't do anything **/
            if (!visited[currentVertex]) {

                /** Add vertex to output  **/
                joiner.add(currentVertex.toString());

                /** Mark current vertex as visited **/
                visited[currentVertex] = true;

                /** Iterate all adjacent vertices of current vertex and push to queue if they are not visited **/
                for (Integer adjacentVertex : graph.adjacencyLists[currentVertex]) {

                    /** Add to queue if they are not visited **/
                    if (!visited[adjacentVertex]) {
                        queue.add(adjacentVertex);
                    }
                }
            }
        }

        /** Return output **/
        return joiner.toString();

    }
}

class GraphBreadthFirstTraversalTest {
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

        System.out.println("BFS : " + GraphBreadthFirstTraversal.BFS(graph));


    }
}