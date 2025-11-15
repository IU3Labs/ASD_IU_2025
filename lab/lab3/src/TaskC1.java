/* Дан целочисленный массив nums и целое число k, верните k наиболее
часто встречающихся элементов. Вернуть ответ в любом порядке.
Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.*/

import java.util.*;

public class TaskC1 {

    public static void main(String[] args) {
        TaskC1 solution = new TaskC1();
        Scanner scanner = new Scanner(System.in);

        int size = ArrayUtil.arraySize();
        int[] nums = ArrayUtil.arrayInput(size);

        ArrayUtil.arrayPrint(nums);

        System.out.print("Введите k (количество наиболее частых элементов): ");
        int k = scanner.nextInt();

        int[] result = solution.maxKFrequent(nums, k);
        System.out.println(k + " наиболее часто встречающихся элементов: " + Arrays.toString(result));
    }

    public int[] maxKFrequent(int[] nums, int k) {
        //Сортировка массива - O(n log n)
        Arrays.sort(nums);

        //Подсчет частоты элементов - O(n)
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                count++;
            } else {
                frequencyMap.put(nums[i], count);
                count = 1;
            }
        }

        //Создание списка записей - O(m)
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());

        //Сортировка по частоте - O(m log m)
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                int freqCompare = b.getValue().compareTo(a.getValue());
                if (freqCompare != 0) {
                    return freqCompare;
                }
                return a.getKey().compareTo(b.getKey());
            }
        });

        //Выбор k наиболее частых элементов - O(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entries.get(i).getKey();
        }
        return result;
    }
}

/* Доказательство сложности O(n*log(n)):
O(n*log n) + O(n) + O(m) + O(m*log m) + O(k) =  O(2n*log n+ n)
Игнорирование констант: O(2n*log n) = O(n*log n)
O(n) пренебрежимо по сравнению с O(n log n)
Значит сложность алгоритма: O(n*log n) */
