package lab2.utils;

import java.util.Random;

public class utils {
    public static String generateSequence() {
        Random random = new Random();
        String result = "" + (char) ('A' + random.nextInt(26));
        for (int i = 0; i < 4; i++) {
            result += (char) ('a' + random.nextInt(26));
        }
        return result;
    }
    public static Integer random100(){
        Random random = new Random();
        return random.nextInt(100);
    }
}
