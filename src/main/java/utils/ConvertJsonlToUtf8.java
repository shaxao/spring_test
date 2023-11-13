package utils;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertJsonlToUtf8 {

    public static void main(String[] args) {
        String folderPath = "D:\\GameDo\\w327\\output";

        try {
            List<Path> jsonlFiles = getJsonlFiles(folderPath);

            for (Path filePath : jsonlFiles) {
                convertToUtf8(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> getJsonlFiles(String folderPath) throws IOException {
        return Files.walk(Paths.get(folderPath))
                .filter(path -> path.toString().toLowerCase().endsWith(".jsonl"))
                .collect(Collectors.toList());
    }

    private static void convertToUtf8(Path filePath) throws IOException {
        String content = new String(Files.readAllBytes(filePath), detectFileCharset(filePath));

        if (!detectFileCharset(filePath).equals(StandardCharsets.UTF_8)) {
            Files.write(filePath, content.getBytes(StandardCharsets.UTF_8));
            System.out.println("Converted file: " + filePath);
        }
    }

    private static Charset detectFileCharset(Path filePath) throws IOException {
        try (InputStream inputStream = new FileInputStream(filePath.toFile())) {
            byte[] buffer = new byte[4096];
            UniversalDetector detector = new UniversalDetector(null);

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0 && !detector.isDone()) {
                detector.handleData(buffer, 0, bytesRead);
            }

            detector.dataEnd();
            String charsetName = detector.getDetectedCharset();
            return charsetName != null ? Charset.forName(charsetName) : StandardCharsets.UTF_8;
        }
    }
}
