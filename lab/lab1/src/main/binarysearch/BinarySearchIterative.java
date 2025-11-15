package main.binarysearch;

import main.utils.ArrayUtils;
import java.util.Scanner;

/**
 * Обязательное задание:
 * Реализовать алгоритм бинарного поиска (итеративный способ)
 */
public class BinarySearchIterative {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int[] arr = ArrayUtils.inputArray(scanner);

        // Сортировка перед поиском
        ArrayUtils.insertionSort(arr);
        ArrayUtils.printArray(arr);

        System.out.print("Введите элемент для поиска: ");
        int target = scanner.nextInt();

        int[] result = binarySearch(arr, target);
        int index = result[0];
        int attempts = result[1];

        if (index == -1) {
            System.out.println("Элемент не найден. Количество попыток найти число: " + attempts);
        } else {
            System.out.println("Элемент найден под индексом: " + index +
                    " (Количество попыток найти число: " + attempts + ")");
        }

        scanner.close();
    }

    public static int[] binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int attempts = 0;

        while (left <= right) {
            attempts++;
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return new int[]{mid, attempts};
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{-1, attempts};
    }
}