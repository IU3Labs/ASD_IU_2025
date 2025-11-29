package lab2.src;

import lab2.models.MultiLayerQueue;

public class TaskB1 {
    public static void main(String[] args) {
        MultiLayerQueue queue = new MultiLayerQueue();

        queue.enqueue(10, 3);
        queue.enqueue(20, 1);
        queue.enqueue(30, 2);
        queue.enqueue(40, 1);
        queue.enqueue(50, 3);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}