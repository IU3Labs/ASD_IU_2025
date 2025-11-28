//Группа А. Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
//таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
//данном массиве и вывести их. Дать комментарии. Вычислить сложность.

import java.util.*;

public class SimpleInversionCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ПОДСЧЕТ ИНВЕРСИЙ В МАССИВЕ ===");
        System.out.println("Инверсия - это пара (i, j), где i < j и arr[i] > arr[j]");
        System.out.println();

        // Ввод массива с клавиатуры
        int[] arr = inputArray(scanner);

        System.out.println("\nВведенный массив: " + Arrays.toString(arr));

        // Подсчет инверсий
        CountResult result = countInversionsSimple(arr);

        // Вывод результатов
        System.out.println("\n=== РЕЗУЛЬТАТЫ ===");
        System.out.println("Количество инверсий: " + result.count);
        System.out.println("Все инверсии:");

        if (result.inversions.isEmpty()) {
            System.out.println("  Инверсий не найдено - массив отсортирован по возрастанию!");
        } else {
            for (String inv : result.inversions) {
                System.out.println("  " + inv);
            }
        }

        scanner.close();
    }

    /**
     * Ввод массива с клавиатуры
     */
    public static int[] inputArray(Scanner scanner) {
        System.out.print("Введите количество элементов в массиве: ");
        int n = scanner.nextInt();

        while (n <= 0) {
            System.out.print("Количество элементов должно быть положительным. Попробуйте снова: ");
            n = scanner.nextInt();
        }

        int[] arr = new int[n];
        System.out.println("Введите " + n + " элементов массива:");

        for (int i = 0; i < n; i++) {
            System.out.print("Элемент [" + i + "]: ");
            arr[i] = scanner.nextInt();
        }

        return arr;
    }

    /**
     * Основной метод для подсчета инверсий
     */
    public static CountResult countInversionsSimple(int[] arr) {
        int[] copy = arr.clone(); // работаем с копией, чтобы не менять исходный массив
        List<String> inversionPairs = new ArrayList<>();
        int count = mergeSortCount(copy, 0, arr.length - 1, inversionPairs);
        return new CountResult(count, inversionPairs);
    }

    /**
     * Рекурсивная сортировка слиянием с подсчетом инверсий
     */
    private static int mergeSortCount(int[] arr, int left, int right, List<String> inversions) {
        // Базовый случай: массив из одного элемента
        if (left >= right) return 0;

        int mid = (left + right) / 2;
        int count = 0;

        // Рекурсивно считаем инверсии в левой и правой половинах
        count += mergeSortCount(arr, left, mid, inversions);
        count += mergeSortCount(arr, mid + 1, right, inversions);

        // Считаем инверсии между половинами при слиянии
        count += mergeCount(arr, left, mid, right, inversions);

        return count;
    }

    /**
     * Слияние двух отсортированных половин с подсчетом инверсий
     */
    private static int mergeCount(int[] arr, int left, int mid, int right, List<String> inversions) {
        // Создаем временные массивы для левой и правой половин
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0; // индекс для левого массива
        int j = 0; // индекс для правого массива
        int k = left; // индекс для исходного массива
        int swaps = 0; // счетчик инверсий

        // Сливаем массивы, сравнивая элементы
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                // Нет инверсии - элемент из левой половины меньше или равен
                arr[k++] = leftArr[i++];
            } else {
                // Найдена инверсия
                // Элемент из правой половины меньше элемента из левой
                arr[k++] = rightArr[j++];

                // Все оставшиеся элементы в левой половине образуют инверсии с текущим правым элементом
                int inversionsCount = leftArr.length - i;
                swaps += inversionsCount;

                // Записываем все найденные инверсии
                for (int p = i; p < leftArr.length; p++) {
                    int leftIndex = left + p; // исходный индекс в левой половине
                    int rightIndex = mid + 1 + j - 1; // исходный индекс в правой половине
                    inversions.add("arr[" + leftIndex + "]=" + leftArr[p] +
                            " > arr[" + rightIndex + "]=" + rightArr[j-1]);
                }
            }
        }

        // Копируем оставшиеся элементы из левой половины (если есть)
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }

        // Копируем оставшиеся элементы из правой половины (если есть)
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }

        return swaps;
    }

    /**
     * Класс для хранения результата
     */
    static class CountResult {
        int count; // количество инверсий
        List<String> inversions; // список всех инверсий

        CountResult(int count, List<String> inversions) {
            this.count = count;
            this.inversions = inversions;
        }
    }
}