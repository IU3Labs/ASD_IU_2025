// Дан циклически сдвинутый отсортированный массив без дубликатов.
// Требуется найти минимальный элемент за O(1og N). Доказать сложность.
// Rotated Sorted Array— повернутый массив. Это массив, который изначально
// был отсортирован по возрастанию, а затем повёрнут от 1 до п раз. Например,
// массив nums = [1,2,3,4,5,6] мог стать [3,4,5,6,1,2] — если его повернули 4 раза.

import java.util.Scanner;

public class FindMinElem_A5 {
    static final Scanner scanner = new Scanner(System.in);

    public static void main() {
        int[] arr = ArrayUtil.inputArray();
        System.out.println("Результат: " + findMinElem(arr));
    }

    public static int findMinElem(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // На каждой итерации уменьшаем диапазон поиска в 2 раза,
        // таким образом (по принципу Бинарного поиска) получаем
        // сложность алгоритма O(log n)
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Минимум находится в правой части
                left = mid + 1;
            } else {
                // Минимум находится в левой части (включая mid)
                right = mid;
            }
        }

        return nums[left];
    }
}

// Вывод сложности: Пусть k - количество итераций, n - размер массива.
// Тогда мы уменьшаем n до того, пока она не станет равной 1, причём каждая
// итерация уменьшает размер массива в 2 раза, значит: n/(2^k) = 1, отсюда
// 2^k = n и, следовательно, k = log n
