package tools;

import java.util.Scanner;

public class Array {
    public static int[] getArray(int length, Scanner scanner) {
        int[] arr = new int[length];

        System.out.print("enter array elements: ");

        for (int i = 0; i < length; i++) {
            arr[i] = scanner.nextInt();
        }

        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("\n");
    }

    public static void sortArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
