
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



    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Сбор всех значений
        List<Integer> allValues = new ArrayList<>();
        for (ListNode list : lists) {           // O(K) итераций по спискам
            while (list != null) {              // O(n_i) итераций для i-го списка
                allValues.add(list.val);        // O(1) - амортизированная сложность
                list = list.next;               // O(1)
            }
        }
        // O(N)

        // Сортировка всех значений
        Collections.sort(allValues);            // O(N log N)

        // Создание нового отсортированного списка
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : allValues) {             // O(N) итераций
            current.next = new ListNode(val);   // O(1)
            current = current.next;             // O(1)
        }
        // O(N)

        return dummy.next;
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
