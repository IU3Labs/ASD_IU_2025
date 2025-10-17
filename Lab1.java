import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/** Главный класс приложения. */
public class Lab1 {

    /** Точка входа в приложение. */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Создание и обработка массива
        ArrayProcessor arrayProcessor = new ArrayProcessor(scanner);
        arrayProcessor.processArray();

        // Обработка строковых операций
        StringProcessor stringProcessor = new StringProcessor(scanner);
        stringProcessor.processStringOperations();

        scanner.close();
    }
}

/** Класс для обработки операций с массивом. */
class ArrayProcessor {
    private final Scanner scanner;
    private int[] array;

    public ArrayProcessor(Scanner scanner) {
        this.scanner = scanner;
    }

    /** Основной метод обработки массива. */
    public void processArray() {
        createAndSortArray();
        performBinarySearches();
        findSpecialNumber();
        calculateSumOfTwoMinPositives();
    }

    private void createAndSortArray() {
        System.out.print("Размер массива (минимум 5): ");
        int size = scanner.nextInt();
        array = new int[size];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        Arrays.sort(array);
    }

    private void performBinarySearches() {
        System.out.print("Число для поиска: ");
        int target = scanner.nextInt();

        BinarySearch binarySearch = new BinarySearch();

        // Итеративный поиск
        int iterativeIndex = binarySearch.searchIterative(array, target);
        System.out.println("Итеративный поиск: " +
                (iterativeIndex == -1 ? "не найдено" : "найдено на индексе " + iterativeIndex));

        // Рекурсивный поиск
        int recursiveIndex = binarySearch.searchRecursive(array, target, 0, array.length - 1);
        System.out.println("Рекурсивный поиск: " +
                (recursiveIndex == -1 ? "не найдено" : "найдено на индексе " + recursiveIndex));
    }

    private void findSpecialNumber() {
        SpecialNumberFinder finder = new SpecialNumberFinder();
        int result = finder.findSpecialNumber(array);
        System.out.println("Число с частотой = значению: " + result);
    }

    private void calculateSumOfTwoMinPositives() {
        PositiveNumberCalculator calculator = new PositiveNumberCalculator();
        int result = calculator.sumTwoMinPositiveNumbers(array);
        System.out.println("Сумма двух наименьших положительных: " + result);
    }
}

/** Класс для обработки строковых операций. */
class StringProcessor {
    private final Scanner scanner;

    public StringProcessor(Scanner scanner) {
        this.scanner = scanner;
    }

    /** Основной метод обработки строковых операций. */
    public void processStringOperations() {
        processIsogram();
        processRomanNumber();
    }

    private void processIsogram() {
        scanner.nextLine(); // Очистка буфера

        System.out.print("Введите слово для изограммы: ");
        String word = scanner.nextLine();

        if (word.chars().allMatch(Character::isLetter)) {
            IsogramChecker checker = new IsogramChecker();
            boolean isIsogram = checker.isIsogram(word);
            System.out.println("Изограмма: " + (isIsogram ? "да" : "нет"));
        } else {
            System.out.println("Слово содержит не только буквы.");
        }
    }

    private void processRomanNumber() {
        System.out.print("Введите римское число: ");
        String roman = scanner.nextLine();

        RomanNumberConverter converter = new RomanNumberConverter();
        int arabicNumber = converter.romanToArabic(roman);
        System.out.println("Арабское число: " + arabicNumber);
    }
}

/** Класс для выполнения бинарного поиска. */
class BinarySearch {

    /** Выполняет бинарный поиск итеративным методом. */
    public int searchIterative(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (array[middle] == target) {
                return middle;
            } else if (array[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    /** Выполняет бинарный поиск рекурсивным методом. */
    public int searchRecursive(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middle = (left + right) / 2;
        if (array[middle] == target) {
            return middle;
        } else if (array[middle] < target) {
            return searchRecursive(array, target, middle + 1, right);
        } else {
            return searchRecursive(array, target, left, middle - 1);
        }
    }
}

/** Класс для поиска числа, частота которого равна его значению. */
class SpecialNumberFinder {

    /** Находит число, частота которого равна его значению. */
    public int findSpecialNumber(int[] array) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int number : array) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }

        int result = -1;
        for (int number : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(number);
            if (frequency == number && number > result) {
                result = number;
            }
        }
        return result;
    }
}

/** Класс для работы с положительными числами. */
class PositiveNumberCalculator {

    /** Вычисляет сумму двух наименьших положительных чисел. */
    public int sumTwoMinPositiveNumbers(int[] array) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int number : array) {
            if (number > 0) {
                if (number < min1) {
                    min2 = min1;
                    min1 = number;
                } else if (number < min2) {
                    min2 = number;
                }
            }
        }

        return (min1 == Integer.MAX_VALUE || min2 == Integer.MAX_VALUE) ? -1 : min1 + min2;
    }
}

/** Класс для проверки изограмм. */
class IsogramChecker {

    /** Проверяет, является ли слово изограммой. */
    public boolean isIsogram(String word) {
        String lowerCaseWord = word.toLowerCase();
        Set<Character> characterSet = new HashSet<>();

        for (char character : lowerCaseWord.toCharArray()) {
            if (!characterSet.add(character)) {
                return false;
            }
        }
        return true;
    }
}

/** Класс для конвертации римских чисел в арабские. */
class RomanNumberConverter {
    private static final Map<Character, Integer> ROMAN_VALUES = Map.of(
            'I', 1, 'V', 5, 'X', 10, 'L', 50,
            'C', 100, 'D', 500, 'M', 1000
    );

    /** Конвертирует римское число в арабское. */
    public int romanToArabic(String roman) {
        String upperCaseRoman = roman.toUpperCase();
        int result = 0;
        int previousValue = 0;

        for (int i = upperCaseRoman.length() - 1; i >= 0; i--) {
            char currentChar = upperCaseRoman.charAt(i);
            int currentValue = ROMAN_VALUES.getOrDefault(currentChar, 0);

            if (currentValue < previousValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            previousValue = currentValue;
        }
        return result;
    }
}