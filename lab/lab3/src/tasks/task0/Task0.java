/**
 * Дано K связанных списков.
 * Слейте их в один полностью отсортированный список.
 * Сложность O (N log K).
 * Докажите сложность.
 * -----------------
 * Доказательство:
 * K - количество связных списков
 * N - общее количество элементов во всех списках
 * ---------
 * 1 Этап - инициализация приоритетной очереди
 * Количество операций: K (в худшем случае)
 * Сложность каждой операции offer(): O(log K), где K - текущий размер очереди
 * Итоговая сложность этапа 1 = O(K log K)
 * ---------
 * Этап 2 - Основной цикл слияния
 * Количество итераций: N (извлекаем все N элементов)
 * poll(): O(log K) - извлечение минимального элемента из очереди размера ≤ K
 * offer(): O(log K) - вставка следующего элемента (выполняется для N-K элементов)
 * Прочие операции: O(1)
 * N операций poll(): O(N log K)
 * В среднем N операций offer(): O(N log K)
 * Итоговая сложность этапа 2 = O(N log K)
 * ---------
 * Поскольку N ≥ K финальная сложность - O(N log K)
 */

package tasks.task0;
import java.util.*;

public class Task0 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Основной метод слияния K отсортированных списков
    public ListNode mergeLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Приоритетная очередь для хранения узлов
        PriorityQueue<ListNode> pQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        // Добавляем первые узлы всех списков в очередь
        for (ListNode list : lists) {
            if (list != null) {
                pQueue.offer(list);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!pQueue.isEmpty()) {
            ListNode minNode = pQueue.poll(); // Извлекаем минимальный узел
            current.next = minNode; // Заносим значение в результирующий список
            current = current.next; // Перемещаем указатель

            // Если у извлеченного узла есть следующий, добавляем его в очередь
            if (minNode.next != null) {
                pQueue.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    // Метод для сортировки связного списка
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // Собираем все значения в список
        ArrayList<Integer> values = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        // Сортируем значения
        Collections.sort(values);

        // Создаем новый отсортированный список
        return createList(values);
    }

    // Вспомогательный метод для создания списка из ArrayList
    public static ListNode createList(ArrayList<Integer> values) {
        if (values == null || values.isEmpty()) return null;
        ListNode head = new ListNode(values.getFirst());
        ListNode current = head;
        for (int i = 1; i < values.size(); i++) {
            current.next = new ListNode(values.get(i));
            current = current.next;
        }
        return head;
    }


    // Вспомогательный метод для печати списка
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    // Метод для ввода списка с клавиатуры
    public static ListNode inputListFromKeyboard(Scanner scanner, int listNumber) {
        System.out.println("Введите элементы списка " + listNumber + " через пробел (или Enter для пустого списка):");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return null;
        }

        String[] valuesStr = input.split(" ");
        ArrayList<Integer> values = new ArrayList<>();

        for (String valueStr : valuesStr) {
            try {
                values.add(Integer.parseInt(valueStr));
            } catch (NumberFormatException e) {
                System.out.println("Пропущен некорректный элемент: " + valueStr);
            }
        }

        return createList(values);
    }

    public static void main() {
        Task0 task0 = new Task0();
        Scanner scanner = new Scanner(System.in);

        // Ввод количества списков
        System.out.print("Введите количество связных списков: ");
        int k = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (k <= 0) {
            System.out.println("Количество списков должно быть положительным числом!");
            return;
        }

        ListNode[] lists = new ListNode[k];

        // Ввод и сортировка каждого списка
        for (int i = 0; i < k; i++) {
            ListNode list = inputListFromKeyboard(scanner, i + 1);
            if (list != null) {
                System.out.print("Исходный список " + (i + 1) + ": ");
                printList(list);

                // Сортируем список
                lists[i] = task0.sortList(list);

                System.out.print("Отсортированный список " + (i + 1) + ": ");
                printList(lists[i]);
            } else {
                lists[i] = null;
                System.out.println("Список " + (i + 1) + ": пустой");
            }
            System.out.println();
        }

        // Объединяем отсортированные списки
        ListNode merged = task0.mergeLists(lists);

        System.out.println("Финальный объединенный отсортированный список:");
        printList(merged);

        scanner.close();
    }
}