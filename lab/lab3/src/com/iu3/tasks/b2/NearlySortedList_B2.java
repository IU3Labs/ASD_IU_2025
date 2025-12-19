package com.iu3.tasks.b2;

import java.util.*;

/**
 * Задача: отсортировать K-sorted массив за O(N log K) времени и минимальной дополнительной памяти.
 * <p>
 * K-sorted массив — это массив, в котором каждый элемент находится не далее чем на K позиций
 * от своей конечной позиции в полностью отсортированном массиве.
 * <p>
 * Решение: используем мин-кучу (PriorityQueue) размером K+1.
 */
public class NearlySortedList_B2 {

    /**
     * Сортирует K-sorted массив с использованием мин-кучи размером K+1.
     * <p>
     * Временная сложность: O(N log K)
     * Пространственная сложность: O(K) — только для хранения кучи.
     * <p>
     * ДОКАЗАТЕЛЬСТВО СЛОЖНОСТИ:
     * <p>
     * - Мы вставляем каждый из N элементов в кучу один раз → N операций вставки.
     * - Мы извлекаем каждый из N элементов из кучи один раз → N операций извлечения.
     * - Размер кучи никогда не превышает K+1, поэтому каждая операция (вставка/извлечение)
     *   стоит O(log(K+1)) = O(log K).
     * - Общая сложность: O(N) × O(log K) = O(N log K).
     * <p>
     * Корректность: поскольку каждый элемент находится не далее чем на K позиций
     * от своей правильной позиции, минимальный элемент среди первых K+1 элементов
     * гарантированно является первым в отсортированном массиве. Это позволяет
     * последовательно "выталкивать" правильные элементы через кучу.
     */
    static void nearlySorted(int[] arr, int k) {
        int n = arr.length;
        // Куча будет содержать не более (k+1) элементов
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Заполняем первые (k+1) элементов
        for (int i = 0; i <= k && i < n; i++) {
            minHeap.add(arr[i]);
        }

        int targetIndex = 0;

        // Обрабатываем оставшиеся элементы
        for (int i = k + 1; i < n; i++) {
            arr[targetIndex++] = minHeap.poll(); // извлекаем минимум
            minHeap.add(arr[i]);                 // добавляем следующий элемент
        }

        // Извлекаем оставшиеся элементы из кучи
        while (!minHeap.isEmpty()) {
            arr[targetIndex++] = minHeap.poll();
        }
    }

    public static void main(String[] args) {
        // Тест 1: k=3
        int[] arr1 = {2, 4, 1, 7, 8, 3, 6, 5, 10, 9};
        int k1 = 3;
        System.out.println("Тест 1:");
        System.out.println("Исходный массив: " + Arrays.toString(arr1));
        System.out.println("k = " + k1);
        nearlySorted(arr1, k1);
        System.out.println("Отсортированный: " + Arrays.toString(arr1));
        System.out.println();

        // Тест 2: уже отсортированный массив (k=0 или k=1)
        int[] arr2 = {1, 2, 3, 4, 5};
        int k2 = 1;
        System.out.println("Тест 2:");
        System.out.println("Исходный массив: " + Arrays.toString(arr2));
        System.out.println("k = " + k2);
        nearlySorted(arr2, k2);
        System.out.println("Отсортированный: " + Arrays.toString(arr2));
        System.out.println();

        // Тест 3: полностью неупорядоченный, но допустимый при k=4
        int[] arr3 = {6, 5, 3, 2, 8, 10, 9};
        int k3 = 4;
        System.out.println("Тест 3:");
        System.out.println("Исходный массив: " + Arrays.toString(arr3));
        System.out.println("k = " + k3);
        nearlySorted(arr3, k3);
        System.out.println("Отсортированный: " + Arrays.toString(arr3));
        System.out.println();

        // Тест 4: один элемент
        int[] arr4 = {42};
        int k4 = 0;
        System.out.println("Тест 4:");
        System.out.println("Исходный массив: " + Arrays.toString(arr4));
        System.out.println("k = " + k4);
        nearlySorted(arr4, k4);
        System.out.println("Отсортированный: " + Arrays.toString(arr4));
    }
}
