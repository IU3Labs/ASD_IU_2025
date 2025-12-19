import java.util.Scanner;
//Пусть любое число – это массив его цифр слева направо. Пример, число
//1234 – это массив [1,2,3,4].
//Дан массив целых чисел. Реализовать умножение двух чисел.
//Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//Результат – число, представленное массивом.
public class ArrayMultiplication {

    public static int[] multiplyArrays(int[] num1, int[] num2) {
        int n1 = num1.length;
        int n2 = num2.length;
        int[] result = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {
            int carry = 0;
            for (int j = n2 - 1; j >= 0; j--) {
                int sum = num1[i] * num2[j] + result[i + j + 1] + carry;
                result[i + j + 1] = sum % 10;
                carry = sum / 10;
            }
            result[i] += carry;
        }

        int start = 0;
        while (start < result.length - 1 && result[start] == 0) {
            start++;
        }

        int[] trimmedResult = new int[result.length - start];
        for (int i = start; i < result.length; i++) {
            trimmedResult[i - start] = result[i];
        }

        return trimmedResult;
    }

    private static int[] stringToDigitsArray(String numberStr) {
        int[] digits = new int[numberStr.length()];
        for (int i = 0; i < numberStr.length(); i++) {
            digits[i] = numberStr.charAt(i) - '0';
        }
        return digits;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первое число: ");
        String firstNumberStr = scanner.nextLine();

        System.out.println("Введите второе число: ");
        String secondNumberStr = scanner.nextLine();

        int[] number1 = stringToDigitsArray(firstNumberStr);
        int[] number2 = stringToDigitsArray(secondNumberStr);

        int[] product = multiplyArrays(number1, number2);

        System.out.print("Результат умножения: ");
        for (int digit : product) {
            System.out.print(digit);
        }
        System.out.println();
    }
}