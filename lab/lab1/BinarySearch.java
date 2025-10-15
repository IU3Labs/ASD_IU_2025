import java.util.Scanner;
//Реализовать алгоритм бинарного поиска двумя способами.
public class BinarySearch {

    public static int binarySearchIterative(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;

            if (array[middle] == target) {
                return array[middle];
            } else if (array[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1;
    }

    public static int binarySearchRecursive(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middle = (left + right) / 2;

        if (array[middle] == target) {
            return array[middle];
        } else if (array[middle] > target) {
            return binarySearchRecursive(array, target, left, middle - 1);
        } else {
            return binarySearchRecursive(array, target, middle + 1, right);
        }
    }

    public static void printResult(int value) {
        if (value != -1) {
            System.out.println("Нашли: " + value);
        } else {
            System.out.println("Не нашли :(");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите положительное число: ");
        int target = scanner.nextInt();

        int[] array = {1, 2, 3, 4, 5, 6, 7, 10, 12, 14, 18};

        printResult(binarySearchIterative(array, target));

        printResult(binarySearchRecursive(array, target, 0, array.length - 1));
    }
}