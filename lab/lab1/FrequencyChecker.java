import java.util.Scanner;
//1 Дан целочисленный массив. Верните число, частота встречи которого в
//массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//таких чисел несколько, вернуть наибольшее.
public class FrequencyChecker {

    private static void checkFrequency(int[] numbers, int size) {
        int result = -1;

        for (int j = 0; j < size; j++) {
            int count = 0;

            for (int k = 0; k < size; k++) {
                if (numbers[j] == numbers[k]) {
                    count++;
                }
            }

            if (numbers[j] == count && numbers[j] > result) {
                result = numbers[j];
            }
        }

        System.out.println("Результат: " + result);
    }

    public static int[] readArray(int size, Scanner scanner) {
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Введите элемент " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }
        return numbers;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество элементов: ");
        int size = scanner.nextInt();

        int[] numbers = readArray(size, scanner);

        checkFrequency(numbers, size);
    }
}