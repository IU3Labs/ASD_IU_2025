import java.util.*;

public class TopKFrequentElements {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        // 1. Подсчет частоты элементов
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 2. Создание мин-кучи с кастомным компаратором
        // Сравниваем сначала по частоте (по возрастанию), затем по значению (по убыванию)
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((a, b) -> {
                    if (a.getValue().equals(b.getValue())) {
                        // При одинаковой частоте: большее значение имеет высший приоритет
                        return b.getKey() - a.getKey();
                    }
                    // При разной частоте: меньшая частота имеет высший приоритет
                    return a.getValue() - b.getValue();
                });

        // 3. Добавление элементов в кучу размера K
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // Удаляем элемент с наименьшей частотой
            }
        }

        // 4. Извлечение результата в правильном порядке
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        // Разворачиваем список, так как в куче элементы в обратном порядке
        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод массива чисел
        System.out.println("Введите целые числа через пробел:");
        String[] input = scanner.nextLine().split(" ");
        int[] nums = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        // Ввод значения K
        System.out.println("Введите значение K (количество наиболее частых элементов):");
        int k = scanner.nextInt();

        // Проверка корректности K
        if (k <= 0 || k > nums.length) {
            System.out.println("Ошибка: K должно быть в диапазоне от 1 до " + nums.length);
            return;
        }

        // Вычисление результата
        List<Integer> result = topKFrequent(nums, k);

        // Вывод результата
        System.out.println("K наиболее часто встречающихся элементов:");
        System.out.println(result);

        // Дополнительная информация: вывод частот всех элементов
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        System.out.println("\nЧастоты всех элементов:");
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " раз(а)");
        }

        scanner.close();
    }
}

/**Обоснование сложности O(N log K) для алгоритма с кучей:**

        1. **Подсчет частот через HashMap: O(N)** - проходим по всем N элементам массива
2. **Обработка U уникальных элементов в куче размером K: O(U log K)** - для каждого уникального элемента выполняем операции offer/poll за O(log K)
3. **Извлечение K элементов из кучи: O(K log K)** - K операций извлечения за O(log K) каждая

**Итог:** O(N) + O(U log K) + O(K log K) = **O(N log K)**
        - Поскольку U ≤ N и K ≤ N, доминирующей операцией является O(N log K)
- Использование кучи размером K вместо сортировки всех элементов позволяет достичь требуемой сложности O(N log K) вместо O(N log N)*/