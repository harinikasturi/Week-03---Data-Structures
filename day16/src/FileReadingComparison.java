import java.io.*;

public class FileReadingComparison {
    public static void main(String[] args) {
        String filename = "large_file.txt";
        createLargeFile(filename, 100); // Create 100MB file

        long start = System.nanoTime();
        try (FileReader fr = new FileReader(filename)) {
            int c;
            while ((c = fr.read()) != -1) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("FileReader: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename));
             InputStreamReader isr = new InputStreamReader(is)) {
            int c;
            while ((c = isr.read()) != -1) {
                // Process character
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        end = System.nanoTime();
        System.out.println("InputStreamReader: " + (end - start) / 1_000_000.0 + " ms");
    }

    private static void createLargeFile(String filename, int sizeMB) {
        try (FileWriter fw = new FileWriter(filename)) {
            for (int i = 0; i < sizeMB * 1024; i++) {
                fw.write(new String(new char[1024]).replace('\0', 'X'));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}