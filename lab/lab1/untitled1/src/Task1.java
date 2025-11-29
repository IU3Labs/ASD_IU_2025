// Группа Б. Дан целочисленный массив. Верните число, частота встречи которого в массиве равна его значению.
// Если таких чисел нет, вернуть «-1». Если таких чисел несколько, вернуть наибольшее.
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = ArrayUtils.inputArray(scanner);

        int result = findFrequencyValue(array);
        System.out.println("Результат: " + result);
    }

    private static int countFrequency(int element, int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                count++;
            }
        }
        return count;
    }

    public static int findFrequencyValue(int[] array) {
        int result = -1;

        for (int number : array) {
            int frequency = countFrequency(number, array);

            if (number == frequency && number > result) {
                result = number;
            }
        }

        return result;
    }
}
