//Пусть любое число – это массив его цифр слева направо. Пример, число
//1234 – это массив [1,2,3,4].
//Дан массив целых чисел. Реализовать умножение двух чисел.
//Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//Результат – число, представленное массивом.

package groupb;

import java.util.Scanner;
import functions.ArrayUtils;

public class ArrayMultiplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество цифр первого числа:");
        int n1 = scanner.nextInt();
        int[] num1 = inputDigitsArray(scanner, n1, "первого числа");

        System.out.println("Введите количество цифр второго числа:");
        int n2 = scanner.nextInt();
        int[] num2 = inputDigitsArray(scanner, n2, "второго числа");

        int[] result = multiplyArrays(num1, num2);
        ArrayUtils.printArray(result, "Результат умножения");

        scanner.close();
    }

    private static int[] inputDigitsArray(Scanner scanner, int size, String arrayName) {
        int[] array = new int[size];
        System.out.println("Введите цифры " + arrayName + " (слева направо):");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static int[] multiplyArrays(int[] num1, int[] num2) {
        int len1 = num1.length;
        int len2 = num2.length;
        int[] result = new int[len1 + len2];

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int product = num1[i] * num2[j];
                int sum = product + result[i + j + 1];

                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }

        int startIndex = 0;
        while (startIndex < result.length - 1 && result[startIndex] == 0) {
            startIndex++;
        }

        if (startIndex == 0) {
            return result;
        }

        int[] finalResult = new int[result.length - startIndex];
        for (int i = 0; i < finalResult.length; i++) {
            finalResult[i] = result[startIndex + i];
        }

        return finalResult;
    }
}