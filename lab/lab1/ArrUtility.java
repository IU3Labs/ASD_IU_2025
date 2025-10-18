package lab.lab1;

import java.util.Scanner;

public class ArrUtility {
    static Scanner input = new Scanner(System.in);

    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[arr.length - 1] + "]");
    }

    public static int[] inputArray() {
        System.out.print("Введите длинну массива: ");
        int arrLen = input.nextInt();
        int[] arr = new int[arrLen];
        System.out.print("Введите массив: ");
        for (int i = 0; i < arrLen; i++) {
            arr[i] = input.nextInt();
        }
        return arr;
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}