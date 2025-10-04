package lab1;

import java.util.Scanner;

/*
Группа В. Задание 1.
Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее.
*/

public class Task1bMain {
    public static int findNumberByFrequency(int[] array) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            int count = 0;
            int current = array[i];
            for (int j = 0; j < array.length; j++) {
                if (array[j] == current) {
                    count++;
                }
            }
            if (count == current) {
                if (current > result) {
                    result = current;
                }
            }
        }
        return result;
    }

    public static int[] inputArray(Scanner scanner) {
        System.out.print("Введите количество элементов массива: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = inputArray(scanner);
        int result = findNumberByFrequency(array);
        System.out.println("Результат: " + result);
        scanner.close();
    }
}
