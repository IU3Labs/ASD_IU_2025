/**
 * Дан целочисленный массив nums и целое число k, верните k наиболее
 * часто встречающихся элементов. Вернуть ответ в любом порядке.
 * Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.
 * ------------
 * Доказательство:
 * Подсчет частот O(n), так как каждый элемент обрабатывается за O(1).
 * Сортировка O(n log n), поскольку в худшем случае все элементы уникальны, и сортируются n элементов.
 * Формирование результата O(k), что не превышает O(n).
 * Итоговая сложность O(n) + O(n log n) + O(n) = O(n log n), что удовлетворяет условию.
 */

package tasks.task1;
import java.util.*;

public class Task1 {
    public static int[] maxFrequent(int[] nums, int k) {
        // Объявление карты частот
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        // Подсчёт частот
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1); // ключ - число, значение - частота
        }

        // Создание списка уникальных элементов и сортировка по убыванию частоты
        List<Integer> uniqueElements = new ArrayList<>(frequencyMap.keySet());
        uniqueElements.sort((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));

        // Формируем результат
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = uniqueElements.get(i);
        }

        return result;
    }

    public static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите элементы массива через пробел: ");
        
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        int[] nums = new int[inputArray.length];

        try {
            for (int i = 0; i < inputArray.length; i++) {
                nums[i] = Integer.parseInt(inputArray[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите только целые числа, разделенные пробелами!");
            return;
        }

        // Ввод k
        System.out.print("Введите k (количество наиболее частых элементов): ");
        int k = scanner.nextInt();

        // Проверка корректности k
        if (k <= 0) {
            System.out.println("Ошибка: k должно быть положительным числом!");
            return;
        }

        if (k > nums.length) {
            System.out.println("Предупреждение: k больше размера массива. Будут возвращены все уникальные элементы.");
        }

        // Выполнение алгоритма
        System.out.println("\nОбработка данных...");
        int[] result = maxFrequent(nums, k);

        // Вывод результата
        System.out.println("\nРезультат:");
        System.out.println("Входной массив: " + Arrays.toString(nums));
        System.out.println("k = " + k);
        System.out.println(k + " наиболее частых элементов: " + Arrays.toString(result));

        scanner.close();
    }

}
