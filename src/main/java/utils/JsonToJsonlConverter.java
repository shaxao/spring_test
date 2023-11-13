package utils;

import java.io.*;

public class JsonToJsonlConverter {
    public static void main(String[] args) {
        // 输入JSON文件路径
        String inputJsonFilePath = "D:\\GameDo\\w327\\qa.json";

        // 输出JSONL文件路径
        String outputJsonlFilePath = "D:\\GameDo\\w327\\qa.jsonl";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputJsonFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputJsonlFilePath))) {

            // 逐行读取JSON文件
            String line;
            while ((line = reader.readLine()) != null) {
                // 写入JSON对象到JSONL文件
                writer.write(line.trim());
                writer.newLine();
            }

            System.out.println("Conversion complete.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
