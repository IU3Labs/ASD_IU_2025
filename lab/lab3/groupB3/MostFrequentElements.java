package groupB3;

import java.util.*;

import static tools.ArrayTools.inputArray;
import static tools.ArrayTools.printArray;

/*1. Дан целочисленный массив nums и целое число k, верните k наиболее
часто встречающихся элементов. Вернуть ответ в любом порядке.
Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.*/

public class MostFrequentElements { //Полное обоснование сложности алгоритма в файле Justification_of_Complexity.md

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("nums length: ");
        int length = scn.nextInt();
        int[] nums = new int[length];
        System.out.println("nums: ");
        inputArray(nums);
        System.out.println("k: ");
        int k = scn.nextInt();

        int[] kFrequent = findMostFrequentNumbers(nums, k);
        printArray(kFrequent);
    }

    public static int[] findMostFrequentNumbers(int[] nums, int k) {
        //1) Подсчет частот с помощью HashMap - O(n)
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        //2) Преобразование в список пар (число, частота) - O(m)
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());

        //3) Сортировка по убыванию частоты - O(n log n)
        entries.sort((a, b) -> b.getValue() - a.getValue());

        //4) Выбор первых k элементов - O(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entries.get(i).getKey();
        }

        return result;
    }
}













