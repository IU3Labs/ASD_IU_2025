import java.util.Arrays;
import java.util.Scanner;

public class ArrayMultiplication {

    private static final String CONTINUE_COMMAND = "да";
    private static final String PROGRAM_TITLE = "=== Умножение чисел-массивов ===";
    private static final int MIN_DIGIT_VALUE = 0;
    private static final int MAX_DIGIT_VALUE = 9;

    public static int[] multiplyNumbers(int[] firstNumber, int[] secondNumber) {
        validateInputArrays(firstNumber, secondNumber);
        
        if (isZero(firstNumber) || isZero(secondNumber)) {
            return new int[]{0};
        }
        
        int[] multiplicationResult = performMultiplication(firstNumber, secondNumber);
        return removeLeadingZeros(multiplicationResult);
    }
    
    private static void validateInputArrays(int[] firstArray, int[] secondArray) {
        if (firstArray == null || secondArray == null) {
            throw new IllegalArgumentException("Массивы не могут быть null");
        }
        
        validateDigits(firstArray);
        validateDigits(secondArray);
    }
    
    private static void validateDigits(int[] numberArray) {
        for (int digit : numberArray) {
            if (digit < MIN_DIGIT_VALUE || digit > MAX_DIGIT_VALUE) {
                throw new IllegalArgumentException("Некорректная цифра");
            }
        }
    }
    
    private static boolean isZero(int[] numberArray) {
        return numberArray.length == 1 && numberArray[0] == 0;
    }
    
    private static int[] performMultiplication(int[] firstNumber, int[] secondNumber) {
        int firstNumberLength = firstNumber.length;
        int secondNumberLength = secondNumber.length;
        int[] result = new int[firstNumberLength + secondNumberLength];
        
        for (int firstIndex = firstNumberLength - 1; firstIndex >= 0; firstIndex--) {
            for (int secondIndex = secondNumberLength - 1; secondIndex >= 0; secondIndex--) {
                int product = firstNumber[firstIndex] * secondNumber[secondIndex];
                int sum = product + result[firstIndex + secondIndex + 1];
                result[firstIndex + secondIndex + 1] = sum % 10;
                result[firstIndex + secondIndex] += sum / 10;
            }
        }
        
        return result;
    }
    
    private static int[] removeLeadingZeros(int[] numberArray) {
        int firstNonZeroIndex = findFirstNonZeroDigit(numberArray);
        
        if (firstNonZeroIndex == numberArray.length) {
            return new int[]{0};
        }
        
        return Arrays.copyOfRange(numberArray, firstNonZeroIndex, numberArray.length);
    }
    
    private static int findFirstNonZeroDigit(int[] numberArray) {
        int index = 0;
        while (index < numberArray.length && numberArray[index] == 0) {
            index++;
        }
        return index;
    }

    public static String arrayToString(int[] number) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int digit : number) {
            stringBuilder.append(digit);
        }
        return stringBuilder.toString();
    }

    public static int[] readNumberArrayFromUser(String numberName) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество цифр в " + numberName + ": ");
        int size = scanner.nextInt();

        while (size <= 0) {
            System.out.print("Размер должен быть положительным: ");
            size = scanner.nextInt();
        }

        int[] number = new int[size];
        System.out.println("Введите " + size + " цифр:");

        for (int index = 0; index < size; index++) {
            System.out.print("Цифра " + (index + 1) + ": ");
            int digit = scanner.nextInt();

            while (digit < MIN_DIGIT_VALUE || digit > MAX_DIGIT_VALUE) {
                System.out.print("Цифра должна быть от " + MIN_DIGIT_VALUE + " до " + MAX_DIGIT_VALUE + ": ");
                digit = scanner.nextInt();
            }

            number[index] = digit;
        }

        return number;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        System.out.println(PROGRAM_TITLE);

        while (shouldContinue) {
            try {
                int[] firstNumber = readNumberArrayFromUser("первом числе");
                int[] secondNumber = readNumberArrayFromUser("втором числе");

                System.out.println("Первое число: " + arrayToString(firstNumber));
                System.out.println("Второе число: " + arrayToString(secondNumber));

                int[] result = multiplyNumbers(firstNumber, secondNumber);
                System.out.println("Результат: " + arrayToString(result));

            } catch (IllegalArgumentException exception) {
                System.out.println("Ошибка: " + exception.getMessage());
            }

            System.out.print("Продолжить? (да/нет): ");
            String userAnswer = scanner.next();

            if (!userAnswer.equalsIgnoreCase(CONTINUE_COMMAND)) {
                shouldContinue = false;
            }
        }

        scanner.close();
    }
}
