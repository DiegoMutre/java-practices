import java.util.*;

public class Practice4 {
    public static void main(String[] args) {

        System.out.println(isIsogram("moose")); // => false
        System.out.println(isIsogram("aba")); // => false
        System.out.println(isIsogram("Dermatoglyphics")); // => true
        System.out.println(isIsogram("")); // => true

        System.out.println(isIsogram2("moose")); // => false
        System.out.println(isIsogram2("aba")); // => false
        System.out.println(isIsogram2("Dermatoglyphics")); // => true
        System.out.println(isIsogram2("")); // => true
    }

    static boolean isIsogram(String word) {

        if (word.isEmpty()) return true;

        HashSet<String> letters = new HashSet<>(Arrays.asList(word.toLowerCase().split("")));

        return letters.size() == word.length();
    }

    static boolean isIsogram2(String str) {
        return str.length() == str.toLowerCase().chars().distinct().count();
    }
}
