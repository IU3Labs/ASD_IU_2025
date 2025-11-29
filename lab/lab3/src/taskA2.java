import java.util.*;

public class taskA2 {

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.put(x, freq.getOrDefault(x, 0) + 1);

        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> {
                    if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
                    return Integer.compare(b[0], a[0]);
                }
        );

        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int value = e.getKey();
            int count = e.getValue();
            int[] pair = new int[]{value, count};
            if (heap.size() < k) heap.offer(pair);
            else {
                int[] top = heap.peek();
                if (count > top[1] || (count == top[1] && value < top[0])) {
                    heap.poll();
                    heap.offer(pair);
                }
            }
        }

        int[][] arr = new int[heap.size()][2];
        int idx = 0;
        for (int[] p : heap) arr[idx++] = p;

        heapSort(arr);

        List<Integer> result = new ArrayList<>();
        for (int[] p : arr) result.add(p[0]);
        return result;
    }

    private static void heapSort(int[][] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[][] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && compare(arr[l], arr[largest]) > 0) largest = l;
        if (r < n && compare(arr[r], arr[largest]) > 0) largest = r;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static int compare(int[] a, int[] b) {
        if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
        return Integer.compare(b[0], a[0]);
    }

    private static void swap(int[][] arr, int i, int j) {
        int[] t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3};
        System.out.println(topKFrequent(nums, 2));
    }
}
