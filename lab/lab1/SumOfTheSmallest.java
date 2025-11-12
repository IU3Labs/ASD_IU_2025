package lab1;

import java.util.Scanner;

public class SumOfTheSmallest {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.println("Введите количество элементов массиве: ");
        n = scanner.nextInt();
        int[] arr = new int[n];
        array_fill(arr, n);
        int result = min_sum(arr, n);
        System.out.println("Результат: " + result);
    }

    public static void array_fill(int[] arr, int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
    }

    public static int min_sum(int[] arr, int n) {
        int max_elem = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > max_elem) {
                max_elem = arr[i];
            }
        }

        int min_elem1 = max_elem;
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0 && arr[i] < min_elem1) {
                min_elem1 = arr[i];
            }
        }

        int min_elem2 = max_elem;
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0 && arr[i] < min_elem2 && arr[i] != min_elem1) {
                min_elem2 = arr[i];
            }
        }

        return min_elem1 + min_elem2;
    }
}
