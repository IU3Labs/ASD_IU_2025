package lab1.b;

//1234 – это массив [1,2,3,4].
//Дан массив целых чисел. Реализовать умножение двух чисел.
//Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//Результат – число, представленное массивом.
import java.util.Scanner;
import lab1.InputArray;
import lab1.PrintArray;

public class Multiplying {
    static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {
        int[] firstArray = InputArray.inputArray();
        int[] secondArray = InputArray.inputArray();
        System.out.print("Перемноженные массивы: ");
        PrintArray.printArray(multiplyArrays(firstArray, secondArray));
    }


    public static int[] numToArray(int number) {
        if (number == 0) {
            return new int[]{0};
        }

        int length = 0;
        int temp = number;
        while (temp > 0) {
            temp /= 10;
            length++;
        }
        int[] result = new int[length];
        temp = number;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = temp % 10;
            temp /= 10;
        }
        return result;
    }

    public static int arrayToNum(int[] array) {
        int number = 0;
        for (int i = 0; i < array.length; i++) {
            number = number * 10 + array[i];
        }
        return number;
    }

    public static int[] multiplyArrays(int[] arr1, int[] arr2) {
        int result = arrayToNum(arr1) * arrayToNum(arr2);
        return numToArray(result);
    }
}

