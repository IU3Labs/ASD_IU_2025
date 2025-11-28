package lab3;

import java.util.*;

/**
 * Группа B
 * 1. Дан целочисленный массив nums и целое число k, вернуть k наиболее часто встречающихся элементов.
 * Сложность: O(n log n).
 */
public class MostFrequentNumbers {

    public static void main(String[] args) {

        int[] nums = inputArray();
        int k = inputK();

        Pair[] freq = countFrequencies(nums);
        sortByFrequency(freq);

        int[] result = takeTopK(freq, k);
        printResult(result);
    }

    private static int[] inputArray() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите количество элементов массива: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Введите элементы:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        return arr;
    }

    private static int inputK() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите k: ");
        return sc.nextInt();
    }

    private static Pair[] countFrequencies(int[] nums) {

        // Подсчёт частот — O(n)
        Map<Integer, Integer> freq = new HashMap<>();
        for (int value : nums) {
            freq.put(value, freq.getOrDefault(value, 0) + 1);
        }

        Pair[] result = new Pair[freq.size()];
        int idx = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            result[idx++] = new Pair(e.getKey(), e.getValue());
        }

        return result;
    }

    private static void sortByFrequency(Pair[] arr) {
        // Arrays.sort — гарантированное O(n log n)
        Arrays.sort(arr, (a, b) -> {

            if (b.frequency != a.frequency) {
                return Integer.compare(b.frequency, a.frequency);
            }

            return Integer.compare(a.number, b.number);
        });
    }


    private static int[] takeTopK(Pair[] freq, int k) {
        int take = Math.min(k, freq.length);
        int[] answer = new int[take];

        for (int i = 0; i < take; i++) {
            answer[i] = freq[i].number;
        }

        return answer;
    }

    private static void printResult(int[] result) {
        System.out.println("Наиболее частые элементы:");
        for (int x : result) {
            System.out.println(x);
        }
    }

    private static class Pair {
        int number;
        int frequency;

        Pair(int number, int frequency) {
            this.number = number;
            this.frequency = frequency;
        }
    }
}
