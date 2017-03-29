package com.hrishikesh.ns.graph;

import java.util.*;

/**
 * Problem:
 * Dijkstra Shortest Path Finder
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/dijkstra-shortest-path-finder/
 */
public class DijkstraShortestPathFinder {

    private static final double DEFAULT_DISTANCE = -1.0;

    public <V> void find(Digraph<V> digraph, V startVertex) {

        Map<V, Double> distanceMap = new HashMap<>();
        Map<V, V> pathMap = new HashMap<>();
        PriorityQueue<PriorityQueueEntry<V>> priorityQueue = new PriorityQueue<>(new EntryComparator<V>());

        /** Initiating Process **/
        pathMap.put(startVertex, null);
        initDistanceMap(distanceMap, digraph.getVertices());
        distanceMap.put(startVertex, 0.0);
        priorityQueue.add(new PriorityQueueEntry<V>(startVertex, 0.0));

        /** Processing each element in queue **/
        while (!priorityQueue.isEmpty()) {
            PriorityQueueEntry<V> pqEntry = priorityQueue.removeMin();

            /** Iterate all adjacent vertices **/
            for (V adjacentVertex : digraph.getOutboundNeighbors(pqEntry.getVertex())) {
                Double newWeight = distanceMap.get(pqEntry.getVertex()) + digraph.getEdgeWeight(pqEntry.getVertex(), adjacentVertex);

                /** UnProcessed vertex **/
                if (distanceMap.get(adjacentVertex) == DEFAULT_DISTANCE) {
                    distanceMap.put(adjacentVertex, newWeight);
                    pathMap.put(adjacentVertex, pqEntry.getVertex());
                    priorityQueue.add(new PriorityQueueEntry<V>(adjacentVertex, newWeight));

                    /** If it is processed vertex then and we found new weight **/
                } else if (distanceMap.get(adjacentVertex) > newWeight) {
                    distanceMap.put(adjacentVertex, newWeight);
                    pathMap.put(adjacentVertex, pqEntry.getVertex());
                    priorityQueue.update(new PriorityQueueEntry<V>(adjacentVertex, newWeight));
                }
            }
        }

        printDistance(startVertex, distanceMap, pathMap);
    }

    private <V> void printDistance(V startVertex, Map<V, Double> distanceMap, Map<V, V> pathMap) {
        Set<Map.Entry<V, Double>> entries = distanceMap.entrySet();

        for (Map.Entry<V, Double> entry : entries) {
            System.out.println(" Distance between V(" + startVertex + ") and V(" +
                    entry.getKey() + ") = " + entry.getValue() + ", Path" +
                    getPathString(entry.getKey(), pathMap));

        }
    }

    private <V> String getPathString(V endVertex, Map<V, V> pathMap) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        V vertex = endVertex;

        while (!Objects.isNull(vertex)) {
            joiner.add(vertex.toString());
            vertex = pathMap.get(vertex);
        }

        return joiner.toString();
    }

    private <V> void initDistanceMap(Map<V, Double> distanceMap, Set<V> vertices) {
        for (V vertex : vertices) distanceMap.put(vertex, DEFAULT_DISTANCE);
    }

    /**
     * <p>
     * Priority  Queue Entry
     * </p>
     *
     * @param <V>
     */
    private static class PriorityQueueEntry<V> {

        private V vertex;
        private Double weight;

        private PriorityQueueEntry(V vertex, Double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public V getVertex() {
            return vertex;
        }

        public void setVertex(V vertex) {
            this.vertex = vertex;
        }

        public Double getWeight() {
            return weight;
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PriorityQueueEntry)) return false;

            PriorityQueueEntry priorityQueueEntry = (PriorityQueueEntry) o;
            if (!vertex.equals(priorityQueueEntry.vertex)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            return vertex.hashCode();
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "vertex=" + vertex +
                    ", weight=" + weight +
                    '}';
        }
    }

    /**
     * <p>
     * Priority Queue Entry Comparator
     * </p>
     *
     * @param <V>
     */
    private static class EntryComparator<V> implements Comparator<PriorityQueueEntry<V>> {

        @Override
        public int compare(PriorityQueueEntry<V> o1, PriorityQueueEntry<V> o2) {
            return o1.getWeight().compareTo(o2.getWeight());
        }
    }

    /**
     * <p>
     * Priority Queue Wrapper
     * </p>
     *
     * @param <E>
     */
    private static class PriorityQueue<E> extends java.util.PriorityQueue<E> {

        private PriorityQueue(Comparator<? super E> comparator) {
            super(comparator);
        }

        public E removeMin() {
            return super.remove();
        }

        public boolean add(E element) {
            return super.add(element);
        }

        public void update(E element) {
            super.remove(element);
            super.add(element);
        }
    }
}

class DijkstraShortPathFinderTest {
    public static void main(String[] args) {
        Digraph<Integer> graph = new Digraph<>();

        /** Vertex **/
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        /** Edges **/
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 5, 3);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 2, 2);
        graph.addEdge(3, 4, 5);
        graph.addEdge(4, 5, 1);

        System.out.println("Graph:\n" + graph + "\n\n");

        DijkstraShortestPathFinder dijkstraShortestPathFinder = new DijkstraShortestPathFinder();
        dijkstraShortestPathFinder.find(graph, 1);

    }
}



