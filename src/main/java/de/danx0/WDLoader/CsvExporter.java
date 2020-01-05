package de.danx0.WDLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter {
    public void exportAstronauts(List<Astronaut> list) {
        try (FileWriter writer = new FileWriter("astro.csv")) {
            writer.write("astronaut;astronautLabel;image;birthdate;birthplace;coord\n");

            for (Astronaut astro : list) {
                writer.write(astro.astronaut + ";");
                writer.write(astro.astronautLabel + ";");
                writer.write(astro.image + ";");
                writer.write(astro.birthdate + ";");
                writer.write(astro.birthplace + ";");
                writer.write(astro.coord + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
