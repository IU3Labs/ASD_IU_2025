package algorithms;

public class BinarySearch {
    public static int iterativeSearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int recursiveSearch(int[] array, int target) {
        return recursiveHelper(array, target, 0, array.length - 1);
    }

    private static int recursiveHelper(int[] array, int target, int left, int right) {
        if (right < left) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return recursiveHelper(array, target, mid + 1, right);
        } else {
            return recursiveHelper(array, target, left, mid - 1);
        }
    }
}