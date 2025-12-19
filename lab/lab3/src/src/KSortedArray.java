//Дан K-sorted array и число K. Отсортировать массив.
//K-sorted array - массив из N элементов, в котором каждый элемент стоит не
//далее, чем на K позиций от своей позиции в отсортированном массиве.
//Отсортируйте массив за время O (N log K) и минимальное количество
//дополнительной памяти. Докажите сложность.

import java.util.*;

public class KSortedArray {

    // Метод для ввода массива
    private static int[] inputArray(Scanner scanner) {
        System.out.println("Введите элементы массива:");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return new int[0];
        }

        String[] numbers = input.split("\\s+");
        int[] arr = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            arr[i] = Integer.parseInt(numbers[i]);
        }

        return arr;
    }

    // Метод для вывода массива
    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    // Основной метод сортировки K-sorted массива
    public static void sortKSortedArray(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) return;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i <= k && i < arr.length; i++) {
            minHeap.offer(arr[i]);
        }

        int index = 0;

        for (int i = k + 1; i < arr.length; i++) {
            arr[index] = minHeap.poll();
            index++;
            minHeap.offer(arr[i]);
        }

        while (!minHeap.isEmpty()) {
            arr[index] = minHeap.poll();
            index++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = inputArray(scanner);

        System.out.println("Введите значение K:");
        int k = scanner.nextInt();

        sortKSortedArray(arr, k);

        System.out.println("Отсортированный массив:");
        printArray(arr);

        scanner.close();
    }
}

// Доказательство сложности O(N log K):
// - N - количество элементов в массиве
// - K - параметр K-sorted массива
// - Куча всегда содержит не более K+1 элементов = O(K)
// - Выполняется N операций добавления в кучу: O(log K) каждая
// - Выполняется N операций извлечения из кучи: O(log K) каждая
// - Итог: O(N log K) + O(N log K) = O(N log K)
// - Доп память: O(K) для хранения кучи