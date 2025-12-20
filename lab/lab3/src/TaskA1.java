public class TaskA1 {

    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 2, 6, 7};
        long count = countInversions(arr);
        System.out.println("Количество инверсий: " + count);
    }

    private static long countInversions(int[] arr) {
        int[] temp = new int[arr.length];
        long result = 0;

        for (int size = 1; size < arr.length; size *= 2) {
            for (int left = 0; left < arr.length - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, arr.length - 1);
                result += merge(arr, temp, left, mid, right);
            }
        }
        return result;
    }

    private static long merge(int[] arr, int[] temp, int left, int mid, int right) {
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int i = left, j = mid + 1, k = left;
        long count = 0;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];

        return count;
    }
}