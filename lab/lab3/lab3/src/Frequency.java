/* Дан целочисленный массив nums и целое число k, верните k наиболее
        * часто встречающихся элементов. Вернуть ответ в любом порядке.
        * Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.
        */
import java.util.*;
public class Frequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Array.inputArray(scanner);
        int k = readInt(scanner);
        int[] result = topKFrequent(nums, k);
        System.out.print("k наиболее частых элементов: ");
        Array.printArray(result);
        scanner.close();
    }
    public static int readInt(Scanner scanner) {
        System.out.print("Введите k: ");
        return scanner.nextInt();
    }
    private static int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        // Сортировка массива - O(n*log(n))
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int[] numbers = new int[sorted.length];
        int[] counts = new int[sorted.length];
        int size = 0;
        // Заполняем массивы - O(n)
        numbers[0] = sorted[0];
        counts[0] = 1;
        size = 1;
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] == sorted[i-1]) {
                counts[size-1]++;
            } else {
                numbers[size] = sorted[i];
                counts[size] = 1;
                size++;
            }
        }
        // Создаем массив пар (число, частота) для сортировки
        int[][] pairs = new int[size][2];
        for (int i = 0; i < size; i++) {
            pairs[i][0] = numbers[i];  // число
            pairs[i][1] = counts[i];   // частота
        }
        // Сортируем по убыванию частоты - O(m*log(m))
        Arrays.sort(pairs, (a, b) -> b[1] - a[1]);
        // Выбираем k наиболее частых элементов - O(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pairs[i][0];
        }
        return result;
    }
}
/*  Поиск k наиболее часто встречающихся элементов
 * Сложность: O(n*log(n)), где n - размер массива
 * Доказательство сложности:
 * 1. Сортировка массива: O(n*log(n))
 * 2. Подсчет частот и заполнение массивов: O(n)
 * 3. Сортировка частот с помощью Arrays.sort: O(m*log(m)), m - кол-во уникальных элементов
 *    В худшем случае m = n, поэтому O(n*log(n))
 * 4. Выбор k элементов: O(k)
 *
 * Общая сложность: O(n*log(n)) + O(n) + O(n*log(n)) + O(k) = O(n*log(n))
 */