import java.util.Arrays;
import java.util.Collections;

public class FreqFinder {

    public static void main(String[] args) {

        int[] nums = {1,1,1,2,2,3,5,4,6,5,5,5,5};  // Исходный массив
        int k = 3;                        // Сколько самых частых элементов нужно

        int[] result = topK(nums, k);     // Вызов метода

        System.out.println(Arrays.toString(result)); // Вывод результата
    }

    public static int[] topK(int[] nums, int k) {

        MergeClass.sort(Collections.singletonList(nums));                  // 1) сортируем массив  -> O(n log n)
        int n = nums.length;
        int[] values = new int[n];          // числа
        int[] counts = new int[n];          // их частоты
        int size = 0;                        // сколько разных чисел уже записано

        // 2) считаем частоты подряд стоящих одинаковых чисел
        for (int i = 0; i < n; ) {           // i движется вручную
            int num = nums[i];              // текущее число
            int count = 0;                 // его частота

            while (i < n && nums[i] == num) { // считаем сколько раз оно подряд встречается
                count++;
                i++;                      // двигаем i
            }
            values[size] = num;              // записываем число
            counts[size] = count;            // записываем частоту
            size++;                          // увеличиваем число уникальных значений
        }

        // 3) выбираем k самых частых (обычный поиск максимума k раз)
        int[] result = new int[k];
        for (int t = 0; t < k; t++) {        // выбираем по одному
            int maxIndex = 0;                // индекс элемента с максимальной частотой

            for (int i = 1; i < size; i++) { // ищем максимальную частоту
                if (counts[i] > counts[maxIndex]) {
                    maxIndex = i;
                }
            }
            result[t] = values[maxIndex];    // записываем число в ответ
            counts[maxIndex] = -1;           // "удаляем" чтобы не выбрать снова
        }
        return result;                        // возвращаем k самых частых чисел
    }
}