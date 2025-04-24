public class LinearSearchWordInSentences {
    public static String findSentenceWithWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {"The quick brown fox", "Jumped over the lazy dog", "Hello world"};
        String word = "lazy";
        String result = findSentenceWithWord(sentences, word);
        System.out.println(result);
    }
}
