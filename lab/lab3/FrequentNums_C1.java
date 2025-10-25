// Дан целочисленный массив nums и целое число k, верните к наиболее
// часто встречающихся элементов. Вернуть ответ в любом порядке.
// Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.

import java.util.*;

public class FrequentNums_C1 {

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FrequentNums_C1 nums1 = new FrequentNums_C1();
        int[] nums = ArrayUtil.inputArray();

        System.out.println("Введите k:");
        int k1 = scanner.nextInt();

        System.out.println("Вывод: " + Arrays.toString(nums1.FrequentNums(nums, k1)));
    }

    public int[] FrequentNums(int[] nums, int k) {
        // Сортировка массива - O(n log n)
        Arrays.sort(nums);

        // Подсчет частот и сохранение в список - O(n)
        List<int[]> freqList = new ArrayList<>(); // Cписок из "структур" [число, частота]

        int current = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == current) {
                count++;
            } else {
                freqList.add(new int[]{current, count}); // Добавляем после подсчёта count
                current = nums[i];
                count = 1;
            }
        }
        // Добавляем последний элемент - O(m)
        freqList.add(new int[]{current, count});

        // Сортировка по частоте - O(m log m)
        freqList.sort((a, b) -> b[1] - a[1]); // Лямбда-функция для удобного сравнения

        // Выбор k наиболее частых элементов - O(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = freqList.get(i)[0];
        }

        return result;
    }
}

// Сложность: O(n*log n) + O(n) + O(m) + O(m*log m) + O(k) =
// [Выделим преобладающую сложность - O(n*log n)] = O(2*n*log n) =
// = [Используем правило игнорирования констант] = O(n*log n)
// --> Сложность алгоритма = O(n*log n)