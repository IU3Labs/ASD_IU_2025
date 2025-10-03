package lab1;

import java.util.Scanner;

public class Bibinarysearch {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Кол-во элементов: ");

        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        BubbleSort(arr);

        System.out.print("Отсортированный массив: ");
        outarr(arr);
        System.out.println("Введите элемент для поиска: ");
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

    public static void BubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void outarr(int[] arr) {
        for (int i = 0; i <= arr.length - 1; i++) {
            System.out.print(arr[i] + " ");
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