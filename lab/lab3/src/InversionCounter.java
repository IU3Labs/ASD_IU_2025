//Группа А Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
//таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
//данном массиве и вывести их. Дать комментарии. Вычислить сложность.
import java.util.*;

public class InversionCounter {
    public int countInversions(int[] arr) {
        if (arr == null || arr.length < 2) return 0;

        int count = 0;

        
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        InversionCounter counter = new InversionCounter();
        int[] arr = {2, 4, 1, 3, 5};

        System.out.println("Массив: " + Arrays.toString(arr));
        System.out.println("Количество инверсий: " + counter.countInversions(arr));

        // Сложность: O(n²)
        // Двойной вложенный цикл
        // Внешний: n-1 итераций
        // Внутренний: в среднем n/2 итераций
        // Всего: ~n²/2 операций сравнения
    }
}