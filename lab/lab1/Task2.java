/*
Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее.
 */
import java.util.Scanner;
import utils.ArrayUtils;

public class Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = ArrayUtils.readArrayFromUser(scanner);

        System.out.print("Исходный массив: ");
        ArrayUtils.printArray(array);

        int result = findNumberWithFrequencyEqualsValue(array);
        System.out.println("Результат: " + result);

        scanner.close();
    }

    public static int findNumberWithFrequencyEqualsValue(int[] array) {
        int maxNumber = -1;

        for (int i = 0; i < array.length; i++) {
            int currentNumber = array[i];
            int frequency = 0;

            for (int j = 0; j < array.length; j++) {
                if (array[j] == currentNumber) {
                    frequency++;
                }
            }

            if (frequency == currentNumber && currentNumber > maxNumber) {
                maxNumber = currentNumber;
            }
        }

        return maxNumber;
    }
}