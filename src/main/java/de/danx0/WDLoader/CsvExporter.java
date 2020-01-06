package de.danx0.WDLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class CsvExporter {
    private static final String EXPORT_FOLDER = "wdloader_export/";
    private String[] header;

    public CsvExporter(String[] header) {
        this.header = header;
        init();
    }

    public void init() {
        try {
            Files.createDirectories(Paths.get(EXPORT_FOLDER));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void export(List<Map<String,String>> list) {
        try (FileWriter writer = new FileWriter(EXPORT_FOLDER + "export.csv")) {
            writeHeader(writer);

            for (Map<String, String> map : list)
                writeElement(writer, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeElement(FileWriter writer, Map<String, String> map) throws IOException {
        for(int i = 0; i < header.length; i++) {
            writer.write(map.get(header[i]));

            if(i < header.length - 1)
                writer.write(";");
            else
                writer.write("\n");
        }
    }

    private void writeHeader(FileWriter writer) throws IOException {
        for(int i = 0; i < header.length; i++) {
            writer.write(header[i]);

            if(i < header.length - 1)
                writer.write(";");
            else
                writer.write("\n");
        }
    }
}
