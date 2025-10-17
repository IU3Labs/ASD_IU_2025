package lab1.b;

//3 Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
//число, которое является суммой двух наименьших положительных чисел.

import java.util.Scanner;

public class SumOfMinPositive {
    public static int calculateSumOfTwoMinPositive(int[] array) {
        int firstMin = -1;
        int secondMin = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                if (firstMin == -1) {
                    firstMin = array[i];
                } else if (firstMin > array[i]) {
                    secondMin = firstMin;
                    firstMin = array[i];
                } else if (secondMin == -1 || secondMin > array[i]) {
                    secondMin = array[i];
                }
            }
        }
        if (firstMin == -1 || secondMin == -1) {
            return -1;
        }
        return firstMin + secondMin;
    }

    public static void arrayFill(int[] array, int n) {
        System.out.print("Введите элементы массива: ");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
    }

    public static void printArray(int[] array) {
        System.out.print("[");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count;

        while (true) {
            System.out.print("Введите количество элементов массива (минимум 5): ");
            count = scanner.nextInt();

            if (count >= 5) {
                break;
            } else {
                System.out.println("Введите число не меньше 5.");
            }
        }

        int[] array = new int[count];
        arrayFill(array, count);

        System.out.println("Исходный массив: ");
        printArray(array);

        int result = calculateSumOfTwoMinPositive(array);

        if (result == -1) {
            System.out.println("В массиве недостаточно положительных чисел");
        } else {
            System.out.println("Сумма двух наименьших положительных элементов массива: " + result);
        }
        scanner.close();
    }
}
