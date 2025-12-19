import java.util.Arrays;
import java.util.Scanner;

public class PositiveNumbersSumWithInput {

    private static final int MIN_ARRAY_SIZE = 5;
    private static final int MIN_POSITIVE_NUMBERS = 2;

    public static int findSumOfTwoSmallestPositive(int[] numbers) {
        validateArray(numbers);
        
        int[] positiveNumbers = filterPositiveNumbers(numbers);
        validatePositiveNumbersCount(positiveNumbers.length);
        
        Arrays.sort(positiveNumbers);
        
        return positiveNumbers[0] + positiveNumbers[1];
    }

    private static void validateArray(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }
        
        if (numbers.length < MIN_ARRAY_SIZE) {
            throw new IllegalArgumentException(
                "Массив должен содержать минимум " + MIN_ARRAY_SIZE + " элементов"
            );
        }
    }

    private static void validatePositiveNumbersCount(int count) {
        if (count < MIN_POSITIVE_NUMBERS) {
            throw new IllegalArgumentException(
                "В массиве должно быть хотя бы " + MIN_POSITIVE_NUMBERS + " положительных числа"
            );
        }
    }

    private static int[] filterPositiveNumbers(int[] numbers) {
        int positiveCount = countPositiveNumbers(numbers);
        return extractPositiveNumbers(numbers, positiveCount);
    }

    private static int countPositiveNumbers(int[] numbers) {
        int count = 0;
        for (int number : numbers) {
            if (number > 0) {
                count++;
            }
        }
        return count;
    }

    private static int[] extractPositiveNumbers(int[] numbers, int positiveCount) {
        int[] positiveNumbers = new int[positiveCount];
        int index = 0;
        
        for (int number : numbers) {
            if (number > 0) {
                positiveNumbers[index] = number;
                index++;
            }
        }
        
        return positiveNumbers;
    }

    public static int[] readArrayFromUser() {
        Scanner scanner = new Scanner(System.in);
        
        try {
            int size = readArraySize(scanner);
            return readArrayElements(scanner, size);
        } finally {
            scanner.close();
        }
    }

    private static int readArraySize(Scanner scanner) {
        System.out.print("Введите количество элементов в массиве (минимум " + MIN_ARRAY_SIZE + "): ");
        int size = scanner.nextInt();
        
        while (size < MIN_ARRAY_SIZE) {
            System.out.print(
                "Массив должен содержать минимум " + MIN_ARRAY_SIZE + " элементов. Повторите ввод: "
            );
            size = scanner.nextInt();
        }
        
        return size;
    }

    private static int[] readArrayElements(Scanner scanner, int size) {
        int[] numbers = new int[size];
        
        System.out.println("Введите " + size + " целых чисел:");
        
        for (int i = 0; i < size; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }
        
        return numbers;
    }

    public static void displayArray(int[] numbers) {
        System.out.print("Ваш массив: [");
        
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);
            
            if (i < numbers.length - 1) {
                System.out.print(", ");
            }
        }
        
        System.out.println("]");
    }

    public static void main(String[] args) {
        System.out.println("Программа для поиска суммы двух наименьших положительных чисел");
        
        try {
            int[] numbers = readArrayFromUser();
            displayArray(numbers);
            
            int result = findSumOfTwoSmallestPositive(numbers);
            
            System.out.println("\nРезультат:");
            System.out.println("Сумма двух наименьших положительных чисел: " + result);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }
}
