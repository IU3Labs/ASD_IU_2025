import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleRomanConverter {

    private static final Map<Character, Integer> ROMAN_NUMERALS = createRomanNumeralsMap();
    private static final String EXIT_COMMAND = "выход";

    private static Map<Character, Integer> createRomanNumeralsMap() {
        Map<Character, Integer> numerals = new HashMap<>();
        numerals.put('I', 1);
        numerals.put('V', 5);
        numerals.put('X', 10);
        numerals.put('L', 50);
        numerals.put('C', 100);
        numerals.put('D', 500);
        numerals.put('M', 1000);
        return numerals;
    }

    public static int romanToArabic(String romanNumber) {
        validateInput(romanNumber);

        String uppercaseRoman = romanNumber.toUpperCase();
        return convertRomanToArabic(uppercaseRoman);
    }

    private static void validateInput(String romanNumber) {
        if (romanNumber == null || romanNumber.isEmpty()) {
            throw new IllegalArgumentException("Римское число не может быть пустым");
        }
    }

    private static int convertRomanToArabic(String romanNumber) {
        int arabicNumber = 0;
        int previousValue = 0;

        for (int index = romanNumber.length() - 1; index >= 0; index--) {
            char currentSymbol = romanNumber.charAt(index);
            validateRomanSymbol(currentSymbol);

            int currentValue = ROMAN_NUMERALS.get(currentSymbol);
            
            if (currentValue < previousValue) {
                arabicNumber -= currentValue;
            } else {
                arabicNumber += currentValue;
            }

            previousValue = currentValue;
        }

        return arabicNumber;
    }

    private static void validateRomanSymbol(char symbol) {
        if (!ROMAN_NUMERALS.containsKey(symbol)) {
            throw new IllegalArgumentException("Недопустимый символ: " + symbol);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Простой конвертер римских чисел ===");

        while (true) {
            System.out.print("\nВведите римское число (или '" + EXIT_COMMAND + "' для завершения): ");
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase(EXIT_COMMAND)) {
                System.out.println("Программа завершена!");
                break;
            }

            try {
                int result = romanToArabic(userInput);
                System.out.println("Результат: " + userInput.toUpperCase() + " = " + result);
            } catch (IllegalArgumentException exception) {
                System.out.println("Ошибка: " + exception.getMessage());
            }
        }

        scanner.close();
    }
}
