package lab1.a;

/*Реализуйте перевод из римских чисел в арабские.*/

import java.util.Scanner;

public class RomanConvertArabic {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String romanNumber = input.next();
        System.out.println(convert(romanNumber));
    }

    static int convert(String romanNumber) {
        int result = 0;
        int prevalue = 0;
        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            int currentvalue = convertSymbol(romanNumber.charAt(i));
            if (currentvalue < prevalue) {
                result -= currentvalue;
            } else {
                result += currentvalue;
            }
            prevalue = currentvalue;
        }
        return result;
    }

    static int convertSymbol(char symbol) {
        return switch (symbol) {
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