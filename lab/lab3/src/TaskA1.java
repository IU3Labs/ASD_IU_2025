/* Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
данном массиве и вывести их. Дать комментарии. Вычислить сложность.*/

import java.util.*;

public class TaskA1 {

    public static void main(String[] args) {
        int size = ArrayUtil.arraySize();
        int[] array = ArrayUtil.arrayInput(size);
        ArrayUtil.arrayPrint(array);

        InversionResult result = countInversions(array);

        System.out.println("\nРезультат:");
        System.out.println("Количество инверсий в массиве: " + result.inversionCount);

        System.out.println("\nВсе пары инверсий:");
        if (result.inversionPairs.isEmpty()) {
            System.out.println("Инверсий не найдено");
        } else {
            for (int i = 0; i < result.inversionPairs.size(); i++) {
                System.out.println(result.inversionPairs.get(i));
            }
        }
    }

    static class InversionResult {
        long inversionCount;
        List<String> inversionPairs;

        InversionResult(long inversionCount, List<String> inversionPairs) {
            this.inversionCount = inversionCount;
            this.inversionPairs = inversionPairs;
        }
    }

    public static InversionResult countInversions(int[] array) {
        long inversionCount = 0;
        List<String> inversionPairs = new ArrayList<>();
        //Внешний цикл
        for (int i = 0; i < array.length; i++) {
            //Внутренний цикл
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    inversionCount++;
                    inversionPairs.add(String.format("(%d, %d): %d > %d", i, j, array[i], array[j]));
                }
            }
        }
        return new InversionResult(inversionCount, inversionPairs);
    }
}

/* Доказательство сложности O(n²):
Внешний цикл выполняется n итераций (i от 0 до n-1)
Внутренний цикл выполняется в среднем n/2 итераций на каждый i (j от i+1 до n-1)
Сложность алгоритма: n * (n/2) = n²/2 = O(n²) */