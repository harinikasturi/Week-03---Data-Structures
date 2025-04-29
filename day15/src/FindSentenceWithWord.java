public class FindSentenceWithWord {
    public static String findSentence(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {
                "Java is a programming language",
                "Python is also popular",
                "JavaScript is for web development"
        };
        String word = "Python";
        System.out.println("Sentence with '" + word + "': " + findSentence(sentences, word));
    }
}