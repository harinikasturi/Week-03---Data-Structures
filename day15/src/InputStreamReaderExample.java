import java.io.*;

public class InputStreamReaderExample {
    public static void readFile(String filePath, String charset) {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), charset);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFile("binary.dat", "UTF-8");
    }
}