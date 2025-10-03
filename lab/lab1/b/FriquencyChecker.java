package lab1.b;

/* 1. Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее. */

import java.util.Scanner;

public class FriquencyChecker { static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите длинну массива: ");
        int arrLen = input.nextInt();
        int[] arr = new int[arrLen];
        System.out.print("Введите массив: ");
        inputArray(arr);
        System.out.println(findFrequentNumber(arr));
    }

    static void inputArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = input.nextInt();
        }
    }

    static int findFrequentNumber(int[] array) {
        int curAnsw = -1;
        int curMaxFrequency = 0;
        for (int j : array) {
            int frequency = count(array, j);
            if (frequency == j) {
                if (frequency > curMaxFrequency) {
                    curAnsw = j;
                    curMaxFrequency = frequency;
                }
            }
        }
        return curAnsw;
    }

    static int count(int[] arr, int num) {
        int count = 0;
        for (int j : arr) {
            if (j == num) {
                count++;
            }
        }
        return count;
    }
}
