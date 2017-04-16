package com.hrishikeshmishra.ns.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Directed Graph Implementation
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/directed-graph-implementation/
 */
public class Digraph<V> {

    /**
     * Vertices Neighbour maps
     */
    private Map<V, List<Edge<V>>> verticesNeighbors = new HashMap<>();

    public Digraph() {
    }

    public Digraph(List<V> vertices) {
        initGraph(vertices);
    }

    private void initGraph(List<V> vertices) {
        vertices.forEach(this::addVertex);
    }

    /**
     * Add new Vertex
     *
     * @param vertex
     */
    public void addVertex(V vertex) {
        if (isVertexExists(vertex)) throw new RuntimeException("Duplicate vertex");
        createVertexNeighbourMap(vertex);
    }

    /**
     * <p>
     * Add edges with default weight
     * </p>
     *
     * @param from
     * @param to
     */
    public void addEdge(V from, V to) {
        addNeighbour(from, to, Edge.DEFAULT_WEIGHT);
    }

    /**
     * <p>
     * Add Edge to graph
     * </p>
     *
     * @param from
     * @param to
     * @param weight
     */
    public void addEdge(V from, V to, double weight) {
        if (!isVertexExists(from)) throw new RuntimeException("From vertex doesn't exists.");
        if (!isVertexExists(to)) throw new RuntimeException("From vertex doesn't exists.");
        if (isEdgeExists(from, to)) throw new RuntimeException("Duplicate edge.");
        addNeighbour(from, to, weight);
    }

    /**
     * <p>
     * Get number of edges
     * </p>
     *
     * @return
     */
    public int getNumberOfEdges() {
        int sum = 0;
        for (List<Edge<V>> outBounds : verticesNeighbors.values()) sum += outBounds.size();
        return sum;
    }

    /**
     * <p>
     * Get out degree
     * </p>
     *
     * @param vertex
     * @return
     */
    public int outDegree(V vertex) {
        return verticesNeighbors.get(vertex).size();
    }

    /**
     * Get in degree
     *
     * @param vertex
     * @return
     */
    public int inDegree(V vertex) {
        return getInboundNeighbors(vertex).size();
    }

    public Map<V, List<Edge<V>>> getVerticesNeighbors() {
        return verticesNeighbors;
    }

    public Set<V> getVertices() {
        return verticesNeighbors.keySet();
    }

    /**
     * <p>
     * Get in bound neighbour
     * </p>
     *
     * @param vertex
     * @return
     */
    public List<V> getInboundNeighbors(V vertex) {
        List<V> inboundList = new ArrayList<>();

        for (V to : verticesNeighbors.keySet())
            inboundList.addAll(
                    verticesNeighbors.get(to).
                            stream().
                            filter(e -> e.getDestination().equals(vertex)).
                            map(e -> to).collect(Collectors.toList())
            );
        return inboundList;
    }

    /**
     * <p>
     * Get out bound neighbor
     * </p>
     *
     * @param vertex
     * @return
     */
    public List<V> getOutboundNeighbors(V vertex) {
        List<V> outboundList = verticesNeighbors.get(vertex).
                stream().
                map(Edge<V>::getDestination).
                collect(Collectors.toList());
        return outboundList;
    }

    public boolean isEdgeExists(V from, V to) {
        for (Edge<V> e : verticesNeighbors.get(from))
            if (e.getDestination().equals(to))
                return true;
        return false;
    }

    public double getEdgeWeight(V from, V to) {
        Edge<V> edge = null;

        for (Edge<V> e : verticesNeighbors.get(from)) {
            if (e.getDestination().equals(to)) {
                edge = e;
                break;
            }
        }

        if (Objects.isNull(edge))
            throw new NoSuchElementException("No such edge between " + from + " to " + to);

        return edge.getWeight();
    }

    private void createVertexNeighbourMap(V vertex) {
        verticesNeighbors.put(vertex, new ArrayList<Edge<V>>());
    }

    public boolean isVertexExists(V vertex) {
        return verticesNeighbors.containsKey(vertex);
    }

    private void addNeighbour(V vertex, V neighbour, double weight) {
        verticesNeighbors.get(vertex).add(new Edge<V>(vertex, neighbour, weight));
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n", "{", "}");
        for (V v : verticesNeighbors.keySet()) joiner.add("V(" + v + "),  " + verticesNeighbors.get(v));
        return joiner.toString();
    }

    /**
     * Edge
     *
     * @param <V>
     */
    public static class Edge<V> {
        private V source;
        private V destination;
        private double weight;
        public static final double DEFAULT_WEIGHT = 1.0;

        public Edge(V source, V destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public V getSource() {
            return source;
        }

        public void setSource(V source) {
            this.source = source;
        }

        public V getDestination() {
            return destination;
        }

        public void setDestination(V destination) {
            this.destination = destination;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;

            Edge edge = (Edge) o;

            if (!destination.equals(edge.destination)) return false;
            if (!source.equals(edge.source)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = source.hashCode();
            result = 31 * result + destination.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Edge (" +
                    "source=" + source +
                    ", destination=" + destination +
                    ", weight=" + weight +
                    ')';
        }
    }
}

class DigraphTest {

    public static void main(String[] args) {
        Digraph<Integer> graph = new Digraph<Integer>();

        /** Vertex of graph **/
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        /** Edges of graph **/
        graph.addEdge(0, 1, 1.0);
        graph.addEdge(1, 2, 2.0);
        graph.addEdge(2, 3, 1.0);
        graph.addEdge(3, 0, 2.0);
        graph.addEdge(1, 3, 1.0);
        graph.addEdge(2, 1, 5.0);


        System.out.println("Number of vertices: " + graph.getVerticesNeighbors().keySet().size());
        System.out.println("Number of edges: " + graph.getNumberOfEdges());
        System.out.println("In-degree of V(1): " + graph.inDegree(1));
        System.out.println("Out-degree of V(1): " + graph.outDegree(1));

        System.out.println("\n\nGraph: \n" + graph);

    }
}