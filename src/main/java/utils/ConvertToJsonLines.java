package utils;

import cn.hutool.core.io.FileUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class ConvertToJsonLines {
    private final static long MIN_FILE_SIZE = 10 * 1024 * 1024L;


    public static void main(String[] args) {
        try {
            // 输入文件路径
            String inputFilePath = "D:\\GameDo\\w327\\qa.json";
            // 输出文件路径
            String outputFilePath = "D:\\GameDo\\w327\\output\\";
            try(FileReader fr = new FileReader(inputFilePath);
                BufferedReader br = new BufferedReader(fr)) {
                String temp = "";
                int i = 0;
                long count = 0L;
                FileWriter fw = null;
                BufferedWriter bw = null;

                while ((temp = br.readLine()) != null){
                    if (fw == null || count >= MIN_FILE_SIZE || count == 0L) {
                        if (bw != null) {
                            bw.close();
                        }
                        count = 0L;
                        fw = new FileWriter(outputFilePath+"f_"+i+".jsonl",true);
                        bw    = new BufferedWriter(fw);
                        i++;
                    }
                    if (temp.contains("},")){
                        System.out.println("Debug: Found '},', temp = " + temp);
                        bw.write(temp);
                        bw.newLine();
                    }else {
                        bw.write(temp);
                    }
                    count += temp.length()*2;
                    bw.flush();
                }
                if (bw != null) {
                    bw.close();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
