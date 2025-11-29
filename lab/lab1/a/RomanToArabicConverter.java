//2 Реализуйте перевод из римских чисел в арабские.

package a;
import java.util.Scanner;

public class RomanToArabicConverter {
    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        System.out.print("Введите римское число: ");
        String romanNumber = scanner.nextLine().toUpperCase();

        int arabicNumber = romanToArabic(romanNumber);
        System.out.println("Римское число " + romanNumber + " = " + arabicNumber);

        scanner.close();
    }

    public static int romanToArabic(String romanNumber) {
        int result = 0;
        int prevValue = 0;

        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            char currentChar = romanNumber.charAt(i);
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

    private static int getValue(char romanChar) {
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
}
