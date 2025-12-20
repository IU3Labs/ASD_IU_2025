public class A5 {

    /*Дан циклически сдвинутый отсортированный массив без дубликатов.
    Требуется найти минимальный элемент за O(log N). Доказать сложность.
    Rotated Sorted Array — повернутый массив. Это массив, который
    изначально был отсортирован по возрастанию, а затем повёрнут от 1 до n
    раз. Например, массив nums = [1,2,3,4,5,6] мог стать [3,4,5,6,1,2] — если
    его повернули 4 раза.*/

    public static void main(String[] args) {
        int[] array = {4, 5, 6, 7, 1, 2};

        int minimumElement = findMinimum(array);

        System.out.println("Минимальный элемент: " + minimumElement);
    }

    public static int findMinimum(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int middle = left + (right - left) / 2;

            if (array[middle] > array[right]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return array[left];
    }
}
