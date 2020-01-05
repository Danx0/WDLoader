package de.danx0.WDLoader;

import java.util.List;

public class Controller {
    public static void main(String[] args) {
        Controller ctrl = new Controller();
        ctrl.start();
        System.out.println("Hello World!");
    }

    private void start() {
        ServerConnection server = new ServerConnection();
        String result = server.loadData();
        if(result != null) {
            SparqlJsonParser parser = new SparqlJsonParser();
            List<Astronaut> astroList = parser.parseSpace(result);
            CsvExporter exporter = new CsvExporter();
            exporter.exportAstronauts(astroList);
        }
    }
}
