package lab1;

import java.util.Scanner;

public class Frequency {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.println("Введите количество элементов массиве: ");
        n = scanner.nextInt();
        int[] arr = new int[n];
        array_fill(arr, n);
        int result = num_frequency(arr, n);
        System.out.println("Результат: " + result);
    }

    public static void array_fill(int[] arr, int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
    }

    public static int count(int[] arr, int n, int x) {
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == x) {
                k += 1;
            }
        }
        return k;
    }
    public static int max_num(int[] arr, int n) {
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > m) {
                m = arr[i];
            }
        }
        return m;
    }

    public static int num_frequency(int[] arr, int n) {
        int[] items = new int[n];
        int item_num = 0;
        for (int i = 0; i < n; i++) {
            if (count(arr, n, arr[i]) == arr[i]) {
                items[item_num] = arr[i];
                item_num++;
            }
        }
        if (item_num > 0) {
            if (item_num > 1) {
                return max_num(items, item_num);
            }
            return items[0];
        }
        return -1;
    }

}
