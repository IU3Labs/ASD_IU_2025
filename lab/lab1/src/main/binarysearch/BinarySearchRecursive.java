package main.binarysearch;

import main.utils.ArrayUtils;
import java.util.Scanner;

/**
 * Обязательное задание:
 * Реализовать алгоритм бинарного поиска (рекурсивный способ)
 */
public class BinarySearchRecursive {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int[] arr = ArrayUtils.inputArray(scanner);
        ArrayUtils.insertionSort(arr);
        ArrayUtils.printArray(arr);

        System.out.print("Введите элемент для поиска: ");
        int target = scanner.nextInt();

        // запускаем рекурсивный бинарный поиск
        int[] result = binarySearch(arr, target, 0, arr.length - 1, 0);
        int index = result[0];
        int attempts = result[1];

        if (index == -1) {
            System.out.println("Элемент не найден. Количество попыток найти число: " + attempts);
        } else {
            System.out.println("Элемент найден под индексом: " + index +
                    " (количество попыток найти число: " + attempts + ")");
        }

        scanner.close();
    }

    /**
     * Рекурсивный бинарный поиск с подсчётом вызовов.
     * arr отсортированный массив
     * target искомое значение
     * left левая граница
     * right правая граница
     * calls счётчик вызовов
     * int[]{индекс или -1, количество попыток найти число}
     */
    public static int[] binarySearch(int[] arr, int target, int left, int right, int calls) {
        calls++;
        if (left > right) {
            return new int[]{-1, calls};
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return new int[]{mid, calls};
        } else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, right, calls);
        } else {
            return binarySearch(arr, target, left, mid - 1, calls);
        }
    }
}