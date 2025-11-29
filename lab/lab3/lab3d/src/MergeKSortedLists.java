
import java.util.PriorityQueue;

/**
 * Задача B1: Слияние K отсортированных связных списков
 * Временная сложность: O(N log K)
 * Пространственная сложность: O(K)
 * где N - общее количество узлов, K - количество списков
 */
public class MergeKSortedLists {

    /**
     * Класс узла связного списка
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /**
     * Класс-обертка для сравнения узлов в куче
     */
    static class NodeWrapper implements Comparable<NodeWrapper> {
        ListNode node;
        int listIndex;

        NodeWrapper(ListNode node, int listIndex) {
            this.node = node;
            this.listIndex = listIndex;
        }

        @Override
        public int compareTo(NodeWrapper other) {
            return Integer.compare(this.node.val, other.node.val);
        }
    }

    /**
     * Сливает K отсортированных связных списков в один
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Создаем минимальную кучу (приоритетную очередь)
        PriorityQueue<NodeWrapper> minHeap = new PriorityQueue<>();

        // Добавляем головы всех непустых списков в кучу
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minHeap.offer(new NodeWrapper(lists[i], i));
            }
        }

        // Создаем фиктивный узел для упрощения
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Извлекаем минимальный элемент и добавляем следующий
        while (!minHeap.isEmpty()) {
            NodeWrapper wrapper = minHeap.poll();
            ListNode node = wrapper.node;

            // Добавляем узел к результату
            current.next = node;
            current = current.next;

            // Если есть следующий элемент, добавляем его в кучу
            if (node.next != null) {
                minHeap.offer(new NodeWrapper(node.next, wrapper.listIndex));
            }
        }

        return dummy.next;
    }

    /**
     * Вспомогательная функция для создания списка из массива
     */
    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * Вспомогательная функция для вывода списка
     */
    public static void printList(ListNode head) {
        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(", ");
            head = head.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        // Создаем тестовые списки
        ListNode list1 = createList(new int[]{1, 4, 5});
        ListNode list2 = createList(new int[]{1, 3, 4});
        ListNode list3 = createList(new int[]{2, 6});

        ListNode[] lists = {list1, list2, list3};

        System.out.println("Исходные списки:");
        for (int i = 0; i < lists.length; i++) {
            System.out.print("Список " + (i + 1) + ": ");
            printList(lists[i]);
        }

        // Сливаем списки
        ListNode merged = mergeKLists(lists);

        System.out.println("\nОбъединенный список:");
        printList(merged);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("ДОКАЗАТЕЛЬСТВО СЛОЖНОСТИ:");
        System.out.println("=".repeat(60));
        System.out.println("""
            Временная сложность: O(N log K)
            где N - общее количество узлов, K - количество списков

            1. Построение кучи: O(K)
            2. Для каждого из N узлов:
               - Извлечение из кучи: O(log K)
               - Вставка в кучу: O(log K)
            3. Итого: O(K) + N * O(log K) = O(N log K)

            Пространственная сложность: O(K)
            - Куча хранит максимум K элементов

            Преимущество перед последовательным слиянием O(NK):
            - При K=100, N=10000: O(N log K) ≈ 66,000 vs O(NK) ≈ 1,000,000
            """);
    }
}
