package utils;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class CheckFileEncoding {

    public static void main(String[] args) {
        String filePath = "D:\\GameDo\\w327\\output\\f_0.jsonl";

        try {
            Charset fileEncoding = getFileEncoding(filePath);
            System.out.println("File encoding: " + fileEncoding);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Charset getFileEncoding(String filePath) throws IOException {
        try (InputStream inputStream = new FileInputStream(filePath)) {
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
