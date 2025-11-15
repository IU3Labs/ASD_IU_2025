/*
Обязательное задание.
Релизовать алгоритм бинарного поиска двумя способами:
1) итеративный
2) рекурсивно
 */
import java.util.Scanner;
import utils.ArrayUtils;

public class Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Бинарный поиск ===");
        int[] array = ArrayUtils.readArrayFromUser(scanner);
        ArrayUtils.bubbleSort(array);

        System.out.print("Отсортированный массив: ");
        ArrayUtils.printArray(array);

        System.out.print("Введите число для поиска: ");
        int target = scanner.nextInt();

        System.out.println("\nСпособ 1 - Итеративный:");
        int result1 = binarySearchIterative(array, target);
        printSearchResult(result1, target);

        System.out.println("\nСпособ 2 - Рекурсивный:");
        int result2 = binarySearchRecursive(array, target, 0, array.length - 1);
        printSearchResult(result2, target);

        scanner.close();
    }

    public static int binarySearchIterative(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int binarySearchRecursive(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return binarySearchRecursive(array, target, mid + 1, right);
        } else {
            return binarySearchRecursive(array, target, left, mid - 1);
        }
    }

    private static void printSearchResult(int index, int target) {
        if (index != -1) {
            System.out.println("Число " + target + " найдено на позиции: " + index);
        } else {
            System.out.println("Число " + target + " не найдено в массиве");
        }
    }
}