/*Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
число, которое является суммой двух наименьших положительных чисел.*/

import java.util.Scanner;

public class SumTwoSmallestPositive {

    public static int calculateSumTwoSmallestPositive(int[] array) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int value : array) {
            if (value > 0) {
                if (value < min1) {
                    min2 = min1;
                    min1 = value;
                } else if (value < min2) {
                    min2 = value;
                }
            }
        }

        if (min2 == Integer.MAX_VALUE) {
            return -1;
        }

        return min1 + min2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        if (size < 5) {
            System.out.println("Error: array must contain at least 5 elements");
            scanner.close();
            return;
        }

        int[] array = new int[size];
        System.out.println("Enter " + size + " elements:");

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        int result = calculateSumTwoSmallestPositive(array);
        System.out.println("Result: " + result);

        scanner.close();
    }
}