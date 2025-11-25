/**
 * Группа С. Задание 1
 * Дан целочисленный массив nums и целое число k, верните k наиболее
 * часто встречающихся элементов. Вернуть ответ в любом порядке.
 * Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.
 * */
import java.util.*;
public class TopFrequent {

    public static void main(String[] args) {

        int n = Util.inputSize(); // считываем размер массива
        int[] nums = Util.inputArray(n); // считываем массив из n элементов

        System.out.print("Введите k: "); // k — сколько самых часто встречающихся чисел нужно вывести
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        int[] result = topKFrequent(nums, k);
        Util.printArray(result);
    }

    public static int[] topKFrequent(int[] nums, int k) {
        // создаём HashMap для подсчёта частоты встречаемости каждого числа
        // ключ — число, значение — сколько раз оно встречается
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) { // проходим по массиву и увеличиваем счетчик для каждого числа
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // создаём список, в котором каждая запись — это массив из двух элементов: [число, частота]
        List<int[]> freqList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) { // Переносим данные из freqMap в список
            freqList.add(new int[]{entry.getKey(), entry.getValue()});
        }

        // сортировка по убыванию частоты
        freqList.sort((a, b) -> b[1] - a[1]);

        // создаём массив, куда положим k самых частых чисел
        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            topK[i] = freqList.get(i)[0]; // берём сам элемент, а не его частоту
        }

        return topK;
    }
}
/**
 * Подсчёт частоты через HashMap: Проходим по массиву один раз = O(n)
 * Перенос в список List<int[]>: Количество уникальных чисел = O(m), т.к. m ≤ n, сложность в худшем случае = O(n)
 * Сортировка списка по частоте: Сортируем m элементов → O(m log m), т.к. m ≤ n, сложность в худшем случае = O(n log n)
 * Выбор k наиболее частых элементов: Проходим по k элементам → O(k), т.к. k ≤ n, сложность в худшем случае = O(n)
 * Сложность алгоритма: n+n+n*log(n)+n = 3n+n*log(n) = O(n log n)
 * */