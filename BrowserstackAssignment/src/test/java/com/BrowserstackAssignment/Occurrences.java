package com.BrowserstackAssignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Occurrences {
	
	public static Map<String, Integer> countWords(List<String> headers) {
        Map<String, Integer> wordCount = new HashMap<>();
        
        for (String header : headers) {
            String[] words = header.toLowerCase().split("\\s+");

            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        return wordCount;
    }
	
	public void ArrayOfRepetations(List<String> translatedHeaders) {
		Map<String, Integer> wordCount = countWords(translatedHeaders);
        System.out.println("-----------------------------------------");
        System.out.println("Now printing the translated headers, identified more than twice across all headers combined");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
	}

}
