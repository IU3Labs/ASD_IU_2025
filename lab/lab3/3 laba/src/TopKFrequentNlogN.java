import java.util.*;

public class TopKFrequentNlogN {

    public static List<Integer> topKFrequent(int[] nums, int k) {

        // Подсчёт частот O(n)
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            freq.put(x, freq.getOrDefault(x, 0) + 1); // O(1) амортизированно
        }

        // Преобразуем в список O(n)
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(freq.entrySet());

        // Сортировка по убыванию частоты O(n log n)
        Collections.sort(list, (a, b) -> b.getValue() - a.getValue());

        // Берём первые k - O(k)
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(list.get(i).getKey());
        }

        // O(n) - подсчёт
        // O(n) - перенос в список
        // O(n log n) - сортировка     (доминирует)
        // O(k) - выбор top K
        return result;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите N: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Введите " + n + " целых чисел:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.print("Введите k: ");
        int k = sc.nextInt();

        List<Integer> res = topKFrequent(nums, k);

        System.out.println("k наиболее частых элементов:");
        for (int x : res) System.out.print(x + " ");
    }
}
