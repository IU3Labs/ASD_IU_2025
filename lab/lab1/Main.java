import utils.InputManager;
import utils.ArrayUtils;
import utils.OutputUtils;
import algorithms.BinarySearch;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = InputManager.getScanner();
        System.out.println("Введите кол-во чисел в массиве: ");
        int size = InputManager.inputTarget(scan);
        int[] array = new int[size];
        ArrayUtils.fillArray(array, scan);
        System.out.println("Введите искомое число: ");
        int target = InputManager.inputTarget(scan);
        System.out.println("1 - Итерационный метод. 2 - Рекурсивный метод: ");
        if (InputManager.inputTarget(scan) == 1) {
            OutputUtils.printSearchResult(target, BinarySearch.iterativeSearch(bubbleSort(array), target));

        } else {
            OutputUtils.printSearchResult(target, BinarySearch.recursiveSearch(bubbleSort(array), target));
        }
    }

    private static int[] bubbleSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
        return array;
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}

