package lab1;

import java.util.Scanner;


public class BinarySearchIterations {
    public static void main() {
        int n;
        System.out.println("Введите количество элементов в массиве: ");
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int[] arr = new int[n];
        arrayFill(arr, n);
        System.out.println("Исходный массив: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        bubbleSort(arr, n);
        int x;
        System.out.println("Введите искомый элемент массива: ");
        x = scanner.nextInt();
        System.out.println("Отсортированный массив: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        int result = binarySearch(arr, x);
        System.out.println("Индекс искомого числа: " + result);
        scanner.close();
    }

    public static void arrayFill(int[] arr, int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
    }


    public static void bubbleSort(int[] arr, int n) {
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

    public static int binarySearch(int[] arr, int elem) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int midIndex = left + (right - left) / 2;
            if (arr[midIndex] == elem) {
                return midIndex;
            }
            else if (arr[midIndex] < elem) {
                left = midIndex + 1;
            }
            else {
                right = midIndex - 1;
            }
        }
        System.out.println("Число " + elem + " не найдено");
        return -1;
    }
}

