//Дано K связанных списков. Слейте их в один полностью
//отсортированный список. Сложность O (N log K). Докажите сложность.

import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeKLists {

    // Метод для ввода одного списка
    private static ListNode inputList(Scanner scanner, int listNumber) {
        System.out.println("Введите числа для списка " + listNumber);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return null;
        }

        String[] numbers = input.split("\\s+");
        ListNode head = new ListNode(Integer.parseInt(numbers[0]));
        ListNode current = head;

        for (int i = 1; i < numbers.length; i++) {
            current.next = new ListNode(Integer.parseInt(numbers[i]));
            current = current.next;
        }

        return head;
    }

    // Метод для вывода списка
    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("Пустой список");
            return;
        }

        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" → ");
            }
            current = current.next;
        }
        System.out.println();
    }

    // Основной метод объединения списков
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;

            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Сколько списков вы хотите объединить?");
        int k = scanner.nextInt();
        scanner.nextLine();

        ListNode[] lists = new ListNode[k];

        for (int i = 0; i < k; i++) {
            lists[i] = inputList(scanner, i + 1);
        }


        ListNode result = mergeKLists(lists);

        System.out.println("\nОбъединенный отсортированный список:");
        printList(result);

        scanner.close();
    }
}

// Доказательство сложности O(N log K):
// - N - общее количество элементов во всех списках
// - K - количество списков
// - Куча всегда содержит не более K элементов (по одному из каждого списка)
// - Всего выполняется N операций извлечения минимума из кучи
// - Каждая операция с кучей размера K: O(log K)
// - Также выполняется не более N операций добавления в кучу: O(log K) каждая
// - Итог: O(N log K) + O(N log K) = O(N log K)