//Дан целочисленный массив nums и целое число k, верните k наиболее
//        часто встречающихся элементов. Вернуть ответ в любом порядке.
//        Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.

import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class MergeKLists {


    public static ListNode mergeKLists(ListNode[] sourceLists) {
        if (sourceLists == null || sourceLists.length == 0) return null;

        // Сбор всех значений
        List<Integer> valueContainer = new ArrayList<>();
        for (ListNode node : sourceLists) {     // O(K) итераций по спискам
            while (node != null) {              // O(n_i) итераций для i-го списка
                valueContainer.add(node.val);   // O(1) - амортизированная сложность
                node = node.next;               // O(1)
            }
        }
        // O(N)

        // Сортировка всех значений
        Collections.sort(valueContainer);            // O(N log N)

        // Создание нового отсортированного списка
        ListNode headPlaceholder = new ListNode(0);
        ListNode iterator = headPlaceholder;
        for (int value : valueContainer) {      // O(N) итераций
            iterator.next = new ListNode(value);// O(1)
            iterator = iterator.next;           // O(1)
        }
        // O(N)

        return headPlaceholder.next;
    }


    // Сложность: O(a + b)
    // А суммарно по всем уровням merge даёт O(N * log K)
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode tempHead = new ListNode(0);
        ListNode curr = tempHead;

        // Пока есть элементы в обоих - выбираем минимум
        while (l1 != null && l2 != null) {
            // 1 сравнение, 1 присваивание
            if (l1.val <= l2.val) {
                curr.next = l1;   // O(1)
                l1 = l1.next;     // O(1)
            } else {
                curr.next = l2;   // O(1)
                l2 = l2.next;     // O(1)
            }
            curr = curr.next;     // O(1)
        }

        // Добавляем остаток - O(length)
        if (l1 != null) curr.next = l1;
        else curr.next = l2;

        return tempHead.next;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите K: ");
        int listCount = scanner.nextInt();

        ListNode[] linkedLists = new ListNode[listCount];

        for (int i = 0; i < listCount; i++) {
            System.out.print("Введите длину списка #" + (i + 1) + ": ");
            int len = scanner.nextInt();

            System.out.println("Введите " + len + " отсортированных чисел:");
            ListNode dummyNode = new ListNode(0);
            ListNode tail = dummyNode;

            for (int j = 0; j < len; j++) {
                tail.next = new ListNode(scanner.nextInt());
                tail = tail.next;
            }

            linkedLists[i] = dummyNode.next;
        }

        ListNode resultNode = mergeKLists(linkedLists);

        System.out.println("Результирующий отсортированный список:");
        while (resultNode != null) {
            System.out.print(resultNode.val + " ");
            resultNode = resultNode.next;
        }
    }
}