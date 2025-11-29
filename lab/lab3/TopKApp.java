import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
/*2 Дан массив из N целых чисел. Требуется найти K наиболее часто
встречающихся элементов и вернуть их в порядке убывания частоты. Если
несколько элементов имеют одинаковую частоту, возвращайте их в
порядке возрастания значения. Сложность: O (N log K). Доказать.
* */
public class TopKApp {

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 3, 4};
        int k = 2;

        int[] result = findTopK(arr, k);

        System.out.println("K самых частых элементов:");
        for (int value : result) {
            System.out.println(value);
        }
    }

    private static int[] findTopK(int[] arr, int k) {

        // 1. Считаем частоты элементов
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        // Время: O(N), где N — размер массива
        // Пространство: O(U), где U — количество уникальных элементов

        // 2. Создаём мин-кучу для хранения K самых частых элементов
        // Массив int[]{число, частота}
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> a[1] == b[1]
                        ? Integer.compare(b[0], a[0]) // если частота равна — сортировать по значению
                        : Integer.compare(a[1], b[1]) // сортировать по частоте (мин-куча)
        );

        // 3. Проходим по всем уникальным элементам и их частотам
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            heap.offer(new int[]{entry.getKey(), entry.getValue()});

            // Если размер кучи > k, удаляем минимальный элемент
            if (heap.size() > k) {
                heap.poll();
            }
        }
        // Время на каждый offer/poll — O(log K)
        // Всего элементов уникальных — U
        // => Время работы этого блока: O(U * log K)
        // Так как U ≤ N, получаем оценку O(N * log K)

        // 4. Извлекаем элементы из кучи в массив результата
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = heap.poll()[0];
        }
        // Время: O(K log K) — обычно пренебрегаем при оценке

        return result;
    }
}

/*
Вывод по сложности:
1. Подсчёт частот: O(N)
2. Построение мин-кучи и поддержка размера K: O(N * log K)
   - offer и poll работают за O(log K)
3. Итоговая сложность: O(N log K)
4. Пространственная сложность: O(U) для HashMap + O(K) для кучи, обычно O(N) в худшем случае
*/