// Реализовать алгоритм бинарного поиска двумя способами.
import java.util.Scanner;

public class BinSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ввод массива
        int[] data = inputArray();

        // сортировка
        bubbleSort(data);
        System.out.println("Отсортированный массив: " + data);

        // число, которые нужно найти
        System.out.print("Введите число: ");
        int target = scanner.nextInt();

        // итератиыный бин. поиск
        int iterativeResult = iterativeBinarySearch(data, target);
        System.out.println("Итеративный способ: " +
                (iterativeResult != -1 ? "позиция " + iterativeResult : "элемент не найден"));

        // рекурсивный бин. поиск
        int recursiveResult = recursiveBinarySearch(data, target, 0, data.length - 1);
        System.out.println("Рекурсивный способ: " +
                (recursiveResult != -1 ? "позиция " + recursiveResult : "элемент не найден"));
    }

    // ввод массива
    public static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        return arr;
    }

    // сортировка пузырьком
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    // итеративный способ
    public static int iterativeBinarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target)
                return mid;
            else if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }

    // рекурсивный способ
    public static int recursiveBinarySearch(int[] arr, int target, int left, int right) {
        if (left > right)
            return -1;

        int mid = left + (right - left) / 2;

        if (arr[mid] == target)
            return mid;
        else if (arr[mid] < target)
            return recursiveBinarySearch(arr, target, mid + 1, right);
        else
            return recursiveBinarySearch(arr, target, left, mid - 1);
    }

}
