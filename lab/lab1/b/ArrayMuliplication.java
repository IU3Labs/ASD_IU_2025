//2 Пусть любое число – это массив его цифр слева направо. Пример, число
//1234 – это массив [1,2,3,4].
//Дан массив целых чисел. Реализовать умножение двух чисел.
//Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//Результат – число, представленное массивом.

package b;

import java.util.Scanner;

public class ArrayMuliplication {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Введите количество цифр первого числа: ");
        int[] num1 = inputArray();

        System.out.print("Введите количество цифр второго числа: ");
        int[] num2 = inputArray();

        int[] result = multiplyNumbers(num1, num2);

        System.out.print("Результат умножения: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        scanner.close();
    }

    public static int[] inputArray(){
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Введите цифры первого числа (слева направо):");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    public static int[] multiplyNumbers(int[] num1, int[] num2) {
        int len1 = num1.length;
        int len2 = num2.length;

        int[] result = new int[len1 + len2];

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int product = num1[i] * num2[j];
                int position1 = i + j;
                int position2 = i + j + 1;

                int sum = product + result[position2];
                result[position2] = sum % 10;
                result[position1] += sum / 10;
            }
        }

        return removeLeadingZeros(result);
    }

    private static int[] removeLeadingZeros(int[] number) {
        int firstNonZero = 0;

        while (firstNonZero < number.length && number[firstNonZero] == 0) {
            firstNonZero++;
        }

        if (firstNonZero == number.length) {
            return new int[]{0};
        }

        int[] result = new int[number.length - firstNonZero];
        for (int i = 0; i < result.length; i++) {
            result[i] = number[firstNonZero + i];
        }

        return result;
    }}
