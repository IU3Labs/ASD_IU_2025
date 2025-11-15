package tasks;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("sdfsdf");
        int[] array = ArrayFunc.getSortedArray(scanner);
        int target = getSearchTarget(scanner);
        System.out.print("Отсортированный массив: ");
        printArray(array);
        System.out.println("Искомый элемент: " + target);
        int resultIterative = binarySearchIterative(array, target);
        int resultRecursive = binarySearchRecursive(array, target);
        printSearchResult("Итеративный метод", resultIterative, target);
        printSearchResult("Рекурсивный метод", resultRecursive, target);
    }

    private static void printArray(int[] array) {
        System.out.print("[");

        for(int i = 0; i < array.length; ++i) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }

    private static int getSearchTarget(Scanner scanner) {
        System.out.print("Введите элемент для поиска: ");
        return scanner.nextInt();
    }

    private static int binarySearchIterative(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            }

            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    private static int binarySearchRecursive(int[] array, int target) {
        return binarySearchRecursiveHelper(array, target, 0, array.length - 1);
    }

    private static int binarySearchRecursiveHelper(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        } else {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else {
                return array[mid] < target ? binarySearchRecursiveHelper(array, target, mid + 1, right) : binarySearchRecursiveHelper(array, target, left, mid - 1);
            }
        }
    }

    private static void printSearchResult(String methodName, int result, int target) {
        if (result != -1) {
            System.out.println(methodName + ": элемент " + target + " найден на позиции " + result);
        } else {
            System.out.println(methodName + ": элемент " + target + " не найден в массиве");
        }

    }
}
