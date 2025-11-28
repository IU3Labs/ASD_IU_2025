// Дан целочисленный массив nums и целое число k, верните k наиболее
//часто встречающихся элементов. Вернуть ответ в любом порядке.
//Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.

// сложность: подсчёт частот - O(N) + сортировка уникальных элементов O(N log N) => итого O(N log N).

import java.util.*;

public class TaskB1 {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<Integer> list = new ArrayList<>(freq.keySet());
        list.sort((a, b) -> freq.get(b) - freq.get(a));

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        int[] res = topKFrequent(nums, k);

        System.out.println(Arrays.toString(nums));
        System.out.println("k elements: " + Arrays.toString(res));
    }
}
