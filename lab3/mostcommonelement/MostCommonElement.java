package lab.lab3.mostcommonelement;

import java.util.*;

public class MostCommonElement {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. Считаем частоты
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        // 2. Переносим пары (число, частота) в список
        List<Map.Entry<Integer, Integer>> entries =
                new ArrayList<>(freq.entrySet());

        // 3. Сортируем по убыванию частоты
        entries.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        // 4. Берём первые k чисел
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = entries.get(i).getKey();
        }

        return res;
    }
}
/** Пусть n — длина массива, а m — число различных элементов.
 Один проход по массиву и заполнение HashMap занимает O(n)Формирование списка из freq.entrySet() требует O(m) времени и памяти.
 Сортировка списка из m элементов по частоте работает за O(m logm). Это не хуже, чем O(nlog n).
 Финальный проход по первым k элементам отсортированного списка занимает O(k), что не превосходит O(n).
 Складывая доминирующие члены, получаем итоговую временную сложность алгоритма O(n+m logm) = O(nlogn),
 а дополнительная память — O(m). */