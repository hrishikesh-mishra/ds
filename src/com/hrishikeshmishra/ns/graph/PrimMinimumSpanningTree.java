package com.hrishikeshmishra.ns.graph;

import java.util.*;
import static com.hrishikeshmishra.ns.graph.PrimMinimumSpanningTree.Graph;

/**
 * Problem:
 *
 *     Prim's Minimum Spanning Tree
 *
 *     There is difference between Dijkstra and Prim algorithm,
 *     we check this will this example.
 *
 *               5     5
 *          s *-----*-----* t
 *             \         /
 *               -------
 *                  9
 *
 *    The shortest path (Dijkstra) is 9, while the MST (Prime) is a different 'path' at 10.
 *
 *    But in code only difference is in relax function.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/prims-minimum-spanning-tree/
 *
 */
public class PrimMinimumSpanningTree {

    private static final double DEFAULT_DISTANCE = Double.MAX_VALUE;

    public <V> void createMinimumSpanningTree(Graph<V> graph){

        Map<V, Double> distanceMap = new HashMap<>();
        Map<V, V> childParentMap = new HashMap<>();
        Set<V> processedVertices = new HashSet<>();
        PriorityQueue<PriorityQueueEntry<V>> priorityQueue = new PriorityQueue<>(new EntryComparator<V>());

        List<V> vertices = graph.getVertices();
        V startVertex = vertices.get(0);
        initDistanceMap(distanceMap, vertices);
        distanceMap.put(startVertex, 0.0);
        initPriorityQueue(priorityQueue, distanceMap);
        childParentMap.put(startVertex, null);
        processedVertices.add(startVertex);

        Graph<V> mmtGraph = new Graph<>( vertices.toArray ((V[]) new Object[vertices.size()]));

        while (!priorityQueue.isEmpty()) {
            PriorityQueueEntry<V> entry = priorityQueue.removeMin();
            V vertex = entry.getVertex();
            addToGraph(mmtGraph, vertex, childParentMap.get(vertex), distanceMap.get(vertex));
            processedVertices.add(vertex);

            for (V adjacentVertex : graph.getAdjacentVertices(vertex)) {
                double weight = graph.getWeight(vertex, adjacentVertex);

                /** **/
                if (!processedVertices.contains(adjacentVertex)
                        && (weight < distanceMap.get(adjacentVertex))) {
                    distanceMap.put(adjacentVertex, weight);
                    priorityQueue.update(new PriorityQueueEntry<V>(adjacentVertex, weight));
                    childParentMap.put(adjacentVertex, vertex);
                }
            }
        }

        System.out.println("\n\n\nMinimum Spanning Tree ");
        mmtGraph.print();
    }

    private <V> boolean isVertexAddToMMT(V adjacentVertex, PriorityQueue<PriorityQueueEntry<V>> priorityQueue) {
        return ! priorityQueue.contains(new PriorityQueueEntry<>(adjacentVertex, 0.0));
    }

    private <V> void addToGraph(Graph<V> mmtGraph, V to, V from, Double weight) {
        if(Objects.isNull(from)) return;
        mmtGraph.addEdge(from, to, weight);
    }

    private <V> void initPriorityQueue(PriorityQueue<PriorityQueueEntry<V>> priorityQueue, Map<V, Double> distanceMap){
        Set<Map.Entry<V, Double>> entries = distanceMap.entrySet();
        for(Map.Entry<V, Double> entry : entries)
            priorityQueue.add(new PriorityQueueEntry<V>(entry.getKey(), entry.getValue()));
   }


    private <V> void printDistance(V startVertex, Map<V, Double> distanceMap, Map<V, V> pathMap) {
        Set<Map.Entry<V, Double>> entries = distanceMap.entrySet();

        for(Map.Entry<V, Double> entry : entries){
            System.out.println(" Distance between V(" + startVertex + ") and V(" +
                    entry.getKey() + ") = " + entry.getValue() + ", Path" +
                    getPathString(entry.getKey(), pathMap));

        }
    }

    private <V> String getPathString(V endVertex, Map<V, V> pathMap) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        V vertex = endVertex;

        while (!Objects.isNull(vertex)){
            joiner.add(vertex.toString());
            vertex = pathMap.get(vertex);
        }

