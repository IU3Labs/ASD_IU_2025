
import java.util.PriorityQueue;
import java.util.Scanner;

class ListNode {
    private final int value;
    private ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}

public class LinkedListMerger {

    public static ListNode mergeKSortedLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = createMinHeap(lists);
        return buildMergedList(minHeap);
    }

    private static PriorityQueue<ListNode> createMinHeap(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
                (node1, node2) -> Integer.compare(node1.getValue(), node2.getValue())
        );

        addInitialNodesToHeap(lists, minHeap);
        return minHeap;
    }

    private static void addInitialNodesToHeap(ListNode[] lists, PriorityQueue<ListNode> minHeap) {
        for (ListNode listHead : lists) {
            if (listHead != null) {
                minHeap.offer(listHead);
            }
        }
    }

    private static ListNode buildMergedList(PriorityQueue<ListNode> minHeap) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        while (!minHeap.isEmpty()) {
            ListNode smallestNode = minHeap.poll();
            current.setNext(smallestNode);
            current = current.getNext();

            addNextNodeToHeap(minHeap, smallestNode);
        }

        return dummyHead.getNext();
    }

    private static void addNextNodeToHeap(PriorityQueue<ListNode> minHeap, ListNode processedNode) {
        ListNode nextNode = processedNode.getNext();
        if (nextNode != null) {
            minHeap.offer(nextNode);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод количества списков
        System.out.println("Введите количество отсортированных списков (K):");
        System.out.print("K = ");
        int k = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        // Создание массива списков
        ListNode[] lists = new ListNode[k];

        // Ввод каждого списка
        for (int i = 0; i < k; i++) {
            System.out.println("\nВведите элементы для списка " + (i + 1) + " через пробел:");
            System.out.print("Элементы: ");
            String input = scanner.nextLine();
            lists[i] = createListFromInput(input);
        }

        scanner.close();

        System.out.println("\nOriginal lists:");
        printLists(lists);

        ListNode mergedList = mergeKSortedLists(lists);

        System.out.println("\nMerged sorted list:");
        printList(mergedList);
    }

    private static ListNode createListFromInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        String[] elements = input.trim().split("\\s+");
        if (elements.length == 0) {
            return null;
        }

        ListNode head = new ListNode(Integer.parseInt(elements[0]));
        ListNode current = head;

        for (int i = 1; i < elements.length; i++) {
            current.setNext(new ListNode(Integer.parseInt(elements[i])));
            current = current.getNext();
        }

        return head;
    }

    private static void printLists(ListNode[] lists) {
        for (int i = 0; i < lists.length; i++) {
            System.out.print("List " + (i + 1) + ": ");
            printList(lists[i]);
        }
    }

    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.getValue());
            if (current.getNext() != null) {
                System.out.print(" -> ");
            }
            current = current.getNext();
        }
        System.out.println();
    }
}
/**
 * Доказательство временной сложности O(N log K):
 *
 * 1. Обозначения:
 *    - K = количество связанных списков
 *    - N = общее количество элементов во всех списках
 *
 * 2. Анализ алгоритма:
 *    - Инициализация кучи с первыми элементами из K списков: O(K)
 *    - Для каждого из N элементов выполняется:
 *        a) Извлечение минимального элемента из кучи: O(log K)
 *        b) Добавление следующего элемента из того же списка в кучу: O(log K)
 *    - Общее время: O(K) + O(N * 2 * log K) = O(N log K)
 *
 * 3. Доказательство оптимальности:
 *    - Минимальная куча всегда содержит не более K элементов
 *    - Каждая операция с кучей имеет сложность O(log K)
 *    - Каждый элемент обрабатывается ровно один раз
 *    - Альтернативный подход слияния попарно имел бы сложность O(N log K^2) = O(N * 2 * log K)
 *
 * 4. Пространственная сложность: O(K)
 *    - Куча содержит не более K элементов одновременно
 *    - Результирующий список использует O(1) дополнительной памяти (переиспользует узлы)
 */
