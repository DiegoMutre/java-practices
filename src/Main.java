// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("This is a great test!!!");
        int result = aggregateIntegerStrings(new String[]{"123"});
        System.out.println(result);
    }

    static int aggregateIntegerStrings(String[] integers) {
        int sum = 0;
        try {
            for (String integer : integers) {
                sum += Integer.parseInt(integer);
            }
        } catch (Exception err) {
            System.out.println("Something went wrong");
        }

        return sum;
    }
}