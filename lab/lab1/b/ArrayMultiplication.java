package lab1.b;

/* 2. Пусть любое число – это массив его цифр слева направо.
Пример, число 1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
Результат – число, представленное массивом. */


import java.util.Scanner;

public class ArrayMultiplication {  static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int[] a = inputArray();
        int[] b = inputArray();
        printArray(multiplyArrays(a, b));
    }

    static int[] multiplyArrays(int[] firstArray, int[] secondArray) {
        int resNum = convertArrayToNumber(firstArray) * convertArrayToNumber(secondArray);
        return convertNumberToArray(resNum);
    }

    static int convertArrayToNumber(int[] arr) {
        int number = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            number += arr[i] * (int) Math.pow(10, arr.length - i - 1);
        }
        return number;
    }

    static int[] convertNumberToArray(int num) {
        int arrayLength = ("" + num).length();
        int[] resultArray = new int[arrayLength];
        for (int i = arrayLength - 1; i >= 0; i--) {
            resultArray[i] = num % 10;
            num /= 10;
        }
        return resultArray;
    }

    static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[arr.length - 1] + "]");
    }

    static int[] inputArray() {
        System.out.print("Введите длинну массива: ");
        int arrLen = input.nextInt();
        int[] arr = new int[arrLen];
        System.out.print("Введите массив: ");
        for (int i = 0; i < arrLen; i++) {
            arr[i] = input.nextInt();
        }
        return arr;
    }

}
