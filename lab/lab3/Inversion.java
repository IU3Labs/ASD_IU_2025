/**
 * Группа А. Задание 1
 * Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
 * таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
 * данном массиве и вывести их. Дать комментарии. Вычислить сложность.
 * */
import java.util.*;
public class Inversion {
    public static void main(String[] args) {

        int n = Util.inputSize(); // считываем размер массива
        int[] arr = Util.inputArray(n); // считываем массив из n элементов

        System.out.print("Введённый массив: ");
        Util.printArray(arr);

        InversionData result = countInversions(arr); //считаем инверсии и их пары

        System.out.println("\n> Количество инверсий: " + result.totalInversions);

        System.out.println("\n> Найденные пары инверсий:");
        if (result.inversionPairs.isEmpty()) {
            System.out.println("Инверсий нет");
        } else {
            for (String s : result.inversionPairs) {
                System.out.println(s);
            }
        }
    }

    // структура для хранения результата
    static class InversionData {
        long totalInversions; // общее число инверсий
        List<String> inversionPairs; // список строк вида "(i,j): arr[i] > arr[j]"

        InversionData(long totalInversions, List<String> inversionPairs) {
            this.totalInversions = totalInversions;
            this.inversionPairs = inversionPairs;
        }
    }

    public static InversionData countInversions(int[] arr) {
        int[] temp = new int[arr.length]; // временный массив для сортировки слиянием
        List<String> pairs = new ArrayList<>(); // список найденных инверсий

        long total = mergeSort(arr, temp, 0, arr.length - 1, pairs);
        return new InversionData(total, pairs);
    }

    // рекурсивная сортировка слиянием
    private static long mergeSort(int[] arr, int[] temp, int left, int right, List<String> pairs) {
        long count = 0;// счётчик инверсий для текущего подмассива

        if (left < right) {
            int mid = (left + right) / 2;

            count += mergeSort(arr, temp, left, mid, pairs); // сортирует левую половину
            count += mergeSort(arr, temp, mid + 1, right, pairs); // сортирует правую половину

            count += merge(arr, temp, left, mid, right, pairs); // соединяет отсортированные половины и считает количество инверсий
        }
        return count;
    }

    // соединение двух отсортированных половин
    private static long merge(int[] arr, int[] temp, int left, int mid, int right, List<String> pairs) {
        int i = left; // указатель по левой половине
        int j = mid + 1; // указатель по правой половине
        int k = left; // указатель по temp
        long invCount = 0; // счётчик инверсий при слиянии

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++]; // Элемент из левой половины меньше или равен - инверсий нет
            } else {
                for (int x = i; x <= mid; x++) { // Элемент из правой половины меньше - инверсии есть
                    pairs.add("(" + x + ", " + j + "): " + arr[x] + " > " + arr[j]);
                }

                invCount += (mid - i + 1); // прибавляет количество инверсий
                temp[k++] = arr[j++]; // записывает правый элемент и двигает указатель j
            }
        }

        while (i <= mid) { // копирование остатков
            temp[k++] = arr[i++];
        }

        while (j <= right) { // копирование остатков
            temp[k++] = arr[j++];
        }

        for (int t = left; t <= right; t++) { // перенос в исходный массив
            arr[t] = temp[t];
        }
        return invCount;
    }
}

/**
 * На каждом уровне рекурсии массив разбивается на две части => глубина рекурсии — log(n) уровней
 * На каждом уровне выполняется операция слияния, которая просматривает все элементы один раз.
 * То есть каждый уровень рекурсии стоит O(n).
 * Сложность алгоритма: n*log(n) = O(n*log(n))
 * */