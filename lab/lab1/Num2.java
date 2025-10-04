package lab1;//Изограмма – это слово, в котором нет повторяющихся букв,
//последовательных или непоследовательных. Реализуйте функцию, которая
//определяет, является ли строка, изограммой. Пустая строка является
//изограммой.

import java.util.Scanner;

public class Num2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Введи слово: ");
        String word = s.nextLine();

        boolean res = Isogram(word);

        if (res) {
            System.out.println("'" + word + "' - изограмма");
        } else {
            System.out.println("'" + word + "' - не изограмма");
        }
        s.close();
    }

    public static boolean Isogram(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        str = str.toLowerCase();
        boolean[] seenCharacters = new boolean[69]; // Увеличили размер для цифр

        for (int i = 0; i < str.length(); i++) {
            char cChar = str.charAt(i);
            int index = -1;

            if (cChar >= 'а' && cChar <= 'я') {
                index = cChar - 'а';
            } else if (cChar >= 'a' && cChar <= 'z') {
                index = cChar - 'a' + 33;
            } else if (cChar >= '0' && cChar <= '9') {
                index = cChar - '0' + 59; // Цифры с 59 по 68 индекс
            }

            if (index != -1 && seenCharacters[index]) {
                return false;
            }

            if (index != -1) {
                seenCharacters[index] = true;
            }
        }
        return true;
    }
}