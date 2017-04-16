package com.hrishikeshmishra.dsjava.practise.graph;

import java.util.*;

/**
 * Created by hrishikesh.mishra
 */
public class Graph {

    private Map<String, Vertex> vertices;
    private List<Edge> edges;


    public Graph() {
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();
    }

    public Graph(List<Vertex> vertices) {
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();

        for (Vertex v : vertices){
            this.vertices.put(v.getLabel(), v);
        }
    }

    public boolean addEdge(Vertex start, Vertex end){
        return addEdge(start, end, Edge.DEFAULT_WEIGHT);
    }

    public boolean addEdge(Vertex start, Vertex end, double weight){
        if(start.equals(end))
            return false;

        Edge e = new Edge(start, end, weight);

        if(edges.contains(e))
            return false;
        else if(start.containNeighbor(e) || end.containNeighbor(e))
            return false;

        edges.add(e);
        start.addNeighbor(e);
        end.addNeighbor(e);
        return true;
    }

    public boolean containEdge(Edge e){
        return edges.contains(e);
    }

    public void removeEdge(Edge e){
        e.getStartVertex().removeNeighbor(e);
        e.getEndVertex().removeNeighbor(e);
        edges.remove(e);
    }

    public boolean containsVertex(Vertex vertex){
        return vertices.containsKey(vertex.getLabel());
    }

    public Vertex getVertex(String v){
        return vertices.get(v);
    }

    public boolean addVertex(Vertex vertex){
        return addVertex(vertex, false);
    }

    public boolean addVertex(Vertex vertex, boolean overwrite){
        Vertex oldVertex = vertices.get(vertex.getLabel());
        if(!Objects.isNull(oldVertex)){
            if(!overwrite)
                return false;

            /** Removing all existing neighbor of old vertex **/
            removeVertexNeighbors(oldVertex);
        }

        vertices.put(vertex.getLabel(), vertex);
        return true;
    }

    public void removeVertex(Vertex v){
        if(!vertices.containsKey(v.getLabel()))
            return;
        removeVertexNeighbors(v);
        vertices.remove(v.getLabel());
    }

    public Set<String> getVertexKeys(){
        return vertices.keySet();
    }

    public List<Edge> getEdges(){
        return edges;
    }

    private void removeVertexNeighbors(Vertex vertex){
        List<Edge> neighbors = vertex.getNeighbors();
        for (Edge e: neighbors)
            vertex.removeNeighbor(e);
    }

}
