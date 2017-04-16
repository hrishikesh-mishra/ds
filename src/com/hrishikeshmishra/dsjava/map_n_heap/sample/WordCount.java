package com.hrishikeshmishra.dsjava.map_n_heap.sample;



import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by hrishikesh.mishra on 26/03/16.
 */
public class WordCount {
    public static void main(String[] args) {
        Map<String, Integer> frequency = new HashMap<>();

        Scanner doc = new Scanner(System.in).useDelimiter("[^a-zA-Z]+");
        while (doc.hasNext()){
            String word = doc.next().toLowerCase();
            Integer count = frequency.get(word);
            if(Objects.isNull(count)) count=0;
            frequency.put(word,count);
        }

        int maxCount = 0 ;
        String maxWord = "no word";
        for(Map.Entry<String, Integer> entry: frequency.entrySet()){
            if(entry.getValue() > maxCount){
                maxCount = entry.getValue();
                maxWord = entry.getKey();
            }
        }

        System.out.print("The most frequent word is :'" + maxWord);
        System.out.println("' with :"+maxCount + " occurrences.");
    }
}
