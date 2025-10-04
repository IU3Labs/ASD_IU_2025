package lab1;

import java.util.Scanner;

//Реализовать алгоритм бинарного поиска двумя способами.

public class TaskMain {
    public static int binarySearchIterative(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == key) {
                return mid;
            } else if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] array, int left, int right, int key) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (array[mid] == key) {
            return mid;
        } else if (array[mid] < key) {
            return binarySearchRecursive(array, mid + 1, right, key);
        } else {
            return binarySearchRecursive(array, left, mid - 1, key);
        }
    }

    public static int[] inputArray(Scanner scanner) {
        System.out.print("Введите количество элементов массива: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Введите элементы массива (отсортированные по возрастанию):");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static void printArray(int[] array) {
        System.out.print("Массив: ");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = inputArray(scanner);
        printArray(array);
        System.out.print("Введите число для поиска: ");
        int key = scanner.nextInt();
        int resultIterative = binarySearchIterative(array, key);
        if (resultIterative != -1) {
            System.out.println("Итеративный поиск: элемент найден, позиция: " + resultIterative);
        } else {
            System.out.println("Итеративный поиск: элемент не найден");
        }
        int resultRecursive = binarySearchRecursive(array, 0, array.length - 1, key);
        if (resultRecursive != -1) {
            System.out.println("Рекурсивный поиск: элемент найден, позиция: " + resultRecursive);
        } else {
            System.out.println("Рекурсивный поиск: элемент не найден");
        }
        scanner.close();
    }
}

