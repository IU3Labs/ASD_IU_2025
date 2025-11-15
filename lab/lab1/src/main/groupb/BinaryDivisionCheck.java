package main.groupb;

import java.util.Scanner;

/**
 * Задание:
 * Дан массив целых чисел, состоящий из 0 и 1 (число в двоичной системе).
 * Необходимо:
 * 1. Вывести массив, полученный при переводе каждой части числа в десятичную систему.
 *    (Пример: [1,1,0] → [1,3,6]).
 * 2. Ввести число n (делитель).
 * 3. Вернуть массив boolean, где true — если число делится на n, иначе false.
 */
public class BinaryDivisionCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Просим пользователя ввести двоичный массив
        System.out.println("Введите количество элементов двоичного массива:");
        int size = scanner.nextInt();

        int[] binaryArray = new int[size];
        System.out.println("Введите элементы массива (только 0 или 1):");
        for (int i = 0; i < size; i++) {
            binaryArray[i] = scanner.nextInt();
            while (binaryArray[i] != 0 && binaryArray[i] != 1) {
                System.out.print("Ошибка! Можно вводить только 0 или 1. Повторите ввод элемента: ");
                binaryArray[i] = scanner.nextInt();
            }
        }

        // 2. Переводим в десятичные числа и выводим
        int[] decimalArray = convertBinaryToDecimal(binaryArray);
        System.out.print("Массив в десятичной системе: ");
        printArray(decimalArray);

        // 3. Вводим число n (делитель)
        System.out.print("Введите число n (делитель): ");
        int n = scanner.nextInt();

        // 4. Получаем булев массив с результатами деления
        boolean[] resultArray = checkDivisibility(decimalArray, n);

        System.out.print("Результат (true - делится нацело, false - нет): ");
        printBooleanArray(resultArray);

        scanner.close();
    }

    /**
     * Преобразует двоичный массив в массив десятичных чисел
     * Пример: [1, 1, 0] → [1, 3, 6]
     */
    public static int[] convertBinaryToDecimal(int[] binaryArray) {
        int[] decimalArray = new int[binaryArray.length];
        int currentValue = 0;

        for (int i = 0; i < binaryArray.length; i++) {
            currentValue = currentValue * 2 + binaryArray[i];
            decimalArray[i] = currentValue;
        }

        return decimalArray;
    }

    /**
     * Проверяет делимость каждого числа массива на n.
     */
    public static boolean[] checkDivisibility(int[] decimalArray, int n) {
        boolean[] result = new boolean[decimalArray.length];
        for (int i = 0; i < decimalArray.length; i++) {
            result[i] = (decimalArray[i] % n == 0);
        }
        return result;
    }

    /**
     * Выводит массив целых чисел.
     */
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    /**
     * Выводит массив логических значений.
     */
    public static void printBooleanArray(boolean[] arr) {
        for (boolean value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}