// Реализуйте перевод из римских чисел в арабские.
import java.util.Scanner;

public class TaskA2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String roman = sc.nextLine();
        System.out.println(convertRoman(roman));
    }

    // переводим буквы в числа
    private static int getRomanValue(char romanChar) {
        return switch (romanChar) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> throw new IllegalArgumentException("Недопустимый символ");
        };
    }

    // составляем арабское число
    public static int convertRoman(String romanNumber) {
        if (romanNumber == null || romanNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Пустая строка");
        }

        String roman = romanNumber.toUpperCase().trim();
        int result = 0;
        int prevValue = 0;

        // идем справа налево и складываем числа
        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);
            int currentValue = getRomanValue(currentChar);
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            prevValue = currentValue;
        }

        if (result <= 0) {
            throw new IllegalArgumentException("Некорректное римское число");
        }
        return result;
    }

}
