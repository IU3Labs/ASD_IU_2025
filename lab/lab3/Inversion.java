import java.util.*;


//Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
//таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
//данном массиве и вывести их. Дать комментарии. Вычислить сложность.


public class Inversion {

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 3, 5};

        // Находим все инверсии
        List<String> inversions = findInversions(arr);

        // Выводим результат
        System.out.println("Массив: " + Arrays.toString(arr));
        System.out.println("Количество инверсий: " + inversions.size());
        System.out.println("Инверсии:");
        for (String inversion : inversions) {
            System.out.println(inversion);
        }
    }


//     Простой метод для поиска инверсий с двумя циклами
//      Инверсия - когда i < j но arr[i] > arr[j]

    public static List<String> findInversions(int[] arr) {
        List<String> inversions = new ArrayList<>();

        // Проходим всеми парами (i, j) где i < j
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // Проверяем условие инверсии
                if (arr[i] > arr[j]) {
                    // Сохраняем инверсию в понятном формате
                    String inversion = String.format("(%d, %d): %d > %d",
                            i, j, arr[i], arr[j]);
                    inversions.add(inversion);
                }
            }
        }

        return inversions;
    }
}
// Сложность O(n^2)
// при поиске для каждого элемента пары для инверсии мы проходимся по всему массиву