public class BinarySearch {
    
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2; 
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1; 
            } else {
                right = mid - 1; 
            }
        }
        
        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1; 
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid; 
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }

    public static int binarySearchRecursive(int[] arr, int target) {
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] sortedArray = {2, 5, 8, 12, 16, 23, 38, 45, 67, 89};
        
        int result1 = binarySearchIterative(sortedArray, 8);
        System.out.println("Итеративный метод: " + result1);

        int result2 = binarySearchRecursive(sortedArray, 67);
        System.out.println("Рекурсивный метод: " + result2);
    }
}