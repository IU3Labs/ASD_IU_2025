// Группа А, Задача 2
// Реализуйте перевод из римских чисел в арабские.

import java.util.Scanner;

public class RomanToArabic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String roman = readString(scanner);
        int arabicvalue = convertRomanToArabic(roman);
        printResult(arabicvalue);
        scanner.close();
    }


    private static String readString(Scanner scanner) {
        System.out.println("Введите римское число");
        return scanner.nextLine();
    }


    private static int convertRomanToArabic(String roman) {

        if (roman.isEmpty()) {
            return 0;
        }

        char[] symbols = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] values = {1, 5, 10, 50, 100, 500, 1000};

        int total = 0;
        int prevNum = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char letter = roman.charAt(i);
            int value = getValue(symbols, values, letter);

            if (value < prevNum) {
                total -= value;
            } else {
                total += value;
            }
            prevNum = value;
        }

        return total;
    }


    private static int getValue(char[] symbols, int[] values, char letter) {

        for (int i = 0; i <= symbols.length - 1; i++) {
            if (letter == symbols[i]) {
                return values[i];
            }
        }
        return -1;
    }


    private static void printResult(int arabicvalue) {
        if (arabicvalue < 0) {
            System.out.println("Введено некорректное число");
        } else {
            System.out.println(arabicvalue);
        }
    }
}
