

package lab3;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class KSortedListsMerger {

    private int heapInsertOperations = 0;
    private int heapExtractOperations = 0;
    private int totalSteps = 0;

    public static void main(String[] args) {
        KSortedListsMerger merger = new KSortedListsMerger();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Слияние K отсортированных связанных списков");

        System.out.print("Введите количество отсортированных списков (K): ");
        int k = scanner.nextInt();

        ListNode[] lists = new ListNode[k];

        for (int i = 0; i < k; i++) {
            System.out.println("\nСписок " + (i + 1) + " ---");
            lists[i] = merger.inputListFromUser(scanner, i + 1);
        }

        System.out.println("\nИсходные данные");
        int totalElements = merger.countTotalElements(lists);
        System.out.println("K = " + k + " списков");
        System.out.println("N = " + totalElements + " элементов всего");

        for (int i = 0; i < k; i++) {
            System.out.print("Список " + (i + 1) + " (" + merger.countListElements(lists[i]) + " элементов): ");
            merger.printList(lists[i]);
        }

        System.out.println("\nПроцесс слияния с анализом сложности");
        ListNode mergedList = merger.mergeKSortedListsWithProof(lists);

        System.out.println("\nРезультат");
        System.out.print("Объединенный список: ");
        merger.printList(mergedList);
        merger.printComplexityProof(k, totalElements);

        scanner.close();
    }

    public ListNode mergeKSortedListsWithProof(ListNode[] lists) {
        heapInsertOperations = 0;
        heapExtractOperations = 0;
        totalSteps = 0;
        if (lists == null || lists.length == 0) {
            return null;
        }

        int k = lists.length;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
                k,
                Comparator.comparingInt(node -> node.value)
        );

        System.out.println("\nШАГ 1: Инициализация кучи");
        System.out.println("Добавляем первые элементы из каждого списка в кучу размера K=" + k);

        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
                heapInsertOperations++;
                System.out.println("  Добавлен элемент: " + list.value +
                        " (операций вставки: " + heapInsertOperations + ")");
            }
        }

        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        System.out.println("\nШАГ 2: Основной процесс слияния");
        System.out.println("Будет обработано N=" + countTotalElements(lists) + " элементов");

        while (!minHeap.isEmpty()) {
            totalSteps++;

            ListNode minNode = minHeap.poll();
            heapExtractOperations++;

            System.out.println("\nШаг " + totalSteps + ":");
            System.out.println("  Извлечен минимальный элемент: " + minNode.value +
                    " (операций извлечения: " + heapExtractOperations + ")");
            System.out.println("  Размер кучи после извлечения: " + minHeap.size());

            current.next = minNode;
            current = current.next;

            if (minNode.next != null) {
                minHeap.offer(minNode.next);
                heapInsertOperations++;
                System.out.println("  Добавлен следующий элемент: " + minNode.next.value +
                        " (операций вставки: " + heapInsertOperations + ")");
                System.out.println("  Размер кучи после добавления: " + minHeap.size());
            }
        }

        return dummyHead.next;
    }

    private void printComplexityProof(int k, int n) {
        System.out.println("\n=== МАТЕМАТИЧЕСКОЕ ДОКАЗАТЕЛЬСТВО СЛОЖНОСТИ O(N log K) ===");

        int totalHeapOperations = heapInsertOperations + heapExtractOperations;
        double logK = Math.log(k) / Math.log(2); // log₂K

        System.out.println("\n1. ФАКТИЧЕСКИЕ ДАННЫЕ:");
        System.out.println("   K = " + k + " списков");
        System.out.println("   N = " + n + " элементов");
        System.out.println("   Всего операций с кучей: " + totalHeapOperations +
                " (вставок: " + heapInsertOperations +
                ", извлечений: " + heapExtractOperations + ")");

        System.out.println("\n2. ТЕОРЕТИЧЕСКИЙ РАСЧЕТ:");
        System.out.println("   Шаг 1: Инициализация кучи");
        System.out.println("   - K операций вставки × O(log K) = O(K log K)");
        System.out.println("   - K = " + k + ", log₂K = " + String.format("%.2f", logK));
        System.out.println("   - Теоретически: " + k + " × " + String.format("%.2f", logK) + " ≈ " +
                String.format("%.2f", k * logK) + " операций");

        System.out.println("\n   Шаг 2: Основной процесс слияния");
        System.out.println("   - N операций извлечения × O(log K) = O(N log K)");
        System.out.println("   - N операций вставки × O(log K) = O(N log K)");
        System.out.println("   - N = " + n + ", log₂K = " + String.format("%.2f", logK));
        System.out.println("   - Теоретически: " + n + " × " + String.format("%.2f", logK) + " ≈ " +
                String.format("%.2f", n * logK) + " операций");

        System.out.println("\n3. ОБЩАЯ СЛОЖНОСТЬ:");
        System.out.println("   O(K log K) + O(N log K) = O((K + N) log K)");
        System.out.println("   Поскольку N ≥ K, упрощаем: O(N log K)");

        System.out.println("\n4. ВАЛИДАЦИЯ РЕЗУЛЬТАТА:");
        double theoreticalOperations = n * logK;
        System.out.println("   Теоретическая оценка: " + String.format("%.2f", theoreticalOperations) + " операций");
        System.out.println("   Фактическое количество: " + totalHeapOperations + " операций");
        System.out.println("   Отношение: " + String.format("%.2f", (double)totalHeapOperations / theoreticalOperations));

    }
    private ListNode inputListFromUser(Scanner scanner, int listNumber) {
        System.out.print("Введите количество элементов в списке " + listNumber + ": ");
        int size = scanner.nextInt();

        if (size == 0) return null;

        System.out.println("Введите " + size + " отсортированных элементов:");
        ListNode head = null;
        ListNode tail = null;

        for (int i = 0; i < size; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            int value = scanner.nextInt();

            ListNode newNode = new ListNode(value);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        return head;
    }

    private int countTotalElements(ListNode[] lists) {
        int total = 0;
        for (ListNode list : lists) {
            total += countListElements(list);
        }
        return total;
    }

    private int countListElements(ListNode head) {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    private void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }
}