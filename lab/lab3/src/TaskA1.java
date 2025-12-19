// 1 Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
//таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
//данном массиве и вывести их. Дать комментарии. Вычислить сложность.

// Вычисление сложности:
// разделение массива - O(log N)
// слияния и подсчёты - O(N)
// итого: O(N log N)

import java.util.Arrays;

public class TaskA1 {
    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 3, 5};

        System.out.println("array: " + Arrays.toString(arr));

        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        long inversions = countInversions(sortedArr);

        System.out.println("sorted array: " + Arrays.toString(sortedArr));
        System.out.println("count inversions: " + inversions);
    }

    public static long countInversions(int[] arr) {
        return mergeSort(arr, 0, arr.length - 1);
    }

    // сортировка слиянием
    private static long mergeSort(int[] arr, int left, int right) {
        if (left >= right) return 0;

        int mid = (left + right) / 2;
        long inversions = 0;

        inversions += mergeSort(arr, left, mid);
        inversions += mergeSort(arr, mid + 1, right);
        inversions += merge(arr, left, mid, right);

        return inversions;
    }

    private static long merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        long inversions = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                inversions += (mid - i + 1);
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);

        return inversions;
    }
}
