import java.util.HashSet;

public class StringBuilderRemoveDuplicates {
    public static String removeDuplicates(String input) {
        StringBuilder sb = new StringBuilder();
        HashSet<Character> seen = new HashSet<>();

        for (char c : input.toCharArray()) {
            if (!seen.contains(c)) {
                seen.add(c);
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "programming";
        System.out.println("Original: " + str);
        System.out.println("Without duplicates: " + removeDuplicates(str));
    }
}