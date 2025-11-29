import java.util.*;
/*
Дан целочисленный массив nums и целое число k, верните k наиболее
часто встречающихся элементов. Вернуть ответ в любом порядке.
Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.(Задача на 3 балла)
 */
public class TaskB1 {

    public int[] maxCount(int[] numbers, int k) {
        if (numbers.length == 0 || k == 0) {
            return new int[0];
        }

        // Найдем диапазон значений внутри массива
        int[] range = findMaxMin(numbers);
        int min = range[0];
        int max = range[1];
        int[] frequency = countFrequencies(numbers, min, max);

        Integer[] sortedIndices = sortIndices(frequency);
        return resultArray(sortedIndices, min, k);
    }

    // Найдем минимальное и максимальное значения в массиве
    private int[] findMaxMin(int[] numbers) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < numbers.length; i++) {
            int currentValue = numbers[i];

            if (currentValue < min) {
                min = currentValue;
            }
            if (currentValue > max) {
                max = currentValue;
            }
        }

        return new int[]{min, max};
    }

    // Найдем частоту встречаемости каждого числа
    private int[] countFrequencies(int[] numbers, int minimumValue, int maximumValue) {
        int valueRange = maximumValue - minimumValue + 1;
        int[] frequencyCounts = new int[valueRange];

        for (int i = 0; i < numbers.length; i++) {
            int currentNumber = numbers[i];
            int frequencyIndex = currentNumber - minimumValue;
            frequencyCounts[frequencyIndex]++;
        }

        return frequencyCounts;
    }

    // Сортируем массив индексов по убыванию частоты
    private Integer[] sortIndices(int[] frequency) {
        Integer[] indices = new Integer[frequency.length];
        for (int i = 0; i < frequency.length; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (a, b) -> Integer.compare(frequency[b], frequency[a]));

        return indices;
    }

    // Создаем результирующий массив результат
    private int[] resultArray(Integer[] sortedIndices, int min, int k) {
        int[] result = new int[k];
        for (int i = 0; (i < k) && (i < sortedIndices.length); i++) {
            result[i] = sortedIndices[i] + min;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskB1 solution = new TaskB1();

        System.out.println("Введите элементы массива через пробел: ");
        String[] input = scanner.nextLine().split(" ");
        int[] nums = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        System.out.println("Введите число k:");
        int k = scanner.nextInt();

        int[] result = solution.maxCount(nums, k);
        System.out.println("K наиболее часто встречающихся элементов: " + Arrays.toString(result));

        scanner.close();
    }
}

/*
                                АНАЛИЗ ВРЕМЕННОЙ СЛОЖНОСТИ АЛГОРИТМА:
1. Метод findMaxMin - временная сложность O(n) (перебор значений в цикле for)
 for (int i = 0; i < nums.length; i++) {   // O(n)
 ...
 }
2. Метод countFrequencies - временная сложность O(n) (перебор значений в цикле for)
 for (int i = 0; i < numbers.length; i++) {  // O(n)
       ...
}
3. Метод sortIndices - временная сложность O(m*log(m))
private Integer[] sortIndices(int[] frequency) {
    Integer[] indices = new Integer[frequency.length];  // m элементов (m = range)
    for (int i = 0; i < frequency.length; i++) {  // O(m) - перебор в цикле for
        indices[i] = i;
    }

    Arrays.sort(indices, (a, b) -> Integer.compare(frequency[b], frequency[a]));  //  O(m log m)
    Метод Arrays.sort() для массивов объектов в Java использует TimSort - гибридный алгоритм,
    сочетающий сортировку слиянием и вставками.
Сложность TimSort:

Худший случай: O(m log m)
Лучший случай: O(m)

4. Метод resultArray - временная сложность O(k) (перебор значений в цикле for)
  for (int i = 0; (i < k) && (i < sortedIndices.length); i++) {   // O(k) - k поскольку необходимо условие i < k
            ...
}
Доказательство O(n log n):

В худшем случае, когда диапазон значений m и число k сравнимы с n(т.е. все элементы уникальны), получаем:

O(3n) + O(m log m) ≈ O(n) + O(n log n) ---> O(n log n) - временная сложность алгоритма в худшем случае

В лучшем случае, когда диапазон значений m мал(числа в введенном массиве одинаковые) получим:
O(4n) =  O(n) --->  O(n) - временная сложность алгоритма в лучшем случае


                             АНАЛИЗ ПРОСТРАНСТВЕННОЙ СЛОЖНОСТИ АЛГОРИТМА:
1. Массив частот:
     int[] frequency = new int[range];  Создаем массив - Пространственная сложномть O(m)
2. Массив индексов для сортировки:
     Integer[] indices = new Integer[frequency.length];  Также создаем массив - Пространственная сложномть O(m)
     (т.к. используем то же количество памяти что и в первом случае,поскольку range = frequency.length)
3. Массив полученный в результате:

Общая пространственная сложность: O(m + m + k) = O(m + k)
В худшем случае:
Когда m = n и k = n, получаем O(2n) = O(n) --->  пространственная сложность алгоритма O(n)
 */
