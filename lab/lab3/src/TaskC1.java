/*
Дан целочисленный массив nums и целое число k, верните k наиболее
часто встречающихся элементов. Вернуть ответ в любом порядке.
Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.
*/

/*
Обоснование временной сложности:
В findTopKFrequent() первый цикл for имеет сложность O(n), т.к.
он проходится по массиву numbers. Во втором цикле for итерация по
карте частот. В худшем случае в ней будет n уникальных элементов.
Для каждого из уникальных элементов выполняется операция
добавления в кучу, которая занимает O(log(K)). Если размер кучи
становится больше K, также выполняется операция извлечения минимума, которая
занимает O(log(K)), что в сумме даёт сложность O(n*log(K)), в худшем
случае O(n*log(n)).

Итого сложность O(n) + O(n*log(n)) = O(n*log(n)), что и требовалось доказать.
*/

import structures.ElementFrequency;
import structures.MinHeap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TaskC1 {

    public static List<Integer> findTopKFrequent(int[] numbers, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int number : numbers) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        };

        MinHeap minHeap = new MinHeap();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.add(new ElementFrequency(entry.getKey(), entry.getValue()));
            if (minHeap.size() > k) {
                minHeap.pollMin();
            };
        };

        return minHeap.getElements();
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int n = in.nextInt();
        int[] numbers = new int[n];
        System.out.print("Введите K: ");
        int k = in.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.printf("Введите элемент №%d: ", i + 1);
            numbers[i] = in.nextInt();
        };

        System.out.println("k наиболее часто встречающихся элементов: " + findTopKFrequent(numbers, k));
    };

};
