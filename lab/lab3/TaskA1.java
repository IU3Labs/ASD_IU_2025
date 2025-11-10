package lab3;

import java.util.Scanner;

public class TaskA1 {

    /**
     * Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
     * таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий
     * в данном массиве и вывести их. Дать комментарии. Вычислить сложность.
     */

    /*
     Обычный обход массива имеет сложность O(n).
     В нашем случае в каждую итерацию основного обхода
     мы заново обходим подмассив, что также имеет сложность O(n).
     Итоговая сложность - O(n^2)
    */
    public static void main(String[] args) {
        int[] array = readArray();
        System.out.println(countInversions(array));
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

    private static int countInversions(int[] arr) {
        int counter = 0;

        // Рассматриваем все пары элементов в массиве с помощью двух указателей
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) counter++;
            }
        }

        return counter;
    }
}
