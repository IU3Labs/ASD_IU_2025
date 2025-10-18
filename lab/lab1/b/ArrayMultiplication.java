package lab.lab1.b;
/* 2. Пусть любое число – это массив его цифр слева направо.
Пример, число 1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
Результат – число, представленное массивом. */
import lab.lab1.ArrUtility;

public class ArrayMultiplication {

    public static void main(String[] args) {
        int[] a = ArrUtility.inputArray();
        int[] b = ArrUtility.inputArray();
        ArrUtility.printArray(multiplyArrays(a, b));
    }

    static int[] multiplyArrays(int[] firstArray, int[] secondArray) {
        long resNum = convertArrayToNumber(firstArray) * convertArrayToNumber(secondArray);
        return convertNumberToArrayLongVer(resNum);
    }

    static int convertArrayToNumber(int[] arr) {
        int number = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            number += arr[i] * (int) Math.pow(10, arr.length - i - 1);
        }
        return number;
    }

    static int[] convertNumberToArrayLongVer(long num) {
        int arrayLength = ("" + num).length();
        int[] resultArray = new int[arrayLength];
        for (int i = arrayLength - 1; i >= 0; i--) {
            resultArray[i] = (int) (num % 10);
            num /= 10;
        }
        return resultArray;
    }
}