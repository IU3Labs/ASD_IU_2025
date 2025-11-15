package groupa;

public class FindMinInRotatedArray {

    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println("Циклически сдвинутый массив: " + java.util.Arrays.toString(nums));
        int min = findMin(nums);
        System.out.println("Минимальный элемент: " + min);
        System.out.println("Сложность: O(log N) - бинарный поиск по массиву");
    }
}