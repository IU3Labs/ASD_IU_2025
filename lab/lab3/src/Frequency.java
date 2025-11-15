// 1 Дан целочисленный массив nums и целое число k, верните k наиболее
// часто встречающихся элементов. Вернуть ответ в любом порядке.
// Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.

// В данном задании используется алгоритм MergeSort, его сложность n*log(n)
// Итого: 3*O(n) + O(l+m) + O(n*log2(n)) = O(n*log2(n))

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Frequency {
    public static void main() { 
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> nums = Utils.createArray(scanner);
        Integer limit = Utils.getLimit(scanner);

        HashMap<Integer, Integer> numsMap = createHashMap(nums);

        HashMap<Integer, ArrayList<Integer>> reversedMap = reversedHashMap(numsMap);

        ArrayList<Integer> result = mostFrequentList(reversedMap, limit);

        Utils.printList(result);
    }


    public static HashMap<Integer, Integer> createHashMap(ArrayList<Integer> array) {
        HashMap<Integer, Integer> nums = new HashMap<>();
        // O(n), все функции внутри O(1)
        for (int i = 0; i < array.size(); i++) {
            int key = array.get(i);
            nums.put(key, nums.getOrDefault(key, 0) + 1);
        }

        return nums;
    }

    public static HashMap<Integer, ArrayList<Integer>> reversedHashMap(HashMap<Integer, Integer> nums) {
        HashMap<Integer, ArrayList<Integer>> result = new HashMap<>();

        // O(n), все функии внутри O(1)
        for (Map.Entry<Integer, Integer> entry : nums.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            result.putIfAbsent(value, new ArrayList<>());
            result.get(value).add(key);
        }

        return result;
    }

    public static ArrayList<Integer> mostFrequentList(HashMap<Integer, ArrayList<Integer>> hashMap, int limit) {
        Set<Integer> keySet = hashMap.keySet();
        int[] vals = new int[keySet.size()];
        int i = 0;
        // O(n)
        for (int key : keySet) {
            vals[i++] = key;
        }
        // O(n*log2(n))
        Utils.mergeSort(vals);

        ArrayList<Integer> result = new ArrayList<>();

        int count = 0;
        // O(n), в худшем случае O(N+m)
        for (int j = vals.length - 1; j >= 0 && count < limit; j--) {
            List<Integer> list = hashMap.get(vals[j]);
            // O(m)
            for (int el : list) {
                result.add(el);
                count++;
                if (count >= limit) break;
            }
        }
        return result;
    }
}
