import java.util.*;
//1 Реализовать «Многослойная очередь» (очередь очередей), которая
//поддерживает приоритетный режим обработки элементов.
//Прокомментировать код.
//1. PriorityQueue<Queue<Integer>>: Приоритетная очередь, где хранятся другие очереди. Очереди сортируются по их длине, т.е. очереди с меньшим количеством элементов будут извлекаться первыми.
//
//
//2. Метод addQueue добавляет новую очередь в многослойную очередь.
//
//
//3. Метод getQueue извлекает очередь с наименьшим размером.
public class multilayerqueue {
    private PriorityQueue<Queue<Integer>> multiLayerQueue;

    public multilayerqueue() {
        multiLayerQueue = new PriorityQueue<>(Comparator.comparingInt(Queue::size));
    }
    public void addQueue(Queue<Integer> queue) {
        multiLayerQueue.add(queue);
    }

    public Queue<Integer> getQueue() {
        return multiLayerQueue.poll();
    }

    public static void main(String[] args) {
        multilayerqueue multiLayerQueue = new multilayerqueue();

        Queue<Integer> queue1 = new LinkedList<>(Arrays.asList(1, 2, 3));
        Queue<Integer> queue2 = new LinkedList<>(Arrays.asList(10, 20, 30, 40));
        Queue<Integer> queue3 = new LinkedList<>(Arrays.asList(100, 200));

        multiLayerQueue.addQueue(queue1);
        multiLayerQueue.addQueue(queue2);
        multiLayerQueue.addQueue(queue3);

        System.out.println("Queue with smallest size: " + multiLayerQueue.getQueue());  // Очередь queue1
    }
}
