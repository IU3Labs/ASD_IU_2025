// Реализовать алгоритм бинарного поиска двумя способами. (рекурсивный способ)

package binarysearch;

import java.util.Scanner;
import  functions.ArrayUtils;

public class BinarySearchRecursive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = ArrayUtils.inputArrayFromUser(scanner, "для поиска");

        System.out.println("Введите элемент для поиска:");
        int target = scanner.nextInt();

        ArrayUtils.sortArray(array);
        int result = binarySearchRecursive(array, target, 0, array.length - 1);

        if (result != -1) {
            System.out.println("Элемент найден на позиции: " + result);
        } else {
            System.out.println("Элемент не найден");
        }

        scanner.close();
    }

    public static int binarySearchRecursive(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return binarySearchRecursive(array, target, mid + 1, right);
        } else {
            return binarySearchRecursive(array, target, left, mid - 1);
        }
    }
}