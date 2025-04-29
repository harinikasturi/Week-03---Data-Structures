import java.io.*;

public class WordCounter {
    public static int countWord(String filePath, String targetWord) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) {
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        String filePath = "example.txt";
        String word = "Java";
        System.out.println("'" + word + "' appears " + countWord(filePath, word) + " times");
    }
}