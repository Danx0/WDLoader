package de.danx0.WDLoader;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class SparqlJsonParser {
    //private class
    public List<Astronaut> parseSpace(String result) {
        List<Astronaut> list = new ArrayList<>();

        JsonObject root = JsonParser.parseString(result).getAsJsonObject();
        JsonObject results = root.getAsJsonObject("results");
        JsonArray bindings = results.getAsJsonArray("bindings");
        for (JsonElement e : bindings) {
            if(e instanceof JsonObject) {
                Astronaut astro = new Astronaut();
                JsonObject obj = (JsonObject)e;
                astro.astronaut = getValue(obj.getAsJsonObject("astronaut"));
                astro.astronautLabel = getValue(obj.getAsJsonObject("astronautLabel"));
                astro.birthdate = getValue(obj.getAsJsonObject("birthdate"));
                astro.birthplace = getValue(obj.getAsJsonObject("birthplace"));
                astro.coord = getValue(obj.getAsJsonObject("coord"));
                astro.image = getValue(obj.getAsJsonObject("image"));
                list.add(astro);
            }
        }
        return list;
    }

    private String getValue(JsonObject json) {
        return json.getAsJsonPrimitive("value").getAsString();
    }
}
