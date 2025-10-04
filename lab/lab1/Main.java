package lab1;// 1 способ обычный

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Сколько чисел? ");
        int n = s.nextInt();
        int[] num = new int[n];
        System.out.println("Введи " + n + " числа по возрастанию:");
        for (int i = 0; i < n; i++) {
            num[i] = s.nextInt();
        }
        System.out.print("Какое число? ");
        int x = s.nextInt();
        int res = bin(num, x);
        if (res != -1) {
            System.out.println("Число " + x + " нашлось на " + res);
        } else {
            System.out.println("Числа " + x + " нет");
        }
        s.close();
    }

    public static int bin(int[] arr, int t) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = (r + l) / 2;
            if (arr[mid] == t) {
                return mid;
            } else if (arr[mid] < t) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

}



