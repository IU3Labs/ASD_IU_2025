package lab1;

import lab1.algorithms.BinarSearch;
import lab1.utils.InputManager;
import lab1.utils.ArrayUtils;
import lab1.utils.OutputUtils;

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
        int[] sortedArray = ArrayUtils.bubbleSort(array);

        if (InputManager.inputTarget(scan) == 1) {
            OutputUtils.printSearchResult(target, BinarSearch.iterativeSearch(sortedArray, target));
        } else {
            OutputUtils.printSearchResult(target, BinarSearch.recursiveSearch(sortedArray, target));
        }
    }
}

