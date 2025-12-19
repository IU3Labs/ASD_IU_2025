//Группа В Дан целочисленный массив nums и целое число k, верните k наиболее
//часто встречающихся элементов. Вернуть ответ в любом порядке.
//Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.
import java.util.*;

public class TopKFrequentFinder {

    public List<Integer> findTopK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new ArrayList<>();
        }

        // 1. Частоты - O(n)
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // 2. Список для сортировки - O(m)
        List<int[]> pairs = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            pairs.add(new int[]{entry.getKey(), entry.getValue()});
        }

        // 3. Сортировка по убыванию частоты - O(m*log(m))
        pairs.sort((a, b) -> b[1] - a[1]);

        // 4. Результат - O(k)
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < Math.min(k, pairs.size()); i++) {
            result.add(pairs.get(i)[0]);
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentFinder result = new TopKFrequentFinder();

        int[] test = {1, 1, 1, 2, 2, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5};
        int k = 3;

        List<Integer> topK = result.findTopK(test, k);

        System.out.println("Массив: " + Arrays.toString(test));
        System.out.println("k = " + k);
        System.out.println("Наиболее частые: " + topK);
    }
}