/*
Группа Б, задание 1
Дан целочисленный массив. Верните число, частота встречи которого в массиве равна его значению.
 Если таких чисел нет, вернуть «-1». Если таких чисел несколько, вернуть наибольшее.
 */

import java.util.Scanner;

public class FrequentNumberFinder {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] array = ArrayTools.createArray();
        int result= finder(array);
        if (result == -1) {
            System.out.println("Результат: -1 (подходящих чисел не найдено)");
        } else {
            System.out.println("Число, частота встречи которого в массиве равна его значению: \n" + result);
        }
    }

    public static int finder(int[] array) {
        ArrayTools.bubbleSort(array);
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            int count = 0;
            int currentNumber = array[i];
            for (int j = 0; j < array.length; j++) {
                if (array[j] == currentNumber) {
                    count++;
                }
            }
            if (currentNumber == count && currentNumber > result) {
                result = currentNumber;

            }
        }
        return result;
    }
}


