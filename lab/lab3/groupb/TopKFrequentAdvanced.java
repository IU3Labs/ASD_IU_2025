package groupb;

import java.util.*;

public class TopKFrequentAdvanced {

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }

        int[] result = new int[k];
        int index = 0;

        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
            if (!buckets[i].isEmpty()) {
                Collections.sort(buckets[i]);
                for (int num : buckets[i]) {
                    if (index < k) {
                        result[index++] = num;
                    } else {
                        break;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 4, 4, 4, 4, 5, 5};
        int k = 3;
        int[] result = topKFrequent(nums, k);

        System.out.println("Массив: " + Arrays.toString(nums));
        System.out.println(k + " наиболее частых элементов: " + Arrays.toString(result));

        System.out.println("\nДоказательство сложности O(N log N):");
        System.out.println("1. frequencyMap: O(N)");
        System.out.println("2. Создание бакетов: O(N)");
        System.out.println("3. Заполнение бакетов: O(U) где U - количество уникальных элементов");
        System.out.println("4. Сортировка бакетов: в худшем случае O(U log U)");
        System.out.println("5. Итог: O(N + U log U) ≤ O(N log N) так как U ≤ N");
    }
}