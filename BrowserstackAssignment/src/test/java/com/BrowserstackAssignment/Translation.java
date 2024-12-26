package com.BrowserstackAssignment;
import com.BrowserstackAssignment.Occurrences;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Translation {

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
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
//        // Create the HTTP request
//                .uri(URI.create(apiUrl))
//                .header("Content-Type", "application/json")
//                .header("X-RapidAPI-Key", apiKey)
//                .header("X-RapidAPI-Host", "rapid-translate-multi-traduction.p.rapidapi.com")
//                .POST(HttpRequest.BodyPublishers.ofString(payload))
//                .build();
//
//        // Send the request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check response status
        String data = response.body().trim();
        if (data.startsWith("[")) {
            JsonArray jsonResponse = JsonParser.parseString(data).getAsJsonArray();
            if (jsonResponse.size() > 0) {
                return jsonResponse.get(0).getAsString(); // Return the first element of the array
            } 
        }
        else {
                throw new RuntimeException("Unexpected empty array in API response: " + data);
        }
		return response.toString();
           
//        if (response.statusCode() == 200) {
//            // Parse JSON response using JsonParser
//        	System.out.println("API Response Body: " + response.body());
////            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
//        	JsonArray jsonResponse = JsonParser.parseString(response).getAsJsonArray();
//            return jsonResponse.get("translated_text").getAsString();
//        } else {
//            throw new RuntimeException("HTTP error code: " + response.statusCode() + " - " + response.body());
//        }
    }
	public void Translate(List<String> data) throws IOException, InterruptedException {
		List<String> translatedHeaders = new ArrayList<>();
		for (String s : data) {
			System.out.println(s+"called");
			String targetLanguage = "en";
			String apiKey = "a4f222f301mshceadc1d17521ac9p1f8e1fjsn5993861e58cb";
			String translatedText = TextTranslator(apiKey, s, targetLanguage);
			translatedHeaders.add(translatedText);
			System.out.println("Translated Text: " + translatedText);
			System.out.println("--------------------------------------");
		}
		Occurrences occur = new Occurrences();
		occur.ArrayOfRepetations(translatedHeaders);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
