/*Дан целочисленный массив nums и целое число k, верните k наиболее
часто встречающихся элементов. Вернуть ответ в любом порядке.
Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.*/
import java.util.*;

public class TopKFrequent {

    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        } //используется метод мапы который возвращает значение ключа если он есть,
        //если нет, то возвращает дефолтное значение(0) map.getOrDefault(ключ, значение_по_умолчанию)


        List<Map.Entry<Integer, Integer>> list =
                new ArrayList<>(frequencyMap.entrySet());

        //сортируем список по убыванию частоты
        //используется сортировка merge sort со сложностью O(n * log n)
        mergeSort(list);

        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }

        return result;
    }

    //рекурсивная сортировка merge sort
    //на каждом шаге список делится пополам, что даёт log n уровней рекурсии
    private static void mergeSort(List<Map.Entry<Integer, Integer>> list) {

        if (list.size() <= 1) {
            return;
        }

        int mid = list.size() / 2;

        List<Map.Entry<Integer, Integer>> left =
                new ArrayList<>(list.subList(0, mid));

        List<Map.Entry<Integer, Integer>> right =
                new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    //метод слияния двух отсортированных списков
    private static void merge(List<Map.Entry<Integer, Integer>> result,
                              List<Map.Entry<Integer, Integer>> left,
                              List<Map.Entry<Integer, Integer>> right) {

        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {

            //сравнение элементов по частоте
            //сортировка по убыванию
            if (left.get(i).getValue() >= right.get(j).getValue()) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            result.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            result.set(k++, right.get(j++));
        }
    }

    public static void main(String[] args) {

        int[] nums = Additionals.inputArray();
        int k = Additionals.inputK();
        int[] answer = topKFrequent(nums, k);

        System.out.println("k наиболее часто встречающихся элементов:");
        System.out.println(Arrays.toString(answer));
    }
}
/*
временная сложность:
на каждом уровне обрабатываем все n элементов O(n), а глубина рекурсии
log n следовательно O(n * log n)
пространственная сложность: O(n) тк используются дополнительные списки и стек рекурсии
*/
