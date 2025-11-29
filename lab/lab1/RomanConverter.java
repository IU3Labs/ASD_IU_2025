import java.util.Scanner;

public class RomanConverter {

    private static final Scanner SCANNER = new Scanner(System.in);


    private static String readString(String prompt) {
        System.out.print(prompt);
        // Используем nextLine(), чтобы считать всю строку, включая пробелы.
        return SCANNER.nextLine();
    }

    private static int getRomanValue(char romanChar) {
        switch (romanChar) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0; // Неверный символ (например, арабская цифра)
        }
    }

    public static int romanToArabic(String roman) {
        if (roman == null || roman.isEmpty()) {
            return 0;
        }

        String upperRoman = roman.toUpperCase();

        for (int i = 0; i < upperRoman.length(); i++) {
            if (getRomanValue(upperRoman.charAt(i)) == 0) {
                return -1;
            }
        }

        int result = 0;
        int prevValue = 0;

        for (int i = upperRoman.length() - 1; i >= 0; i--) {
            int currentValue = getRomanValue(upperRoman.charAt(i));

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
        System.out.println("--- Группа А, Задача 2: Римские числа в Арабские ---");

        String inputRoman;
        int arabicNumber;

        while (true) {
            inputRoman = readString("Введите римское число (например, MCMXCIV): ");

            if (inputRoman.trim().isEmpty()) {
                System.out.println("Ошибка: Введите непустую строку.");
                continue;
            }

            arabicNumber = romanToArabic(inputRoman);

            if (arabicNumber != -1) {
                break;
            } else {
                System.out.println("Ошибка: Введенная строка содержит недопустимые символы или арабские цифры.");
                System.out.println("Пожалуйста, используйте только римские цифры: I, V, X, L, C, D, M.");
            }
        }

        System.out.println("\n--- Результат ---");
        System.out.println("Римское число: " + inputRoman.toUpperCase());
        System.out.println("Арабское число: " + arabicNumber);
    }
}