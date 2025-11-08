package lab1.b;

//1234 – это массив [1,2,3,4].
//Дан массив целых чисел. Реализовать умножение двух чисел.
//Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//Результат – число, представленное массивом.
import java.util.Scanner;

public class Multiplying {
    static Scanner input = new Scanner(System.in);

    static int[] inputArray() {
        System.out.print("Введите длину массива: ");
        int arrayLength = input.nextInt();
        int[] array = new int[arrayLength];
        System.out.print("Введите элементы массива: ");
        for (int i = 0; i < arrayLength; i++) {
            array[i] = input.nextInt();
        }
        return array;
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

    static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.print(array[array.length - 1] + "]");
    }

    public static void main(String[] args) {
        int[] firstArray = inputArray();
        int[] secondArray = inputArray();
        System.out.print("Перемноженные массивы: ");
        printArray(multiplyArrays(firstArray, secondArray));
    }
}

