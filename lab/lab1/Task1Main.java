package lab1;

import java.util.Scanner;

/*
Группа А. Задача 1.
Реализуйте метод, входными данными которого являются два числа N и M,
где N – число в десятичной системе исчисления, а M – число в диапазоне от
2 до 9, основание системы исчисления, в которое надо перевести исходное
9 число. Метод должен возвращать строку с преобразованным значением.
*/

public class Task1Main {
    public static String convertToBase(int n, int m) {
        if (m < 2 || m > 9) {
            return "Ошибка.";
        }
        if (n == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        int number = Math.abs(n);
        while (number > 0) {
            int remainder = number % m;
            result.insert(0, remainder);
            number = number / m;
        }
        if (n < 0) {
            result.insert(0, "-");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число N (в десятичной системе): ");
        int n = scanner.nextInt();
        System.out.print("Введите основание системы M (от 2 до 9): ");
        int m = scanner.nextInt();
        String converted = convertToBase(n, m);
        System.out.println("Результат: " + converted);
        scanner.close();
    }
}
