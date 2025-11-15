/*
Пусть любое число – это массив его цифр слева направо. Пример, число
1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
Результат – число, представленное массивом.
 */
import java.util.Scanner;
import utils.ArrayUtils;

public class Task3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первое число (как массив цифр):");
        int[] number1 = ArrayUtils.readArrayFromUser(scanner);

        System.out.println("Введите второе число (как массив цифр):");
        int[] number2 = ArrayUtils.readArrayFromUser(scanner);

        System.out.print("Первое число: ");
        ArrayUtils.printArray(number1);
        System.out.print("Второе число: ");
        ArrayUtils.printArray(number2);

        int[] result = multiplyNumberArrays(number1, number2);
        System.out.print("Результат умножения: ");
        ArrayUtils.printArray(result);

        scanner.close();
    }

    public static int[] multiplyNumberArrays(int[] num1, int[] num2) {
        int number1 = arrayToInt(num1);
        int number2 = arrayToInt(num2);

        int result = number1 * number2;

        return intToArray(result);
    }

    private static int arrayToInt(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result = result * 10 + array[i];
        }
        return result;
    }

    private static int[] intToArray(int number) {
        if (number == 0) {
            return new int[]{0};
        }

        int temp = number;
        int length = 0;
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
}