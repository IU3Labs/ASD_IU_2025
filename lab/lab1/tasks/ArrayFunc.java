package tasks;

import java.util.Scanner;

public class ArrayFunc {
    public static void sortArray(int[] array) {
        int n = array.length;

        for(int i = 0; i < n - 1; ++i) {
            for(int j = 0; j < n - i - 1; ++j) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

    }

    public static int[] getSortedArray(Scanner scanner) {
        System.out.print("Введите количество элементов в массиве: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Введите " + size + " элементов массива:");

        for(int i = 0; i < size; ++i) {
            array[i] = scanner.nextInt();
        }

        sortArray(array);
        return array;
    }

    public static void printArray(int[] array) {
        for(int i = 0; i < array.length; ++i) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }

    }

    public static long arrayToLong(int[] array) {
        long number = 0L;

        for(int digit : array) {
            number = number * 10L + (long)digit;
        }

        return number;
    }

    public static int[] longToArray(long number) {
        if (number == 0L) {
            return new int[]{0};
        } else {
            String str = Long.toString(number);
            int[] array = new int[str.length()];

            for(int i = 0; i < str.length(); ++i) {
                array[i] = str.charAt(i) - 48;
            }

            return array;
        }
    }

    public static int[] numberArray(Scanner scanner, String numberName) {
        System.out.print("Введите количество цифр для " + numberName + " числа: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Введите " + size + " цифр для " + numberName + " числа:");

        for(int i = 0; i < size; ++i) {
            array[i] = scanner.nextInt();
        }

        return array;
    }

    public static int[] readArray() {
        Scanner scanner = new Scanner(System.in);

        int size;
        do {
            System.out.print("Введите размер массива (минимум 5 элементов): ");
            size = scanner.nextInt();
            if (size < 5) {
                System.out.println("Массив должен содержать минимум 5 элементов.");
            }
        } while(size < 5);

        int[] array = new int[size];
        System.out.print("Введите " + size + " целых чисел: ");

        for(int i = 0; i < size; ++i) {
            array[i] = scanner.nextInt();
        }

        return array;
    }
}
