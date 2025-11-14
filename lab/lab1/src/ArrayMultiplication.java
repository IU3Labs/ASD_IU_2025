/*
Группа Б, задание 2
Пусть любое число – это массив его цифр слева направо.
Пример, число 1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
Результат – число, представленное массивом.
 */

import java.util.Scanner;

public class ArrayMultiplication {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Первый масссив");
        int[] array1 = ArrayTools.createArray();
        System.out.println("Второй массив");
        int[] array2 = ArrayTools.createArray();
        int[] resultArray = multipliarArrays(array1, array2);
        System.out.println("Результат умножения");
        ArrayTools.printArray(resultArray);

    }


    public static int arrayToNumber(int[] array) {
        int number = 0;
        for (int i = 0; i < array.length; i++) {
            number = number * 10 + array[i];
        }
        return number;
    }

    public static int[] numberToArray(int number) {
        if (number == 0) {
            return new int[]{0};
        }

        int length = 0;
        int temp = number;
        while (temp > 0) {
            length++;
            temp /= 10;
        }

        int[] result = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            result[i] = number % 10;
            number /= 10;
        }

        return result;
    }

    private static int[] multipliarArrays(int[] first, int[] second) {
        return (numberToArray(arrayToNumber(first) * arrayToNumber(second)));
    }
}



