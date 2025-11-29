public class InversionApp {
    //1 Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в данном массиве и вывести их. Дать комментарии. Вычислить сложность.
//1Сортировка слиянием сама по себе работает за O(n log n).
//2. Подсчёт инверсий не добавляет дополнительных циклов, поэтому время выполнения остаётся O(n log n).
//3. Используется вспомогательный массив размера n => пространственная сложность O(n).
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 5, 4};

        // Вызываем метод подсчёта инверсий
        long inversions = countInversions(arr);

        // Выводим результат
        System.out.println("Количество инверсий: " + inversions);
    }

    // Метод подсчёта инверсий с помощью модифицированной сортировки слиянием
    private static long countInversions(int[] arr) {
        // Вспомогательный массив для слияния
        int[] temp = new int[arr.length];
        return mergeSort(arr, temp, 0, arr.length - 1);
    }

    // Рекурсивная сортировка слиянием с подсчётом инверсий
    private static long mergeSort(int[] arr, int[] temp, int left, int right) {
        // Если массив из одного элемента, инверсий нет
        if (left >= right) {
            return 0;
        }

        int mid = (left + right) / 2;
        long inversions = 0;

        // Считаем инверсии в левой части массива
        inversions += mergeSort(arr, temp, left, mid);

        // Считаем инверсии в правой части массива
        inversions += mergeSort(arr, temp, mid + 1, right);

        // Считаем инверсии при слиянии левой и правой части
        inversions += merge(arr, temp, left, mid, right);

        return inversions;
    }

    // Метод слияния двух отсортированных частей массива
    // Также подсчитывает количество инверсий
    private static long merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;     // указатель на левую часть
        int j = mid + 1;  // указатель на правую часть
        int k = left;     // указатель для временного массива

        long inversions = 0;

        // Слияние двух частей массива
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                // Если элемент левой части <= правой, просто копируем
                temp[k++] = arr[i++];
            } else {
                // arr[i] > arr[j] → инверсии есть
                // Количество инверсий = количество оставшихся элементов в левой части
                inversions += (mid - i + 1);
                temp[k++] = arr[j++];
            }
        }

        // Копируем оставшиеся элементы левой части (если есть)
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Копируем оставшиеся элементы правой части (если есть)
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Копируем отсортированную и слитую часть обратно в исходный массив
        for (int p = left; p <= right; p++) {
            arr[p] = temp[p];
        }

        return inversions;
    }
}