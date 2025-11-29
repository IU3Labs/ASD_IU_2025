// 1. Дано K связанных списков. Слейте их в один полностью
// отсортированный список. Сложность O (N log K). Докажите сложность.

/*
Доказательство сложности O(n*log(n)):
В mergeKLists() первый цикл for имеет сложность O(K log K), т.к.
он проходится по массиву lists и для каждого непустого списка выполняется операция добавления в Min-Heap,
которая занимает O(log K). Во втором цикле while выполняется итерация по всем N элементам.
Для каждого элемента выполняется:
Операция извлечения минимального элемента из кучи: O(log K)
Возможная операция добавления следующего элемента в кучу: O(log K)
Что в сумме даёт сложность O(2N × log K) = O(N log K).
Поскольку K ≤ N, в худшем случае когда K = N (каждый список содержит 1 элемент), получаем O(N log N).
Итого сложность: O(K log K) + O(N log K) = O(N log K), что в худшем случае O(N log N), что и требовалось доказать.

K - количество списков (ограничено сверху N)
N - общее количество элементов во всех списках
*/

package com.iu3.tasks.b1;

import java.util.*;

public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Min-Heap для хранения узлов
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Добавляем первые узлы всех списков в кучу
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        // Фиктивная голова для упрощения кода
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Извлекаем минимальный элемент и добавляем следующий из того же списка
        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            current.next = minNode;
            current = current.next;

            // Если в текущем списке есть следующий элемент, добавляем его в кучу
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    // Вспомогательный класс для связанного списка
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // Тестирование
    public static void main(String[] args) {
        // Создаем K отсортированных списков
        ListNode[] lists = new ListNode[3];

        // Список 1: 1 → 4 → 5
        lists[0] = new ListNode(1, new ListNode(4, new ListNode(5)));

        // Список 2: 1 → 3 → 4
        lists[1] = new ListNode(1, new ListNode(3, new ListNode(4)));

        // Список 3: 2 → 6
        lists[2] = new ListNode(2, new ListNode(6));

        System.out.println("Исходные списки:");
        printLists(lists);

        ListNode merged = mergeKLists(lists);

        System.out.println("Объединенный отсортированный список:");
        printList(merged);
    }

    private static void printLists(ListNode[] lists) {
        for (int i = 0; i < lists.length; i++) {
            System.out.print("Список " + (i + 1) + ": ");
            printList(lists[i]);
        }
    }

    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" → ");
            current = current.next;
        }
        System.out.println();
    }
}
