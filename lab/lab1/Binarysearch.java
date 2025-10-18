package lab.lab1;
import java.util.Scanner;
import lab.lab1.ArrUtility;

public class Binarysearch {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int[] arr = ArrUtility.inputArray();
        ArrUtility.bubbleSort(arr);
        System.out.print("Отсортированный массив: ");
        ArrUtility.printArray(arr);

        System.out.println(" Введите элемент для поиска: ");
        int target = scanner.nextInt();
        scanner.close();

        int result = binarySearch(arr, target);
        int resultrec = binarySearchRec(arr, target, 0, arr.length - 1);
        if (result == resultrec) {
            if (resultrec != -1) {
                System.out.println("Номер элемента: " + resultrec);
            } else {
                System.out.println("Элемент не найден");
            }
        } else {
            System.out.println("Ошибка");
        }
    }

    public static int binarySearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchRec(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] < target) {
            return binarySearchRec(arr, target, mid + 1, right);
        } else {
            return binarySearchRec(arr, target, left, mid - 1);
        }
    }
}
