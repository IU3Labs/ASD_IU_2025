/*
Задача.
Реализовать метод, входными данными которого являются два числа N и M,
где N – число в десятичной системе исчисления, а M – число в диапазоне от 2 до 9,
основание системы исчисления, в которое надо перевести исходное число.
Метод должен возвращать строку с преобразованным значением.
*/

import java.util.Scanner;

public class Task2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Число N (в 10-ой): ");
        int n = sc.nextInt();

        System.out.print("Основание M (от 2 до 9): ");
        int m = sc.nextInt();

        String result = convert(n, m);
        System.out.println("Результат: " + result);
    }
    private static String convert(int n, int m) {
        if (m < 2 || m > 9) {
            return "Основание должно быть от 2 до 9";
        }
        if (n == 0) {
            return "0";
        }
        String result = "";
        int number = n;
        while (number > 0) {
            int remainder = number % m;
            result = remainder + result;
            number = number / m;
        }
        return result;
    }
}
