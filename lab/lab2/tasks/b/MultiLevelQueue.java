package org.example.lab2.tasks.b;

import java.util.*;

/**
 * Многослойная очередь (очередь очередей по приоритетам).
 */
public class MultiLevelQueue<E> {
    private final List<Deque<E>> levels;

    public MultiLevelQueue(int n) {
        levels = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            levels.add(new ArrayDeque<>());
        }
    }

    public void enqueue(E e, int level) {
        levels.get(level).addLast(e);
    }

    public E dequeue() {
        for (Deque<E> q : levels) {
            if (!q.isEmpty()) {
                return q.removeFirst();
            }
        }
        return null;
    }

    public int size() {
        return levels.stream().mapToInt(Deque::size).sum();
    }

    public String printForward() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < levels.size(); i++) {
            sb.append("L").append(i).append(": ").append(levels.get(i)).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MultiLevelQueue<String> mq = new MultiLevelQueue<>(3);
        mq.enqueue("A0", 0);
        mq.enqueue("B1", 1);
        mq.enqueue("C2", 2);
        mq.enqueue("A0-2", 0);
        System.out.println("Очереди:\n" + mq.printForward());
        System.out.println("dequeue -> " + mq.dequeue());
        System.out.println("После удаления:\n" + mq.printForward());
    }
}