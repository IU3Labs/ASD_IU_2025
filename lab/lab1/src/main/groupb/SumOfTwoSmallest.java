package main.groupb;

import main.utils.ArrayUtils;
import java.util.Scanner;

/**
 * Задание:
 * Дан массив целых чисел (минимум 5 элементов).
 * Найти сумму двух наименьших положительных чисел.
 */
public class SumOfTwoSmallest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n;
        // Проверяем, чтобы пользователь ввёл хотя бы 5 элементов
        while (true) {
            System.out.print("Введите количество элементов массива (не меньше 5): ");
            n = scanner.nextInt();
            if (n >= 5) {
                break;
            } else {
                System.out.println("Ошибка: количество элементов не может быть меньше 5. Попробуйте снова.");
            }
        }

        // Теперь вводим массив вручную
        int[] arr = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int result = sumOfTwoSmallestPositives(arr);
        System.out.println("Результат: " + result);

        scanner.close();
    }

    /**
     * Метод ищет сумму двух наименьших положительных чисел.
     * Если меньше двух положительных — возвращает -1.
     */
    public static int sumOfTwoSmallestPositives(int[] arr) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int value : arr) {
            if (value > 0) {
                if (value < min1) {
                    min2 = min1;
                    min1 = value;
                } else if (value < min2) {
                    min2 = value;
                }
            }
        }

        if (min1 == Integer.MAX_VALUE || min2 == Integer.MAX_VALUE) {
            return -1; // недостаточно положительных чисел
        }

        return min1 + min2;
    }
}