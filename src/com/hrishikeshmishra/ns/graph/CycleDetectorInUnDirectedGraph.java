package com.hrishikeshmishra.ns.graph;

import java.util.*;

/**
 * D - Problem
 * Cycle / loop detector in undirected graph using Union-Find Algorithm
 *
 * @author hrishikesh.mishra
 * @link hrishikeshmishra.com/cycle-loop-detector-in-undirected-graph-using-union-find-algorithm/
 */
public class CycleDetectorInUnDirectedGraph {

    public boolean isLoopExist(Graph graph) {
        Map<Integer, Integer> parentChild = new HashMap<>();

        /** All child in different group **/
        for (Integer v : graph.getVertices()) parentChild.put(v, -1);

        for (Graph.Edge edge : graph.getEdges()) {
            int x = graph.find(parentChild, edge.getFrom());
            int y = graph.find(parentChild, edge.getTo());
            if (x == y) return true;
            graph.union(x, y, parentChild);
        }
        return false;
    }

    public static class Graph {

        private final int totalVertices;
        private final List<Edge> edges;

        public Graph(int totalVertex) {
            this.totalVertices = totalVertex;
            edges = new ArrayList<>();
        }

        public void addEdge(int from, int to) {
            if (!isValidVertex(from)) throw new NoSuchElementException("No such vertex " + from);
            if (!isValidVertex(to)) throw new NoSuchElementException("No such vertex " + to);
            if (edges.contains(new Edge(from, to)))
                throw new RuntimeException("Duplicate edge betwew   " + from + " to " + to);
            edges.add(new Edge(from, to));
        }

        public boolean isValidVertex(int vertex) {
            return (vertex >= 0 && vertex < totalVertices);
        }

        public List<Integer> getVertices() {
            List<Integer> vertices = new ArrayList<>();
            for (int i = 0; i < totalVertices; i++) vertices.add(i);
            return vertices;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public boolean isEmpty() {
            return totalVertices == 0;
        }

        public int find(Map<Integer, Integer> parent, int i) {
            if (parent.get(i) == -1)
                return i;
            return find(parent, parent.get(i));
        }

        public void union(Integer x, Integer y, Map<Integer, Integer> parent) {
            parent.put(x, y);
        }


        public class Edge {
            private int from;
            private int to;

            public Edge(int from, int to) {
                this.from = from;
                this.to = to;
            }

            public int getFrom() {
                return from;
            }

            public void setFrom(int from) {
                this.from = from;
            }

            public int getTo() {
                return to;
            }

            public void setTo(int to) {
                this.to = to;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Edge)) return false;

                Edge edge = (Edge) o;

                if (from != edge.from) return false;
                if (to != edge.to) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = from;
                result = 31 * result + to;
                return result;
            }

            @Override
            public String toString() {
                return "Edge{" +
                        "from=" + from +
                        ", to=" + to +
                        '}';
            }
        }


        @Override
        public String toString() {
            return "Graph{" +
                    "totalVertices=" + totalVertices +
                    ", edges=" + edges +
                    '}';
        }
    }
}

class CycleDetectorInUnDirectedGraphTest {

    public static void main(String[] args) {

        CycleDetectorInUnDirectedGraph cycleDetector = new CycleDetectorInUnDirectedGraph();
        CycleDetectorInUnDirectedGraph.Graph graph = new CycleDetectorInUnDirectedGraph.Graph(3);
        graph.addEdge(0, 1);
        //graph.addEdge(1, 2);
        graph.addEdge(0, 2);

        if (cycleDetector.isLoopExist(graph)) System.out.println("graph contains cycle");
        else System.out.println("graph doesn't contain cycle");
    }
}
