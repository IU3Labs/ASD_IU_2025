//Дан целочисленный массив. Верните число, частота встречи которого в
//массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//таких чисел несколько, вернуть наибольшее.

package groupb;

import java.util.Scanner;
import functions.ArrayUtils;

public class FrequencyNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = ArrayUtils.inputArrayFromUser(scanner, "");
        int result = findFrequencyNumber(array);

        System.out.println("Результат: " + result);

        scanner.close();
    }

    public static int findFrequencyNumber(int[] array) {
        int maxNumber = -1;

        for (int i = 0; i < array.length; i++) {
            int frequency = 0;

            for (int j = 0; j < array.length; j++) {
                if (array[j] == array[i]) {
                    frequency++;
                }
            }

            if (array[i] == frequency) {
                if (array[i] > maxNumber) {
                    maxNumber = array[i];
                }
            }
        }

        return maxNumber;
    }
}