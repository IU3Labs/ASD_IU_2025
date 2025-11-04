import java.util.Scanner;

public class BinarySearchRecurtion {

    public static void main(String[] args) {
        int[] array = Additionals.inputArray();
        Additionals.instertionSort(array);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элемент для поиска: ");
        int target = scanner.nextInt();

        int result = binarySearchRecursive(array, target, 0, array.length - 1);

        if (result != -1) {
            System.out.println("Элемент найден на индексе: " + result);
        } else {
            System.out.println("Элемент не найден в массиве.");
        }
    }

    public static int binarySearchRecursive(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            return mid;
        }
        if (array[mid] < target) {
            return binarySearchRecursive(array, target, mid + 1, right);
        } else {
            return binarySearchRecursive(array, target, left, mid - 1);
        }
    }


}