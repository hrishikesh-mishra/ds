package com.hrishikesh.dsjava.practise.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hrishikesh.mishra on 02/05/16.
 */
public class Digraph<V> {

    public static class Edge<V>{
        private V vertex;
        private double weight;

        public Edge(V vertex, double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public V getVertex() {
            return vertex;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "vertex=" + vertex +
                    ", weight=" + weight +
                    '}';
        }
    }


    private Map<V, List<Edge<V>>> neighbors = new HashMap<>();

    public void add(V vertex){
        if(neighbors.containsKey(vertex)) return;
        neighbors.put(vertex, new ArrayList<Edge<V>>());
    }

    public int getNumberOfEdges(){
        int sum = 0;
        for (List<Edge<V>> outBounds:  neighbors.values()) sum += outBounds.size();
        return sum;
    }

    public boolean contains(V vertex){
        return neighbors.containsKey(vertex);
    }

    public void add(V from , V to, double weight){
        add(from);
        add(to);
        neighbors.get(from).add(new Edge<V>(to, weight));
    }

    public int outDegree(V vertex){
        return neighbors.get(vertex).size();
    }

    public int inDegree(V vertex){
        return getInboundNeighbors(vertex).size();
    }

    public List<V> getInboundNeighbors(V vertex){
        List<V> inboundList = new ArrayList<>();
        for(V to: neighbors.keySet()){
            for(Edge<V> e: neighbors.get(to)){
                if(e.getVertex().equals(vertex))
                    inboundList.add(to);
            }
        }
        return inboundList;
    }

    public List<V> getOutboundNeighbors(V vertex){
        List<V> outboundList = new ArrayList<>();
        for(Edge<V> e : neighbors.get(vertex))
            outboundList.add(e.getVertex());
        return outboundList;
    }

    public boolean isEdgeExists(V from, V to){
        for(Edge<V> e: neighbors.get(from))
            if(e.getVertex().equals(e))
                return true;
        return false;
    }

    @Override
    public String toString() {
        StringBuilder returnStr = new StringBuilder();
        for(V v: neighbors.keySet()) returnStr.append("\n" + v + neighbors.get(v));
        return returnStr.toString();

    }

    public Map<V, List<Edge<V>>> getNeighbors() {
        return neighbors;
    }

    public static void main(String[] args) {
        Digraph<Integer> graph =  new Digraph<>();

        /** Vertex of graph **/
        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);

        /** Edges of graph **/
        graph.add(0, 1, 1.0);
        graph.add(1, 2, 2.0);
        graph.add(2, 3, 1.0);
        graph.add(3, 0, 2.0);
        graph.add(1, 3, 1.0);
        graph.add(2, 1, 5.0);

        System.out.println("Number of vertices: " + graph.getNeighbors().keySet().size());
        System.out.println("Number of edges: " + graph.getNumberOfEdges());
        System.out.println("Indegree of V(1): " + graph.inDegree(1));
        System.out.println("Outdegree of V(1): " + graph.outDegree(1));


        System.out.println("Graph : \n\n\n" + graph);
        System.out.println(graph.getNeighbors().get(1));



    }
}