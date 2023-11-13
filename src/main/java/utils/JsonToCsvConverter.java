package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class JsonToCsvConverter {

    public static void main(String[] args) {
        File jsonFile = new File("D:\\GameDo\\w327\\output.json");
        File outputCsvFile = new File("D:\\GameDo\\w327\\csv\\qa.csv");

        try {
            processJsonFile(jsonFile, outputCsvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processJsonFile(File jsonFile, File outputCsvFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Iterator<JsonNode> jsonIterator = objectMapper.reader().createParser(jsonFile).readValuesAs(JsonNode.class);

        try (FileWriter csvWriter = new FileWriter(outputCsvFile)) {
            int batchSize = 0;
            StringBuilder batchBuilder = new StringBuilder();

            while (jsonIterator.hasNext()) {
                JsonNode jsonNode = jsonIterator.next();
                String q = jsonNode.get("q").asText();
                String a = jsonNode.get("a").asText();

                String csvLine = String.format("\"%s\",\"%s\"\n", q, a);
                batchBuilder.append(csvLine);

                // Adjust the batch size as needed
                batchSize += csvLine.length();

                // If the batch size exceeds 15MB, write to the CSV file and reset the batch
                if (batchSize > 15 * 1024 * 1024) {
                    csvWriter.append(batchBuilder);
                    batchBuilder.setLength(0);
                    batchSize = 0;
                }
            }

            // Write any remaining data to the CSV file
            if (batchBuilder.length() > 0) {
                csvWriter.append(batchBuilder);
            }
        }
    }
}
