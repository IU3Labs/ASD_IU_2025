/*
Пусть любое число – это массив его цифр слева направо.
Пример, число 1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
Результат – число, представленное массивом.
*/
import java.util.Scanner;
public class ArrayMultiply {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        String firstString = scanner.next();
        System.out.print("Введите второе число: ");
        String secondString = scanner.next();

        int[] firstNumber = stringIntoArray(firstString);
        int[] secondNumber = stringIntoArray(secondString);

        long firstLong = Long.parseLong(firstString);
        long secondLong = Long.parseLong(secondString);
        long result = firstLong * secondLong;

        int[] resultArray = numberIntoArray(result);

        printArray(firstNumber);
        System.out.print(" * ");
        printArray(secondNumber);
        System.out.print(" = ");
        printArray(resultArray);

    }
    private static int[] stringIntoArray(String number) {
        int n = number.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = number.charAt(i) - '0';
        }
        return arr;
    }
    private static int[] numberIntoArray(long number) {
        String str = String.valueOf(number);
        return stringIntoArray(str);
    }
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
}