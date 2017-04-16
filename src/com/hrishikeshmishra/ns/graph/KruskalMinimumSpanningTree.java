package com.hrishikeshmishra.ns.graph;

import java.util.*;

/**
 * Problem:
 * Kruskal Minimum spanning tree based on disjoint set.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/kruskal-minimum-spanning-tree-based-on-disjoint-set/
 */
public class KruskalMinimumSpanningTree {


    public Graph findMST(Graph graph) {
        List<Graph.Edge> edges = graph.getEdges();
        Collections.sort(edges);

        DisjointSets disjointSets = new DisjointSets();
        createDisjointSet(disjointSets, graph.getTotalVertices());
        Graph mmt = new Graph(graph.getTotalVertices());

        for (Graph.Edge edge : edges) {
            int startVertexSet = disjointSets.find(edge.getStart());
            int endVertexSet = disjointSets.find(edge.getEnd());
            if (startVertexSet != endVertexSet) {
                disjointSets.union(edge.getStart(), edge.getEnd());
                mmt.addEdge(edge.getStart(), edge.getEnd(), edge.getWeight());
            }
        }

        return mmt;
    }

    public void createDisjointSet(DisjointSets disjointSets, int totalNumberOfVertices) {
        for (int i = 0; i <= totalNumberOfVertices; i++) disjointSets.make(i);

    }

    /**
     * <p>
     * Disjoint set based on
     * rank and path compression
     * </p>
     */
    public static class DisjointSets {

        private Map<Integer, Node> map = new HashMap<>();

        private class Node {
            private int rank;
            private int data;
            private Node parent;

            private Node(int data) {
                this.rank = 0;
                this.data = data;
                this.parent = this;
            }
        }

        public void make(int data) {
            map.put(data, new Node(data));
        }

        public void union(int data1, int data2) {
            Node node1 = map.get(data1),
                    node2 = map.get(data2);

            Node parent1 = find(node1),
                    parent2 = find(node2);

            /** both are in same set **/
            if (parent1.data == parent2.data) return;
            ;

            if (parent1.rank >= parent2.rank) {
                parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
                parent2.parent = parent1;
            } else {
                parent1.parent = parent2;
            }
        }

        public int find(int data) {
            return find(map.get(data)).data;
        }

        /**
         * Find set representative and also do path
         * compression.
         *
         * @param node
         * @return
         */
        private Node find(Node node) {
            if (node.parent == node) return node;
            node.parent = find(node.parent);
            return node.parent;
        }

    }

    public static class Graph {

        private int[][] matrix;
        private int totalVertices;

        public Graph(int totalVertices) {
            this.totalVertices = totalVertices;
            this.matrix = new int[totalVertices][totalVertices];
        }

        public void addEdge(int sourceVertex, int destinationVertex, int weight) {
            if (!isValidVertex(sourceVertex) || !isValidVertex(destinationVertex))
                throw new NoSuchElementException("No such vertex. ");
            this.matrix[sourceVertex][destinationVertex] = weight;
            this.matrix[destinationVertex][sourceVertex] = weight;
        }

        public boolean isValidVertex(int vertex) {
            return (vertex >= 0 && vertex < totalVertices);
        }

        public List<Edge> getEdges() {
            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[i].length; j++)
                    if (matrix[i][j] != 0 && !edges.contains(new Edge(i, j, matrix[i][j])))
                        edges.add(new Edge(i, j, matrix[i][j]));
            return edges;
        }

        public void display() {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++)
                    System.out.print((matrix[i][j] == 0 ? matrix[i][j] : " - ") + " ");
                System.out.println("");
            }
        }

        public int getTotalVertices() {
            return totalVertices;
        }

        public static class Edge implements Comparable<Edge> {
            private int start;
            private int end;
            private int weight;

            public Edge(int start, int end, int weight) {
                this.start = start;
                this.end = end;
                this.weight = weight;
            }

            @Override
            public int compareTo(Edge o) {
                return this.weight - o.weight;
            }

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            @Override
            public String toString() {
                return "E(" +
                        "start=" + start +
                        ", end=" + end +
                        ", weight=" + weight +
                        ')';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Edge)) return false;

                Edge edge = (Edge) o;

                if (start == edge.end && end == edge.start) return true;
                if (end != edge.end) return false;
                if (start != edge.start) return false;


                return true;
            }

            @Override
            public int hashCode() {
                int result = start;
                result = 31 * result + end;
                return result;
            }
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] != 0)
                        sb.append(matrix[i][j] + " ");
                    else
                        sb.append("- ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

}


class KruskalMinimumSpanningTreeTest {

    public static void main(String[] args) {
        KruskalMinimumSpanningTree mmt = new KruskalMinimumSpanningTree();
        KruskalMinimumSpanningTree.Graph graph = new KruskalMinimumSpanningTree.Graph(4);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);


        System.out.println(mmt.findMST(graph));

    }
}
