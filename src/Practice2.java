import java.util.Arrays;
import java.util.Collections;

public class Practice2 {

    public static void main(String[] args) {
        Boolean[] booleansArrays = {true,  true,  true,  false,
                true,  true,  true,  true,
                true,  false, true,  false,
                true,  false, false, true,
                true,  true,  true,  true,
                false, false, true,  true, null, true, null, true};

        int result = countSheep(booleansArrays);
        int result2 = countSheep2(booleansArrays);
        int result3 = countSheep3(booleansArrays);
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
    }

    static int countSheep(Boolean[] sheep) {
        int sheepPresent = 0;

        for (Boolean sheepValue : sheep) {
            try {
                if (sheepValue) sheepPresent++;
            } catch (Exception e) {
                System.out.println("Null values aren't allowed");
            }
        }

        return sheepPresent;
    }

    static int countSheep2(Boolean[] sheep) {
        int sheepPresent = 0;

        for (Boolean present : sheep) {
            if (Boolean.TRUE.equals(present)) sheepPresent++;
        }

        return sheepPresent;
    }

    static int countSheep3(Boolean[] sheep) {
        return Collections.frequency(Arrays.asList(sheep), true);
    }
}
