import java.util.Scanner;
//2 Реализуйте перевод из римских чисел в арабские.
public class ArabicConverter {

    private static int romanToValue(char romanChar) {
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

    public static int convertRomanToArabic(String romanNumber) {
        int result = 0;
        for (int i = 0; i < romanNumber.length();) {
            if (romanNumber.length() > i + 1
                    && romanToValue(romanNumber.charAt(i)) < romanToValue(romanNumber.charAt(i + 1))) {
                result += romanToValue(romanNumber.charAt(i + 1)) - romanToValue(romanNumber.charAt(i));
                i += 2;
            } else {
                result += romanToValue(romanNumber.charAt(i));
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число римскими цифрами: ");
        String romanInput = scanner.nextLine().toUpperCase();

        int arabicNumber = convertRomanToArabic(romanInput);
        System.out.println("Число в арабской системе: " + arabicNumber);
    }
}