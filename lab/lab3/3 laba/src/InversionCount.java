//Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
//таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
//данном массиве и вывести их. Дать комментарии. Вычислить сложность.


public class InversionCount {

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.print("Введите N: ");
        int n = sc.nextInt();                     // 1 операция чтения

        int[] arr = new int[n];                  // n+2 операций

        System.out.println("Введите " + n + " элементов:");
        for (int i = 0; i < n; i++) {            // O(n)
            arr[i] = sc.nextInt();               // 1 присваивание + 1 чтение
        }

        long inv = countInversions(arr);         // 1 вызов, сложность O(n log n)

        System.out.println("Число инверсий: " + inv);
    }

    public static long countInversions(int[] arr) {
        int[] temp = new int[arr.length];     // n+2 операций
        return mergeSort(arr, temp, 0, arr.length - 1); // 1
    }

    private static long mergeSort(int[] arr, int[] temp, int left, int right) {
        long invCount = 0;                    // 1

        if (left < right) {                   // 1
            int mid = (left + right) / 2;     // 3

            // первая рекурсия
            // массив делится пополам, создаётся следующий уровень рекурсии
            // глубина рекурсивного дерева = log n
            invCount += mergeSort(arr, temp, left, mid);      // 2 операции + вклад в глубину рекурсии (log n)

            // вторая рекурсия
            // ещё один рекурсивный вызов того же уровня
            // суммарно 2 вызова на каждом уровне, полный бинарный рекурсивный уровень
            invCount += mergeSort(arr, temp, mid + 1, right); // 2 операции + второй дочерний узел уровня (log n уровней)

            // merge выполняется на каждом уровне рекурсивного дерева
            // на каждом уровне обрабатывается весь массив за O(n)
            // повторяется log n раз, n * log n
            invCount += merge(arr, temp, left, mid, right);   // 2
        }

        return invCount;                      // 1
    }

    private static long merge(int[] arr, int[] temp, int left, int mid, int right) {

        int i = left;        // 1
        int j = mid + 1;     // 2
        int k = left;        // 1
        long invCount = 0;   // 1
        // всего 5

        // выполняется на каждом уровне mergeSort → O(n) * log n
        while (i <= mid && j <= right) { // O(n) итераций
            if (arr[i] <= arr[j]) {  // 3
                temp[k] = arr[i];    // 3
                i++;                 // 1
            } else {
                temp[k] = arr[j];    // 3
                j++;                 // 1
                invCount += (mid - i + 1); // 3
            }
            k++; // 1
        }

        while (i <= mid) {  // O(n)
            temp[k] = arr[i]; // 3
            i++;              // 1
            k++;              // 1
        }

        while (j <= right) { // O(n)
            temp[k] = arr[j];
            j++;
            k++;
        }

        for (i = left; i <= right; i++) { // O(n)
            arr[i] = temp[i];
        }

        return invCount; // 1
    }
}
