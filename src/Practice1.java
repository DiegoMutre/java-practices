// Write a function that takes an array of words and smashes them together into a sentence and returns the sentence.
// You can ignore any need to sanitize words or add punctuation, but you should add spaces between each word.
// Be careful, there shouldn't be a space at the beginning or the end of the sentence!

public class Practice1 {
    public static void main(String[] args) {
        String result;
        result = smash("hello", "world", "brother", "you", "are", "the", "best", "you", "will", "always", "be");
        System.out.println(result);

        result = smashImproved("hello", "world", "brother", "you", "are", "the", "best", "you", "will", "always", "be");
        System.out.println("With method improved: ");
        System.out.println(result);
    }

    static String smash(String... words) {
        StringBuilder smashedWords = new StringBuilder();

        for (String word : words) {
            smashedWords.append(word).append(" ");
        }

        return smashedWords.toString().trim();

//        StringBuilder smashedWords;
//        smashedWords = new StringBuilder();
//
//        for (String word : words) {
//            smashedWords.append(word).append(" ");
//        }
//
//        return smashedWords.toString().trim();
    }

    static String smashImproved(String... words) {
        return String.join(" ", words);
    }
}
