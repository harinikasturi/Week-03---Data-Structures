public class StringConcatenation {
    public static void main(String[] args) {
        final int N = 10_000;


        long start = System.nanoTime();
        String result = "";
        for (int i = 0; i < N; i++) {
            result += "a"; // Creates new String object each time
        }
        long end = System.nanoTime();
        System.out.println("String concatenation: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append("a");
        }
        String sbResult = sb.toString();
        end = System.nanoTime();
        System.out.println("StringBuilder: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < N; i++) {
            sbf.append("a");
        }
        String sbfResult = sbf.toString();
        end = System.nanoTime();
        System.out.println("StringBuffer: " + (end - start) / 1_000_000.0 + " ms");
    }
}