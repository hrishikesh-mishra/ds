package com.hrishikesh.dsjava.practise.graph;

/**
 * Created by hrishikesh.mishra
 */
public class GraphDemo {

    public static void main(String[] args) {
        Graph graph = new Graph();

        /** Adding vertex to graph **/
        Vertex[] vertices = new Vertex[5];
        for(int i=0; i<5; i++){
            vertices[i] = new Vertex("V" + i);
            graph.addVertex(vertices[i]);
        }


        /** adding edges **/
        for(int i=0; i< vertices.length; i++){
            for(int j= i+1; j<vertices.length; j++){
                graph.addEdge(vertices[i], vertices[j]);
                graph.addEdge(vertices[j], vertices[i]);
            }
        }


        /** Print all vertices with their neighbors **/
        for(int i=0; i< vertices.length; i++){
            System.out.println("------------------------------------------------------------------------");
            System.out.println(graph.getVertex(vertices[i].getLabel()));
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Neighbors : ("+graph.getVertex(vertices[i].getLabel()).getNeighborCount()+")");
            System.out.println(graph.getVertex(vertices[i].getLabel()).getNeighborhood());
            System.out.println("------------------------------------------------------------------------\n\n");
        }



    }
}
