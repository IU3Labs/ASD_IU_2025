package main.groupb;

import main.utils.ArrayUtils;
import java.util.Scanner;

/**
 * Задание:
 * Дан целочисленный массив.
 * Найти число, частота появления которого равна его значению.
 * Если таких чисел несколько — вернуть наибольшее.
 * Если таких нет — вернуть -1.
 */
public class FrequencyEqualsValue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = ArrayUtils.inputArray(scanner);

        int result = findFrequencyEqualsValue(arr);

        // просто выводим результат, включая -1
        System.out.println("Результат: " + result);

        scanner.close();
    }

    /**
     * Метод ищет число, частота которого равна его значению.
     */
    public static int findFrequencyEqualsValue(int[] arr) {
        int result = -1;

        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }
            if (count == arr[i] && arr[i] > result) {
                result = arr[i];
            }
        }

        return result;
    }
}