package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//D:\BaiduSyncdisk\2023\01-Java基础知识面试题.pdf
public class PdfToMarkdownConverter {

    public static void main(String[] args) {
        // Specify the directory containing PDF files to be converted
        String pdfDirectory = "D:\\BaiduSyncdisk\\2023\\24.面试和成功求职的秘笈\\文档\\面试题(附赠)【尚学堂·百战程序员】";

        File directory = new File(pdfDirectory);
        File[] pdfFiles = directory.listFiles();

        if (pdfFiles != null) {
            for (File pdfFile : pdfFiles) {
                if (pdfFile.isFile() && pdfFile.getName().toLowerCase().endsWith(".pdf")) {
                    processPdfToMarkdown(pdfFile);
                }
            }
        }

        System.out.println("Batch PDF to Markdown conversion completed.");
    }

    private static void processPdfToMarkdown(File pdfFile) {
        try {
            // 1. Load the PDF file
            PDDocument document = PDDocument.load(pdfFile);

            // 2. Extract text from PDF
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String pdfText = pdfTextStripper.getText(document);

            // 3. Replace NBSP with regular space
            pdfText = pdfText.replaceAll("\u00A0", " "); // Replace NBSP with regular space

            // 4. Convert extracted text to Markdown
            Parser parser = Parser.builder().build();
            Node documentNode = parser.parse(pdfText);
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            String markdownText = renderer.render(documentNode);

            // 5. Save Markdown to a file with the same name as the PDF file
            String outputFileName = pdfFile.getName().replace(".pdf", ".md");
            try (FileWriter writer = new FileWriter(outputFileName)) {
                writer.write(markdownText);
            }

            // 6. Close the PDF document
            document.close();

            System.out.println("Converted " + pdfFile.getName() + " to Markdown.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
