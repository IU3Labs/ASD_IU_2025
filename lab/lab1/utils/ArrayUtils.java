package lab1.utils;

import java.util.Scanner;


public class ArrayUtils {
    public static void fillArray(int[] array, Scanner scan) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Введите " + (i + 1) + " элемент: ");
            array[i] = scan.nextInt();
        }
    }

    public static int[] bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
        return array;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
