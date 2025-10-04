// Группа Б. Номер 2
// Пусть любое число – это массив его цифр слева направо. Пример, число
// 1234 – это массив [1,2,3,4].
// Дан массив целых чисел. Реализовать умножение двух чисел.
// Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
// Результат – число, представленное массивом.

import java.util.Scanner;

public class ArrayMultiplication {
    public static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First array");
        int[] array1 = Utils.createArray(scanner);

        System.out.println("Second array");
        int[] array2 = Utils.createArray(scanner);

        int[] result = multiplication(array1, array2);
        System.out.print("Multiplication result: ");
        Utils.printArr(result);

    }

    private static int[] multiplication(int[] arr1, int[] arr2) {
        int intArr1 = Utils.arrToInt(arr1);
        int intArr2 = Utils.arrToInt(arr2);

        return Utils.intToArr(intArr1 * intArr2);
    }
}