/*
(1 балл) Дан массив из N целых чисел (N > 10^7), который может содержать.
Найдите все уникальные элементы и выведите их в отсортированном порядке.
Сложность: O (N log U). Докажите сложность.
*/
package lab3.uniqueElem;

import java.util.*;

import lab3.utils.Utils;

public class UniqueElem {

    public static int[] findUniqueElements(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        // Этап 1: Извлечение уникальных элементов - O(N)
        Set<Integer> uniqueSet = new HashSet<>();
        for (int num : arr) {
            uniqueSet.add(num);
        }

        // Этап 2: Конвертирование Set в массив - O(U) где U <= N
        int[] uniqueArray = new int[uniqueSet.size()];
        int index = 0;
        for (int num : uniqueSet) {
            uniqueArray[index++] = num;
        }

        // Этап 3: Сортировка массива - O(U log U)
        Utils.mergeSort(uniqueArray,0,uniqueArray.length-1);

        // Итог O(N) + O(U) + O(U log U) ~ O(N log U)
        return uniqueArray;
    }

    public static void main(String[] args) {
        // Простой массив
        int[] arr1 = {5, 2, 8, 2, 9, 1, 5, 5};
        for (int elem: arr1) {
            System.out.print(elem + " ");
        }
        System.out.println(); // 5 2 8 2 9 1 5 5

        int[] result1 = findUniqueElements(arr1);
        for (int elem: result1) {
            System.out.print(elem + " ");
        }
        System.out.println(); // 1 2 5 8 9

        // Массив на 10^7 элементов
        int countElements = 10000000;
        int[] arr2 = new int[countElements];
        for (int i=0; i < countElements; i++) {
            arr2[i] = Utils.random10();
        }

        int[] result2 = findUniqueElements(arr2);
        for (int elem: result2) {
            System.out.print(elem + " ");
        }
        System.out.println(); // 0 1 2 3 4 5 6 7 8 9
    }
}