        return joiner.toString();
    }

    private <V> void initDistanceMap(Map<V, Double> distanceMap, List<V> vertices) {
        for(V vertex : vertices) distanceMap.put(vertex, DEFAULT_DISTANCE);
    }

    /**
     * <p>
     *     Priority  Queue Entry
     * </p>
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
     *     Priority Queue Entry Comparator
     * </p>
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
     *     Priority Queue Wrapper
     * </p>
     * @param <E>
     */
    private static class PriorityQueue<E> extends java.util.PriorityQueue<E>{

        private PriorityQueue(Comparator<? super E> comparator) {
            super(comparator);
        }

        public E removeMin(){
            return super.remove();
        }

        public boolean add(E element){
            return super.add(element);
        }

        public void update(E element){
            super.remove(element);
            super.add(element);
        }
    }


    /**
     * UnDirected Graph.
     * @param <V>
     */
    public static class Graph<V> {
        private Double [][]  adjacencyMatrix;
        private List<V> vertices;
        private final int totalVertices;

        private final Double DEFAULT_WEIGHT = 0.0;

        public Graph(V[] vertices) {
            this.vertices = Arrays.asList(vertices);
            this.adjacencyMatrix = new Double[vertices.length][vertices.length];
            this.totalVertices = vertices.length;
        }

        public void addEdge(V from, V to, double weight){
            if(!isVertexExists(from)) throw new RuntimeException(" V(" + from + ") does not exist.");
            if(!isVertexExists(to)) throw new RuntimeException(" V(" + to + ") does not exist.");

            this.adjacencyMatrix[getVertexIndex(from)] [getVertexIndex(to)] = weight;
            this.adjacencyMatrix[getVertexIndex(to)] [getVertexIndex(from)] = weight;
        }

        public void addEdge(V from, V to){
            if(isEdgeExist(from, to))  throw new RuntimeException(" V(" + from + ") to V(" + to + ") edge already exist.");
            this.addEdge(from, to, DEFAULT_WEIGHT);
        }

        public List<V> getAdjacentVertices(V vertex){
            if(!isVertexExists(vertex)) throw new RuntimeException(" V(" + vertex + ") does not exist.");

            int vertexIndex = getVertexIndex(vertex);
            List<V> adjacentVertices = new ArrayList<V>();

            for(int i = 0; i < this.totalVertices; i++)
                if(i != vertexIndex && isEdgeExist(vertexIndex, i))
                    adjacentVertices.add(this.getVertex(i));

            return adjacentVertices;
        }

        public boolean isEdgeExist(V from, V to){
            if(!isVertexExists(from)) throw new RuntimeException(" V(" + from + ") does not exist.");
            if(!isVertexExists(to)) throw new RuntimeException(" V(" + to + ") does not exist.");
            return isEdgeExist(getVertexIndex(from) , getVertexIndex(to));
        }

        public boolean isEdgeExist(int fromIndex, int toIndex){
            return !Objects.isNull(this.adjacencyMatrix[fromIndex][toIndex]);
        }

        public Double getWeight(V from, V to){
            if(!isVertexExists(from)) throw new RuntimeException(" V(" + from + ") does not exist.");
            if(!isVertexExists(to)) throw new RuntimeException(" V(" + to + ") does not exist.");
            return getWeight(getVertexIndex(from), getVertexIndex(to));
        }

        public Double getWeight(int fromIndex, int toIndex){
            return this.adjacencyMatrix[fromIndex][toIndex];
        }

        public List<V> getVertices() {
            return vertices;
        }

        public void print(){

            System.out.println("\n************************ Graph ************************");
            System.out.printf("%-7s", " ");
            for(int i = 0; i < vertices.size(); i++)
                System.out.printf("%-7s", "V(" + vertices.get(i) + ")");
            System.out.println("\n******************************************************");

            for (int i =0; i < adjacencyMatrix.length; i++){
                System.out.printf("%-7s", "V(" + vertices.get(i) + ")");
                for (int j =0; j < adjacencyMatrix.length; j++){
                    if(isEdgeExist(i, j)) System.out.printf("%-7s", getWeight(i, j) + " ");
                    else System.out.printf("%-7s", "-");
                }
                System.out.println("");
            }
            System.out.println("******************************************************");
        }

        private int getVertexIndex(V vertex){
            return this.vertices.indexOf(vertex);
        }

        private V getVertex(int index){
            return this.vertices.get(index);
        }

        private boolean isVertexExists(V vertex){
            return this.vertices.contains(vertex);
        }


    }
}

class PrimMinimumSpanningTreeTest{

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph(new Integer[] {1, 2, 3, 4, 5});

        /** Edges **/
        graph.addEdge(1, 2, 4.0); graph.addEdge(1, 3, 1.0);
        graph.addEdge(2, 3, 2.0); graph.addEdge(2, 4, 1.0); graph.addEdge(2, 5, 2.0);
        graph.addEdge(3, 4, 5.0);
        graph.addEdge(4, 5, 3.0);

        graph.print();

        PrimMinimumSpanningTree primMinimumSpanningTree = new PrimMinimumSpanningTree();
        primMinimumSpanningTree.createMinimumSpanningTree(graph);
    }


}
