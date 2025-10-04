package lab1;

import java.util.Scanner;

/*
Группа А. Задание 3.
Изограмма – это слово, в котором нет повторяющихся букв,
последовательных или непоследовательных. Реализуйте функцию, которая
определяет, является ли строка, изограммой. Пустая строка является
изограммой.
*/

public class Task3Main {
    public static boolean isIsogram(String word) {
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!Character.isLetter(currentChar)) {
                continue;
            }
            for (int j = i + 1; j < word.length(); j++) {
                if (currentChar == word.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите слово: ");
        String input = scanner.nextLine();
        boolean result = isIsogram(input);
        if (result) {
            System.out.println("Строка является изограммой.");
        } else {
            System.out.println("Строка не является изограммой.");
        }
        scanner.close();
    }
}
