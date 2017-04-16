package com.hrishikeshmishra.practices.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.hrishikeshmishra.practices.graph.GraphCutVertices.findArticulationPoints;

/**
 * Problem :
 * Articulation Points Graph Algorithm using torjan's algorithm
 * ;
 * - Facts:
 * - - Root is articulation point iff it has more than one child
 * - - Any other vertex v is an articulation point iff v has some child w such that Low(w) ≥ Num(v)
 * - - - I.e., is there a child w of v that cannot reach a vertex visited before v?
 * - - - If yes, then removing v will disconnect w (and v is an articulation point)
 * - Algorithm:
 * - From any vertex v, perform DFS and number vertices as they are visited
 * - - Num(v) is the visit number
 * - Let Low(v) = lowest-numbered vertex reachable from v using 0 or more spanning tree edges and then at most
 * one back edge
 * - -  Low(v) = minimum of
 * - - Num(v)
 * - - Lowest Num(w) among all back edges (v,w)
 * - - Lowest Low(w) among all tree edges (v,w)
 * - Can compute Num(v) and Low(v) in O(|E|+|V|) time
 * --------------------------------------------------------------------------------------------------------
 * - Apply DFS on a graph. Get the DFS tree.
 * - A node which is visited earlier is a "parent" of those nodes which are reached by it and visited later.
 * - If any child of a node does not have a path to any of the ancestors of its parent, it means that removing this
 * node would make this child disjoint from the graph.
 * -  There is an exception: the root of the tree. If it has more than one child, then it is an articulation point,
 * otherwise not.
 * --------------------------------------------------------------------------------------------------------
 *
 * @author hrishikesh.mishra
 * @link http://www.eecs.wsu.edu/~holder/courses/CptS223/spr08/slides/graphapps.pdf
 * @link http://stackoverflow.com/questions/15873153/explanation-of-algorithm-for-finding-articulation-points-or-cut-vertices-of-a-gr
 * @video https://www.youtube.com/watch?v=2kREIkF9UAs
 * @link http://hrishikeshmishra.com/articulation-points-graph-algorithm-using-torjans-algorithm/
 */
public class GraphCutVertices {

    private static int COUNTER = 0;

    public static class Graph {

        private boolean[][] matrix;
        private int totalVertices;

        public Graph(int totalVertices) {
            this.totalVertices = totalVertices;
            this.matrix = new boolean[totalVertices][totalVertices];
        }


        public void addEdge(int sourceVertex, int destinationVertex) {
            if (!isValidVertex(sourceVertex) || !isValidVertex(destinationVertex))
                throw new NoSuchElementException("No such vertex. ");
            this.matrix[sourceVertex][destinationVertex] = true;
            this.matrix[destinationVertex][sourceVertex] = true;
        }

        public List<Integer> getAdjacentVertices(int vertex) {
            List<Integer> adjacentVertices = new ArrayList<>();
            for (int i = 0; i < matrix[vertex].length; i++)
                if (matrix[vertex][i]) adjacentVertices.add(i);
            return adjacentVertices;
        }

        public boolean isValidVertex(int vertex) {
            return (vertex >= 0 && vertex < totalVertices);
        }

        public void display() {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++)
                    System.out.print((matrix[i][j] ? " T " : " - ") + " ");
                System.out.println("");
            }
        }

        public int getTotalVertices() {
            return totalVertices;
        }

    }

    public static List<Integer> findArticulationPoints(Graph graph) {
        Map<Integer, Integer> numbers = new HashMap<>();
        Map<Integer, Integer> low = new HashMap<>();
        boolean[] visited = new boolean[graph.totalVertices];
        Map<Integer, Integer> parent = new HashMap<>();
        List<Integer> articulationPoints = new ArrayList<>();

        parent.put(0, null);

        findArticulationPoints(graph, 0, numbers, low, visited, parent, articulationPoints);
        return articulationPoints;
    }

    private static void findArticulationPoints(Graph graph, int currentVertex, Map<Integer, Integer> numbers,
                                               Map<Integer, Integer> low, boolean[] visited,
                                               Map<Integer, Integer> parent, List<Integer> articulationPoints) {
        visited[currentVertex] = true;
        numbers.put(currentVertex, COUNTER);
        low.put(currentVertex, COUNTER);

        COUNTER++;
        int children = 0;

        boolean isArticulationPoint = false;

        for (Integer adjacentVertex : graph.getAdjacentVertices(currentVertex)) {

            /** If adjacent vertex is parent of current vertex **/
            if (adjacentVertex.equals(parent.get(currentVertex))) {
                continue;
            }



            /** If node was not visited earlier **/
            if (!visited[adjacentVertex]) {
                children++;
                parent.put(adjacentVertex, currentVertex);
                findArticulationPoints(graph, adjacentVertex, numbers, low, visited, parent, articulationPoints);

                if (numbers.get(currentVertex) <= low.get(adjacentVertex)) {
                    isArticulationPoint = true;
                } else {
                    low.put(currentVertex, Math.min(low.get(currentVertex), low.get(adjacentVertex)));
                }
            } else {
                low.put(currentVertex, Math.min(low.get(currentVertex), numbers.get(adjacentVertex)));
            }
        }


        if (( !Objects.isNull(parent.get(currentVertex)) && isArticulationPoint) ||
                (Objects.isNull(parent.get(currentVertex)) && children >= 2)) {

            articulationPoints.add(currentVertex);
        }
    }


}

class GraphCutVerticesTest {
    public static void main(String[] args) {
        GraphCutVertices.Graph graph = new GraphCutVertices.Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(3, 6);
        graph.addEdge(5, 6);

        graph.display();

        List<Integer> articulationPoints = findArticulationPoints(graph);
        System.out.println("Articulation Points:  " + articulationPoints);

    }
}

