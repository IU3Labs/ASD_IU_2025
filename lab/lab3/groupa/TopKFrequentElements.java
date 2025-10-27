package groupa;

import java.util.*;

public class TopKFrequentElements {

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap =
                new PriorityQueue<>((a, b) -> {
                    if (a.getValue().equals(b.getValue())) {
                        return a.getKey() - b.getKey();
                    }
                    return b.getValue() - a.getValue();
                });

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            heap.offer(entry);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k && !heap.isEmpty(); i++) {
            result.add(heap.poll().getKey());
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 4, 4, 4, 4};
        int k = 2;
        List<Integer> result = topKFrequent(nums, k);
        System.out.println("Массив: " + Arrays.toString(nums));
        System.out.println(k + " наиболее частых элементов: " + result);
        System.out.println("Сложность: O(N log K) - N элементов, куча размера K");
    }
}