//Дан массив целых чисел и целое число. Реализовать метод, который
//возвращает индексы тех двух чисел массива, которые дают сумму
//заданного числа. Индексы вернуть в любом порядке. Один элемент в сумме
//использовать дважды нельзя.

package groupb;

import java.util.Scanner;
import functions.ArrayUtils;

public class TwoSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = ArrayUtils.inputArrayFromUser(scanner, "");

        System.out.println("Введите целевую сумму:");
        int target = scanner.nextInt();

        int[] result = findTwoSum(array, target);

        if (result[0] != -1) {
            System.out.println("Индексы: " + result[0] + ", " + result[1]);
        } else {
            System.out.println("Пары не найдено");
        }

        scanner.close();
    }

    public static int[] findTwoSum(int[] array, int target) {
        int[] sortedArray = ArrayUtils.copyArray(array);
        ArrayUtils.sortArray(sortedArray);

        int left = 0;
        int right = sortedArray.length - 1;

        while (left < right) {
            int sum = sortedArray[left] + sortedArray[right];

            if (sum == target) {
                return findOriginalIndices(array, sortedArray[left], sortedArray[right]);
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1};
    }

    private static int[] findOriginalIndices(int[] original, int value1, int value2) {
        int index1 = -1;
        int index2 = -1;

        for (int i = 0; i < original.length; i++) {
            if (index1 == -1 && original[i] == value1) {
                index1 = i;
            } else if (index2 == -1 && original[i] == value2) {
                index2 = i;
            }
        }

        if (index1 == -1 || index2 == -1) {
            for (int i = 0; i < original.length; i++) {
                if (index1 == -1 && original[i] == value2) {
                    index1 = i;
                } else if (index2 == -1 && original[i] == value1) {
                    index2 = i;
                }
            }
        }

        return new int[]{index1, index2};
    }
}