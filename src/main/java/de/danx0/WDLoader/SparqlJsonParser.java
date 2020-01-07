package de.danx0.WDLoader;

import com.google.gson.*;

import java.util.*;

public class SparqlJsonParser {
    private String[] header;

    public List<Map<String, String>> parse(String result) {
        List<Map<String, String>> list = new ArrayList<>();

        JsonObject root = JsonParser.parseString(result).getAsJsonObject();
        header = readHeader(root);
        JsonObject results = root.getAsJsonObject("results");
        JsonArray bindings = results.getAsJsonArray("bindings");
        for (JsonElement e : bindings) {
            if(e instanceof JsonObject) {
                JsonObject obj = (JsonObject)e;
                Map<String, String> map = readElement(obj);
                list.add(map);
            }
        }
        return list;
    }

    private Map<String, String> readElement(JsonObject obj) {
        Map<String, String> map = new HashMap<>();

        for(String var : header) {
            map.put(var, readValue(obj.getAsJsonObject(var)));
        }
        return map;
    }

    private String[] readHeader(JsonObject root) {
        JsonObject head = root.getAsJsonObject("head");
        JsonArray vars = head.getAsJsonArray("vars");
        String[] header = new String[vars.size()];
        int num = 0;
        for(JsonElement e : vars) {
            header[num++] = e.getAsString();
        }

        return header;
    }

    private String readValue(JsonObject json) {
        return json.getAsJsonPrimitive("value").getAsString();
    }

    public String[] getHeader() {
        return header;
    }
}
