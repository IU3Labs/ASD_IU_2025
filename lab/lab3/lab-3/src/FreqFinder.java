import java.util.*;

public class FreqFinder {

    public static void main(String[] args) {
        int[] nums = inputMethods.inputArray();
        int k = inputMethods.inputK();

        int[] result = topK(nums, k);

        System.out.println(Arrays.toString(result));
    }

    public static int[] topK(int[] nums, int k) {

        // 1) считаем частоты через HashMap — O(n)
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // 2) превращаем в список пар [value, count]
        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            list.add(new int[]{ e.getKey(), e.getValue() });
        }
        // 3) сортируем
        MergeClass.sort(list);

        // 4) берем первые k значений (если k больше числа уникальных уменьшаем)
        int m = list.size();
        if (k > m) k = m;

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i)[0]; // берем число (а не частоту)
        }

        return result;
    }
}
/*
 * Возвращает k наиболее часто встречающихся элементов массива nums.
 * 1) HashMap для подсчёта частот O(n)
 * 2) Конвертация в List<int[]> {value, count} O(m) где m = число уникальных
 * 3) Сортировка этим же списком твоим MergeClass.sort O(n log n)
 * 4) Возврат первых k элементов O(k) или O(n)
 */