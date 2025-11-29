import java.util.List;

public class MergeClass {

    public static void sort(List<int[]> list) {
        // Если список пустой или из 1 элемента — уже отсортирован
        if (list.size() <= 1) return;
        // Запуск рекурсивной сортировки
        mergeSort(list, 0, list.size() - 1);
    }

    private static void mergeSort(List<int[]> list, int left, int right) {
        // Пока в диапазоне больше 1 элемента
        if (left < right) {
            // Находим середину
            int mid = (left + right) / 2;
            // Рекурсивно сортируем левую половину
            mergeSort(list, left, mid);

            // Рекурсивно сортируем правую половину
            mergeSort(list, mid + 1, right);

            // Сливаем две отсортированные части
            merge(list, left, mid, right);
        }
    }

    private static void merge(List<int[]> list, int left, int mid, int right) {

        // Размеры двух частей
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Временные массивы
        int[][] L = new int[n1][];
        int[][] R = new int[n2][];
        // Копируем данные из list в L и R
        for (int i = 0; i < n1; i++) L[i] = list.get(left + i);
        for (int j = 0; j < n2; j++) R[j] = list.get(mid + 1 + j);
        int i = 0, j = 0;   // Индексы для L и R
        int k = left;       // Индекс для основного списка
        // Слияние по убыванию второго элемента массива: arr[1]
        while (i < n1 && j < n2) {

            // сравнение по второму элементу int[]
            if (L[i][1] >= R[j][1]) {
                list.set(k, L[i]);
                i++;
            } else {
                list.set(k, R[j]);
                j++;
            }
            k++;
        }
        // Копируем остатки левой части
        while (i < n1) {
            list.set(k, L[i]);
            i++;
            k++;
        }
        // Копируем остатки правой части
        while (j < n2) {
            list.set(k, R[j]);
            j++;
            k++;
        }
    }
}