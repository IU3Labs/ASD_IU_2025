import java.util.*;

public class taskB1 {

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.put(x, freq.getOrDefault(x, 0) + 1);

        int[][] arr = new int[freq.size()][2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            arr[idx][0] = e.getKey();
            arr[idx][1] = e.getValue();
            idx++;
        }

        heapSort(arr);

        int[] res = new int[k];
        for (int i = 0; i < k; i++) res[i] = arr[i][0];
        return res;
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

        if (l < n && arr[l][1] > arr[largest][1]) largest = l;
        if (r < n && arr[r][1] > arr[largest][1]) largest = r;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static void swap(int[][] arr, int i, int j) {
        int[] t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,2,3};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
    }
}
