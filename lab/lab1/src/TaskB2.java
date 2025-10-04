import java.util.Arrays;
import java.util.Scanner;


/*
Пусть любое число – это массив его цифр слева направо. Пример, число
1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
Результат – число, представленное массивом.
 */


public class TaskB2 {
    public static void main(String[] args) {
        //Ввод двух чисел и вывод их в виде массива
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        int num1 = scanner.nextInt();
        System.out.println("Enter the second number: ");
        int num2 = scanner.nextInt();
        System.out.println("\nThe first number is: ");
        printArray(numToArray(num1));
        System.out.println("\nThe second number is: ");
        printArray(numToArray(num2));
        //Умножение этих чисел с выводом
        int result = num1 * num2;
        int[] resultArr = numToArray(result);
        System.out.println("\nThe result is: ");
        printArray(resultArr);
    }

    //Перевод числа в массив
    public static int[] numToArray(int n) {

        //Поиск длины числа, для создания массива нужной длины
        int length = 0;
        int lengthCalcN = n;
        while (lengthCalcN != 0) {
            lengthCalcN /= 10;
            length++;
        }

        //Запись числа в массив (задом на перед)
        int[] array = new int[length];
        int i = 0;
        while (n != 0) {
            array[i] = n % 10;
            n = n / 10;
            i++;
        }
        return array;
    }

    //Вывод массива начиная с конца, т. к. у нас числа записывались начиная с конца в методе numToArray
    public static void printArray(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) System.out.print(arr[i] + " ");
    }
}
