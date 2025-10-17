package tasks;
import java.util.Scanner;
/* задание Б2 : Пусть любое число – это массив его цифр слева направо. Пример, число
1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
Результат – число, представленное массивом*/

public class TaskB2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] firstNumberArray = NumberArray(scanner, "первого");
        int[] secondNumberArray = NumberArray(scanner, "второго");

        System.out.print("Первое число: ");
        printArray(firstNumberArray);
        System.out.print("Второе число: ");
        printArray(secondNumberArray);

        int[] result = multiply(firstNumberArray, secondNumberArray);

        System.out.print("Результат умножения: ");
        printArray(result);


    }

    private static int[] NumberArray(Scanner scanner, String numberName) {
        System.out.print("Введите количество цифр для " + numberName + " числа: ");
        int size = scanner.nextInt();

        int[] array = new int[size];
        System.out.println("Введите " + size + " цифр для " + numberName + " числа:");

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        return array;
    }

    private static int[] multiply(int[] firstArray, int[] secondArray) {
        int firstNumber = ArrayToNumber(firstArray);
        int secondNumber = ArrayToNumber(secondArray);

        int result = firstNumber * secondNumber;

        return NumberToArray(result);
    }

    private static int ArrayToNumber(int[] array) {
        int number = 0;
        for (int i = 0; i < array.length; i++) {
            number = number * 10 + array[i];
        }
        return number;
    }

    private static int[] NumberToArray(int number) {
        if (number == 0) {
            return new int[]{0};
        }

        int temp = number;
        int digitCount = 0;

        while (temp > 0) {
            digitCount++;
            temp /= 10;
        }

        int[] array = new int[digitCount];
        temp = number;

        for (int i = digitCount - 1; i >= 0; i--) {
            array[i] = temp % 10;
            temp /= 10;
        }

        return array;
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
    }
}
