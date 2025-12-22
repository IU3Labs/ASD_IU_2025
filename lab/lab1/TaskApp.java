package lab1;

import java.util.Scanner;

// Основное задание. Реализовать алгоритм бинарного поиска двумя способами.
public class TaskApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер массива:");
        int size = scanner.nextInt();
        int[] arr = fillArray(scanner, size);
        System.out.println("Введите элемент для поиска:");
        int target = scanner.nextInt();
        int idx1 = iterativeBinarySearch(arr, target);
        int idx2 = recursiveBinarySearch(arr, target, 0, arr.length - 1);
        System.out.println("Итеративный поиск: " + idx1);
        System.out.println("Рекурсивный поиск: " + idx2);
    }

    private static int[] fillArray(Scanner scanner, int size) {
        int[] arr = new int[size];
        System.out.println("Введите элементы массива по возрастанию:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    public static int iterativeBinarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    public static int recursiveBinarySearch(int[] arr, int target, int l, int r) {
        if (l > r) return -1;
        int mid = l + (r - l) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) return recursiveBinarySearch(arr, target, mid + 1, r);
        else return recursiveBinarySearch(arr, target, l, mid - 1);
    }
}

// Группа А. Задача 1. Реализуйте метод, входными данными которого являются два числа N и M, где N – число в десятичной системе исчисления, а M – число в диапазоне от 2 до 9, основание системы исчисления, в которое надо перевести исходное число. Метод должен возвращать строку с преобразованным значением.
class DecimalToBaseConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(convert(n, m));
    }

    public static String convert(int n, int m) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % m);
            n /= m;
        }
        return sb.reverse().toString();
    }
}

// Группа А. Задача 2. Реализуйте перевод из римских чисел в арабские.
class RomanToArabic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String roman = sc.next();
        System.out.println(toArabic(roman));
    }

    public static int toArabic(String roman) {
        int result = 0;
        int prev = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int val = value(roman.charAt(i));
            if (val < prev) result -= val;
            else result += val;
            prev = val;
        }
        return result;
    }

    private static int value(char r) {
        return switch (r) {
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

// Группа А. Задача 3. Изограмма – это слово, в котором нет повторяющихся букв, последовательных или непоследовательных. Реализуйте функцию, которая определяет, является ли строка, изограммой. Пустая строка является изограммой.
class IsogramChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        System.out.println(isIsogram(word));
    }

    public static boolean isIsogram(String word) {
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            for (int j = i + 1; j < word.length(); j++) {
                if (word.charAt(i) == word.charAt(j)) return false;
            }
        }
        return true;
    }
}

// Группа А. Задача 4. Дано целое число. Реализуйте метод, который находит N первых простых чисел. Используйте алгоритм «Решето Эратосфена».
class PrimeNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] primes = sieve(n);
        for (int prime : primes) {
            System.out.print(prime + " ");
        }
    }

    public static int[] sieve(int n) {
        boolean[] isPrime = new boolean[n * n];
        for (int i = 2; i < isPrime.length; i++) isPrime[i] = true;
        for (int i = 2; i * i < isPrime.length; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < isPrime.length; j += i) isPrime[j] = false;
            }
        }
        int[] result = new int[n];
        int count = 0;
        for (int i = 2; i < isPrime.length && count < n; i++) {
            if (isPrime[i]) result[count++] = i;
        }
        return result;
    }
}

// Группа Б. Задача 1. Дан целочисленный массив. Верните число, частота встречи которого в массиве равна его значению. Если таких чисел нет, вернуть «-1». Если таких чисел несколько, вернуть наибольшее.
class FrequencyEqualsValue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = sc.nextInt();
        System.out.println(findValue(arr));
    }

    public static int findValue(int[] arr) {
        int result = -1;
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == arr[i]) count++;
            }
            if (count == arr[i] && arr[i] > result) result = arr[i];
        }
        return result;
    }
}

// Группа Б. Задача 2. Пусть любое число – это массив его цифр слева направо. Дан массив целых чисел. Реализовать умножение двух чисел.
class MultiplyArrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int[] a = new int[n1];
        for (int i = 0; i < n1; i++) a[i] = sc.nextInt();
        int n2 = sc.nextInt();
        int[] b = new int[n2];
        for (int i = 0; i < n2; i++) b[i] = sc.nextInt();
        int[] res = multiply(a, b);
        for (int digit : res) System.out.print(digit);
    }

    public static int[] multiply(int[] a, int[] b) {
        int num1 = 0, num2 = 0;
        for (int j : a) num1 = num1 * 10 + j;
        for (int j : b) num2 = num2 * 10 + j;
        int prod = num1 * num2;
        String s = Integer.toString(prod);
        int[] res = new int[s.length()];
        for (int i = 0; i < s.length(); i++) res[i] = s.charAt(i) - '0';
        return res;
    }
}

