import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class MergeKLists {

    // Метод использует разделяй и властвуй
    // Общая сложность: O(N log K)
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // На каждом уровне мы объединяем списки попарно
        //
        // Количество уровней = log K
        //
        // На каждом уровне происходит слияние всех узлов (N)
        //
        // N * log K = O(N log K)

        int interval = 1;

        while (interval < lists.length) {
            // Сливаем каждую пару списков:
            for (int i = 0; i + interval < lists.length; i += interval * 2) {

                // merge двух отсортированных списков O(a + b)
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }

            interval *= 2; // следующий уровень (делим K на 2)
        }

        return lists[0];
    }


    // Сложность: O(a + b)
    // А суммарно по всем уровням merge даёт O(N * log K)
    private static ListNode mergeTwoLists(ListNode a, ListNode b) {

        ListNode dummy = new ListNode(0);
        ListNode t = dummy;

        // Пока есть элементы в обоих - выбираем минимум
        while (a != null && b != null) {
            // 1 сравнение, 1 присваивание
            if (a.val <= b.val) {
                t.next = a;   // O(1)
                a = a.next;   // O(1)
            } else {
                t.next = b;   // O(1)
                b = b.next;   // O(1)
            }
            t = t.next;       // O(1)
        }

        // Добавляем остаток - O(length)
        if (a != null) t.next = a;
        else t.next = b;

        return dummy.next;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите K: ");
        int K = sc.nextInt();

        ListNode[] lists = new ListNode[K];

        for (int i = 0; i < K; i++) {
            System.out.print("Введите длину списка #" + (i + 1) + ": ");
            int n = sc.nextInt();

            System.out.println("Введите " + n + " отсортированных чисел:");
            ListNode dummy = new ListNode(0);
            ListNode t = dummy;

            for (int j = 0; j < n; j++) {
                t.next = new ListNode(sc.nextInt());
                t = t.next;
            }

            lists[i] = dummy.next;
        }

        ListNode res = mergeKLists(lists);

        System.out.println("Результирующий отсортированный список:");
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
