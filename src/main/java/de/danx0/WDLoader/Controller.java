package de.danx0.WDLoader;

import java.util.List;
import java.util.Map;

public class Controller {
    public static void main(String[] args) {
        Controller ctrl = new Controller();
        ctrl.start();
    }

    private void start() {
        ServerConnection server = new ServerConnection();
        String result = server.loadData();
        if(result != null) {
            SparqlJsonParser parser = new SparqlJsonParser();
            List<Map<String,String>> list = parser.parse(result);
            String[] header = parser.getHeader();
            CsvExporter exporter = new CsvExporter(header);
            exporter.export(list);
        }
    }
}
