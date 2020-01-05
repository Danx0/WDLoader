package de.danx0.WDLoader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerConnection {
    private final static String urlSpace = "https://query.wikidata.org/sparql?format=json&query=%23added%20before%202016-10%0A%0A%23%20select%20all%20astronauts%20with%20name%2C%20image%2C%20birthdate%2C%20birthplace%20and%20coordinates%20of%20the%20birthplace%0A%0ASELECT%20%3Fastronaut%20%3FastronautLabel%20%3Fimage%20%3Fbirthdate%20%3Fbirthplace%20%3Fcoord%20WHERE%20%7B%0A%20%20%3Fastronaut%20%3Fx1%20wd%3AQ11631%3B%0A%20%20wdt%3AP18%20%3Fimage%3B%0A%20%20wdt%3AP569%20%3Fbirthdate%3B%0A%20%20wdt%3AP19%20%3Fbirthplace.%0A%20%0A%20%20%3Fbirthplace%20wdt%3AP625%20%3Fcoord%0A%20%20SERVICE%20wikibase%3Alabel%20%7B%20bd%3AserviceParam%20wikibase%3Alanguage%20%22en%22.%20%7D%0A%7D%0AORDER%20BY%20DESC(%3Fbirthdate)";


    public String loadData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlSpace))
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
