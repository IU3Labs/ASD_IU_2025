package lab3;

import java.util.Scanner;

/**
 * Группа А
 * 5. Дан циклически сдвинутый отсортированный массив без дубликатов.
 * Требуется найти минимальный элемент за O(log N). Доказать сложность.
 * Rotated Sorted Array— повернутый массив. Это массив, который
 * изначально был отсортирован по возрастанию, а затем повёрнут от 1 до n
 * раз. Например, массив nums = [1,2,3,4,5,6] мог стать [3,4,5,6,1,2] — если
 * его повернули 4 раза.
 **/

public class RotatedSortedArrayFindMin {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int n = scanner.nextInt();

        int[] nums = new int[n];
        System.out.println("Введите элементы циклически сдвинутого массива:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        scanner.close();

        int minElement = findMinimum(nums);
        System.out.println("Минимальный элемент: " + minElement);
    }

    static int findMinimum(int[] array) {
        int left = 0;
        int right = array.length - 1;

        if (array[left] < array[right]) {
            return array[left];
        }

        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (array[mid] > array[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return array[right];
    }
}
