package tasks;

import utils.ArrayUtils;

public class RotatedArrayMinFinder {
    public static void main(String[] args) {
        int[] array = ArrayUtils.inputArray();
        int min = findMinInRotatedArray(array);
        System.out.println("Минимальный элемент: " + min);
    }


    public static int findMinInRotatedArray(int[] array) {
        int left = 0;
        int right = array.length - 1;
        if (array[left] < array[right]) {
            return array[0];
        }
        while (right - left > 1) {
            int middle = left + (right - left) / 2;
            if (array[left] < array[middle]) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return array[right];
    }
}

