// Основное задание.
// Задание оценивается в 0 баллов, но является обязательным.
// Реализовать алгоритм бинарного поиска двумя способами.

package lab1.maintask;

import lab1.utils.ArrayUtils;
import java.util.Scanner;

public class MainTask {
    public static void main() {
        Scanner in = new Scanner(System.in);
        int lenArray = ArrayUtils.getLenArray(in);

        System.out.println("Input int array: ");
        int[] array = new int[lenArray];
        ArrayUtils.getIntArray(in, array, lenArray);

        ArrayUtils.bubbleSortIntArray(array);
        System.out.println("Sorted array:");
        ArrayUtils.printIntArray(array);

        System.out.print("Input find number: ");
        int inputFindNumber = in.nextInt();
        int indexFind = binaryFind(array, inputFindNumber);
        System.out.println("binaryFind: " + indexFind);

        int indexRecFind = recursionBinaryFind(array, inputFindNumber, 0, array.length - 1);
        System.out.println("recursionBinaryFind: " + indexRecFind);
    }

    public static int binaryFind(int[] array, int number) {
        if (array.length == 0) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        while (right != left) {
            int center = left + (right - left) / 2;
            if (array[center] < number) {
                left = center + 1;
            } else {
                right = center;
            }
        }
        if (array[left] == number) {
            return left;
        } else {
            return -1;
        }
    }

    public static int recursionBinaryFind(int[] array, int number, int left, int right) {
        if (array.length == 0) {
            return -1;
        }
        if (left == right) {
            if (array[left] == number) {
                return left;
            } else {
                return -1;
            }
        }
        int center = left + (right - left) / 2;
        if (array[center] < number) {
            return recursionBinaryFind(array, number, center + 1, right);
        } else {
            return recursionBinaryFind(array, number, left, center);
        }
    }
}

