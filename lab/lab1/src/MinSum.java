// Группа Б. Номер 3
// Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
// число, которое является суммой двух наименьших положительных чисел.

import java.util.Scanner;

public class MinSum {
    public static void main() {
        Scanner scanner = new Scanner(System.in);

        int[] array = Utils.createArray(scanner);
        Utils.quickSort(array, 0, array.length - 1);

        int result = findMinSum(array);

        if (result == -1) {
            System.out.println("Given array contains less than 2 positive numbers");
        } else {
            System.out.printf("Minimum sum is %d", result);
        }
    }

    private static int findMinSum(int[] array) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                if ((i + 1) <= array.length - 1) {
                    result = array[i] + array[i+1];
                    break;
                }
            }
        }
        return result;
    }
}
