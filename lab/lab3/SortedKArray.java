package lab3;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SortedKArray {

    public static void sortKSorted(int[] arr, int k) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        /*
           - В куче хранится не более K+1 элементов.
           - Время: O(min(n, K+1) * log(min(n, K+1))) ≤ O(K log K) для инициализации.
           - Память: O(K).
         */

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i = 0;
        for (; i < Math.min(n, k + 1); i++) pq.add(arr[i]);

        /*
           - Для каждого оставшегося элемента (всего N - (K+1) итераций):
             * извлекаем min из кучи (O(log K)) -> записываем в arr[writeIndex]
             * вставляем очередной входной элемент (O(log K))
           - Каждая из N элементов будет извлечена ровно один раз и вставлена ровно один раз.
           - Время этого блока: O(N log K).
           - Память: куча всё так же O(K).
         */
        int writeIndex = 0;
        for (; i < n; i++) {
            arr[writeIndex++] = pq.poll(); // O(log K)
            pq.add(arr[i]);              // O(log K)
        }

        /*
           - Оставшиеся в куче (≤ K+1 элементов) извлекаем по одному и записываем в массив.
           - Время: O(K log K) (в худшем случае).
         */

        while (!pq.isEmpty()) {
            arr[writeIndex++] = pq.poll(); // O(log K) на извлечение
        }
    }


    public static void main(String[] args) {
        int[] a = {3, 2, 6, 5, 4, 8};
        int k = 2;
        System.out.println("Before: " + Arrays.toString(a));
        sortKSorted(a, k);
        System.out.println("After : " + Arrays.toString(a));
    }
}

