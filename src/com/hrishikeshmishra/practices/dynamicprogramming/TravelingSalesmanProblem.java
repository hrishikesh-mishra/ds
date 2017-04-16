package com.hrishikeshmishra.practices.dynamicprogramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * Problem:
 * Traveling salesman problem (TSP) using DP
 *
 * @author hrishikesh.mishra
 * @wiki https://en.wikipedia.org/wiki/Held%E2%80%93Karp_algorithm
 * @video https://www.youtube.com/watch?v=-JjA4BLQyqE
 * @link http://hrishikeshmishra.com/traveling-salesman-problem-tsp-using-dp/
 */
public class TravelingSalesmanProblem {

    private static final int INFINITY = 999999999;

    /**
     * Create power set and sort them by size
     */
    private static class PowerSet {

        public static Set<Set<Integer>> generate(Set<Integer> originalSet) {

            Set<Set<Integer>> sets = new TreeSet<>(new SetSizeComparator());
            if (originalSet.isEmpty()) {
                sets.add(new HashSet<>());
                return sets;
            }

            List<Integer> list = new ArrayList<>(originalSet);
            Integer first = list.get(0);

            Set<Integer> rest = new HashSet<>(list.subList(1, list.size()));

            Set<Set<Integer>> subSets = generate(rest);

            for (Set<Integer> set : subSets) {
                Set<Integer> newSet = new HashSet<>();
                newSet.add(first);
                newSet.addAll(set);
                sets.add(newSet);
                sets.add(set);
            }

            return sets;
        }
    }

    private static class SetSizeComparator implements Comparator<Set<Integer>> {

        @Override
        public int compare(Set<Integer> o1, Set<Integer> o2) {
            /** Dummy value, because we don't want to remove all sets of same sizes  **/
            if (o1.size() - o2.size() == 0) {
                return 1;
            } else {
                return o1.size() - o2.size();
            }
        }
    }

    /**
     * To hold vertex and vertex set which used to reach source
     */
    private static class Vertex {

        private int vertex;
        private Set<Integer> vertexSet;

        private Vertex(int vertex, Set<Integer> vertexSet) {
            this.vertex = vertex;
            this.vertexSet = vertexSet;
        }

        public static Vertex build(int vertex, Set<Integer> vertexSet) {
            return new Vertex(vertex, vertexSet);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Vertex)) return false;

            Vertex vertex1 = (Vertex) o;

            if (vertex != vertex1.vertex) return false;
            return (vertexSet != null ? vertexSet.equals(vertex1.vertexSet) : vertex1.vertexSet == null);

        }

        @Override
        public int hashCode() {
            int result = vertex;
            result = 31 * result + vertexSet.hashCode();
            return result;
        }
    }

    public static void compute(int[][] distance) {

        /** Total number of cities **/
        int totalNumberOfCities = distance.length;

        /** Power set of all cities except source city **/
        Set<Set<Integer>> sets = PowerSet.generate(createSetElements(totalNumberOfCities - 1));

        /** Work as dynamic programming table, to hold the solution of smaller problem **/
        Map<Vertex, Integer> minimumDistance = new HashMap<>();

        /** Vertex  and parent vertex **/
        Map<Vertex, Integer> parent = new HashMap<>();

        /** Iterate all set from smaller to larger set size |S| **/
        for (Set<Integer> set : sets) {

            /** Iterate all other vertices except source **/
            for (int currentVertex = 1; currentVertex < totalNumberOfCities; currentVertex++) {


                if (set.contains(currentVertex)) {
                    continue;
                }

                Vertex vertex = Vertex.build(currentVertex, set);

                int minDistance = INFINITY;
                int minPreviousVertex = 0;

                /** Finding minimum distance and min previous veretex **/
                for (Integer previousVertex : set) {
                    int cost = distance[previousVertex][currentVertex] + getCost(previousVertex, set, minimumDistance);
                    if (cost < minDistance) {
                        minDistance = cost;
                        minPreviousVertex = previousVertex;
                    }
                }

                /** for ϕ set **/
                if (set.isEmpty()) {
                    minDistance = distance[0][currentVertex];
                }

                /** Updating minimum Distance and min previous vertex **/
                minimumDistance.put(vertex, minDistance);
                parent.put(vertex, minPreviousVertex);

            }
        }


        Set<Integer> set = createSetElements(totalNumberOfCities - 1);
        int minDistance = INFINITY;
        int minPreviousVertex = -1;
        for (Integer previousVertex : set) {
            int cost = distance[previousVertex][0] + getCost(previousVertex, set, minimumDistance);
            if (cost < minDistance) {
                minDistance = cost;
                minPreviousVertex = previousVertex;
            }
        }

        parent.put(Vertex.build(0, set), minPreviousVertex);
        String path = getPath(parent, totalNumberOfCities);
        System.out.println("Minimum Distance: " + minDistance);
        System.out.println("Tour Path: " + path);


    }

    private static String getPath(Map<Vertex, Integer> parent, int totalNumberOfCities) {

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < totalNumberOfCities; i++) {
            set.add(i);
        }

        Integer startVertex = 0;
        Stack<Integer> stack = new Stack<>();

        while (!Objects.isNull(startVertex)) {
            stack.push(startVertex);
            set.remove(startVertex);
            startVertex = parent.get(Vertex.build(startVertex, set));
        }

        StringJoiner joiner = new StringJoiner("->");
        while (!stack.isEmpty()) {
            joiner.add(stack.pop().toString());
        }

        return joiner.toString();


    }

    private static int getCost(Integer previousVertex, Set<Integer> set, Map<Vertex, Integer> minimumDistance) {
        Set<Integer> tempSet = new HashSet<>(set);
        tempSet.remove(previousVertex);
        Vertex vertex = Vertex.build(previousVertex, tempSet);
        return minimumDistance.get(vertex);
    }


    private static Set<Integer> createSetElements(int size) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= size; i++) {
            set.add(i);
        }
        return set;
    }
}

class TravelingSalesmanProblemTest {
    public static void main(String[] args) {

        int[][] distance = {
                {0, 2, 9, 10},
                {1, 0, 6, 4},
                {15, 7, 0, 8},
                {6, 3, 12, 0}
        };


        TravelingSalesmanProblem.compute(distance);
    }
}


