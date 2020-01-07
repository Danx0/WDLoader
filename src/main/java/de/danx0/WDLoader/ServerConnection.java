package de.danx0.WDLoader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerConnection {
    private final static String BASE_URL = "https://query.wikidata.org/sparql?format=json&query=";

    public String loadData(String query) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + query))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("ServerConnection.loadData(): " + response.statusCode());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
