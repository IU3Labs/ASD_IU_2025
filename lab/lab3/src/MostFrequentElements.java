/*Группа B. Задание 1. Дан целочисленный массив nums и целое число k, верните k наиболее
часто встречающихся элементов. Вернуть ответ в любом порядке.
Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.*/

import java.util.*;

public class MostFrequentElements {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] nums=ArrayTools.createArray();
        System.out.println("k: ");
        int k = scanner.nextInt();

        int[] kFrequent = TopKFrequent(nums, k);
        ArrayTools.printArray(kFrequent);
    }

    public static int[] TopKFrequent(int[] nums, int k) {
        // O(1) - создание пустой HashMap
        Map<Integer, Integer> count = new HashMap<>();

        // O(n) - цикл по всем n элементам
        // Каждая операция put/get в HashMap - O(1) в среднем случае
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // Создание списка: _O(m)_, где _m_ - количество уникальных элементов
        //- В худшем случае m = n (все элементы уникальны)
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(count.entrySet());

        // Сортировка по убыванию частоты - O(n log n)
        entries.sort((a, b) -> b.getValue() - a.getValue());

        //Берем первые k элементов- O(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entries.get(i).getKey();
        }

        return result;
    }
}