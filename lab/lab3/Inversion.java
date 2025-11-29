package lab3;

/* Дан массив аг из N лементов.
Назовем инверсией пару индексов (і, j), таких что і < j и arr[i] > arr[].
Требуется определить количество инверсий в данном массиве и вывести их.
Дать комментарии. Вычислить сложность. */

import java.util.ArrayList;
import java.util.Scanner;

public class Inversion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();

        System.out.println("\nИсходный массив:");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        ArrayList<int[]> inversions = GetInversions(array);
        int inversionsNumber = inversions.size();

        System.out.println("\nКоличество инверсий: " + inversionsNumber);

        if (inversionsNumber > 0) {
            System.out.println("Инверсии (пары индексов [i, j]):");
            for (int i = 0; i < inversionsNumber; i++) {
                int[] inversion = inversions.get(i);
                System.out.println("[" + inversion[0] + ", " + inversion[1] + "] - элементы: " +
                        array[inversion[0]] + " > " + array[inversion[1]]);
            }
        } else {
            System.out.println("В массиве нет инверсий");
        }
    }

    static ArrayList<int[]> GetInversions(int[] arr) {
        ArrayList<int[]> inversions = new ArrayList<>();
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] > arr[j]) {
                    int[] inversion = {i, j};
                    inversions.add(inversion);
                }
            }
        }
        return inversions;
    }
}

/* Сложность алгоритма
Пусть n - количество элементов в перебираемом массиве
Внешний цикл выполняется n раз от 0 до n-1
Количество итераций внутреннего цикла зависит от i
При i = o j изменяется от 1 до n-1 => n-1 раз
При i = 1 j изменяется от 2 до n-1 => n-2 раз
и так далее
При i = n - 2 j изменяется от n - 2 до n - 1 => 1 раз
При i = n - 1 j изменяется от n - 1 до n - 1 => 0 раз
Общее количество итераций может быть подсчитано как
(n-1) + (n-2) + (n-3) + ... + 1 + 0 = n*(n-1)/2
n(n-1)/2 = 1/2 * n^2 - 1/2 * n
n^2 растет быстрее остальных => сложность алгоритма будет O(n^2)
Ответ: O(n^2) */