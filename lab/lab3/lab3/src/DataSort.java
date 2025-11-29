//В рамках данной лабораторной работы предлагается решить основную
//задачу и дополнительные. Для сдачи лабораторной работы необходимо набрать 4
//баллов.

//2. Дан K-sorted array и число K. Отсортировать массив.
//K-sorted array - массив из N элементов, в котором каждый элемент стоит не
//далее, чем на K позиций от своей позиции в отсортированном массиве.
//Отсортируйте массив за время O (N log K) и минимальное количество
//дополнительной памяти. Докажите сложность.

import java.util.*;

public class DataSort {
    private static final Scanner scanner = new Scanner(System.in);

    static void Sorted(int[] arr, int k) {
        int n = arr.length;

        // PriorityQueue - класс, реализующий интерфейс неограниченной очереди,
        // которая обрабатывает элементы на основе их приоритетов.
        // Элементы хранятся в структуре «приоритетная куча» — полном двоичном дереве,
        // где каждый узел имеет значение приоритета (по умолчанию используется min-heap)
        PriorityQueue<Integer> kList = new PriorityQueue<>();

        int i;

        // Цикл - O(N)
        for (i = 0; i < k; i++) {
            // Вставка элемента в двоичное дерево - O(logK)
            kList.add(arr[i]);
        }

        // Сложность цикла O(N)
        for (i = k; i < n; i++) {
            // Вставка элемента в двоичное дерево - O(logK)
            kList.add(arr[i]);

            // Изъятие элемента из двоичного дерева - O(logK)
            arr[i - k] = kList.poll();
        }

        while (!kList.isEmpty()) {
            // Изъятие элемента из двоичного дерева - O(logK)
            arr[i - k] = kList.poll();
            i++;
        }
    }
    // Общая сложность: O(N)*6*O(logK) = 6*O(NlogK) ~ O(NlogK)

    public void run() {
        int n;
        System.out.println("Введите количество элементов массива");
        n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Введите элементы массива: "); // 2,4,1,7,8,3,6,5,10,9   к=3
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Введите степень отсортированности списка: ");
        int k = scanner.nextInt();

        Sorted(arr, k);
        System.out.println(Arrays.toString(arr));

        // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }

    // Точка входа в программу
    public static void main(String[] args) {
        new DataSort().run();
    }
}