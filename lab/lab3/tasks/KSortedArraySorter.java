package tasks;

import utils.ArrayUtils;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KSortedArraySorter {

    public static void main(String[] args) {
        int[] array = ArrayUtils.inputArray();

        System.out.print("Введите K: ");
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        scanner.close();

        sortKSortedArray(array, k);

        System.out.println("Отсортированный массив:");
        ArrayUtils.printArray(array);
    }

    public static void sortKSortedArray(int[] array, int k) {
        if (array.length == 0) return;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int index = 0;

        for (int i = 0; i < array.length; i++) {
            minHeap.add(array[i]);

            if (minHeap.size() > k) {
                array[index++] = minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            array[index++] = minHeap.poll();
        }
    }
}