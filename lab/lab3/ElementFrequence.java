package lab3;

/* Дан целочисленный массив nums и целое число к, верните к наиболее часто встречающихся элементов.
Вернуть ответ в любом порядке.
Примечание. Сложность должна быть O(n*log(n)). Докажите сложность. */

import java.util.*;

public class ElementFrequence {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        System.out.print("Введите значение K: ");
        int k = scanner.nextInt();
        scanner.close();

        if (k <= 0 || k > n) {
            System.out.println("Ошибка: K должно быть в диапазоне от 1 до " + n);
            return;
        }

        int[] elements = getElements(nums, k);
        System.out.println("\n" + k + " наиболее часто встречающихся элементов:");
        for (int element : elements) {
            System.out.println(element);
        }
    }

    public static int[] getElements(int[] nums, int k) {
        Map<Integer, Integer> frequences = new HashMap<>();
        for (int num : nums) {
            frequences.put(num, frequences.getOrDefault(num, 0) + 1);
        }
        // Сложность: O(n)

        List<int[]> pairs = new ArrayList<>();
        for (Map.Entry<Integer, Integer> frequence : frequences.entrySet()) {
            pairs.add(new int[]{frequence.getKey(), frequence.getValue()});
        }
        // Сложность: O(n)

        QuickSort.sort(pairs);
        // Сложность: O(n*log(n))

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pairs.get(i)[0];
        }
        // Сложность: O(k) = O(n)

        return result;
    }
}

/* сложность алгоритма
o(n) + o(n) + o(n*log(n)) + o(n) = o(n*log(n)), так как n*log(n) растет быстрее n */