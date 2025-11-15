/*
Дан массив целых чисел. Минимальное количество элементов – 5. Вернуть
число, которое является суммой двух наименьших положительных чисел.
 */

import java.util.Scanner;
import utils.ArrayUtils;

public class Task4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = ArrayUtils.readArrayFromUser(scanner);

        if (array.length < 5) {
            System.out.println("Ошибка: массив должен содержать минимум 5 элементов");
            scanner.close();
            return;
        }

        System.out.print("Исходный массив: ");
        ArrayUtils.printArray(array);

        int result = sumOfTwoSmallestPositiveNumbers(array);
        System.out.println("Сумма двух наименьших положительных чисел: " + result);

        scanner.close();
    }

    public static int sumOfTwoSmallestPositiveNumbers(int[] array) {
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                if (array[i] < firstMin) {
                    secondMin = firstMin;
                    firstMin = array[i];
                } else if (array[i] < secondMin && array[i] >= firstMin) {
                    secondMin = array[i];
                }
            }
        }

        if (secondMin == Integer.MAX_VALUE) {
            System.out.println("В массиве недостаточно положительных чисел");
            return -1;
        }

        return firstMin + secondMin;
    }

}
