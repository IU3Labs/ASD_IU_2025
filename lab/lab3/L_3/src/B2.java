import java.util.PriorityQueue;
import java.util.Arrays;

public class B2 {

    /*Дан K-sorted array и число K. Отсортировать массив.
    K-sorted array - массив из N элементов, в котором каждый элемент стоит не
    далее, чем на K позиций от своей позиции в отсортированном массиве.
    Отсортируйте массив за время O (N log K) и минимальное количество
    дополнительной памяти. Докажите сложность.*/

    public static void main(String[] args) {
        int[] array = {3, 2, 6, 5, 4, 8};
        int k = 2;

        sortKSortedArray(array, k);

        System.out.println(Arrays.toString(array));
    }

    public static void sortKSortedArray(int[] array, int k) {
        int heapSize = Math.min(array.length, k + 1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < heapSize; i++) {
            minHeap.add(array[i]);
        }

        int index = 0;

        for (int i = heapSize; i < array.length; i++) {
            array[index] = minHeap.remove();
            index++;

            minHeap.add(array[i]);
        }

        while (!minHeap.isEmpty()) {
            array[index] = minHeap.remove();
            index++;
        }
    }
}
