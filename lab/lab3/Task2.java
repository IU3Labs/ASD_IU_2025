import java.util.*;

public class Task2 {
    // Класс для хранения элемента и его частоты
    static class ElementInfo {
        int value;
        int frequency;

        ElementInfo(int _value, int _frequency) {
            value = _value;
            frequency = _frequency;
        }
    }

    // Создание массива
    private static ArrayList<Integer> createMassive() {
        System.out.print("Введите размер массива: ");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Integer> data = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            System.out.print("Введите число: ");
            data.add(sc.nextInt());
        }
        return data;
    }

    // Подсчет частот: Сложность O(N)
    private static HashMap<Integer, Integer> countFrequencies(ArrayList<Integer> arr) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {  // аналог foreach
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        return freqMap;
    }

    // Создаем массив из K самых частых элементов
    private static ArrayList<Integer> getTopKFrequent(HashMap<Integer, Integer> freqMap, int k) {
        // Создаем min-heap для ElementInfo
        PriorityQueue<ElementInfo> minHeap = new PriorityQueue<>((a, b) -> {  // (a, b)->компаратор
            if (a.frequency != b.frequency) {
                return Integer.compare(a.frequency, b.frequency); // По возрастанию частоты
            }
            return Integer.compare(b.value, a.value); // При равных частотах по убыванию значения
        });

        // Заполняем кучу: сложность O(N*log(K))
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(new ElementInfo(entry.getKey(), entry.getValue()));
            if (minHeap.size() > k) {
                minHeap.poll();  // Выбрасываем элемент с самым низким значением сравнения или как там это назвать
            }
        }

        // Извлекаем результат O(K log K)
        ArrayList<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().value);
        }

        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        // 1. Создание массива
        ArrayList<Integer> arr = createMassive();
        System.out.println("Исходный массив: " + arr);

        // 2. Подсчёт частот
        HashMap<Integer, Integer> freqMap = countFrequencies(arr);
        System.out.println("Частотный словарь: " + freqMap);

        // 3. Запрос K
        System.out.print("Введите K: ");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        // 4. Определение K наиболее частых элементов
        ArrayList<Integer> topK = getTopKFrequent(freqMap, k);

        // 5. Выводим результат
        System.out.println(k + " наиболее частых элемента: " + topK);
    }
}