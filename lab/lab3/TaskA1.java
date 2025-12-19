import java.util.Scanner;

public class TaskA1 {

    /**
     * Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
     * таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий
     * в данном массиве и вывести их. Дать комментарии. Вычислить сложность.
     */

    /*
     Первый метод рассматривает все пары элементов в массиве и имеет временную сложность O(n^2).
     Второй метод использует алгоритм сортировки слиянием и имеет временную сложность O(n*log(n)).
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
     * Прямой метод подсчета инверсий (brute-force)
     * Инверсия - пара индексов (i, j), где i < j и arr[i] > arr[j]
     *
     * Сложность: O(n²)
     * Обоснование: для каждого элемента i мы сравниваем его со всеми
     * последующими элементами j (от i+1 до n-1).
     * Количество сравнений: (n-1) + (n-2) + ... + 1 = n(n-1)/2 = O(n²)
     */
    private static int countInversions(int[] arr) {
        int counter = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) counter++;
            }
        }

        return counter;
    }

    /*
     * Оптимизированный метод подсчета инверсий с использованием
     * модифицированной сортировки слиянием (merge sort)
     *
     * Сложность: O(n·log n)
     * Обоснование: сортировка слиянием делит массив пополам (log n уровней)
     * и выполняет слияние за O(n) на каждом уровне
     * Пространственная сложность: O(n) для временных массивов
     */
    private static int countInversionsAlt(int[] arr) {
        return mergeSortAndCount(arr, 0, arr.length - 1);
    }

    /**
     * Рекурсивно подсчитывает инверсии в подмассиве [left, right]
     *
     * @param arr   исходный массив (будет отсортирован в процессе)
     * @param left  левая граница текущего подмассива
     * @param right правая граница текущего подмассива
     * @return количество инверсий в подмассиве [left, right]
     */
    private static int mergeSortAndCount(int[] arr, int left, int right) {
        int counter = 0;

        // Базовый случай рекурсии: подмассив содержит более одного элемента
        if (left < right) {
            // Находим середину подмассива (без переполнения для больших массивов)
            int mid = left + (right - left) / 2;

            // Рекурсивно считаем инверсии в левой половине [left, mid]
            counter += mergeSortAndCount(arr, left, mid);

            // Рекурсивно считаем инверсии в правой половине [mid+1, right]
            counter += mergeSortAndCount(arr, mid + 1, right);

            // Считаем кросс-инверсии между левой и правой половинами при их слиянии
            counter += mergeAndCount(arr, left, mid, right);
        }

        return counter;
    }

    /**
     * Сливает два отсортированных подмассива и подсчитывает инверсии между ними
     *
     * @param arr   исходный массив
     * @param left  левая граница первого подмассива
     * @param mid   середина (конец первого подмассива, начало второго)
     * @param right правая граница второго подмассива
     * @return количество инверсий между подмассивами
     */
    private static int mergeAndCount(int[] arr, int left, int mid, int right) {
        // Создаем временные массивы для левой и правой половин
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        // Копируем данные из исходного массива во временные массивы
        System.arraycopy(arr, left, leftArr, 0, leftArr.length);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightArr.length);

        int i = 0;
        int j = 0;
        int k = left;
        int swaps = 0;

        // Слияние двух отсортированных массивов с подсчетом инверсий
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {

                arr[k++] = rightArr[j++];
                /*
                 * Ключевой момент подсчета инверсий:
                 * Если rightArr[j] < leftArr[i], то rightArr[j] образует инверсии
                 * со ВСЕМИ оставшимися элементами leftArr[i..end]
                 *
                 * Количество таких инверсий = количество элементов в leftArr,
                 * которые еще не были обработаны: (mid + 1) - (left + i)
                 * или проще: leftArr.length - i
                 */
                swaps += (mid + 1) - (left + i);
            }
        }

        // Копируем оставшиеся элементы из левого массива (если есть)
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }

        // Копируем оставшиеся элементы из правого массива (если есть)
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }

        return swaps;
    }
}