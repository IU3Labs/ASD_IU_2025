import java.util.ArrayList;
import java.util.List;
//1 Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в данном массиве и вывести их. Дать комментарии. Вычислить сложность.
//1Сортировка слиянием сама по себе работает за O(n log n).
//2. Подсчёт инверсий не добавляет дополнительных циклов, поэтому время выполнения остаётся O(n log n).
//3. Используется вспомогательный массив размера n => пространственная сложность O(n).
public class InversionApp {

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 5, 4};

        List<String> inversions = findInversions(arr);


        System.out.println("Инверсии:");
        for (String inv : inversions) {
            System.out.println(inv);
        }

        System.out.println("Количество инверсий: " + inversions.size());
    }

    // O(N^2) метод чтобы вывести все инверсии
    private static List<String> findInversions(int[] arr) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    result.add("(" + i + ", " + j + ") → (" + arr[i] + ", " + arr[j] + ")");
                }
            }
        }

        return result;
    }
}