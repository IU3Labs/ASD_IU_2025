/*
(3 балла) Дан целочисленный массив nums и целое число k, верните k наиболее часто встречающихся элементов. Вернуть ответ в любом порядке.
Примечание. Сложность должна быть O(n*log(n)).
*/
package lab3.topKFreq;

import java.util.*;

public class TopKFreq {

    static class Element<T> {
        T value;
        int frequency;

        Element(T value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }
    }

    public static <T> List<T> topKFrequent(List<T> nums, int k) {
        if (nums == null || nums.isEmpty()) {
            return new ArrayList<>();
        }

        // Шаг 1: Подсчет частоты каждого элемента - O(n)
        Map<T, Integer> frequencyMap = new HashMap<>();
        for (T num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Шаг 2: Создание списка уникальных элементов - O(m) где m < n
        List<Element<T>> elements = new ArrayList<>();
        for (Map.Entry<T, Integer> entry : frequencyMap.entrySet()) {
            elements.add(new Element<>(entry.getKey(), entry.getValue()));
        }

        // Шаг 3: Сортировка через merge sort - O(m*log(m))
        mergeSort(elements, 0, elements.size() - 1);

        // Шаг 4: Сбор результата - O(k)
        List<T> result = new ArrayList<>();
        for (int i = 0; i < Math.min(k, elements.size()); i++) {
            result.add(elements.get(i).value);
        }

        return result;
    }

    private static <T> void mergeSort(List<Element<T>> list, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private static <T> void merge(List<Element<T>> list, int left, int mid, int right) {
        List<Element<T>> leftPart = new ArrayList<>(list.subList(left, mid + 1));
        List<Element<T>> rightPart = new ArrayList<>(list.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < leftPart.size() && j < rightPart.size()) {
            if (leftPart.get(i).frequency >= rightPart.get(j).frequency) {
                list.set(k++, leftPart.get(i++));
            } else {
                list.set(k++, rightPart.get(j++));
            }
        }

        while (i < leftPart.size()) {
            list.set(k++, leftPart.get(i++));
        }
        while (j < rightPart.size()) {
            list.set(k++, rightPart.get(j++));
        }
    }


    public static void main(String[] args) {
//      Список целых чисел
        List<Integer> nums1 = Arrays.asList(1, 1, 1, 2, 2, 3);
        int k1 = 2;
        System.out.println("Input: " + nums1 + ", k = " + k1);
        System.out.println("Output: " + topKFrequent(nums1, k1));
        System.out.println();

//        Список символов
        List<Character> nums2 = Arrays.asList('a', 'a', 'a', 'b', 'b', 'c', 'd', 'd', 'd', 'd');
        int k2 = 2;
        System.out.println("Input: " + nums2 + ", k = " + k2);
        System.out.println("Output: " + topKFrequent(nums2, k2));
        System.out.println();


//        Пустой список
        List<Integer> nums3 = new ArrayList<>();
        int k3 = 2;
        System.out.println("Input: " + nums3 + ", k = " + k3);
        System.out.println("Output: " + topKFrequent(nums3, k3));
        System.out.println();

    }
}
