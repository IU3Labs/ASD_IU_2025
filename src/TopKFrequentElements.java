import java.util.Arrays;
import java.util.Scanner;

/*
Дан целочисленный массив nums и целое число k, верните k наиболее
часто встречающихся элементов. Вернуть ответ в любом порядке.
Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.(Задача на 3 балла)
 */

public class TopKFrequentElements {
    public static int[] topKFrequent(int[] nums, int k) {
        // Сортируем исходный массив - O(n log n)
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        // Создаем массив для хранения пар (число, частота)
        int[][] frequencyPairs = new int[nums.length][2];
        int uniqueCount = 0;
        int currentNum = sorted[0];
        int currentFreq = 1;

        //Подсчет частот в отсортированном массиве - O(n)
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] == currentNum) {
                currentFreq++;
            } else {
                frequencyPairs[uniqueCount][0] = currentNum;
                frequencyPairs[uniqueCount][1] = currentFreq;
                uniqueCount++;
                currentNum = sorted[i];
                currentFreq = 1;
            }
        }

        // Добавляем последнее число
        frequencyPairs[uniqueCount][0] = currentNum;
        frequencyPairs[uniqueCount][1] = currentFreq;
        uniqueCount++;

        // Создаем массив только с уникальными элементами - O(m)
        int[][] uniquePairs = new int[uniqueCount][2];
        for (int i = 0; i < uniqueCount; i++) {
            uniquePairs[i][0] = frequencyPairs[i][0];
            uniquePairs[i][1] = frequencyPairs[i][1];
        }

        //Сортируем по убыванию частоты - O(m log m)
        Arrays.sort(uniquePairs, (a, b) -> b[1] - a[1]);

        //Выбираем k наиболее частых элементов - O(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = uniquePairs[i][0];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов в массиве: ");
        int n = scanner.nextInt();

        int[] nums = new int[n];
        System.out.println("Введите " + n + " элементов массива:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        System.out.print("Введите значение k (количество наиболее частых элементов): ");
        int k = scanner.nextInt();

        scanner.close();

        if (k <= 0 || k > n) {
            System.out.println("Ошибка: k должно быть в диапазоне от 1 до " + n);
            return;
        }

        // Вычисление результата
        int[] result = topKFrequent(nums, k);

        // Вывод результата
        System.out.println("\nИсходный массив: " + Arrays.toString(nums));
        System.out.println(k + " наиболее часто встречающихся элементов: " + Arrays.toString(result));
    }
}

/*
ДОКАЗАТЕЛЬСТВО СЛОЖНОСТИ O(n log n):

ОБОЗНАЧЕНИЯ:
- n - общее количество элементов в массиве
- m - количество уникальных элементов (m ≤ n)
- k - количество возвращаемых элементов (k ≤ m ≤ n)

АНАЛИЗ СЛОЖНОСТИ ПО ШАГАМ:

1. Arrays.sort(sorted) - O(n log n)
   Доминирующая операция алгоритма

2. Подсчет частот в отсортированном массиве - O(n)
   Однопроходный алгоритм по отсортированному массиву

3. Копирование уникальных пар - O(m)
   m ≤ n, поэтому O(m) ≤ O(n)

4. Arrays.sort(uniquePairs) - O(m log m)
   Так как m ≤ n, то O(m log m) ≤ O(n log n)

5. Выбор k элементов - O(k)
   k ≤ n, поэтому O(k) ≤ O(n)

СУММАРНАЯ СЛОЖНОСТЬ:
O(n log n) + O(n) + O(m) + O(m log m) + O(k) = O(n log n)

ОБОСНОВАНИЕ:
- Слагаемое O(n log n) является доминирующим
- Все остальные слагаемые не превышают O(n log n)
- Даже в худшем случае, когда m = n (все элементы уникальны),
  сложность остается O(n log n)

*/
