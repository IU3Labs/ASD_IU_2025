package lab1;

import java.util.Scanner;

public class Frequency {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.println("Введите количество элементов массиве: ");
        n = scanner.nextInt();
        int[] arr = new int[n];
        arrayFill(arr, n);
        int result = numFrequency(arr, n);
        System.out.println("Результат: " + result);
    }

    public static void arrayFill(int[] arr, int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
    }

    public static int numFrequency(int[] arr, int n) {
        int[] items = new int[n];
        int itemNum = 0;
        for (int i = 0; i < n; i++) {
            if (Count(arr, n, arr[i]) == arr[i]) {
                items[itemNum] = arr[i];
                itemNum++;
            }
        }
        if (itemNum > 0) {
            if (itemNum > 1) {
                return maxNum(items, itemNum);
            }
            return items[0];
        }
        return -1;
    }

    public static int Count(int[] arr, int n, int x) {
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == x) {
                k += 1;
            }
        }
        return k;
    }
    public static int maxNum(int[] arr, int n) {
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > m) {
                m = arr[i];
            }
        }
        return m;
    }
}

