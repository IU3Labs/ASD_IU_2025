import java.util.PriorityQueue;
import java.util.Scanner;

public class KSortedArraySorter {

    public static void sortKSortedArray(int[] array, int k) {
        if (array == null || array.length <= 1 || k < 0) {
            return;
        }

        if (k >= array.length) {
            k = array.length - 1;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k + 1);

        fillInitialHeap(array, minHeap, k);
        processRemainingElements(array, minHeap, k);
        clearHeap(array, minHeap);
    }

    private static void fillInitialHeap(int[] array, PriorityQueue<Integer> minHeap, int k) {
        int elementsToAdd = Math.min(k + 1, array.length);
        for (int i = 0; i < elementsToAdd; i++) {
            minHeap.offer(array[i]);
        }
    }

    private static void processRemainingElements(int[] array, PriorityQueue<Integer> minHeap, int k) {
        int resultIndex = 0;
        for (int i = k + 1; i < array.length; i++) {
            array[resultIndex] = minHeap.poll();
            resultIndex++;
            minHeap.offer(array[i]);
        }
    }

    private static void clearHeap(int[] array, PriorityQueue<Integer> minHeap) {
        int resultIndex = array.length - minHeap.size();
        while (!minHeap.isEmpty()) {
            array[resultIndex] = minHeap.poll();
            resultIndex++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод массива от пользователя
        System.out.println("Введите элементы массива через пробел:");
        System.out.print("Массив: ");
        String arrayInput = scanner.nextLine();
        String[] elements = arrayInput.trim().split("\\s+");
        int[] array = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            array[i] = Integer.parseInt(elements[i]);
        }

        // Ввод k от пользователя
        System.out.println("Введите значение K:");
        System.out.print("K = ");
        int k = scanner.nextInt();

        scanner.close();

        System.out.println("Original array:");
        printArray(array);

        sortKSortedArray(array, k);

        System.out.println("Sorted array:");
        printArray(array);
    }

    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
/**
 * Доказательство сложности O(N log K) и использования O(K) дополнительной памяти:
 *
 * 1. Сложность по времени:
 *    - Создание кучи (minHeap) размером (K+1): O(K) операций
 *    - Для каждого из N элементов выполняется:
 *        * Извлечение минимума из кучи: O(log K)
 *        * Вставка элемента в кучу: O(log K)
 *    - Общая сложность: O(K) + O(N * 2 * log K) = O(N log K)
 *
 * 2. Сложность по памяти:
 *    - Используется приоритетная очередь (куча) размером K+1: O(K)
 *    - Другие переменные используют O(1) памяти
 *    - Общая дополнительная память: O(K)
 *
 * 3. Корректность алгоритма:
 *    - В K-отсортированном массиве элемент стоит не дальше K позиций от своей позиции
 *    - Куча размером K+1 гарантированно содержит минимальный элемент для текущей позиции
 *    - Каждый извлеченный элемент точно является минимальным среди следующих K+1 элементов
 */


