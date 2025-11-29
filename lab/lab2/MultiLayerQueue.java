/**
 * Группа B. Задание 1.
 * Реализовать «Многослойная очередь» (очередь очередей), которая
 * поддерживает приоритетный режим обработки элементов.
 * Прокомментировать код.
 **/

import java.util.*;
public class MultiLayerQueue {

    private final List<ArrayDeque<Integer>> layers; // Список очередей
    private final int levels; // количество уровней

    public MultiLayerQueue(int levels) {
        this.levels = levels;
        layers = new ArrayList<>(levels);
        for (int i = 0; i < levels; i++) {
            layers.add(new ArrayDeque<>());
        }
    }

    public void enqueue(int value, int priority) { // Добавление элемента в очередь с указанным приоритетом
        if (priority >= levels) { // если priority больше количества уровней, ставим в самый низкий
            priority = levels - 1;
        }
        layers.get(priority).addLast(value); // добавляем в конец очереди уровня
    }

    public int dequeue() { // Извлечение элемента в приоритетном режиме, от высокого приоритета к низкому
        for (int p = 0; p < levels; p++) {
            ArrayDeque<Integer> q = layers.get(p);
            if (!q.isEmpty()) {
                return q.removeFirst();
            }
        }
        System.out.println("Все слои пусты — нечего извлечь");
        return Integer.MIN_VALUE;
    }

    private int lastRoundRobin = 0;
    public int roundRobinDequeue() { // Извлечение по кругу
        for (int i = 0; i < levels; i++) {
            int idx = (lastRoundRobin + i) % levels; // текущий уровень по кругу
            ArrayDeque<Integer> q = layers.get(idx);
            if (!q.isEmpty()) {
                lastRoundRobin = (idx + 1) % levels; // запоминаем следующий уровень
                return q.removeFirst();
            }
        }
        System.out.println("RoundRobin: все слои пусты");
        return Integer.MIN_VALUE;
    }

    public boolean isEmpty() { // Проверка: пусты ли все очереди
        for (ArrayDeque<Integer> q : layers) {
            if (!q.isEmpty()) return false;
        }
        return true;
    }

    public int totalSize() {// количество элементов во всех слоях
        int s = 0;
        for (ArrayDeque<Integer> q : layers) s += q.size();
        return s;
    }

    public void printState() {
        for (int p = 0; p < levels; p++) {
            System.out.print("Уровень " + p + " (приоритет " + (levels - p - 1) + "): ");
            ArrayDeque<Integer> q = layers.get(p);
            if (q.isEmpty()) {
                System.out.println("[пусто]");
            } else {
                System.out.println(q);
            }
        }
        System.out.println("Общий размер: " + totalSize());
    }

    public static void main(String[] args) {
        System.out.println("Демонстрация:  ");
        System.out.println();

        MultiLayerQueue mlq = new MultiLayerQueue(3); // 3 уровня приоритетов

        System.out.println("1) Добавляем элементы в разные уровни:");
        mlq.enqueue(11, 0); // высокий приоритет
        mlq.enqueue(12, 0);
        mlq.enqueue(13, 0);
        mlq.enqueue(21, 1); // средний приоритет
        mlq.enqueue(22, 1);
        mlq.enqueue(23, 1);
        mlq.enqueue(31, 2); // низкий приоритет
        mlq.enqueue(32, 2);
        mlq.enqueue(33, 2);
        mlq.printState();
        System.out.println();

        System.out.println("2) Извлекаем в приоритетном режиме:");
        System.out.println("dequeue() -> " + mlq.dequeue());
        System.out.println("dequeue() -> " + mlq.dequeue());
        System.out.println("dequeue() -> " + mlq.dequeue());
        mlq.printState();
        System.out.println();

        System.out.println("3) Добавим ещё элементов");
        mlq.enqueue(34, 2);
        mlq.enqueue(35, 2);
        mlq.printState();
        System.out.println();

        System.out.println("4) Продемонстрируем round-robin режим:");
        System.out.println("roundRobinDequeue() -> " + mlq.roundRobinDequeue());
        System.out.println("roundRobinDequeue() -> " + mlq.roundRobinDequeue());
        System.out.println("roundRobinDequeue() -> " + mlq.roundRobinDequeue());
        mlq.printState();
        System.out.println();

        System.out.println("5) Извлекаем всё приоритетно до конца:");
        while (!mlq.isEmpty()) {
            System.out.println("dequeue() -> " + mlq.dequeue());
        }
        mlq.printState();
    }
}
/**
 * Демонстрация:
 *
 * 1) Добавляем элементы в разные уровни:
 * Уровень 0 (приоритет 2): [11, 12, 13]
 * Уровень 1 (приоритет 1): [21, 22, 23]
 * Уровень 2 (приоритет 0): [31, 32, 33]
 * Общий размер: 9
 *
 * 2) Извлекаем в приоритетном режиме:
 * dequeue() -> 11
 * dequeue() -> 12
 * dequeue() -> 13
 * Уровень 0 (приоритет 2): [пусто]
 * Уровень 1 (приоритет 1): [21, 22, 23]
 * Уровень 2 (приоритет 0): [31, 32, 33]
 * Общий размер: 6
 *
 * 3) Добавим ещё элементов
 * Уровень 0 (приоритет 2): [пусто]
 * Уровень 1 (приоритет 1): [21, 22, 23]
 * Уровень 2 (приоритет 0): [31, 32, 33, 34, 35]
 * Общий размер: 8
 *
 * 4) Продемонстрируем round-robin режим:
 * roundRobinDequeue() -> 21
 * roundRobinDequeue() -> 31
 * roundRobinDequeue() -> 22
 * Уровень 0 (приоритет 2): [пусто]
 * Уровень 1 (приоритет 1): [23]
 * Уровень 2 (приоритет 0): [32, 33, 34, 35]
 * Общий размер: 5
 *
 * 5) Извлекаем всё приоритетно до конца:
 * dequeue() -> 23
 * dequeue() -> 32
 * dequeue() -> 33
 * dequeue() -> 34
 * dequeue() -> 35
 * Уровень 0 (приоритет 2): [пусто]
 * Уровень 1 (приоритет 1): [пусто]
 * Уровень 2 (приоритет 0): [пусто]
 * Общий размер: 0
 */
