package com.iu3.tasks.c1;

/*
1 Дан целочисленный массив nums и целое число k, верните k наиболее
часто встречающихся элементов. Вернуть ответ в любом порядке.
Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.
 */

/*
 Обоснование временной сложности O(n log n):
 1. Подсчет частот элементов:
 - Проходим по массиву nums длиной n
 - Операции put и get в HashMap: O(1) в среднем случае
 - Итого: O(n)

 2. Преобразование записей Map в List:
 - Создаем List из n элементов в худшем случае (все элементы уникальны)
 - Итого: O(n)

 3. Сортировка списка по убыванию частот:
 - Сортировка списка длиной n: O(n log n)
 - Используем Collections.sort с компаратором
 4. Выбор первых k элементов:
 - Создаем подсписок: O(1)
 - Преобразование в массив: O(k)

 Суммарная сложность: O(n) + O(n) + O(n log n) + O(k) = O(n log n)

 В худшем случае, когда все элементы уникальны, n = количеству уникальных элементов

 Дополнительная память: O(n) для хранения HashMap и списка
*/

import java.util.*;

public class TopKFrequentElements {

    public static void main(String[] args) {
        // Тест 1: Стандартный случай
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;

        System.out.println("Тест 1:");
        System.out.println("Массив: " + Arrays.toString(nums1));
        System.out.println("K = " + k1);

        int[] result1 = topKFrequent(nums1, k1);
        System.out.println("K наиболее частых элементов: " + Arrays.toString(result1));
        System.out.println();

        // Тест 2: Все элементы уникальны
        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 3;

        System.out.println("Тест 2:");
        System.out.println("Массив: " + Arrays.toString(nums2));
        System.out.println("K = " + k2);

        int[] result2 = topKFrequent(nums2, k2);
        System.out.println("K наиболее частых элементов: " + Arrays.toString(result2));
        System.out.println();

        // Тест 3: Один элемент повторяется много раз
        int[] nums3 = {1, 1, 1, 1, 2, 2, 3, 3, 3};
        int k3 = 2;

        System.out.println("Тест 3:");
        System.out.println("Массив: " + Arrays.toString(nums3));
        System.out.println("K = " + k3);

        int[] result3 = topKFrequent(nums3, k3);
        System.out.println("K наиболее частых элементов: " + Arrays.toString(result3));
        System.out.println();

        // Тест 4: Сравнение двух методов
        int[] nums4 = {4, 1, -1, 2, -1, 2, 3, 4, 4, 4, -1, -1, -1};
        int k4 = 3;

        System.out.println("Тест 4 (сравнение методов):");
        System.out.println("Массив: " + Arrays.toString(nums4));
        System.out.println("K = " + k4);

        int[] result4a = topKFrequent(nums4, k4);
        int[] result4b = topKFrequentWithHeap(nums4, k4);

        System.out.println("Метод с сортировкой: " + Arrays.toString(result4a));
        System.out.println("Метод с кучей: " + Arrays.toString(result4b));
        System.out.println();

        // Тест производительности
        performanceTest();
    }

    public static int[] topKFrequent(int[] nums, int k) {
        // 1. Подсчет частот элементов - O(n)
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 2. Создание списка записей - O(n)
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());

        // 3. Сортировка по убыванию частот - O(n log n)
        entries.sort((a, b) -> b.getValue() - a.getValue());

        // 4. Выбор первых k элементов - O(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entries.get(i).getKey();
        }

        return result;
    }

    // Альтернативная версия с использованием PriorityQueue
    public static int[] topKFrequentWithHeap(int[] nums, int k) {
        // 1. Подсчет частот элементов - O(n)
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 2. Создание Min-Heap для хранения k наиболее частых элементов - O(n log k)
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // Удаляем наименее частый элемент
            }
        }

        // 3. Извлечение результатов из кучи - O(k log k)
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = Objects.requireNonNull(minHeap.poll()).getKey();
        }

        return result;
    }

    // Тест производительности для демонстрации сложности
    private static void performanceTest() {
        System.out.println("=== ТЕСТ ПРОИЗВОДИТЕЛЬНОСТИ ===");

        int[] sizes = {1000, 10000, 50000};

        for (int size : sizes) {
            // Генерируем случайный массив
            int[] nums = generateRandomArray(size);
            int k = 10;

            long startTime1 = System.nanoTime();
            topKFrequent(nums, k);
            long endTime1 = System.nanoTime();

            long startTime2 = System.nanoTime();
            topKFrequentWithHeap(nums, k);
            long endTime2 = System.nanoTime();

            double duration1 = (endTime1 - startTime1) / 1_000_000.0;
            double duration2 = (endTime2 - startTime2) / 1_000_000.0;

            System.out.printf("n = %6d, k = %2d - Сортировка: %.3f мс, Куча: %.3f мс%n",
                    size, k, duration1, duration2);
        }
    }

    // Генератор случайного массива
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size / 2); // Гарантируем повторения
        }
        return arr;
    }
}