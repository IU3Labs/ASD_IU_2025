// Task: Реализовать алгоритм бинарного поиска двумя способами
package lab1;
import java.util.Scanner;
public class Main {


    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
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

    public static int binarySearchRecursive(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
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

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter " + n + " sorted numbers:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter number to search: ");
        int target = sc.nextInt();

        int result1 = binarySearchIterative(arr, target);
        if (result1 != -1) {
            System.out.println("Iterative: Found at index " + result1);
        } else {
            System.out.println("Iterative: Not found");
        }

        int result2 = binarySearchRecursive(arr, 0, n - 1, target);
        if (result2 != -1) {
            System.out.println("Recursive: Found at index " + result2);
        } else {
            System.out.println("Recursive: Not found");
        }
        sc.close();
    }
}
