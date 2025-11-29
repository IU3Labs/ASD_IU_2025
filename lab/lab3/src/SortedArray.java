/*2. Дан K-sorted array и число K. Отсортировать массив.
K-sorted array - массив из N элементов, в котором каждый элемент стоит не далее, чем на K позиций от своей позиции в отсортированном массиве.
Отсортируйте массив за время O (N log K) и минимальное количество дополнительной памяти. Докажите сложность.
 */


import java.util.*;

public class SortedArray {

    static void nearlySorted(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<Integer> kList = new PriorityQueue<>();

        for (int i = 0; i < k; i++) { // O(k)
            kList.add(arr[i]); // O(log k)
        }

        int i;
        for (i = k; i < n; i++) { // O(n-k)
            kList.add(arr[i]); // O(log k)
            arr[i - k] = kList.poll(); // O(log k)
        }

        while (!kList.isEmpty()) { // O(k)
            arr[i - k] = kList.poll(); // O(log k)
            i++;
        }
    } // Общая сложность: O(n log k)

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 6, 8, 7, 6, 3, 5, 9};
        int k = 3;
        nearlySorted(arr, k);
        System.out.println(Arrays.toString(arr));

    }
}