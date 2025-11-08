package lab1.utils;

import java.util.Scanner;

public class ArrayUtils {
    public static int getLenArray(Scanner in) {
        int inputLen = 0;
        while (inputLen <= 0) {
            System.out.print("Input length array: ");
            inputLen = in.nextInt();
        }
        return inputLen;
    }
    public static int getLenArray(Scanner in, String title) {
        int inputLen = 0;
        while (inputLen <= 0) {
            System.out.print(title);
            inputLen = in.nextInt();
        }
        return inputLen;
    }

    public static void getIntArray(Scanner in, int[] array, int lenArray) {
        for (int i = 0; i < lenArray; i++) {
            array[i] = in.nextInt();
        }

    }

    public static void printIntArray(int[] array) {
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void bubbleSortIntArray(int[] array) {
        for (int pass = 1; pass < array.length; pass++) {
            for (int i = 0; i < array.length - pass; i++) {
                if (array[i] > array[i + 1]) {
                    swapInt(array, i, i + 1);
                }
            }
        }
    }

    public static void swapInt(int[] array, int a, int b) {
        if (a < 0 || array.length <= a) return;
        if (b < 0 || array.length <= b) return;
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
