public class StringBufferVsBuilder {
    public static void main(String[] args) {
        int iterations = 1000000;
        String str = "hello";

        // StringBuffer test
        long startTime = System.nanoTime();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append(str);
        }
        long bufferTime = System.nanoTime() - startTime;

        // StringBuilder test
        startTime = System.nanoTime();
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sBuilder.append(str);
        }
        long builderTime = System.nanoTime() - startTime;

        System.out.println("StringBuffer time: " + bufferTime + " ns");
        System.out.println("StringBuilder time: " + builderTime + " ns");
        System.out.println("Difference: " + (bufferTime - builderTime) + " ns");
    }
}