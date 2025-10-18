//Дан массив целых чисел. Минимальное количество элементов – 5. Вернуть
//число, которое является суммой двух наименьших положительных чисел.

package groupb;

import java.util.Scanner;
import functions.ArrayUtils;

public class SumTwoSmallest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество элементов массива (минимум 5):");
        int n = scanner.nextInt();

        if (n < 5) {
            System.out.println("Массив должен содержать минимум 5 элементов");
            return;
        }

        int[] array = ArrayUtils.inputArrayFromUser(scanner, "");
        int result = sumTwoSmallestPositive(array);

        System.out.println("Сумма двух наименьших положительных чисел: " + result);

        scanner.close();
    }

    public static int sumTwoSmallestPositive(int[] array) {
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                if (array[i] < firstMin) {
                    secondMin = firstMin;
                    firstMin = array[i];
                } else if (array[i] < secondMin) {
                    secondMin = array[i];
                }
            }
        }

        if (firstMin == Integer.MAX_VALUE || secondMin == Integer.MAX_VALUE) {
            return -1;
        }

        return firstMin + secondMin;
    }
}