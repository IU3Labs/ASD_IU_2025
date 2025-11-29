/* Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
данном массиве и вывести их. Дать комментарии. Вычислить сложность. */

public class Inversion {

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 3, 5, 6, 10, 4, 7, 9, 3, 2};     // Исходный массив

        System.out.println("Массив:");
        printArray(arr);                 // Красиво выводим массив
        int result = countInversions(arr); // Вызываем отдельный метод подсчёта инверсий
        System.out.println("\nОбщее количество инверсий: " + result); // Вывод результата
    }

    // Метод, который считает инверсии через двойной цикл
    public static int countInversions(int[] arr) {
        int count = 0;                               // Счётчик инверсий
        System.out.println("\nНайденные инверсии:");

        for (int i = 0; i < arr.length; i++) {       // Первый индекс i
            for (int j = i + 1; j < arr.length; j++) { // Второй индекс j (справа от i)

                if (arr[i] > arr[j]) {               // Проверяем условие инверсии
                    count++;                         // Увеличиваем счётчик

                    // Показываем, что за инверсия нашлась
                    System.out.println("(" + i + ", " + j + ") -> " +
                                    arr[i] + " > " + arr[j]);
                }
            }
        }

        return count;       // Возвращаем итоговое число инверсий
    }

    public static void printArray(int[] arr) {
        for (int x : arr) {                          // Проходим по элементам
            System.out.print(x + " ");               // Печатаем каждый
        }
        System.out.println();                        // Перевод строки
    }
}