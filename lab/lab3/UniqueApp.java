import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/*3 Дан массив из N целых чисел (N > 10^7), который может содержать
Найдите все уникальные элементы и выведите их в отсортированном
порядке. Сложность: O (N log U). Докажите сложность.*/
public class UniqueApp {

    public static void main(String[] args) {
        int[] arr = {5, 3, 5, 2, 8, 2, 1};

        int[] unique = findUniqueSorted(arr);

        System.out.println("Уникальные элементы:");
        for (int value : unique) {
            System.out.println(value);
        }
    }

    private static int[] findUniqueSorted(int[] arr) {

        // 1. Добавляем все элементы в HashSet для получения уникальных
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        // Время на каждое добавление в HashSet: O(1) в среднем
        // Всего N элементов => O(N) в среднем
        // Пространство: O(U), где U — количество уникальных элементов

        // 2. Переводим множество в массив
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        // Время: O(U)

        // 3. Сортируем уникальные элементы
        Arrays.sort(result);
        // Время сортировки: O(U log U) — стандартная сортировка массивов в Java (Dual-Pivot Quicksort / TimSort)

        return result;
    }
}

/*
Вывод по сложности:

1. Добавление N элементов в HashSet: O(N)
2. Преобразование множества в массив: O(U)
3. Сортировка массива из U элементов: O(U log U)

Итоговая сложность: O(N + U log U) = O(N log U),
так как U ≤ N и при больших N сортировка доминирует. 

Пространственная сложность: O(U) для хранения множества и массива уникальных элементов.
*/