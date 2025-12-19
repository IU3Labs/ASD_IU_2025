//Дан K-sorted array и число K. Отсортировать массив.
//K-sorted array - массив из N элементов, в котором каждый элемент стоит не
//далее, чем на K позиций от своей позиции в отсортированном массиве.
//Отсортируйте массив за время O (N log K) и минимальное количество
//дополнительной памяти. Докажите сложность.

import java.util.*;

public class KSortedArray {
    private final Scanner inputScanner = new Scanner(System.in);

    private void sortKSortedArray(int[] data, int kValue) {
        int dataSize = data.length;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int currentIndex;

        for (currentIndex = 0; currentIndex < kValue; currentIndex++) {  // O(K log K)
            minHeap.add(data[currentIndex]);
        }

        for (currentIndex = kValue; currentIndex < dataSize; currentIndex++) {  // O((N-K) log K)
            minHeap.add(data[currentIndex]);
            data[currentIndex - kValue] = minHeap.poll();
        }

        while (!minHeap.isEmpty()) {  // O(K log K)
            data[currentIndex - kValue] = minHeap.poll();
            currentIndex++;
        }
    }

    public void execute() {
        int[] dataArray = ArrayMethods.readData(inputScanner);

        System.out.print("Введите параметр k: ");
        int kValue = inputScanner.nextInt();

        sortKSortedArray(dataArray, kValue);
        System.out.print("Отсортированный массив: ");
        ArrayMethods.displayData(dataArray);
    }

    public static void main(String[] args) {
        new KSortedArray().execute();
    }
}
