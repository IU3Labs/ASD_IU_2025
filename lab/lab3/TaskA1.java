import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/*
  Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
  таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий
  в данном массиве и вывести их. Дать комментарии. Вычислить сложность.(Задача на 1 балл)
 */

public class TaskA1 {

    // Метод для чтения массива с консоли
    public static int[] inputArray() {
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

    //  Метод для вывода инверсий
    public static List<String> printInversions(int[] array) {
        List<String> inversions = new ArrayList<>();


        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    inversions.add("(" + i + ", " + j + ") : " + array[i] + " > " + array[j]);
                }
            }
        }

        return inversions;
    }

    public static void main(String[] args) {
        int[] array = inputArray();
        List<String> inversions = printInversions(array);
        System.out.println("Количество инверсий: " + inversions.size());
        System.out.println("Список инверсий:");
        for (String s : inversions) {
            System.out.println(s);
        }
    }

}
/*
                                    АНАЛИЗ ВРЕМЕННОЙ СЛОЖНОСТИ АЛГОРИТМА:

1. Метод inputArray() - временная сложность O(n)
 for (int i = 0; i < size; i++) { // O(n) - перебор всех элементов массива
...
}
2. Метод printInversions() - временная сложность O(n²)
 for (int i = 0; i < array.length - 1; i++) { // O(n) внешний цикл
    for (int j = i + 1; j < array.length; j++) { // O(n) внутренний цикл
 ...
    }
}
Суммарное количество итераций: (n-1) + (n-2) + ... + 1 = n*(n-1)/2

Таким образом: O(n*(n-1)/2) = O(n²) - квадратичная временная сложность

Итоговая временная сложность алгоритма:
O(n) + O(n²) = O(n²) ---> Временная сложность алгоритма


                            АНАЛИЗ ПРОСТРАНСТВЕННОЙ СЛОЖНОСТИ АЛГОРИТМА:
1. Массив array:
int[] array = new int[size]; // Пространственная сложность O(n);
2. Список inversions:
В худшем случае массив полностью убывающий и количество инверсий ≈ n(n-1)/2 -->
Пространственная сложность O(n(n-1)/2) = O(n²)

Общая пространственная сложность алгоритма: O(n) + O(n²) = O(n²)
 */
