package lab1;//2 способ с рекурсией

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Сколько чисел в массиве? ");
        int n = s.nextInt();
        int[] num = new int[n];
        System.out.println("Введите " + n + " отсортированные числа:");
        for (int i = 0; i < n; i++) {
            num[i] = s.nextInt();
        }
        printArray(num);
        System.out.print("Какое число ищем? ");
        int x = s.nextInt();
        int res = rec(num, x);
        if (res != -1) {
            System.out.println("Число " + x + " найдено на " + res);
        } else {
            System.out.println("Числа " + x + " нет");
        }
        s.close();
    }

    public static void printArray(int[] arr) {
        System.out.print("Массив: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static int rec(int[] arr, int target) {
        return search(arr, target, 0, arr.length - 1);
    }

    private static int search(int[] arr, int target, int l, int r) {
        if (l > r) return -1;
        int mid = (l + r) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) return search(arr, target, mid + 1, r);
        else return search(arr, target, l, mid - 1);
    }

}