package de.danx0.WDLoader;

import java.util.List;
import java.util.Map;

public class Controller {
    private final static String querySpace = "%23added%20before%202016-10%0A%0A%23%20select%20all%20astronauts%20with%20name%2C%20image%2C%20birthdate%2C%20birthplace%20and%20coordinates%20of%20the%20birthplace%0A%0ASELECT%20%3Fastronaut%20%3FastronautLabel%20%3Fimage%20%3Fbirthdate%20%3Fbirthplace%20%3Fcoord%20WHERE%20%7B%0A%20%20%3Fastronaut%20%3Fx1%20wd%3AQ11631%3B%0A%20%20wdt%3AP18%20%3Fimage%3B%0A%20%20wdt%3AP569%20%3Fbirthdate%3B%0A%20%20wdt%3AP19%20%3Fbirthplace.%0A%20%0A%20%20%3Fbirthplace%20wdt%3AP625%20%3Fcoord%0A%20%20SERVICE%20wikibase%3Alabel%20%7B%20bd%3AserviceParam%20wikibase%3Alanguage%20%22en%22.%20%7D%0A%7D%0AORDER%20BY%20DESC(%3Fbirthdate)";

    public static void main(String[] args) {
        Controller ctrl = new Controller();
        ctrl.start();
    }

    private void start() {
        ServerConnection server = new ServerConnection();
        String result = server.loadData(querySpace);
        if(result != null) {
            SparqlJsonParser parser = new SparqlJsonParser();
            List<Map<String,String>> list = parser.parse(result);
            String[] header = parser.getHeader();
            CsvExporter exporter = new CsvExporter(header);
            exporter.export(list);
        }
    }
}
