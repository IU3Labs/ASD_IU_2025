/*
Дано K связанных списков. Слейте их в один полностью
отсортированный список. Сложность O (N log K). Докажите сложность.
*/

/*
Обоснование сложности:
Цикл while в mergeKLists() имеет сложность O(log K) по времени,
т.к. interval увеличивается в два раза на каждой итерации, пока
не станет больше или равен K (lists.length).
Внутри while вызывается mergeTwoLists(), которая имеет сложность
по времени O(N), так как внутренний цикл for независимо от interval
обрабатывает все N узлов.

Итоговая сложность: O(N*log(K)), что и требовалось доказать.
*/

import java.util.Scanner;
import structures.ListNode;

public class TaskB1 {

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        };
        int amount = lists.length;
        int interval = 1;
        while (interval < amount) {
            for (int i = 0; i < amount - interval; i += interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            };
            interval *= 2;
        };

        return lists[0];
    };

    public static ListNode mergeTwoLists(ListNode firstList, ListNode secondList) {
        ListNode head = new ListNode(0);
        ListNode tail = head;

        while (firstList != null && secondList != null) {
            if (firstList.value < secondList.value) {
                tail.next = firstList;
                firstList = firstList.next;
            } else {
                tail.next = secondList;
                secondList = secondList.next;
            };
            tail = tail.next;
        };

        if (firstList != null) {
            tail.next = firstList;
        } else {
            tail.next = secondList;
        };

        return head.next;
    };

    public static void printList(ListNode head) {
        if  (head == null) {
            System.out.println("Список пуст");
            return;
        };
        ListNode current = head;
        while (current != null) {
            System.out.print(current.value + " -> ");
            current = current.next;
        };
        System.out.println("null");
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество списков: ");
        int k = in.nextInt();
        ListNode[] lists = new ListNode[k];

        for (int i = 0; i < k; i++) {
            System.out.printf("Введите количество элементов в списке %d: ", i + 1);
            int n = in.nextInt();
            System.out.printf("Введите элемент №1 списка %d: ", i + 1);
            ListNode head = new ListNode(in.nextInt());
            ListNode current = head;
            for (int j = 1; j < n; j++) {
                System.out.printf("Введите элемент №%d списка %d: ", j + 1, i + 1);
                current.next = new ListNode(in.nextInt());
                current = current.next;
            };
            lists[i] = head;
        };

        System.out.println("Результат: ");
        printList(mergeKLists(lists));
    };

};
