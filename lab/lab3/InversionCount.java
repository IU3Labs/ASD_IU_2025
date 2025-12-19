//Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
//таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
//данном массиве и вывести их. Дать комментарии. Вычислить сложность.


public class InversionCount {

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Введите N: ");
        int size = scanner.nextInt();             // 1 операция чтения

        int[] dataset = new int[size];            // n+2 операций

        System.out.println("Введите " + size + " элементов:");
        for (int i = 0; i < size; i++) {          // O(n)
            dataset[i] = scanner.nextInt();       // 1 присваивание + 1 чтение
        }

        long totalInversions = countInversions(dataset); // 1 вызов, сложность O(n log n)

        System.out.println("Число инверсий: " + totalInversions);
    }

    public static long countInversions(int[] array) {
        int[] buffer = new int[array.length];    // n+2 операций
        return mergeSort(array, buffer, 0, array.length - 1); // 1
    }

    private static long mergeSort(int[] source, int[] buffer, int left, int right) {
        long count = 0;                       // 1

        if (left < right) {                   // 1
            int mid = (left + right) / 2;     // 3

            // первая рекурсия
            // массив делится пополам, создаётся следующий уровень рекурсии
            // глубина рекурсивного дерева = log n
            count += mergeSort(source, buffer, left, mid);      // 2 операции + вклад в глубину рекурсии (log n)

            // вторая рекурсия
            // ещё один рекурсивный вызов того же уровня
            // суммарно 2 вызова на каждом уровне, полный бинарный рекурсивный уровень
            count += mergeSort(source, buffer, mid + 1, right); // 2 операции + второй дочерний узел уровня (log n уровней)

            // merge выполняется на каждом уровне рекурсивного дерева
            // на каждом уровне обрабатывается весь массив за O(n)
            // повторяется log n раз, n * log n
            count += merge(source, buffer, left, mid, right);   // 2
        }

        return count;                         // 1
    }

    private static long merge(int[] source, int[] buffer, int left, int mid, int right) {

        int i = left;        // 1
        int j = mid + 1;     // 2
        int k = left;        // 1
        long invCounter = 0; // 1
        // всего 5

        // выполняется на каждом уровне mergeSort → O(n) * log n
        while (i <= mid && j <= right) { // O(n) итераций
            if (source[i] <= source[j]) { // 3
                buffer[k] = source[i];    // 3
                i++;                      // 1
            } else {
                buffer[k] = source[j];    // 3
                j++;                      // 1
                invCounter += (mid - i + 1); // 3
            }
            k++; // 1
        }

        while (i <= mid) {    // O(n)
            buffer[k] = source[i]; // 3
            i++;              // 1
            k++;              // 1
        }

        while (j <= right) {  // O(n)
            buffer[k] = source[j];
            j++;
            k++;
        }

        for (i = left; i <= right; i++) { // O(n)
            source[i] = buffer[i];
        }

        return invCounter; // 1
    }
}