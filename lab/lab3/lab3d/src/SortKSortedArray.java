
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Scanner;

public class SortKSortedArray {

    public static void sortKSorted(int[] arr, int k) {
        int n = arr.length;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < Math.min(k + 1, n); i++) {
            minHeap.offer(arr[i]);
        }

        int targetIndex = 0;

        for (int i = k + 1; i < n; i++) {
            arr[targetIndex++] = minHeap.poll();

            minHeap.offer(arr[i]);
        }

        while (!minHeap.isEmpty()) {
            arr[targetIndex++] = minHeap.poll();
        }
    }

    public static int[] inputArray(Scanner scanner) {
        System.out.print("Введите размер массива N: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.print("Введите " + n + " элементов массива (через пробел): ");

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=".repeat(60));
        System.out.println("Задача B2: Сортировка K-sorted массива");
        System.out.println("=".repeat(60));

        int[] arr = inputArray(scanner);

        System.out.print("Введите параметр K (максимальное расстояние элемента " +
                        "от правильной позиции): ");
        int k = scanner.nextInt();

        if (k < 0 || k >= arr.length) {
            System.out.println("Ошибка: K должно быть от 0 до " + (arr.length - 1));
            scanner.close();
            return;
        }

        System.out.println("\n" + "-".repeat(60));
        System.out.println("Исходный K-sorted массив:");
        System.out.println("-".repeat(60));
        System.out.println("Массив: " + Arrays.toString(arr));
        System.out.println("Размер: " + arr.length);
        System.out.println("Параметр K: " + k);

        int[] arrCopy = arr.clone();

        long startTime = System.nanoTime();
        sortKSorted(arr, k);
        long endTime = System.nanoTime();

        System.out.println("\n" + "-".repeat(60));
        System.out.println("Отсортированный массив:");
        System.out.println("-".repeat(60));
        System.out.println("Результат: " + Arrays.toString(arr));
        System.out.println("Время выполнения: " + (endTime - startTime) / 1000.0 + " мкс");

        // Проверка корректности
        boolean isSorted = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                isSorted = false;
                break;
            }
        }

        System.out.println("\n" + "-".repeat(60));
        System.out.println("Проверка результата:");
        System.out.println("-".repeat(60));
        System.out.println("Массив отсортирован: " + (isSorted ? "ДА ✓" : "НЕТ ✗"));
        scanner.close();
    }
}
