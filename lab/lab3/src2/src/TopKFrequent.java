//Группа C. Дан целочисленный массив nums и целое число k, верните k наиболее
//часто встречающихся элементов. Вернуть ответ в любом порядке.
//Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.

import java.util.*;

public class TopKFrequent {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите числа через пробел: ");
        String[] input = scanner.nextLine().split(" ");

        System.out.print("Введите k: ");
        int k = scanner.nextInt();

        Map<Integer, Integer> freq = new HashMap<>();
        for (String s : input) {
            int num = Integer.parseInt(s);
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int[][] pairs = new int[freq.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            pairs[index][0] = entry.getKey();
            pairs[index][1] = entry.getValue();
            index++;
        }

        quickSort(pairs, 0, pairs.length - 1);

        System.out.print("Результат: ");
        for (int i = 0; i < k; i++) {
            System.out.print(pairs[i][0] + " ");
        }
    }

    static void quickSort(int[][] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    static int partition(int[][] arr, int left, int right) {
        int pivot = arr[right][1];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j][1] >= pivot) { // сортировка по убыванию
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    static void swap(int[][] arr, int i, int j) {
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}