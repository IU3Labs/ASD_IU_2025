package lab1;//Реализуйте перевод из римских чисел в арабские.

import java.util.Scanner;
public class Num1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Введи римское число: ");
        String rom = s.nextLine().toUpperCase();

        int res = convertRomanToArabic(rom);
        System.out.println(rom + " = " + res);
        s.close();
    }

    public static int convertRomanToArabic(String roman) {
        int res = 0;
        int prev = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char cChar = roman.charAt(i);
            int cValue = getValue(cChar);

            if (cValue < prev) {
                res -= cValue;
            } else {
                res += cValue;
            }
            prev = cValue;
        }
        return res;
    }

    public static int getValue(char romanChar) {
        return switch (romanChar) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }
}