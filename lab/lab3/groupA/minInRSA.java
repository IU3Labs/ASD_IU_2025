package groupA;

/*5.  Дан циклически сдвинутый отсортированный массив без дубликатов.
Требуется найти минимальный элемент за O(log N). Доказать сложность.
Rotated Sorted Array— повернутый массив. Это массив, который
изначально был отсортирован по возрастанию, а затем повёрнут от 1 до n
раз. Например, массив nums = [1,2,3,4,5,6] мог стать [3,4,5,6,1,2] — если
его повернули 4 раза. */

import java.util.Scanner;

import static tools.ArrayTools.inputArray;

public class minInRSA {
    public static Scanner scn = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("nums length: ");
        int length = scn.nextInt();
        int[] rsa = new int[length];
        System.out.println("Rotated Sorted Array without duplicates: ");
        inputArray(rsa);

        System.out.println(findMinimumInRotatedSortedArray(rsa));
    }

    public static int findMinimumInRotatedSortedArray(int[] rsa) { //Полное обоснование сложности алгоритма в файле Justification_of_Complexity.md
        int left = 0, right = rsa.length - 1;
        if (rsa[left] < rsa[right]) return rsa[left];
        int mid =  left + (right - left) / 2;
        while (left + 1 < right) {
            if (rsa[mid] > rsa[left]) left = mid;
            else right = mid;
            mid = left + (right - left) / 2;
        }
        return rsa[right];
    }
}
