/*
Реализовать алгоритм бинарного поиска двумя способами:
- итеративным;
- рекурсивным.
*/
import java.util.Scanner;
public class BinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = ArrayUtil.sizeArray(scanner);
        int[] array = ArrayUtil.inputArray(scanner, size);

        System.out.println("Исходный массив:");
        ArrayUtil.printArray(array);
        System.out.println(" ");

        ArrayUtil.bubbleSort(array);

        System.out.println("Отсортированный массив:");
        ArrayUtil.printArray(array);
        System.out.println(" ");

        System.out.print("Введите число для поиска: ");
        int target = scanner.nextInt();

        int iterativeResult = binarySearchIterative(array, target);
        if (iterativeResult == -1) {
            System.out.println("Итеративный поиск: элемент не найден.");
        } else {
            System.out.println("Итеративный поиск: элемент находится на позиции " + (iterativeResult));
        }

        int recursiveResult = binarySearchRecursive(array, target, 0, array.length - 1);
        if (recursiveResult == -1) {
            System.out.println("Рекурсивный поиск: элемент не найден.");
        } else {
            System.out.println("Рекурсивный поиск: элемент находится на позиции " + (recursiveResult));
        }
    }

    private static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (arr[middle] == target) {
                return middle;
            } else if (arr[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    private static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right) / 2;
        if (arr[middle] == target) {
            return middle;
        } else if (arr[middle] < target) {
            return binarySearchRecursive(arr, target, middle + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, middle - 1);
        }
    }
}
