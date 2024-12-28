package com.Browserstack;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class Translation {
	// Translation
	public String TextTranslator(String apiKey, String str, String targetStr) throws IOException, InterruptedException {
		String apiUrl = "https://rapid-translate-multi-traduction.p.rapidapi.com/t";
        HttpClient client = HttpClient.newHttpClient();
        
        String presentLanguage = "es";
        // Create JSON payload
        String payload = String.format(
        		"{\"from\": \"%s\", \"to\": \"%s\", \"q\": \"%s\"}",
                presentLanguage, targetStr, str
        );
        
        HttpRequest request = HttpRequest.newBuilder()
        		.uri(URI.create(apiUrl))
        		.header("Content-Type", "application/json")
        		.header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "rapid-translate-multi-traduction.p.rapidapi.com")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check response status
        String data = response.body().trim();
        if (data.startsWith("[")) {
            JsonArray jsonResponse = JsonParser.parseString(data).getAsJsonArray();
            if (jsonResponse.size() > 0) {
                return jsonResponse.get(0).getAsString(); 
            } 
        }
        else {
                throw new RuntimeException("Unexpected empty array in API response: " + data);
        }
		return response.toString();
    }
	public void Translate(List<String> data) throws IOException, InterruptedException {
		List<String> translatedHeaders = new ArrayList<>();
		for (String s : data) {
			String targetLanguage = "en";
			String apiKey = "a4f222f301mshceadc1d17521ac9p1f8e1fjsn5993861e58cb";
			String translatedText = TextTranslator(apiKey, s, targetLanguage);
			translatedHeaders.add(translatedText);
			System.out.println("Orginal Text: " + s);
			System.out.println("Translated Text: " + translatedText);
			System.out.println("--------------------------------------");
		}
		Occurrences occur = new Occurrences();
		occur.ArrayOfRepetations(translatedHeaders);
		
	}

}
