package com.hrishikesh.practices.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hrishikesh.practices.graph.BabyNames.printTrueNameFrequency;

/**
 * Problem:
 * Baby Names
 * Given two lists, one of names/frequencies and other of pairs of equivalent names (synonyms),
 * implement an algorithm to print a new list of the true frequency of each name.
 * Note: Name relationship is both transitive and symmetric.
 * ;
 * ;
 * Solution:
 * - There are various ways to solve this problem
 * - - Using disjoint set
 * - - Graph
 * ;
 * ;
 * Graph Implementation Algorithm:
 * - Create a undirected graph with node (name, frequency)
 * - Add edges between nodes of graph using synonyms
 * - Use DFS to add all frequencies of same names
 *
 * @author hrishikesh.mishra
 * @link hrishikeshmishra.com/duplicate-baby-names-problem/
 */
public class BabyNames {

    private static class GraphNode {
        private String name;
        private int frequency;
        private boolean isVisited;

        public GraphNode(String name, int frequency) {
            this.name = name;
            this.frequency = frequency;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GraphNode)) return false;

            GraphNode graphNode = (GraphNode) o;

            return name != null ? name.equals(graphNode.name) : graphNode.name == null;

        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "GraphNode(" + name + ')';
        }
    }

    private static class Graph {

        private Map<String, GraphNode> nodes;
        private Map<GraphNode, List<GraphNode>> adjacencyList;

        public Graph() {
            this.nodes = new HashMap<>();
            this.adjacencyList = new HashMap<>();
        }

        public void addNode(String name, int frequency) {
            GraphNode graphNode = new GraphNode(name, frequency);
            nodes.put(name, graphNode);
            adjacencyList.put(graphNode, new ArrayList<>());
        }

        public void addEdge(String from, String to) {
            /** Adding edge in undirected graph **/
            GraphNode sourceNode = nodes.get(from);
            GraphNode destinationNode = nodes.get(to);
            adjacencyList.get(sourceNode).add(destinationNode);
            adjacencyList.get(destinationNode).add(sourceNode);
        }

        public List<GraphNode> getAdjacencyList(GraphNode graphNode) {
            return adjacencyList.get(graphNode);
        }
    }

    public static void printTrueNameFrequency(Map<String, Integer> nameWithFrequency, String[][] synonyms) {
        Graph graph = createGraph(nameWithFrequency);
        addGraphEdges(graph, synonyms);
        Map<String, Integer> trueFrequency = createTrueFrequency(graph);
        print(trueFrequency);
    }

    private static void print(Map<String, Integer> trueFrequency) {
        for (Map.Entry<String, Integer> entry : trueFrequency.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    private static Map<String, Integer> createTrueFrequency(Graph graph) {
        Map<String, Integer> trueFrequencyMap = new HashMap<>();
        for (GraphNode graphNode : graph.nodes.values()) {

            if (!graphNode.isVisited) {
                trueFrequencyMap.put(graphNode.name, DFS(graph, graphNode));
            }
        }

        return trueFrequencyMap;
    }

    private static int DFS(Graph graph, GraphNode graphNode) {
        if (graphNode.isVisited) {
            return 0;
        }

        graphNode.isVisited = true;
        int total = graphNode.frequency;

        for (GraphNode adjacentNode : graph.getAdjacencyList(graphNode)) {
            if (!adjacentNode.isVisited) {
                total += DFS(graph, adjacentNode);
            }
        }
        return total;
    }

    private static void addGraphEdges(Graph graph, String[][] synonyms) {
        for (String[] synonym : synonyms) {
            graph.addEdge(synonym[0], synonym[1]);
        }

    }

    private static Graph createGraph(Map<String, Integer> nameWithFrequency) {
        Graph graph = new Graph();
        for (Map.Entry<String, Integer> entry : nameWithFrequency.entrySet()) {
            graph.addNode(entry.getKey(), entry.getValue());
        }
        return graph;
    }


}


class BabyNamesTest {
    public static void main(String[] args) {
        Map<String, Integer> nameWithFrequency = new HashMap<String, Integer>() {
            {
                put("John", 15);
                put("Jon", 12);
                put("Johnny", 0);
                put("Chris", 13);
                put("Kris", 4);
                put("Christopher", 19);
            }
        };

        String[][] synonyms = {
                {
                        "Jon", "John"},
                {"John", "Johnny"},
                {"Chris", "Kris"},
                {"Chris", "Christopher"}

        };

        printTrueNameFrequency(nameWithFrequency, synonyms);
    }
}