package com.hrishikeshmishra.practices.recursion;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

import static com.hrishikeshmishra.practices.recursion.StickToBuy.buy;

/**
 * @author hrishikesh.mishra
 */
public class StickToBuy {



    private static class Element {
        char c;
        int count;
        Element(char c, int count){
            this.c= c;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "c=" + c +
                    ", count=" + count +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Element)) return false;

            Element element = (Element) o;

            if (c != element.c) return false;
            return count == element.count;

        }

        @Override
        public int hashCode() {
            int result = (int) c;
            result = 31 * result + count;
            return result;
        }
    }

    private static class Pair {
        Element e1;
        Element e2;
        Pair(Element e1, Element e2){
            this.e1 = e1;
            this.e2 = e2;
        }

        public Integer totalCount () {
            return e1.count + e2.count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;

            Pair pair = (Pair) o;

            if (e1 != null ? !e1.equals(pair.e1) : pair.e1 != null) return false;
            return e2 != null ? e2.equals(pair.e2) : pair.e2 == null;

        }

        @Override
        public int hashCode() {
            int result = e1 != null ? e1.hashCode() : 0;
            result = 31 * result + (e2 != null ? e2.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "e1=" + e1 +
                    ", e2=" + e2 +
                    '}';
        }
    }



    public static void buy(int sticksToBuy, int boxesInStore, int boxesToBuy) {
        /** Base case validation **/
        int totalSum = (boxesInStore) * (boxesInStore + 1) / 2;
        if (totalSum < sticksToBuy) {
            System.out.println("-1");
            return;
        }

        Set<Integer> set = new HashSet<>();
        

        Set<Integer> srt = new TreeSet<>();

        Set<Integer> boxes = new HashSet<>();
        boolean isSuccess = buy(sticksToBuy, boxesInStore, boxesToBuy, boxes, false, boxesToBuy);

        if (isSuccess) {
            StringJoiner sb = new StringJoiner(" ");
            for(int box: boxes ){
                sb.add(String.valueOf(box));
            }

            System.out.println(sb);
        }

    }


    private static boolean buy(int sticksToBuy, int boxesInStore, int boxesToBuy, Set<Integer> boxes, boolean flg, int ttl) {

        if (boxes.size() == ttl){
            return true;
        }
        /** Base case for success **/
        if (boxesToBuy == 0 && sticksToBuy == 0) {
            return true;
        }
        /** Failure case **/
        else if (boxesInStore <= 0 && (boxesToBuy > 0 || sticksToBuy > 0)) {
            return false;
        } else if (sticksToBuy < 0 || boxesInStore < 0 || boxesToBuy < 0) {
            return false;
        }


       // System.out.printf("%d %d %d %b %d \n", sticksToBuy, boxesInStore, boxesToBuy, flg, ttl);


        int remainingSticks = sticksToBuy - boxesInStore;

        boolean exclude = buy(sticksToBuy, boxesInStore - 1, boxesToBuy, boxes, false, ttl);
        boolean include = buy(remainingSticks, boxesInStore - remainingSticks, boxesToBuy - 1, boxes, true, ttl);

        if (include && boxes.size() < ttl ) {

            boxes.add(boxesInStore);

        }

        return exclude || include;
    }

}


class StickToBuyTest {

    public static void main(String[] args) {
        buy(9, 10, 2);
    }
}
