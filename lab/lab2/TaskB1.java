//1 Реализовать «Многослойная очередь» (очередь очередей), которая
//поддерживает приоритетный режим обработки элементов.
//Прокомментировать код.

import models.MultiLayerQueue;

public class TaskB1 {
    public static void main(String[] args) {
        MultiLayerQueue<String> q = new MultiLayerQueue<>(2);

        q.add("low-1", 2);
        q.add("high-1", 0);
        q.add("middle-1", 1);
        q.add("high-2", 0);
        System.out.println("size: " + q.size());
        q.print();
        System.out.println();

        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println("size: " + q.size());
        System.out.println();

        q.add("A", 1);
        q.add("B", 0);
        q.add("C", 2);
        q.print();
        System.out.println("first high priority: " + q.poll());
        q.print();
    }
}