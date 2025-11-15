package tasks;

import java.util.Scanner;

import static ArrayFunc.*;
/* задание Б2 : Пусть любое число – это массив его цифр слева направо. Пример, число
1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
Результат – число, представленное массивом*/

public class TaskB2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] firstNumberArray = numberArray(scanner, "первого");
        int[] secondNumberArray = numberArray(scanner, "второго");

        System.out.print("Первое число: ");
        printArray(firstNumberArray);
        System.out.print("Второе число: ");
        printArray(secondNumberArray);

        int[] result = multiply(firstNumberArray, secondNumberArray);

        System.out.print("Результат умножения: ");
        printArray(result);
    }


    private static int[] multiply(int[] firstArray, int[] secondArray) {
        long firstNumber = arrayToLong(firstArray);
        long secondNumber = arrayToLong(secondArray);

        long result = firstNumber * secondNumber;

        return longToArray(result);
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}