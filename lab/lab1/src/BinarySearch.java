
// Реализовать алгоритм бинарного поиска двумя способами.
import java.util.Scanner;

public class BinarySearch {
    int[] vector;

    public static void main() {
        Scanner scanner = new Scanner(System.in);

        int[] array = Utils.createArray(scanner);
        Utils.quickSort(array, 0, array.length - 1);

        System.out.print("Sorted array: ");
        Utils.printArr(array);

        System.out.println("Enter the element to search for:");
        int num = scanner.nextInt();

        System.out.printf("Index of the element via iterative: %d\n", iterative(array, num));
        System.out.printf("Index of the element via recursive: %d", recursive(array, num, 0, array.length));
    }

    private static int iterative(int[] array, int num) {
        int l = 0, r = array.length - 1;
        int mid = l + (r - l) / 2;
        while (l <= r) {
            if (num == array[mid]) {
                return mid;
            } else if (num > array[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
            mid = (l + r) / 2;
        }
        return -1;
    }

    private static int recursive(int[] array, int num, int low, int high) {
        int mid = low + (high - low) / 2;
        if (low > high) {
            return -1;
        }

        if (num == array[mid]) {
            return mid;
        } else if (num > array[mid]) {
            return recursive(array, num, mid + 1, high);
        } else {
            return recursive(array, num, low, mid - 1);
        }
    }
}