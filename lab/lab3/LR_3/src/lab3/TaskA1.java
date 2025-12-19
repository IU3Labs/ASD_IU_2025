package lab3;

import java.util.Scanner;

public class TaskA1 {

    /**
     * Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
     * таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий
     * в данном массиве и вывести их. Дать комментарии. Вычислить сложность.
     */

    /*
     Первый метод рассматривает все пары элементов в массиве и имеет временную сложность O(n^2).
     Второй метод использует алгоритм сортировки слиянием и имеет временную сложность O(n*log(n)),
     однако использует дополнительную память, имея пространственную сложность O(n).
    */
    public static void main(String[] args) {
        int[] array = readArray();
        System.out.println("Первый способ: " + countInversions(array));
        System.out.println("Второй способ: " + countInversionsAlt(array));
    }

    public static int[] readArray() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("\nВведите содержимое массива");
        for (int i = 0; i < size; i++) {
            System.out.print("array[" + i + "] = ");
            array[i] = scanner.nextInt();
        }

        return array;
    }

    /*
     Обычный обход массива имеет сложность O(n).
     В нашем случае в каждую итерацию основного обхода
     мы заново обходим подмассив, что также имеет сложность O(n).
     Итоговая сложность - O(n^2)
    */
    private static int countInversions(int[] arr) {
        int counter = 0;

        // Рассматриваем все пары элементов в массиве с помощью двух указателей
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) counter++;
            }
        }

        return counter;
    }

    /*
     Мы используем алгоритм сортировки слиянием,
     подсчитывая количество инверсий в процессе объединения частей массива.
     Сортировка слиянием имеет временную сложность O(n*log(n))
     и пространственную сложность O(n).
    */
    private static int countInversionsAlt(int[] arr) {
        return mergeSortAndCount(arr, 0, arr.length - 1);
    }

    private static int mergeSortAndCount(int[] arr, int left, int right) {
        int counter = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;

            // Рекурсивно считаем инверсии в левой и правой половинах
            counter += mergeSortAndCount(arr, left, mid);
            counter += mergeSortAndCount(arr, mid + 1, right);

            // Считаем инверсии при слиянии
            counter += mergeAndCount(arr, left, mid, right);
        }

        return counter;
    }

    private static int mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        // Копируем данные во временные массивы
        System.arraycopy(arr, left, leftArr, 0, leftArr.length);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightArr.length);

        int i = 0, j = 0, k = left;
        int swaps = 0;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
                swaps += (mid + 1) - (left + i);
            }
        }

        // Копируем оставшиеся элементы
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }

        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }

        return swaps;
    }
}