import java.util.ArrayList;
import java.util.List;

public class Practice6 {
    public static void main(String[] args) {
        validateBet(new int[]{5, 90}, "1, 2, 3, 4, 5");
        validateBet(new int[]{5, 90}, "5,4,3,1,2");
        validateBet(new int[]{5, 90}, "5,4;3,1,2");
    }

    static void validateBet(int[] gameType, String textMessage) {

        if (gameType.length != 2) {
            throw new IllegalArgumentException("Only two integer values");
        }

        if (!textMessage.matches("[0-9 ,]+")) {
            throw new IllegalArgumentException("Only a string of integers separated by , or spaces");
            // Return null
        }

        String onlyNumbers = textMessage.replaceAll("(\\s|,)", "");
        String[] numbersArray = onlyNumbers.split("");
        ArrayList<Integer> integersArray = new ArrayList<>();

        for (String number : numbersArray) {
            integersArray.add(Integer.parseInt(number));
        }

        List<Integer> sortedIntegersList = integersArray.stream().sorted().toList();

        sortedIntegersList.forEach(System.out::println);
    }
}
