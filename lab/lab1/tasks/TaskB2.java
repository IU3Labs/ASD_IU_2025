package tasks;

import java.util.Scanner;

public class TaskB2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] firstNumberArray = ArrayFunc.numberArray(scanner, "первого");
        int[] secondNumberArray = ArrayFunc.numberArray(scanner, "второго");
        System.out.print("Первое число: ");
        printArray(firstNumberArray);
        System.out.print("Второе число: ");
        printArray(secondNumberArray);
        int[] result = multiply(firstNumberArray, secondNumberArray);
        System.out.print("Результат умножения: ");
        printArray(result);
    }

    private static int[] multiply(int[] firstArray, int[] secondArray) {
        long firstNumber = ArrayFunc.arrayToLong(firstArray);
        long secondNumber = ArrayFunc.arrayToLong(secondArray);
        long result = firstNumber * secondNumber;
        return ArrayFunc.longToArray(result);
    }

    private static void printArray(int[] array) {
        for(int i = 0; i < array.length; ++i) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }
}
