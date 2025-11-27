import java.util.*;

//Дан целочисленный массив nums и целое число k, верните k наиболее часто встречающихся элементов. Вернуть ответ в любом порядке. Сложность должна быть O(n*log(n)). Докажите сложность.
public class GroupBTask1 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = topKFrequent(nums, k);
        ArrayUtility.printArray(result);
    }

    // Сложность O(n log n) в худшем случае (при всех уникальных элементах)
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }
        return result;
    }
}
//Доказательство сложности: Построение freq - O(N). Конвертация в list - O(M), где M ≤ N уникальных. Сортировка - O(M log M). Взятие K - O(K). Итого O(N + M log M), что в худшем случае будет O(N log N) при M = N.