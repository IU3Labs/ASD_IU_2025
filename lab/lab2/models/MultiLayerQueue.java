package lab2.models;

import java.util.LinkedList;
import java.util.Queue;

public class MultiLayerQueue {
    private Queue<Integer> highPriority;   // очередь высшего приоритета
    private Queue<Integer> mediumPriority; // очередь среднего приоритета
    private Queue<Integer> lowPriority;    // очередь низшего приоритета

    public MultiLayerQueue() {
        highPriority = new LinkedList<>();
        mediumPriority = new LinkedList<>();
        lowPriority = new LinkedList<>();
    }

    public void enqueue(int value, int priority) {
        // добавляем элемент в очередь соответствующего приоритета
        if (priority == 1) {
            highPriority.add(value);
        } else if (priority == 2) {
            mediumPriority.add(value);
        } else {
            lowPriority.add(value);
        }
    }

    public Integer dequeue() {
        // сначала обрабатываем высший приоритет, потом средний, потом низший
        if (!highPriority.isEmpty()) {
            return highPriority.poll();
        }
        if (!mediumPriority.isEmpty()) {
            return mediumPriority.poll();
        }
        if (!lowPriority.isEmpty()) {
            return lowPriority.poll();
        }
        return null; // все очереди пусты
    }
}