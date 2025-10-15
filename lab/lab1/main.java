// Task: Binary Search in two ways (iterative and recursive)

import java.util.Scanner;

public class Main {

    // Iterative binary search
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                return mid; // found
            } else if (arr[mid] < target) {
                left = mid + 1; // search in right half
            } else {
                right = mid - 1; // search in left half
            }
        }
        return -1; // not found
    }

    // Recursive binary search
    public static int binarySearchRecursive(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1; // not found
        }

        int mid = (left + right) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, mid + 1, right, target);
        } else {
            return binarySearchRecursive(arr, left, mid - 1, target);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Enter size of array
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        // Step 2: Enter elements
        System.out.println("Enter " + n + " sorted numbers:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Step 3: Enter target
        System.out.print("Enter number to search: ");
        int target = sc.nextInt();

        // Step 4: Iterative search
        int result1 = binarySearchIterative(arr, target);
        if (result1 != -1) {
            System.out.println("Iterative: Found at index " + result1);
        } else {
            System.out.println("Iterative: Not found");
        }

        // Step 5: Recursive search
        int result2 = binarySearchRecursive(arr, 0, n - 1, target);
        if (result2 != -1) {
            System.out.println("Recursive: Found at index " + result2);
        } else {
            System.out.println("Recursive: Not found");
        }

        sc.close();
    }
}
