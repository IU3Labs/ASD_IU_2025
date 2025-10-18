//Реализовать алгоритм бинарного поиска двумя способами. (итеративный способ)

package binarysearch;

import java.util.Scanner;
import functions.ArrayUtils;

public class BinarySearchIterative {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = ArrayUtils.inputArrayFromUser(scanner, "для поиска");

        System.out.println("Введите элемент для поиска:");
        int target = scanner.nextInt();

        ArrayUtils.sortArray(array);
        int result = binarySearchIterative(array, target);

        if (result != -1) {
            System.out.println("Элемент найден на позиции: " + result);
        } else {
            System.out.println("Элемент не найден");
        }

        scanner.close();
    }

    public static int binarySearchIterative(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}