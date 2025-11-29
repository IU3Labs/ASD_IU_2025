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
        System.out.println("Первый массив:");
        int firstSize = ArrayUtil.sizeArray(scanner);
        int[] firstArray = ArrayUtil.inputArray(scanner, firstSize);

        System.out.println("Второй массив:");
        int secondSize = ArrayUtil.sizeArray(scanner);
        int[] secondArray = ArrayUtil.inputArray(scanner, secondSize);

        int[] result = multiplyArrays(firstArray, secondArray);

        ArrayUtil.printArray(firstArray);
        System.out.print(" * ");
        ArrayUtil.printArray(secondArray);
        System.out.print(" = ");
        ArrayUtil.printArray(result);
    }

    public static int arrayNumber(int[] numberArray) {
        int number = 0;
        for (int i = 0; i < numberArray.length; i++) {
            number = number * 10 + numberArray[i];
        }
        return number;
    }
    public static int[] numberToArray(long number) {
        if (number == 0) {
            return new int[]{0};
        }
        long temp = number;
        int count = 0;
        while (temp > 0) {
            temp = temp / 10;
            count++;
        }
        int[] digits = new int[count];
        temp = number;
        for (int i = count - 1; i >= 0; i--) {
            digits[i] = (int) (temp % 10);
            temp = temp / 10;
        }
        return digits;
    }

    public static int[] multiplyArrays(int[] firstArray, int[] secondArray) {
        int firstNumber = arrayNumber(firstArray);
        int secondNumber = arrayNumber(secondArray);
        long result = (long) firstNumber * secondNumber;
        return numberToArray(result);
    }
}

