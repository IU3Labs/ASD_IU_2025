import java.util.*;

public class TaskB1 {

    private static void mergeSort(List<Integer> nums, int left, int right,
                                  Map<Integer, Integer> freq, Integer[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(nums, left, mid, freq, temp);
            mergeSort(nums, mid + 1, right, freq, temp);
            merge(nums, left, mid, right, freq, temp);
        }
    }

    private static void merge(List<Integer> nums, int left, int mid, int right,
                              Map<Integer, Integer> freq, Integer[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums.get(i);
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (freq.get(temp[i]) >= freq.get(temp[j])) {
                nums.set(k++, temp[i++]);
            } else {
                nums.set(k++, temp[j++]);
            }
        }

        while (i <= mid) {
            nums.set(k++, temp[i++]);
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> uniqueNums = new ArrayList<>(freqMap.keySet());

        Integer[] temp = new Integer[uniqueNums.size()];

        mergeSort(uniqueNums, 0, uniqueNums.size() - 1, freqMap, temp);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = uniqueNums.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }
}