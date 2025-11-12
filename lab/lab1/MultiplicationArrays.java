package lab1;

import java.util.Scanner;


public class MultiplicationArrays {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        int n1;
        int n2;
        System.out.println("Введите количество элементов в первом массиве: ");
        n1 = scanner.nextInt();
        int[] arr1 = new int[n1];
        array_fill(arr1, n1);
        System.out.println("Введите количество элементов во втором массиве: ");
        n2 = scanner.nextInt();
        int[] arr2 = new int[n2];
        array_fill(arr2, n2);
        int[] result = num_to_array(multiplication(array_to_num(arr1), array_to_num(arr2)));
        int l = result.length;
        System.out.println("Результат: ");
        for (int i = l - 1; i >= 0; i--) {
            if (i == l - 1) {
                System.out.print("[" + result[i] + ", ");
            }
            else if (i == 0) {
                System.out.print(result[i] + "]");
            }
            else {
                System.out.print(result[i] + ", ");
            }
        }
    }

    public static int multiplication(int num1, int num2) {
        return num1 * num2;
    }

    public static void array_fill(int[] arr, int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
    }

    public static int array_to_num(int[] arr) {
        int length = arr.length;
        int num = 0;
        int digit = 1; int i = 0;
        while(i < length) {
            num += arr[i] * digit;
            digit *= 10;
            i += 1;
        }
        return num;
    }
    public static int[] num_to_array(int n) {
        int length = 0;
        int temp = n;
        while (temp > 0) {
            temp /= 10;
            length++;
        }
        int[] arr = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            arr[i] = n % 10;
            n /= 10;
        }
        return arr;
    }
}
