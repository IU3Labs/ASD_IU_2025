import java.util.Arrays;

public class InversionCounter {

    public static int countInversions(int[] arr) {
        int[] temp = arr.clone();
        return mergeSortAndCount(arr, 0, arr.length - 1, temp);
    }

    private static int mergeSortAndCount(int[] arr, int left, int right, int[] temp) {
        int count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;

            count += mergeSortAndCount(arr, left, mid, temp);
            count += mergeSortAndCount(arr, mid + 1, right, temp);
            count += mergeAndCount(arr, left, mid, right, temp);
        }
        return count;
    }

    private static int mergeAndCount(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int k = left;
        int swaps = 0;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
                swaps += (mid - i + 1);
            }
        }

        while (i <= mid) {
            arr[k++] = temp[i++];
        }

        while (j <= right) {
            arr[k++] = temp[j++];
        }

        System.arraycopy(arr, left, temp, left, right - left + 1);
        return swaps;
    }

    public static void printInversions(int[] arr) {
        System.out.println("Инверсии в массиве:");
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    System.out.println("(" + i + "," + j + "): " + arr[i] + " > " + arr[j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 3, 5};
        System.out.println("Массив: " + Arrays.toString(arr));
        int inversionCount = countInversions(arr);
        System.out.println("Количество инверсий: " + inversionCount);
        printInversions(new int[]{2, 4, 1, 3, 5});
    }
}