/*
Основное задание:
Реализовать алгоритм бинарного поиска двумя способами
 */

import java.util.Scanner;

public class BinarySearch {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] array = ArrayTools.createArray();
        ArrayTools.printArray(array);
        ArrayTools.bubbleSort(array);
        System.out.println("Отсортированный массив");
        ArrayTools.printArray(array);
        System.out.print("Введите элемент, который ищем: ");
        int target = scanner.nextInt();

        int result1 = binaryIterativeSearch(array, target);
        int result2 = binaryRecursiveSearch(array, target, 0, array.length - 1);

        System.out.println("Результат метода итераций: " + (result1 == -1 ? "Элемент не найден" : result1));
        System.out.println("Результат метода рекурсии: " + (result2 == -1 ? "Элемент не найден" : result2));
    }

    public static int binaryIterativeSearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int middle;

        while (low <= high) {
            middle = low + (high - low) / 2;

            if (array[middle] == target) {
                return middle;
            } else if (array[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    public static int binaryRecursiveSearch(int[] array, int target, int low, int high) {
        // Границы пересеклись
        if (low > high) {
            return -1;
        }

        int middle = low + (high - low) / 2;

        if (array[middle] == target) {
            return middle;
        } else if (array[middle] < target) {
            return binaryRecursiveSearch(array, target, middle + 1, high);
        } else {
            return binaryRecursiveSearch(array, target, low, middle - 1);
        }
    }
}