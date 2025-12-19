package com.iu3.tasks.b1;

import java.util.*;

/**
 * Дано K отсортированных связанных списков.
 * Требуется слить их в один полностью отсортированный список за время O(N log K),
 * где N — общее число элементов во всех списках, K — количество списков.
 */
public class MergedLists_B1 {

    /**
     * Сливает два отсортированных связанных списка в один отсортированный.
     * <p>
     * Временная сложность: O(m + n), где m и n — длины входных списков.
     * Каждый элемент из обоих списков посещается ровно один раз.
     * Пространственная сложность: O(m + n) — создаётся новый список.
     */
    private static LinkedList<Integer> mergeTwoLists(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> merged = new LinkedList<>();

        while (!list1.isEmpty() && !list2.isEmpty()) {
            if (list1.peek() <= list2.peek()) {
                merged.add(list1.poll());
            } else {
                merged.add(list2.poll());
            }
        }

        merged.addAll(list1);
        merged.addAll(list2);

        return merged;
    }

    /**
     * Сливает K отсортированных связанных списков в один отсортированный список
     * с использованием итеративного попарного слияния.
     * <p>
     * Временная сложность: O(N log K), где:
     *   - N = общее количество элементов во всех K списках, — K = количество списков.
     * <p>
     * ДОКАЗАТЕЛЬСТВО СЛОЖНОСТИ:
     * <p>
     * На каждом уровне итеративного слияния:
     *   - Все N элементов участвуют ровно в одном вызове mergeTwoLists.
     *   - Следовательно, работа на одном уровне = O(N).
     * <p>
     * Сколько уровней? 
     *   - Изначально имеем K списков.
     *   - На каждом уровне число списков уменьшается вдвое (попарное слияние).
     *   - Количество уровней = ceil(log₂ K) = O(log K).
     * <p>
     * Общая сложность = (работа на уровне) × (число уровней) = O(N) × O(log K) = O(N log K).
     * <p>
     * Пространственная сложность: O(N) — для хранения результата и промежуточных списков.
     */
    private static LinkedList<Integer> mergeKLists(ArrayList<LinkedList<Integer>> lists) {
        if (lists == null || lists.isEmpty()) {
            return new LinkedList<>();
        }

        ArrayList<LinkedList<Integer>> current = new ArrayList<>(lists);

        // Количество итераций цикла = O(log K)
        while (current.size() > 1) {
            ArrayList<LinkedList<Integer>> nextRound = new ArrayList<>();

            // На каждой итерации обрабатываем все текущие списки попарно
            for (int i = 0; i < current.size(); i += 2) {
                LinkedList<Integer> left = current.get(i);
                LinkedList<Integer> right = (i + 1 < current.size()) ? current.get(i + 1) : new LinkedList<>();
                nextRound.add(mergeTwoLists(left, right)); // O(длина left + длина right)
            }

            current = nextRound;
        }

        return current.getFirst();
    }

    public static void main(String[] args) {
        // Тест 1: три отсортированных списка
        ArrayList<LinkedList<Integer>> test1 = new ArrayList<>();
        test1.add(new LinkedList<>(Arrays.asList(1, 4, 5)));
        test1.add(new LinkedList<>(Arrays.asList(1, 3, 4)));
        test1.add(new LinkedList<>(Arrays.asList(2, 6)));

        System.out.println("Тест 1:");
        System.out.println("Вход: [[1,4,5], [1,3,4], [2,6]]");
        System.out.println("Результат: " + mergeKLists(test1));

        // Тест 2: пустой ввод
        ArrayList<LinkedList<Integer>> test2 = new ArrayList<>();
        System.out.println("\nТест 2:");
        System.out.println("Вход: []");
        System.out.println("Результат: " + mergeKLists(test2));

        // Тест 3: один список
        ArrayList<LinkedList<Integer>> test3 = new ArrayList<>();
        test3.add(new LinkedList<>(Arrays.asList(-1, 0, 1, 2)));
        System.out.println("\nТест 3:");
        System.out.println("Вход: [[-1, 0, 1, 2]]");
        System.out.println("Результат: " + mergeKLists(test3));

        // Тест 4: списки разной длины, включая пустой
        ArrayList<LinkedList<Integer>> test4 = new ArrayList<>();
        test4.add(new LinkedList<>(Arrays.asList(1, 2)));
        test4.add(new LinkedList<>()); // пустой список
        test4.add(new LinkedList<>(Arrays.asList(3, 4, 5)));
        test4.add(new LinkedList<>(List.of(0)));

        System.out.println("\nТест 4:");
        System.out.println("Вход: [[1,2], [], [3,4,5], [0]]");
        System.out.println("Результат: " + mergeKLists(test4));
    }
}