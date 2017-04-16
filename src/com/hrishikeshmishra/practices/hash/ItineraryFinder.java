package com.hrishikeshmishra.practices.hash;


import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Problem:
 * Find Itinerary from a given list of tickets.
 * Given a list of tickets, find itinerary in order using the given list.
 * ;
 * Example:
 * Input:
 * "Chennai" -> "Banglore"
 * "Bombay" -> "Delhi"
 * "Goa"    -> "Chennai"
 * "Delhi"  -> "Goa"
 * ;
 * Output:
 * Bombay->Delhi, Delhi->Goa, Goa->Chennai, Chennai->Banglore,
 * ;
 * It may be assumed that the input list of tickets is not cyclic and there is one ticket from
 * every city except final destination.
 * ;
 * Solution:
 * - One solution is  Topological Sorting
 * - Another is iteration over hash map (hint reverse of map to get starting point)
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-itinerary-given-list-tickets/
 */
public class ItineraryFinder {

    public static void printFindItinerary(Map<String, String> map) {
        String startingPoint = getStartingPoint(map);

        StringJoiner joiner = new StringJoiner(" -> ");
        joiner.add(startingPoint);

        String intermediatePoint = startingPoint;

        /** Follow starting point to end point  **/
        while (map.containsKey(intermediatePoint)) {
            intermediatePoint = map.get(intermediatePoint);
            joiner.add(intermediatePoint);
        }

        System.out.println(joiner.toString());
    }

    private static String getStartingPoint(Map<String, String> map) {
        /** Map for reverse lookup, its easy to eariler stating poin **/
        Map<String, String> reserveMap = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            reserveMap.put(entry.getValue(), entry.getKey());
        }

        String startCity = getFirstKey(map);

        /** Iterate till starting exists **/
        while (reserveMap.containsKey(startCity)) {
            startCity = reserveMap.get(startCity);
        }
        return startCity;
    }

    private static String getFirstKey(Map<String, String> map) {
        return map.entrySet().stream().findFirst().get().getKey();
    }
}


class ItineraryFinderTest {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>() {
            {
                put("Chennai", "Banglore");
                put("Bombay", "Delhi");
                put("Goa", "Chennai");
                put("Delhi", "Goa");
            }
        };

        ItineraryFinder.printFindItinerary(map);

    }
}