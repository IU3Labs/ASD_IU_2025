package lab1;//Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
//число, которое является суммой двух наименьших положительных чисел.

import java.util.Scanner;

public class Num5 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Сколько чисел введешь? ");
        int size = s.nextInt();
        int[] arr = inputArray(size, s);
        bubbleSort(arr);
        int sum = sumTwoSmallestPositive(arr);
        System.out.println("Сумма 2х наименьших положительных: " + sum);
        s.close();
    }

    public static int[] inputArray(int size, Scanner scanner) {
        int[] arr = new int[size];
        System.out.println("Введи " + size + " чисел:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int sumTwoSmallestPositive(int[] arr) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < arr.length && count < 2; i++) {
            if (arr[i] > 0) {
                sum += arr[i];
                count++;
            }
        }
        return sum;
    }
}