import java.util.Arrays;

public class CombSort {

    public static void combSort(int[] arr) {
        int n = arr.length;
        int gap = n;
        double shrink = 1.3;
        boolean sorted = false;

        while (!sorted) {
            // Вычисляем новый зазор
            gap = (int)(gap / shrink);
            if (gap <= 1) {
                gap = 1;
                sorted = true;
            }

            // Сравниваем элементы с текущим зазором
            for (int i = 0; i < n - gap; i++) {
                if (arr[i] > arr[i + gap]) {
                    // Меняем элементы местами
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    sorted = false;
                }
            }
        }
    }

    // Пример использования
    public static void main(String[] args) {
        int[] arr = {8, 4, 1, 3, -2, 7, 6, 2, 9, 0};

        System.out.println("Исходный массив: " + Arrays.toString(arr));
        combSort(arr);
        System.out.println("Отсортированный массив: " + Arrays.toString(arr));
    }
}
