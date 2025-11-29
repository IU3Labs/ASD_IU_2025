package lab1;

import java.util.Scanner;

public class Binarysearch {


     // Реализовать алгоритм бинарного поиска двумя способами.


    public static void main(String[] args) {
        int[] array = readArray();
        System.out.println();
        int value = readIntSafe("Введите искомое число: ", false);

        System.out.println("\nИтеративный поиск...");
        int indexIterative = binarySearchIterative(array, value);
        if (indexIterative == -1) System.out.println("Число отсутствует в массиве");
        else System.out.println("Индекс искомого числа: " + indexIterative);

        System.out.println("\nРекурсивный поиск...");
        int indexRecursive = binarySearchRecursive(array, value);
        if (indexRecursive == -1) System.out.println("Число отсутствует в массиве");
        else System.out.println("Индекс искомого числа: " + indexRecursive);
    }

    public static int[] readArray() {
        int size = readIntSafe("Введите размер массива: ", true);
        int[] array = new int[size];

        System.out.println("\nВведите содержимое массива");
        for (int i = 0; i < size; i++) {
            array[i] = readIntSafe("array[" + i + "] = ", false);
        }

        return array;
    }

    public static int readIntSafe(String message, boolean positiveOnly) {
        Scanner scanner = new Scanner(System.in);
        int value;

        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (!positiveOnly || value > 0) break;
            }
            System.out.println("Введено недопустимое значение");
            scanner.nextLine();
        }

        return value;
    }

    private static int binarySearchIterative(int[] array, int value) {
        int start = 0;
        int end = array.length;

        while (start < end) {
            int middle = (start + end) / 2;
            if (array[middle] < value) start = middle + 1;
            else if (array[middle] > value) end = middle;
            else return middle;
        }
        return -1;
    }

    private static int binarySearchRecursive(int[] array, int value) {
        if (array.length < 1) return -1;
        int start = 0;
        int end = array.length;
        int middle = (start + end) / 2;

        if (array[middle] < value) {
            int[] subArray = new int[end - (middle + 1)];
            System.arraycopy(array, middle + 1, subArray, 0, subArray.length);
            int result = binarySearchRecursive(subArray, value);
            if (result == -1) return -1;
            else return result + middle + 1;
        } else if (array[middle] > value) {
            int[] subArray = new int[middle];
            System.arraycopy(array, 0, subArray, 0, subArray.length);
            return binarySearchRecursive(subArray, value);
        } else {
            return middle;
        }
    }
}