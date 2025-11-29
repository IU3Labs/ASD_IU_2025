/*Задание В1
* Дан целочисленный массив nums и целое число k, верните k наиболее
часто встречающихся элементов. Вернуть ответ в любом порядке.
Примечание. Сложность должна быть O(n*log(n)). */
import java.util.*;
//общ сложность О(n)+О(m)+О(k)+О(n logn), самое сложное n logn, по этому сложность  n logn
public class KMass {

    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new ArrayList<>();
        }

        Map<Integer, Integer> frequencyMap = createFrequencyMap(nums);
        List<Map.Entry<Integer, Integer>> entries = convertMapToSortedList(frequencyMap);

        return extractTopKElements(entries, k);
    }

    /*
    ключ - элемент, значение - частота
     */
    private Map<Integer, Integer> createFrequencyMap(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) { //считаем частоту, О(n)
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        return frequencyMap;
    }

    private List<Map.Entry<Integer, Integer>> convertMapToSortedList(Map<Integer, Integer> frequencyMap) {
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencyMap.entrySet()); //создаем список O(m)

        Collections.sort(entries, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        //сортировка О(n logn) тк TimSort

        return entries;
    }

    private List<Integer> extractTopKElements(List<Map.Entry<Integer, Integer>> entries, int k) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k && i < entries.size(); i++) { //извлечение результата O(k)
            result.add(entries.get(i).getKey());
        }

        return result;
    }
}

class Demo {

    private static final int TEST_K_VALUE = 3;

    public static void main(String[] args) {
        KMass solver = new KMass();

        int[] testNumbers = {1, 1, 1, 2, 2, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5 };
        List<Integer> result = solver.topKFrequent(testNumbers, TEST_K_VALUE);

        printResult(result);
    }

    private static void printResult(List<Integer> result) {
        System.out.print("Наиболее частые элементы: ");

        for (int num : result) {
            System.out.print(num + " ");
        }

        System.out.println();
    }
}