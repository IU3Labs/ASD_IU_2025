//Пусть любое число – это массив его цифр слева направо. Пример, число
//        1234 – это массив [1,2,3,4].
//        Дан массив целых чисел. Реализовать умножение двух чисел.
//        Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//        Результат – число, представленное массивом.

package tasks;

import utils.*;

import java.util.Scanner;

public class MultiplyingLists {
    public static void main(String[] args) {
        Scanner scan = InputManager.getScanner();
        System.out.println("Количество цифр в первом массиве: ");
        int firstArraySize = InputManager.inputTarget(scan);
        int[] number1 = new int[firstArraySize];
        ArrayUtils.fillArray(number1, scan);
        System.out.println("Количество цифр во втором массиве: ");
        int secondArraySize = InputManager.inputTarget(scan);
        int[] number2 = new int[secondArraySize];
        ArrayUtils.fillArray(number2, scan);
        OutputUtils.printArray(multiplying(number1, number2));

    }

    private static int[] multiplying(int[] number1, int[] number2) {
        if (number1.length == 0 || number2.length == 0) {
            return new int[]{0};
        }

        int n = number1.length;
        int m = number2.length;
        int[] res = new int[n + m];


        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int product = number1[i] * number2[j];
                int sum = product + res[i + j + 1];
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }


        for (int i = res.length - 1; i > 0; i--) {
            if (res[i] >= 10) {
                res[i - 1] += res[i] / 10;
                res[i] %= 10;
            }
        }

        int start = 0;
        while (start < res.length - 1 && res[start] == 0) {
            start++;
        }


        int len = res.length - start;
        int[] result = new int[len];
        System.arraycopy(res, start, result, 0, len);
        return result;
    }

    private static long transformationNumber(int[] array) {
        long sum = 0;
        long digit = 1;
        for (int i = array.length - 1; i >= 0; i--) {
            sum += (long) array[i] * digit;
            digit *= 10;
        }
        return sum;
    }

    private static int[] transformationArray(long number) {
        if (number == 0) return new int[]{0};

        long temp = Math.abs(number);
        int length = 0;
        long tempCopy = temp;

        while (tempCopy > 0) {
            tempCopy /= 10;
            length++;
        }

        int[] result = new int[length];
        temp = Math.abs(number);

        for (int i = length - 1; i >= 0; i--) {
            result[i] = (int) (temp % 10);
            temp /= 10;
        }
        return result;
    }
}