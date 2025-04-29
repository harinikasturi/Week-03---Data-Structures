public class StringBuilderReverse {
    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public static void main(String[] args) {
        String str = "hello";
        System.out.println("Original: " + str);
        System.out.println("Reversed: " + reverseString(str));
    }
}