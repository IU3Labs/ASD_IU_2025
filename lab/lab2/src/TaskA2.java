package lab2.src;

import lab2.models.CircularQueue;

public class TaskA2 {
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(3);

        System.out.println(queue.enQueue(1));
        System.out.println(queue.enQueue(2));
        System.out.println(queue.enQueue(3));
        System.out.println(queue.enQueue(4));

        System.out.println(queue.Rear());
        System.out.println(queue.isFull());

        System.out.println(queue.deQueue());
        System.out.println(queue.enQueue(4));

        System.out.println(queue.Rear());
        System.out.println(queue.Front());
    }
}