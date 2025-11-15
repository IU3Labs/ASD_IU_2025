package main.utils;

import java.util.Scanner;

/**
 * Вспомогательный класс для работы с массивами.
 * Содержит методы для:
 * - ввода массива с клавиатуры;
 * - вывода массива на экран;
 * - сортировки массива методом вставок.
 */

public class ArrayUtils {
    //Метод ввода массива с клавиатуры.
    public static int[] inputArray(Scanner scanner) {
        System.out.print("Введите количество элементов массива: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    //Метод вывода массива в консоль.
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    //Метод сортировки массива методом вставок (insertion sort).
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];     // текущий элемент
            int j = i - 1;

            // перемещаем элементы arr[0..i-1], которые больше key, на одну позицию вперёд
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}