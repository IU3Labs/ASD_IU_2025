// Реализовать алгоритм бинарного поиска двумя способами.
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элемент для поиска:");
        int elem = scanner.nextInt();

        int[] array = ArrayUtils.inputArray(scanner); 

        int resultIterative = SearchIterative(array, elem);
        int resultRecursive = SearchRecursive(array, elem);

        System.out.println("Итерационный: " + resultIterative);
        System.out.println("Рекурсивный: " + resultRecursive);
    }

    // Итерационный
    public static int SearchIterative(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (target == array[mid]) {
                return mid;
            }
            if (target > array[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Рекурсивный
    public static int SearchRecursive(int[] array, int target) {
        return SearchRecursive(array, 0, array.length - 1, target);
    }

    private static int SearchRecursive(int[] array, int low, int high, int target) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (target == array[mid]) {
            return mid;
        }
        if (target > array[mid]) {
            return SearchRecursive(array, mid + 1, high, target);
        } else {
            return SearchRecursive(array, low, mid - 1, target);
        }
    }
}