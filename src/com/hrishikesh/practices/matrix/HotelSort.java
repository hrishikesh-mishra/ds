package com.hrishikesh.practices.matrix;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author hrishikesh.mishra
 */
public class HotelSort {

    public static void main(String[] args) {
        String hotels = getHotels();
        System.out.println(hotels);
    }

    public static String getHotels() {
        Scanner in = new Scanner(System.in);
        String words = in.nextLine();
        int m = Integer.parseInt(in.nextLine());
        Set<String> userWord = getWords(words);
        Map<Integer, Integer> hotelWords = new TreeMap<>();

        for (int i = 0; i < m; i++) {
            int hotelId = Integer.parseInt(in.nextLine());

            String lines = in.nextLine();
            Set<String> wordsSet = getWords(lines);
            wordsSet.retainAll(userWord);
            Integer reviewCount = hotelWords.getOrDefault(hotelId, 0);
            hotelWords.put(hotelId, reviewCount + wordsSet.size());
        }

        return getSortedHotelId(hotelWords);
    }


    private static Set<String> getWords(String words) {
        words = words.replace(",", "").replace(".", "");
        String[] array = words.split(" ");
        Set<String> validWords = new HashSet<>();
        for (String w : array) {
            w = w.trim();
            if (!w.equals(" ")) {
                validWords.add(w.toLowerCase());
            }
        }
        return validWords;
    }

    private static String getSortedHotelId(Map<Integer, Integer> hotelReviewCount) {

        Map<Integer, Integer> orderedHotelReview = new LinkedHashMap<>();
        StringBuffer result = new StringBuffer();

        hotelReviewCount.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).
                forEachOrdered(x -> orderedHotelReview.put(x.getKey(), x.getValue()));

        orderedHotelReview.keySet().forEach(a -> result.append(a + " "));
        return result.toString().trim();
    }


}
