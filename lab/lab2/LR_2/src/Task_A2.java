//2 Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
//логику.

import Models.CircularQueue;

public class Task_A2 {
    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(3);

        q.add(10);
        q.add(20);
        q.add(30);
        System.out.println("size: " + q.size());

        int removed = q.remove();
        System.out.println("removed element: " + removed);
        System.out.println("size: " + q.size());

        q.add(40);
        System.out.println("size: " + q.size());
    }
}