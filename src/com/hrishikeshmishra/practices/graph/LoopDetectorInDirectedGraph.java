package com.hrishikeshmishra.practices.graph;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

/**
 * Problem:
 * Detect Cycle in a Directed Graph
 * Given a directed graph, check whether the graph contains a cycle or not
 * ;
 * Solution:
 * - Loop can be detected by DFS traversal
 * - If there is loop then there will be back edge,
 * - A Back edge - is a edge that is from a node to itself (selfloop) or one of its ancestors in the
 * - - tree produced by DFS.
 * - -  To detect a back edge - We will use local visited boolean will set true before each stack call and set false
 * - - at end of stack call.
 *
 * @author hrishikesh.
 * @link http://hrishikeshmishra.com/detect-cycle-directed-graph-2/
 */
public class LoopDetectorInDirectedGraph {

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


    public static boolean isLoopExist(Graph graph) {

        boolean [] visited = new boolean[graph.numberOfVertices];
        boolean [] localVisited = new boolean[graph.numberOfVertices];

        for (int currentVertex = 0; currentVertex < graph.numberOfVertices; currentVertex++) {
            boolean isLoopDetected = detectLoopByDFSTraversal(graph,visited, currentVertex, localVisited);

            if (isLoopDetected) {
                return true;
            }
        }
        return false;
    }

    private static boolean detectLoopByDFSTraversal(Graph graph, boolean[] visited, Integer currentVertex, boolean[] localVisited) {

        /** If node is already visited don't do anything **/
        if (visited[currentVertex]) {
            return false;
        }

        /** Mark current vertex visited **/
        visited[currentVertex] = true;

        /** Add to local visited vertex **/
        localVisited[currentVertex] = true;

        /** Iterate all over adjacent vertices **/
        for (Integer adjacentVertex : graph.adjacencyLists[currentVertex]) {

            /** call loop detector for current adjacent vertex and current adjacent vertex is not already visited **/
            if (!visited[adjacentVertex] && detectLoopByDFSTraversal(graph, visited, adjacentVertex, localVisited)) {
                return true;
            } else if (localVisited[adjacentVertex]) {
                return true;
            }
        }

        /** Remove from local visited vertex **/
        localVisited[currentVertex] = false;

        return false;
    }

}


class LoopDetectorInGraphTest {
    public static void main(String[] args) {
        LoopDetectorInDirectedGraph.Graph graph = new LoopDetectorInDirectedGraph.Graph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("Graph:");
        System.out.println(graph);

        System.out.println("Is loop exists: " + LoopDetectorInDirectedGraph.isLoopExist(graph));
    }
}