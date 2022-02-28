package sentences;

public class SentenceTransformer {

    public String shortenSentence(String sentence) {
        String result = sentence;
        if (isValid(sentence)) {
            String[] words = sentence.split(" ");
            if (words.length >=5) {
                result = words[0] + " ... " + words[words.length-1];
            }
        }
        return result;
    }

    private boolean isValid (String sentence) {
        char chrFirst = sentence.charAt(0);
        char chrLast = sentence.charAt(sentence.length()-1);

        if (chrFirst < 'A' || chrFirst > 'Z') throw new IllegalArgumentException("Must start with capital letter!");
        if (chrLast != '.' && chrLast != '!' && chrLast != '?') throw new IllegalArgumentException("Must end with . ! or ?");
        return true;
    }
}
