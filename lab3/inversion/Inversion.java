package lab.lab3.inversion;
import java.io.*;
import java.util.*;

/**
 * Класс с логикой подсчёта инверсий:
 *  - хранит массив
 *  - реализует mergeSort и merge
 *  - хранит список инверсий и их количество
 *  - содержит FastScanner
 */
public class Inversion {

    /** Элемент массива: значение + исходный индекс (1-based). */
    public static class Item {
        public long value;
        public int index; // индекс в исходном массиве (1..N)

        public Item(long value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    // Основной массив (для merge sort)
    public static Item[] a;

    // Временный массив для слияния
    public static Item[] temp;

    // Количество инверсий
    public static long inversionCount = 0;

    // Список всех инверсий (пары индексов i, j)
    public static List<int[]> inversions = new ArrayList<>();

    /**
     * Рекурсивная сортировка слиянием на отрезке [left, right).
     * Считает инверсии и сортирует массив a на этом отрезке.
     */
    public static void mergeSort(int left, int right) {
        if (right - left <= 1) {
            return; // отрезок длины 0 или 1 уже отсортирован
        }

        int mid = (left + right) / 2;

        // Сортируем левую и правую половины
        mergeSort(left, mid);
        mergeSort(mid, right);

        // Сливаем две отсортированные половины и считаем инверсии между ними
        merge(left, mid, right);
    }

    /**
     * Слияние двух отсортированных отрезков:
     *  - левый: [left, mid)
     *  - правый: [mid, right)
     *
     * Во время слияния считаем инверсии:
     * Если a[i].value > a[j].value, то элемент a[j] меньше ВСЕХ элементов
     * a[i], a[i+1], ..., a[mid-1] (левый отрезок отсортирован),
     * значит добавляем (mid - i) инверсий:
     * (a[i].index, a[j].index), ..., (a[mid-1].index, a[j].index).
     */
    public static void merge(int left, int mid, int right) {
        int i = left;  // указатель по левому отрезку
        int j = mid;   // указатель по правому отрезку
        int k = left;  // указатель в temp

        while (i < mid && j < right) {
            if (a[i].value <= a[j].value) {
                // Инверсий нет: левый элемент меньше или равен правому
                temp[k++] = a[i++];
            } else {
                // a[i].value > a[j].value -> есть инверсии
                inversionCount += (mid - i); // количество инверсий

                // Сохраняем сами пары (индексы)
                for (int p = i; p < mid; p++) {
                    inversions.add(new int[]{a[p].index, a[j].index});
                }

                // В слияние идёт элемент из правого подмассива
                temp[k++] = a[j++];
            }
        }

        // Копируем остаток левой части
        while (i < mid) {
            temp[k++] = a[i++];
        }

        // Копируем остаток правой части
        while (j < right) {
            temp[k++] = a[j++];
        }

        // Переписываем отсортированный участок обратно в a
        for (int t = left; t < right; t++) {
            a[t] = temp[t];
        }
    }

    /**
     * Быстрый ввод (чтобы не лагало на больших данных).
     */
    public static class FastScanner {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}



/** Алгоритм основан  методе декомпозиции, а именно: на сортировке слиянием, которая рекурсивно делит массив пополам и
 *  на каждом уровне выполняет линейное слияние, поэтому сама сортировка работает за O(n log n).
 *  Подсчёт инверсий добавляет к этому только перебор элементов внутри каждого шага слияния,
 *  но каждая инверсия фиксируется ровно один раз, когда элементы из левой и правой половины впервые сравниваются,
 *  поэтому суммарное количество дополнительных операций равно O(K), где K — число инверсий.
 *  Следовательно, итоговая временная сложность алгоритма равна O(n log n + K), а в худшем случае,
 *  когда массив полностью убывающий и K = n(n−1)/2, она достигает O(n²). */