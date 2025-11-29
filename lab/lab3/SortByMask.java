package lab3;

import java.util.Arrays;

public class SortByMask {

    public static void sortByMask(int[] arr, int mask) {

        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        /* Создаём массив индексов indexArray и заполняем его значениями 0--n-1.
           Каждый шаг цикла — O(1), выполняется n раз => O(n) времени.
           Доп. память: массив indexArray размера n => O(n).
           */

        int[] indexArray = new int[n];
        for (int i = 0; i < n; i++) indexArray[i] = i;

        /* Выделяем buffer длины n для слияния — это O(n) по памяти и O(1) по времени для выделения.
         */

        int[] buffer = new int[n];
        /*
        Рекуррентное соотношение: T(n) = 2 T(n/2) + Θ(n)
        Получается T(n) = Θ(n log n).
         */
        mergeSortIdx(arr, mask, indexArray, buffer, 0, n - 1);
/* Проход по indexArray и копирование arr[indexArray[i]] в tmp — n присваиваний => O(n) времени.
           Доп. память: tmp длины n => O(n).

 */
        int[] tmp = new int[n];
        for (int i = 0; i < n; i++) tmp[i] = arr[indexArray[i]];
/*Копирование результата обратно в arr
           System.arraycopy копирует n элементов за O(n) времени.

 */
        System.arraycopy(tmp, 0, arr, 0, n);
    }

    private static void mergeSortIdx(int[] arr, int mask, int[] index, int[] buffer, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSortIdx(arr, mask, index, buffer, left, mid);

        mergeSortIdx(arr, mask, index, buffer, mid + 1, right);

        merge(arr, mask, index, buffer, left, mid, right);
    }

    private static void merge(int[] arr, int mask, int[] index, int[] buffer, int left, int mid, int right) {

        for (int k = left; k <= right; k++) buffer[k] = index[k];

        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            int leftIndex  = buffer[i];
            int rightIndex = buffer[j];
            int leftKey  = arr[leftIndex] & mask;
            int rightKey = arr[rightIndex] & mask;

            if (leftKey < rightKey) {
                index[k++] = leftIndex; i++;
            }
            else if (leftKey > rightKey) {
                index[k++] = rightIndex; j++;
            }
            else {
                if (arr[leftIndex] <= arr[rightIndex]) {
                    index[k++] = leftIndex; i++;
                }
                else {
                    index[k++] = rightIndex; j++;
                }
            }
        }

        while (i <= mid) index[k++] = buffer[i++];
        while (j <= right) index[k++] = buffer[j++];
    }

    public static void main(String[] args) {
        int[] arr = {9, 4, 6, 3, 8, 7, 2, 5};
        int mask = 1;
        System.out.println("Перед: " + Arrays.toString(arr));
        sortByMask(arr, mask);
        System.out.println("После : " + Arrays.toString(arr));
    }
}


/*Суммарное время: O(n) + Θ(n log n) + O(n) = Θ(n log n).
Дополнительная память: массивы idx, aux, tmp — каждый размера n => O(n).

 Временная сложность: Θ(n log n).
 Пространственная сложность: Θ(n).
 */
