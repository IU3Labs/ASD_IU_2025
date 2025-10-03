import java.util.Scanner;

import tools.Array;

// Реализовать алгоритм бинарного поиска двумя способами.

public class Task0 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter length of array: ");
        int length = scanner.nextInt();
        if (length <= 0) {
            System.out.println("invalid length!");
            return;
        }

        int[] arr = Array.getArray(length, scanner);
        Array.sortArray(arr);
        Array.printArray(arr);

        System.out.print("enter N: ");
        int value = scanner.nextInt();
        int binarySearchIndex = binarySearch(arr, value);
        if (binarySearchIndex != -1) {
            System.out.println("BINARY SEARCH; found index: " + binarySearchIndex);
        } else {
            System.out.println("BINARY SEARCH; index not found");
        }
        int recursiveBinarySearchIndex = recursiveBinarySearch(arr, value, 0, arr.length - 1);
        if (recursiveBinarySearchIndex != -1) {
            System.out.println("RECURSIVE BINARY SEARCH; found index: " + recursiveBinarySearchIndex);
        } else {
            System.out.println("RECURSIVE BINARY SEARCH; index not found");
        }
    }

    public static int binarySearch(int[] arr, int n) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;

            if (arr[middle] == n) {
                return middle;
            } else if (arr[middle] < n) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    public static int recursiveBinarySearch(int[] arr, int n, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middle = (left + right) / 2;

        if (arr[middle] == n) {
            return middle;
        } else if (arr[middle] < n) {
            return recursiveBinarySearch(arr, n, middle + 1, right);
        } else {
            return recursiveBinarySearch(arr, n, left, middle - 1);
        }
    }
}
