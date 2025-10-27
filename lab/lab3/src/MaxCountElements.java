//Дан целочисленный массив nums и целое число k, верните k наиболее
//часто встречающихся элементов. Вернуть ответ в любом порядке.
//Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.

import java.util.*;

public class MaxCountElements {

    public int[] maxCount(int[] nums, int k) {
        if (nums.length == 0) return new int[0];

        // найдем диапазон значений внутри массива
        int[] range = findMaxMin(nums);
        int min = range[0];
        int max = range[1];
        int[] frequency = countFrequencies(nums, min, max);

        // сортируем массив, в котором подсчитано количество элементов
        Integer[] sortedIndices = sorted(frequency);

        return result(sortedIndices, min, k);
    }

    // найдем минимальное и максимальное значение в массиве
    private int[] findMaxMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        return new int[]{min, max};
    }

    // найдем частоту встречаемости каждого числа
    private int[] countFrequencies(int[] nums, int min, int max) {
        int range = max - min + 1;
        int[] frequency = new int[range];

        for (int num : nums) {
            frequency[num - min]++;
        }

        return frequency;
    }

    // сортируем массив количества подсчитанных элементов
    private Integer[] sorted(int[] frequency) {
        Integer[] indices = new Integer[frequency.length];
        for (int i = 0; i < frequency.length; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> frequency[b] - frequency[a]);

        return indices;
    }

    private int[] result(Integer[] sortedIndices, int min, int k) {
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = sortedIndices[i] + min;
        }
        return result;
    }

    /*
    доказательство:
    1. O(n) - один проход по массиву
    2. O(n) - один проход по массиву
    3. O(n*log(n)) - сортировка индексов
    4. O(k) ≤ O(n) - формирование результата
    O(n*log(n))
    */

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        MaxCountElements solution = new MaxCountElements();

        System.out.println("Введите элементы массива через пробел: ");
        String[] input = scanner.nextLine().split(" ");
        int[] nums = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        System.out.println("Введите число k:");
        int k = scanner.nextInt();

        int[] result = solution.maxCount(nums, k);
        System.out.println("K наиболее часто встречающихся элементов: " + Arrays.toString(result));

        scanner.close();
    }
}