package lab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InversionFinder {


    private static class Elem {
        int value;
        int index;
        Elem(int v, int i) { value = v; index = i; }
    }


    public static long findAndPrintInversions(int[] arr) {
        if (arr == null || arr.length <= 1) {
            System.out.println("Инверсии: 0");
            return 0;
        }
        int n = arr.length;

        /*
           - Формируем массив Elem в исходном порядке (индексы 0..n-1).
           - Время: O(n) (один проход).
           - Память: O(n) для массива elems и O(n) для buffer в merge.
         */

        Elem[] elems = new Elem[n];
        for (int i = 0; i < n; i++) elems[i] = new Elem(arr[i], i);
        Elem[] buffer = new Elem[n];

        /*
           - Мы собираем все пары (i, j). Хранение каждой пары требует O(1).
           - Память для inversions: O(K), где K — количество инверсий.
         */
        List<int[]> inversions = new ArrayList<>();

        /*
           - mergeSortRange выполняет разделение и слияние.
           - Сложность работы: Θ(n log n).
           - Но фактическое время = Θ(n log n + K), где K — число инверсий,
             так как мы записываем каждую инверсию в список (затраты O(1) на инверсию).
         */
        long count = mergeSortRange(elems, buffer, 0, n - 1, inversions);

        /*
          Возвращение требует O(K) времени для перечисления всех инверсий.
         */
        System.out.println("Все инверсии: " + count);
        for (int[] p : inversions) {

            System.out.printf("(%d, %d) : (%d, %d)%n", p[0], p[1], arr[p[0]], arr[p[1]]);
        }
        return count;
    }

    // Возвращает количество инверсий в elems[l--r], записывая пары в inversions
    private static long mergeSortRange(Elem[] elems, Elem[] buffer, int l, int r, List<int[]> inversions) {
        if (l >= r) return 0;
        int mid = l + (r - l) / 2;
        long cnt = 0;
        cnt += mergeSortRange(elems, buffer, l, mid, inversions);
        cnt += mergeSortRange(elems, buffer, mid + 1, r, inversions);
        cnt += mergeAndCollect(elems, buffer, l, mid, r, inversions);
        return cnt;
    }

    // Слияние двух отсортированных частей elems[left--mid] и elems[mid+1--right]
    // Возвращает число инверсий, обнаруженных при слиянии, и добавляет пары в inversions.
    private static long mergeAndCollect(Elem[] elems, Elem[] buffer, int left, int mid, int right, List<int[]> inversions) {
        // Копируем в buffer диапазон left--right
        for (int k = left; k <= right; k++) buffer[k] = elems[k];

        int i = left;
        int j = mid + 1;
        int k = left;
        long invCount = 0;

        while (i <= mid && j <= right) {
            if (buffer[i].value <= buffer[j].value) {
                elems[k++] = buffer[i++];
            } else {
                // buffer[i].val > buffer[j].val -> все элементы buffer[i..mid] > buffer[j]
                int num = mid - i + 1;
                invCount += num;
                // Записываем каждую инверсию: (indexLeft, indexRight)
                // Замечание: это добавляет O(num) затрат, итого суммарно O(K) по всем слияниям.
                for (int t = i; t <= mid; t++) {
                    inversions.add(new int[]{ buffer[t].index, buffer[j].index});
                }
                elems[k++] = buffer[j++];
            }
        }
        while (i <= mid) elems[k++] = buffer[i++];
        while (j <= right) elems[k++] = buffer[j++];
        return invCount;
    }


    public static void main(String[] args) {
        int[] arr1 = {5, 1, 8, 3, 10, 2, 7, 4};
        System.out.println("Массив: " + Arrays.toString(arr1));
        findAndPrintInversions(arr1);

        System.out.println();

        int[] arr2 = {9, 4, 6, 3, 8, 7, 2, 5};
        System.out.println("Массив: " + Arrays.toString(arr2));
        findAndPrintInversions(arr2);

        System.out.println();

        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("Массив: " + Arrays.toString(arr3));
        findAndPrintInversions(arr3);
    }
}

/*
Временная сложность: Θ(n log n) + O(K).
  - В лучшем случае (мало инверсий) — Θ(n log n).
  - В худшем случае (массив в обратном порядке) K = n(n-1)/2 ⇒ время Θ(n^2).

Память:
  - Доп. массивы (elems, buffer): O(n).
  - Хранение результатов (inversions): O(K).
  - Итого доп. память: O(n + K).
*/
