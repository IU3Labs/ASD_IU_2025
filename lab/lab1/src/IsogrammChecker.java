// Задача 3.
// Изограмма – это слово, в котором нет повторяющихся букв, последовательных или непоследовательных.
// Реализуйте функцию, которая определяет, является ли строка изограммой.
// Пустая строка является изограммой.

import java.util.Scanner;

public class IsogrammChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = readString(scanner);
        boolean result = isIsogram(input);
        printResult(result);
        scanner.close();
    }

    public static String readString(Scanner scanner) {
        System.out.println("Введите строку:");
        return scanner.nextLine();
    }

    public static boolean isIsogram(String s) {
        s = s.toLowerCase();
        boolean[] seen = new boolean[1104];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (seen[c]) {
                return false;
            }
            seen[c] = true;
        }
        return true;
    }

    public static void printResult(boolean result) {
        System.out.println(result);
    }
}