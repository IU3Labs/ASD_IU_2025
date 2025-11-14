/*
Задание:
    Пусть любое число – это массив его цифр слева направо. Пример, число
1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
 */
import java.util.Scanner;
import java.util.Arrays;

public class Multiplication {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        int[] number1 = input(scan, "первого");

        int[] number2 = input(scan, "второго");


        double num1 = ArrayToNumber(number1);
        double num2 = ArrayToNumber(number2);


        double answer = multiply(num1, num2);

        int[] Arr_Answer = NumberToArray(answer);
        System.out.println("Результат в виде массива: " + Arrays.toString(Arr_Answer));

        scan.close();
    }


    public static int[] input(Scanner scan, String numberName) {
        System.out.print("Введите длину " + numberName + " числа: ");
        int len = scan.nextInt();
        int[] number = new int[len];

        for (int i = 0; i < len; i++) {
            System.out.print("Введите " + (i + 1) + "-й элемент числа: ");
            number[i] = scan.nextInt();
        }

        return number;
    }


    public static double ArrayToNumber(int[] numberArray) {
        double num = 0;
        int degree = 0;

        for (int i = numberArray.length - 1; i >= 0; i--) {
            num = num + numberArray[i] * Math.pow(10, degree);
            degree += 1;
        }

        return num;
    }


    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    public static int[] NumberToArray(double answer) {
        double temp = answer;
        int count = 0;


        while (temp >= 1) {
            count++;
            temp = Math.floor(temp / 10);
        }


        int[] Arr_Answer = new int[count];

        temp = answer;
        for (int i = count - 1; i >= 0; i--) {
            Arr_Answer[i] = (int)(temp % 10);
            temp = Math.floor(temp / 10);
        }

        return Arr_Answer;
    }
}