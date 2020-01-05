package de.danx0.WDLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvExporter {
    private static final String EXPORT_FOLDER = "wdloader_export/";

    public CsvExporter() {
        init();
    }

    public void init() {
        try {
            Files.createDirectories(Paths.get(EXPORT_FOLDER));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportAstronauts(List<Astronaut> list) {
        try (FileWriter writer = new FileWriter(EXPORT_FOLDER + "astro.csv")) {
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
