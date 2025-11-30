package models;

import java.util.*;

public class MultiLayerQueue<T> {
    private final Map<Integer, Queue<T>> priorityQueues; // основная очередь
    private final int maxPriority;

    public MultiLayerQueue(int maxPriority) {
        this.maxPriority = maxPriority;
        this.priorityQueues = new LinkedHashMap<>();

        for (int p = 0; p <= maxPriority; p++) { // пустые очереди
            priorityQueues.put(p, new LinkedList<>());
        }
    }

    public void add(T item, int priority) { // добавление элемента
        Queue<T> q = priorityQueues.get(priority);
        q.offer(item);
    }

    public T poll() { // получаем элемент у которого самый высокий приоритет
        for (int p = 0; p <= maxPriority; p++) {
            Queue<T> q = priorityQueues.get(p);
            if (!q.isEmpty()) {
                return q.poll();
            }
        }
        return null; // если все очереди пустые
    }

    public int size() { // общее количество элементов
        int total = 0;
        for (Queue<T> q : priorityQueues.values()) {
            total += q.size();
        }
        return total;
    }

    public void print() { // вывести очереди
        for (int p = 0; p <= maxPriority; p++) {
            Queue<T> q = priorityQueues.get(p);
            if (!q.isEmpty()) {
                System.out.println("priority " + p + ": " + q);
            }
        }
    }
}
