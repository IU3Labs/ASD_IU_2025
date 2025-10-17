import java.util.Scanner;

public class BinarySearch {

    public static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Введите массив:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid + 1;
            }
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = inputArray();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элемент для поиска: ");
        int target = scanner.nextInt();

        int result = binarySearch(array, target);
        if (result != -1) {
            System.out.println("Элемент найден на индексе: " + (result));
        } else {
            System.out.println("Элемент не найден в массиве.");
        }
    }
}