import java.util.*;

public class KSortedArray {
    public void sortKSortedArray(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) return;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i <= k && i < arr.length; i++) {
            minHeap.offer(arr[i]);
        }

        int index = 0;

        for (int i = k + 1; i < arr.length; i++) {
            arr[index++] = minHeap.poll();
            minHeap.offer(arr[i]);
        }

        while (!minHeap.isEmpty()) {
            arr[index++] = minHeap.poll();
        }
    }

    private static boolean isKSorted(int[] arr, int k) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> positionMap = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            positionMap.put(sorted[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(i - positionMap.get(arr[i])) > k) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        KSortedArray solution = new KSortedArray();

        int[] arr = {6, 5, 3, 2, 8, 10, 9};
        int k = 3;

        System.out.println("Исходный массив: " + Arrays.toString(arr));
        System.out.println("K-отсортирован? " + isKSorted(arr, k));

        solution.sortKSortedArray(arr, k);

        System.out.println("Отсортированный массив: " + Arrays.toString(arr));
        System.out.println("Проверка сортировки: " + Arrays.equals(arr, Arrays.stream(arr).sorted().toArray()));
    }
}