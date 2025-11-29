/*Задание А1
* Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
данном массиве и вывести их. Дать комментарии. Вычислить сложность.
*
* тк самое сложное это вложенный цикл то сложность О(n^2)*/

import java.util.*;

public class InversionIndex {

    public int countInversions(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int inversionCount = 0;
        List<String> inversionPairs = new ArrayList<>();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    inversionCount++;
                    inversionPairs.add(arr[i] + " , " + arr[j]);
                }
            }
        }

        printInversions(inversionPairs);
        return inversionCount;
    }

    private void printInversions(List<String> inversionPairs) {
        System.out.println("Найденные инверсии:");
        if (inversionPairs.isEmpty()) {
            System.out.println("Инверсий не найдено");
        } else {
            for (String pair : inversionPairs) {
                System.out.println(pair);
            }
        }
    }
}

class InversionDemo {

    public static void main(String[] args) {
        InversionIndex counter = new InversionIndex();

        int[] testArray = {2, 4, 1, 3, 5};

        System.out.println("Исходный массив: " + arrayToString(testArray));

        int inversionCount = counter.countInversions(testArray);

        System.out.println("Количество инверсий: " + inversionCount);
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}