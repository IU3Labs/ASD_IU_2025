package lab1;

import java.util.Scanner;

public class SecondDop extends Zero {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        secondDopTask();
        in.close();
    }

    public static void secondDopTask () {

        // ЗАДАНИЕ:
        // Дан целочисленный массив. Верните число, частота встречи которого в
        // массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
        // таких чисел несколько, вернуть наибольшее.

        System.out.println(" --------------- Second task ---------------");
        byte[] inputt = inData();
        byte[] simpleNums = new byte[inputt.length];
        byte[] meetCounter = new byte[inputt.length];
        byte next_empty = 0;
        byte answer2 = 0;
        for (byte value : inputt) {
            if (!(isElInList(simpleNums, value))) {
                simpleNums[next_empty] = value;
                meetCounter[next_empty] = 0;
                for (byte b : inputt) {
                    if (simpleNums[next_empty] == b) {
                        meetCounter[next_empty]++;
                    }
                }
                next_empty++;
            }
        }
        for (int i = 0; i < simpleNums.length; i++) {
            if ((simpleNums[i] == meetCounter[i]) && (simpleNums[i] > answer2)) {
                answer2 = simpleNums[i];
            }
        }
        if (answer2 == 0) {
            answer2 = -1;
        }

        print(simpleNums);
        System.out.println();
        print(meetCounter);
        System.out.println();
        System.out.println("Answer = " + answer2);

    }
}