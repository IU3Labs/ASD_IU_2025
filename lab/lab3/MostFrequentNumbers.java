package lab3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Группа B
 * 1. Дан целочисленный массив nums и целое число k, верните k наиболее
 * часто встречающихся элементов. Вернуть ответ в любом порядке.
 * Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.
 **/

public class MostFrequentNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод массива чисел
        System.out.print("Введите количество элементов в массиве: ");
        int n = scanner.nextInt();

        int[] nums = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Ввод значения k
        System.out.print("Введите значение k: ");
        int k = scanner.nextInt();

        scanner.close();

        // Поиск и вывод результата
        int[] result = findMostFrequentNumbers(nums, k);
        System.out.println(k + " наиболее часто встречающихся элементов:");
        for (int number : result) {
            System.out.println(number);
        }
    }

    static int[] findMostFrequentNumbers(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        ArrayList<Pair> frequencies = countFrequencies(nums);
        quickSortByFrequency(frequencies, 0, frequencies.size() - 1);

        int[] mostFrequentNumbers = new int[k];
        for (int i = 0; i < k; i++) {
            mostFrequentNumbers[i] = frequencies.get(frequencies.size() - i - 1).number;
        }
        return mostFrequentNumbers;
    }

    static void quickSort(int[] list, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left, j = right;
        int middle = list[left + (right - left) / 2];
        while (i <= j) {
            while (list[i] < middle) i++;
            while (list[j] > middle) j--;
            if (i <= j) {
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                i++;
                j--;
            }
        }
        quickSort(list, left, j);
        quickSort(list, i, right);
    }

    static ArrayList<Pair> countFrequencies(int[] sortedList) {
        ArrayList<Pair> frequencies = new ArrayList<>();
        int currentFrequency = 1;
        for (int i = 0; i < sortedList.length; i++) {
            if ((i < sortedList.length - 1 && sortedList[i] == sortedList[i + 1])) {
                currentFrequency++;
            } else {
                frequencies.add(new Pair(sortedList[i], currentFrequency));
                currentFrequency = 1;
            }
        }
        return frequencies;
    }

    static void quickSortByFrequency(ArrayList<Pair> list, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left, j = right;
        int middle = list.get(left + (right - left) / 2).frequency;
        while (i <= j) {
            while (list.get(i).frequency < middle) i++;
            while (list.get(j).frequency > middle) j--;
            if (i <= j) {
                Pair temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                i++;
                j--;
            }
        }
        quickSortByFrequency(list, left, j);
        quickSortByFrequency(list, i, right);
    }

    private static class Pair {
        public int number;
        public int frequency;

        public Pair(int number, int frequency) {
            this.number = number;
            this.frequency = frequency;
        }
    }
}