/*Реализуйте перевод из римских чисел в арабские*/

import java.util.Scanner;

public class RomanToArabicConverter {

    public static int getValue(char romanChar) {
        switch (romanChar) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    public static int romanToArabic(String roman) {
        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);
            int currentValue = getValue(currentChar);

            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Roman number: ");
        String romanNumber = scanner.nextLine().toUpperCase();

        int arabicNumber = romanToArabic(romanNumber);
        System.out.println(romanNumber + " = " + arabicNumber);

        scanner.close();
    }
}