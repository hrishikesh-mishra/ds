package com.hrishikesh.practices.queue;

import static com.hrishikesh.practices.queue.PetrolPumpsCircularTour.*;

/**
 * Problem:
 * Find the first circular tour that visits all petrol pumps
 * ;
 * Suppose there is a circle. There are n petrol pumps on that circle. You are given two sets of data.
 * 1. The amount of petrol that every petrol pump has.
 * 2. Distance from that petrol pump to the next petrol pump.
 * ;
 * Calculate the first point from where a truck will be able to complete the circle
 * (The truck will stop at each petrol pump and it has infinite capacity).
 * Expected time complexity is O(n). Assume for 1 litre petrol, the truck can go 1 unit of distance.
 * ;
 * For example,
 * Let there be 4 petrol pumps with amount of petrol and distance to next petrol pump value pairs
 * as {4, 6}, {6, 5}, {7, 3} and {4, 5}. The first point from where truck can make a circular
 * tour is 2nd petrol pump. Output should be "start = 1" (index of 2nd petrol pump)
 * ;
 * ;
 * Solution:
 * - We'll pick any petrol pumps as starting point,
 * - Now there could be two possible than
 * - Either, we have enough petrol to reach next petrol pumps, in that case
 * - - We'll move to next petrol pump
 * - Or, we haven't enough petrol to reach next petrol pumps, in that case
 * - - We'll back to one step back from starting point.
 * ;
 * ;
 * Algorithm:
 * - Start from 0th petrol pump;
 * - - Set startIndex = 0, endIndex = startIndex + 1,  availablePetrol = P0.petrol and distance = P0.distance
 * - Iterate till startIndex != endIndex
 * - - Iterate till startIndex != endIndex and availablePetrol < distance
 * - - - Move one step back from starting point i.e. startIndex = (startIndex - 1 ) % totalPetrolPump
 * - - - Update availablePetrol with availablePetrol += P[startIndex].petrol
 * - - - Update distance with distance +=  P[startIndex].distance
 * - - Move one step next i.e. endIndex = (endIndex + 1) % totalPetrolPump
 * - - Update availablePetrol with availablePetrol += P[startIndex].petrol
 * - - Update distance with distance +=  P[startIndex].distance
 * - If our availablePetrol is less than distance then,
 * - - throw "Not possible" exception
 * - Return startIndex
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/find-the-first-circular-tour-that-visits-all-petrol-pumps/
 */
public class PetrolPumpsCircularTour {

    public static class PetrolPump {
        private String name;
        private int petrolQuantity;
        private int nextPetrolPumpDistance;

        public PetrolPump(String name, int petrolQuantity, int nextPetrolPumpDistance) {
            this.name = name;
            this.petrolQuantity = petrolQuantity;
            this.nextPetrolPumpDistance = nextPetrolPumpDistance;
        }

        public String getName() {
            return name;
        }

        public int getPetrolQuantity() {
            return petrolQuantity;
        }

        public int getNextPetrolPumpDistance() {
            return nextPetrolPumpDistance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PetrolPump)) return false;

            PetrolPump that = (PetrolPump) o;

            return getName() != null ? getName().equals(that.getName()) : that.getName() == null;

        }

        @Override
        public int hashCode() {
            return getName() != null ? getName().hashCode() : 0;
        }

        @Override
        public String toString() {
            return name + "(" + petrolQuantity + ", " + nextPetrolPumpDistance + ')';
        }


    }

    public static PetrolPump findStartingPoint(PetrolPump[] petrolPumps) {

        int startIndex = 0,
                endIndex = startIndex + 1;
        int availablePetrol = petrolPumps[startIndex].getPetrolQuantity(),
                distance = petrolPumps[startIndex].getNextPetrolPumpDistance();


        while (startIndex != endIndex) {

            while (availablePetrol < distance && startIndex != endIndex) {
                startIndex = getMod(startIndex - 1, petrolPumps.length);

                availablePetrol += petrolPumps[startIndex].getPetrolQuantity();
                distance += petrolPumps[startIndex].getNextPetrolPumpDistance();
            }

            endIndex = (endIndex + 1) % petrolPumps.length;
            availablePetrol += petrolPumps[endIndex].getPetrolQuantity();
            distance += petrolPumps[endIndex].getNextPetrolPumpDistance();

        }

        if (availablePetrol < distance) {
            throw new RuntimeException("Not possible ");
        }

        return petrolPumps[startIndex];
    }


    private static int getMod(int x, int n) {
        int r = x % n;
        if (r < 0) {
            r += n;
        }
        return r;
    }
}


class PetrolPumpsCircularTourTest {

    public static void main(String[] args) {

        PetrolPump[] petrolPumps = {
                new PetrolPump("A", 4, 6),
                new PetrolPump("B", 6, 5),
                new PetrolPump("C", 7, 3),
                new PetrolPump("D", 4, 5),
        };

        PetrolPump startingPoint = PetrolPumpsCircularTour.findStartingPoint(petrolPumps);
        System.out.println("Starting Point : " + startingPoint.getName());


    }

}
