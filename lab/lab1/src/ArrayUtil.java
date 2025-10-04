package src;

import java.util.Scanner;

public class ArrayUtil {
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
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}