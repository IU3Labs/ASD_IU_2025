/*
Реализовать алгоритм бинарного поиска двумя способами.
 */

package lab1;

import java.util.Scanner;

public class MainTask {
    public static void main(String[] args) {
        int[] array = readArray();
        int value = readInt("Введите искомое число: ", false);

        System.out.println("\nРезультат итеративного поиска:");
        int indexIterative = iterativeSearch(array, value);
        if (indexIterative == -1) System.out.println("Число отсутствует в массиве");
        else System.out.println("Индекс искомого числа: " + indexIterative);

        System.out.println("\nРезультат рекурсивный поиска:");
        int indexRecursive = recursiveSearch(array, value, 0, array.length);
        if (indexRecursive == -1) System.out.println("Число отсутствует в массиве");
        else System.out.println("Индекс искомого числа: " + indexRecursive);
    }
    public static int readInt(String message, boolean positive) {
        Scanner scanner = new Scanner(System.in);
        int value;

        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (!positive || value > 0) break;
            }
            System.out.println("Введено недопустимое значение");
            scanner.nextLine();
        }

        return value;
    }
    public static int[] readArray() {
        int size = readInt("Введите размер массива: ", true);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = readInt("array [" + i + "]: ", false);
        }
        return array;
    }
    private static int iterativeSearch(int[] array, int value) {
        int left = 0;
        int right = array.length;

        while (left < right) {
            int mid = (right + left) / 2;
            if (array[mid] == value) return mid;
            if (array[mid] < value) {
                left = mid + 1;
            }
            if (array[mid] > value){
                right = mid;
            }
        }

        return -1;
    }
    public static int recursiveSearch(int[] array, int value, int left, int right) {
        if (left >= right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (array[mid] == value) {
            return mid;
        }
        if (array[mid] < value) {
            return recursiveSearch(array, value, mid + 1, right);
        } else {
            return recursiveSearch(array, value, left, mid - 1);
        }
    }

}
