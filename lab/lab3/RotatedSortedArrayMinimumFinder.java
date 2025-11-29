/**
 * Группа А
 * 5. Дан циклически сдвинутый отсортированный массив без дубликатов.
 * Требуется найти минимальный элемент за O(log N). Доказать сложность.
 * Rotated Sorted Array— повернутый массив. Это массив, который
 * изначально был отсортирован по возрастанию, а затем повёрнут от 1 до n
 * раз. Например, массив nums = [1,2,3,4,5,6] мог стать [3,4,5,6,1,2] — если
 * его повернули 4 раза.
 **/

public class RotatedSortedArrayMinimumFinder {

    public static void main(String[] args) {
        int[] list = {4, 5, 7, 9, 10, 1, 2};
        System.out.println(findMinimum(list));
    }

    static int findMinimum(int[] array) {
        int left = 0;
        int right = array.length - 1;
        if (array[left] < array[right]) return array[left];
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