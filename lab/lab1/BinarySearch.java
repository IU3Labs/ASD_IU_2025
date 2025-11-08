package lab1;

/* Реализовать алгоритм бинарного поиска двумя способами. */
import java.util.Scanner;

public class BinarySearch {
    static int binarySearch(int[] numbers, int target) {
        int first = 0;
        int last = numbers.length - 1;

        while (first <= last) {
            int middle = first + (last - first) / 2;

            if (numbers[middle] == target) {
                System.out.println("Индекс числа " + target + " в отсортированном массиве итеративным методом: " + middle);
                return middle;
            } else if (numbers[middle] < target) {
                first = middle + 1;
            } else {
                last = middle - 1;
            }
        }
        System.out.println("Число " + target + " не найдено");
        return -1;
    }

    static int recursiveBinarySearch(int[] array, int number, int first, int last) {
        if (first > last) {
            System.out.println("Число " + number + " не найдено");
            return -1;
        }
        int middle = first + (last - first) / 2;
        if (array[middle] == number) {
            return middle;
        } else if (array[middle] < number) {
            return recursiveBinarySearch(array, number, middle + 1, last);
        } else {
            return recursiveBinarySearch(array, number, first, middle - 1);
        }
    }


    public static void fillArray(int[] array) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элементы массива: ");

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
    }


    public static void bubbleSort(int[] array) {
        int length = array.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swapElements(array, j, j + 1);
                }
            }
        }
    }

    private static void swapElements(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов в массиве: ");
        int count = scanner.nextInt();
        int[] array = new int[count];

        fillArray(array);

        System.out.print("Введите искомое число: ");
        int targetNumber = scanner.nextInt();

        System.out.print("Исходный массив: ");
        printArray(array);

        bubbleSort(array);

        System.out.print("Отсортированный массив: ");
        printArray(array);

        int firstResult = binarySearch(array, targetNumber);
        int secondResult = recursiveBinarySearch(array, targetNumber, 0, array.length - 1);
        System.out.println("Индекс числа " + targetNumber + " в отсортированном массиве рекурсивным методом: " + secondResult);
        scanner.close();
    }
}

