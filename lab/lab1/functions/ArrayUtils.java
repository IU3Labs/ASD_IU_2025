//Методы работы с массивами для использования в заданиях
package functions;

import java.util.Scanner;

public class ArrayUtils {
    public static int[] inputArrayFromUser(Scanner scanner, String arrayName) {
        System.out.println("Введите количество элементов массива " + arrayName + ":");
        int n = scanner.nextInt();

        int[] array = new int[n];
        System.out.println("Введите элементы массива " + arrayName + ":");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        return array;
    }

    public static void printArray(int[] array, String message) {
        System.out.print(message + ": [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static int[] copyArray(int[] array) {
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    public static void sortArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}