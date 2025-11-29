//Реализовать алгоритм бинарного поиска двумя способами.

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        System.out.print("Введите размер массива: ");
        int arrayLength = scanner.nextInt();

        int[] arr = new int[arrayLength];
        inputArray(arr);

        System.out.println("Исходный массив:");
        printArray(arr);
        bubbleSort(arr);
        System.out.println("Отсортированный массив:");
        printArray(arr);

        System.out.print("Введите элемент для поиска: ");
        int target = scanner.nextInt();

        int resultIterative = binarySearchIterative(arr, target);

        int resultRecursive = binarySearchRecursive(arr, target);

        printResult(resultIterative, resultRecursive);
        scanner.close();
    }

    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
            if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }

    public static int binarySearchRecursive(int[] arr, int target) {
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void inputArray(int [] arr) {
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void printResult(int resultIterative, int resultRecursive) {
        System.out.println("\nРезультаты поиска:");

        if (resultIterative != -1) {
            System.out.println("Итеративный метод: элемент найден на позиции " + resultIterative);
        } else {
            System.out.println("Итеративный метод: элемент не найден");
        }

        if (resultRecursive != -1) {
            System.out.println("Рекурсивный метод: элемент найден на позиции " + resultRecursive);
        } else {
            System.out.println("Рекурсивный метод: элемент не найден");
        }
    }

}