

import java.util.*;

public class TopKFrequentNlogN {

    public static List<Integer> topKFrequent(int[] inputArray, int k) {

        // Подсчёт частот O(n)
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : inputArray) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1); // O(1) амортизированно
        }

        // Преобразуем в список O(n)
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());

        // Сортировка по убыванию частоты O(n log n)
        Collections.sort(entryList, (item1, item2) -> item2.getValue() - item1.getValue());

        // Берём первые k - O(k)
        List<Integer> topElements = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            topElements.add(entryList.get(i).getKey());
        }

        // O(n) - подсчёт
        // O(n) - перенос в список
        // O(n log n) - сортировка     (доминирует)
        // O(k) - выбор top K
        return topElements;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите N: ");
        int count = scanner.nextInt();

        int[] numbers = new int[count];
        System.out.println("Введите " + count + " целых чисел:");
        for (int i = 0; i < count; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.print("Введите k: ");
        int k = scanner.nextInt();

        List<Integer> resultList = topKFrequent(numbers, k);

        System.out.println("k наиболее частых элементов:");
        for (int element : resultList) System.out.print(element + " ");
    }
}