import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PerformanceComparison {

    public static void main(String[] args) {
        String str = "hello";
        int times = 1000000;

        long startTime = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            stringBuilder.append(str);
        }
        long endTime = System.nanoTime();
        long stringBuilderTime = endTime - startTime;
        System.out.println("StringBuilder time: " + stringBuilderTime / 1000000 + " ms");

        startTime = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < times; i++) {
            stringBuffer.append(str);
        }
        endTime = System.nanoTime();
        long stringBufferTime = endTime - startTime;
        System.out.println("StringBuffer time: " + stringBufferTime / 1000000 + " ms");


        String filePath = "largefile.txt"; 
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            startTime = System.nanoTime();
            int wordCountFileReader = countWords(bufferedReader);
            endTime = System.nanoTime();
            long fileReaderTime = endTime - startTime;
            System.out.println("FileReader word count: " + wordCountFileReader);
            System.out.println("FileReader time: " + fileReaderTime / 1000000 + " ms");
        } catch (IOException e) {
            System.err.println("Error reading file using FileReader: " + e.getMessage());
        }

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            startTime = System.nanoTime();
            int wordCountInputStreamReader = countWords(bufferedReader);
            endTime = System.nanoTime();
            long inputStreamReaderTime = endTime - startTime;
            System.out.println("InputStreamReader word count: " + wordCountInputStreamReader);
            System.out.println("InputStreamReader time: " + inputStreamReaderTime / 1000000 + " ms");
        } catch (IOException e) {
            System.err.println("Error reading file using InputStreamReader: " + e.getMessage());
        }
    }

    private static int countWords(BufferedReader reader) throws IOException {
        int wordCount = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");
            wordCount += words.length;
        }
        return wordCount;
    }
}
