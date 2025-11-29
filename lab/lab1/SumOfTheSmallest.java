package lab1;

import java.util.Scanner;

public class SumOfTheSmallest {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.println("Введите количество элементов массиве: ");
        n = scanner.nextInt();
        int[] arr = new int[n];
        arrayFill(arr, n);
        int result = minSum(arr, n);
        System.out.println("Результат: " + result);
    }

    public static void arrayFill(int[] arr, int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
    }

    public static int minSum(int[] arr, int n) {
        int maxElem = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > maxElem) {
                maxElem = arr[i];
            }
        }

        int minElem1 = maxElem;
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0 && arr[i] < minElem1) {
                minElem1 = arr[i];
            }
        }

        int minElem2 = maxElem;
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0 && arr[i] < minElem2 && arr[i] != minElem1) {
                minElem2 = arr[i];
            }
        }

        return minElem1 + minElem2;
    }
}
